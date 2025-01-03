package backend;

import gui.MusicLibraryGUI;
import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class AudioPlayer extends PlaybackListener {
    private AdvancedPlayer advancedPlayer;
    private Thread playbackThread;
    private boolean isPaused = false;
    private boolean isPlaying = false;
    private int currentFrame = 0; // Tracks the current playback frame
    private double frameRatePerMilliseconds = 0.026; // Default value for MP3s
    private File currentFile; // The currently playing file
    private long playbackStartTime = 0; // Tracks when playback started (in ms)

    private MusicLibraryGUI musicLibraryGUI;

    public AudioPlayer(MusicLibraryGUI musicLibraryGUI) {
        this.musicLibraryGUI = musicLibraryGUI;
    }

    public double getFrameRatePerMilliseconds() {
        return frameRatePerMilliseconds;
    }

    public long getPlaybackPosition() {
        long mediaPlayer = 0;
        return mediaPlayer != 0 ? mediaPlayer / 1000 : 0;
    }
    public void playSong(File file) {
        try {
            // Validate MP3 file
            if (!isMp3FileValid(file)) {
                System.err.println("Invalid MP3 file. Cannot play: " + file.getName());
                return;
            }

            // If it's a new file, reset playback state
            if (!file.equals(currentFile)) {
                currentFile = file;
                currentFrame = 0; // Reset frame for new file
                isPaused = false;
            }

            // Stop any existing playback
            stopSong();

            // Load MP3 metadata
            Mp3File mp3File = new Mp3File(file);
            frameRatePerMilliseconds = (double) mp3File.getFrameCount() / mp3File.getLengthInMilliseconds();

            // Initialize player
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            advancedPlayer = new AdvancedPlayer(bis);
            advancedPlayer.setPlayBackListener(this);

            // Start playback thread
            playbackThread = new Thread(() -> {
                try {
                    isPlaying = true;
                    if (isPaused) {
                        advancedPlayer.play(currentFrame, Integer.MAX_VALUE); // Resume from current frame
                    } else {
                        advancedPlayer.play(); // Play from the beginning
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    isPlaying = false;
                }
            });
            playbackStartTime = System.currentTimeMillis(); // Mark playback start time
            playbackThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseSong() {
        if (advancedPlayer != null && isPlaying) {
            isPaused = true;
            isPlaying = false;

            // Calculate the current frame based on elapsed time
            long elapsedTime = System.currentTimeMillis() - playbackStartTime; // Time since playback started
            currentFrame += (int) (elapsedTime * frameRatePerMilliseconds); // Update current frame

            stopSong(); // Stop playback
        }
    }

    public void stopSong() {
        try {
            if (advancedPlayer != null) {
                advancedPlayer.close(); // Close the player
            }

            if (playbackThread != null && playbackThread.isAlive()) {
                playbackThread.interrupt(); // Stop the playback thread
            }

            isPlaying = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isMp3FileValid(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            AdvancedPlayer testPlayer = new AdvancedPlayer(bis);
            return true; // If no exception occurs, the file is valid
        } catch (Exception e) {
            System.err.println("Invalid MP3 file: " + file.getName());
            return false;
        }
    }

    @Override
    public void playbackStarted(PlaybackEvent evt) {
        System.out.println("Playback started");
    }

    @Override
    public void playbackFinished(PlaybackEvent evt) {
        System.out.println("Playback finished");
        if (!isPaused) {
            currentFrame = 0; // Reset frame if playback finishes naturally
        }
        isPlaying = false; // Mark as not playing
    }
}
