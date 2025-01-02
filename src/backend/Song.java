package backend;

import java.io.File;

public class Song {
    private String title;
    private String artist;
    private String genre;
    private File file;
    private  File albumCover; // Path to the album cover image


    public Song(String title, String artist, String genre, File file, File albumCover) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.file = file;
        this.albumCover = albumCover;
    }

    public Song(String title, String artist, String genre, File albumCover) {
        this.albumCover = albumCover;
    }

    public File getAlbumCover() {
        return albumCover;
    }


    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public File getFile() {
        return file;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getDuration() {
        return 0;
    }

    public double getRating() {
        return 0;
    }

    public void setAlbumCover(File file) {
    }
}
