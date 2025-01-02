package backend;

import backend.Song;
import backend.Artist;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MusicLibraryManager {
    private final List<Song> favoriteSongs = new ArrayList<>();
    private final List<Song> library;  // The music library containing all the songs
    private final LinkedList<Song> recentPlays = new LinkedList<>();
    private final Map<String, List<Song>> playlists = new HashMap<>();
    private final Map<String, Artist> artists = new HashMap<>(); // Map to store artists by name

    // Constructor
    public MusicLibraryManager() {
        this.library = new ArrayList<>();
    }

    // --- Song management ---
    public void addSong(Song song) {
        // Add the song to the library
        library.add(song);

        // Ensure the artist is added to the artist map and the file if not present
        if (!artists.containsKey(song.getArtist())) {
            addArtistToFile(song.getArtist());
            artists.put(song.getArtist(), new Artist(song.getArtist()));
        }
    }

    private void addArtistToFile(String artistName) {
        // Append the new artist to the Artist.txt file if it's not already present
        File artistFile = new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\Artist.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(artistFile))) {
            String line;
            Set<String> existingArtists = new HashSet<>();
            while ((line = reader.readLine()) != null) {
                existingArtists.add(line.trim());
            }

            // If the artist doesn't exist in the file, append it
            if (!existingArtists.contains(artistName)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(artistFile, true))) {
                    writer.newLine();
                    writer.write(artistName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean removeSongByTitle(String title) {
        return library.removeIf(song -> song.getTitle().equalsIgnoreCase(title));
    }

    public boolean updateSong(String title, String newTitle, String newArtist, String newGenre, File newAlbumCover) {
        for (Song song : library) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                song.setTitle(newTitle != null ? newTitle : song.getTitle());
                song.setArtist(newArtist != null ? newArtist : song.getArtist());
                song.setGenre(newGenre != null ? newGenre : song.getGenre());
                song.setAlbumCover(newAlbumCover != null ? newAlbumCover : song.getAlbumCover());
                return true;
            }
        }
        return false;
    }

    // --- Artist management ---
    public void addArtist(Artist artist) {
        if (!artists.containsKey(artist.getName())) {
            artists.put(artist.getName(), artist);
        }
    }

    public Artist getArtistByName(String name) {
        return artists.getOrDefault(name, null);
    }

    public List<Artist> getAllArtists() {
        return new ArrayList<>(artists.values());
    }

    public boolean removeArtist(String name) {
        if (artists.containsKey(name)) {
            artists.remove(name);
            return true;
        }
        return false;
    }

    // --- Favorite songs ---
    public void addToFavorites(Song song) {
        if (!favoriteSongs.contains(song)) {
            favoriteSongs.add(song);
        }
    }

    public void removeFromFavorites(Song song) {
        favoriteSongs.remove(song);
    }

    public List<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    // --- Recent plays ---
    public void addToRecentPlays(Song song) {
        recentPlays.remove(song);
        recentPlays.addFirst(song);
        if (recentPlays.size() > 10) {
            recentPlays.removeLast();
        }
    }

    public List<Song> getRecentPlays() {
        return new ArrayList<>(recentPlays);
    }

    // --- Playlists ---
    public void createPlaylist(String name) {
        if (!playlists.containsKey(name)) {
            playlists.put(name, new ArrayList<>());
        }
    }

    public void addSongToPlaylist(String playlistName, Song song) {
        playlists.getOrDefault(playlistName, new ArrayList<>()).add(song);
    }

    public List<Song> getPlaylist(String playlistName) {
        return playlists.getOrDefault(playlistName, new ArrayList<>());
    }

    public Set<String> getPlaylistNames() {
        return playlists.keySet();
    }

    // --- Search and sorting ---
    public List<Song> searchByGenre(String genre) {
        return library.stream()
                .filter(song -> song.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Song> searchByArtist(String artist) {
        return library.stream()
                .filter(song -> song.getArtist().equalsIgnoreCase(artist))
                .collect(Collectors.toList());
    }

    public List<Song> searchByTitle(String title) {
        return library.stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Song> searchByCriteria(String title, String artist, String genre) {
        return library.stream()
                .filter(song -> (title == null || song.getTitle().equalsIgnoreCase(title)) &&
                        (artist == null || song.getArtist().equalsIgnoreCase(artist)) &&
                        (genre == null || song.getGenre().equalsIgnoreCase(genre)))
                .collect(Collectors.toList());
    }

    public void sortSongsByTitle() {
        library.sort(Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    public void sortSongsByArtist() {
        library.sort(Comparator.comparing(Song::getArtist, String.CASE_INSENSITIVE_ORDER));
    }

    public void sortSongsByGenre() {
        library.sort(Comparator.comparing(Song::getGenre, String.CASE_INSENSITIVE_ORDER));
    }

    public Map<String, Long> getSongCountByGenre() {
        return library.stream()
                .collect(Collectors.groupingBy(Song::getGenre, Collectors.counting()));
    }

    public Map<String, Long> getSongCountByArtist() {
        return library.stream()
                .collect(Collectors.groupingBy(Song::getArtist, Collectors.counting()));
    }

    public List<Song> getTopSongsByArtist(String artist, int limit) {
        return library.stream()
                .filter(song -> song.getArtist().equalsIgnoreCase(artist))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Song getSongByTitle(String selectedSongTitle) {
        return library.stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(selectedSongTitle))
                .findFirst()
                .orElse(null);
    }

    public List<Song> getLibrary() {
        return library;
    }

    public Set<String> getArtistNames() {
        return artists.keySet();
    }
}
