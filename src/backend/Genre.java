package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Genre {
    private String name;
    private String description;
    private  List<Song> songs;
    private  List<Artist> artists;
    private  List<Album> albums;
    private int songCount;
    private double averageRating;

    // Constructor
    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
        this.songs = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.songCount = 0;
        this.averageRating = 0.0;
    }

    public Genre() {

    }

    // Add song to genre
    public void addSong(Song song) {
        songs.add(song);
        songCount++;
        updateAverageRating(song.getRating());
    }

    // Add artist to genre
    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    // Add album to genre
    public void addAlbum(Album album) {
        albums.add(album);
    }

    // Update the average rating of the genre
    private void updateAverageRating(double rating) {
        double totalRating = averageRating * (songCount - 1);
        totalRating += rating;
        averageRating = totalRating / songCount;
    }

    // Save genre to file
    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Genre:" + name + ";" + description);
            writer.newLine();

            for (Song song : songs) {
                writer.write("Song:" + song.getTitle() + ";" + song.getArtist() + ";" + song.getGenre());
                writer.newLine();
            }

            for (Artist artist : artists) {
                writer.write("Artist:" + artist.getName());
                writer.newLine();
            }

            for (Album album : albums) {
                writer.write("Album:" + album.getTitle());
                writer.newLine();
            }
            writer.write("---");
            writer.newLine();
        }
    }

    // Load genres from file
    public static List<Genre> loadFromFile(String filePath) throws IOException {
        List<Genre> genres = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Genre currentGenre = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Genre:")) {
                    String[] parts = line.split(";", 2);
                    currentGenre = new Genre(parts[0].replace("Genre:", "").trim(), parts[1].trim());
                    genres.add(currentGenre);
                } else if (line.startsWith("Song:") && currentGenre != null) {
                    String[] parts = line.split(";", 3);
                    String title = parts[0].replace("Song:", "").trim();
                    String artist = parts[1].trim();
                    String genreName = parts[2].trim();
                    currentGenre.addSong(new Song(title, artist, genreName, null, null));
                } else if (line.startsWith("Artist:") && currentGenre != null) {
                    String artistName = line.replace("Artist:", "").trim();
                    currentGenre.addArtist(new Artist(artistName));
                } else if (line.startsWith("Album:") && currentGenre != null) {
                    String albumTitle = line.replace("Album:", "").trim();
                    currentGenre.addAlbum(new Album(albumTitle));
                }
            }
        }
        return genres;
    }

    // Get genre details
    public String getGenreDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Genre: ").append(name).append("\n");
        details.append("Description: ").append(description).append("\n");
        details.append("Artists: ");
        for (Artist artist : artists) {
            details.append(artist.getName()).append(" ");
        }
        details.append("\nAlbums: ");
        for (Album album : albums) {
            details.append(album.getTitle()).append(" ");
        }
        details.append("\nSongs: ");
        for (Song song : songs) {
            details.append(song.getTitle()).append(" ");
        }
        details.append("\nAverage Rating: ").append(averageRating).append("\n");
        return details.toString();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSongCount() {
        return songCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<Song> getSongs() {
        return List.copyOf(songs);
    }

    public List<Artist> getArtists() {
        return List.copyOf(artists);
    }

    public List<Album> getAlbums() {
        return List.copyOf(albums);
    }
}
