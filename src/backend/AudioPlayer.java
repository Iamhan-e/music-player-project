package backend;

import javazoom.jl.player.Player;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class AudioPlayer extends PlaybackListener {
    private AdvancedPlayer advancedPlayer;
    private boolean isPaused;
    public long getPlaybackPosition() {
        long mediaPlayer = 0;
        return mediaPlayer != 0 ? mediaPlayer / 1000 : 0;
    }

    public void playSong(File file) {

        try {
            isMp3FileValid(file);
            stopSong();

            // Initialize the player and playback thread

            isPaused = false;
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            advancedPlayer = new AdvancedPlayer(bis);
            advancedPlayer.setPlayBackListener(this);

            startMusicThread();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startMusicThread(){
            new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        advancedPlayer.play();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

//

    public void pauseSong(){
        if(advancedPlayer!= null){
            isPaused= true;
            stopSong();
        }
    }

    public void stopSong() {
        if (advancedPlayer != null) {
            advancedPlayer.stop();
            advancedPlayer.close();
            advancedPlayer= null;
        }

    }

    public boolean isPlaying() {
        return false;
    }

    public void setPlaybackPosition(int newPosition) {
    }

    public boolean isMp3FileValid(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            AdvancedPlayer testPlayer = new AdvancedPlayer(bis);
            return true; // If no exception occurs, the file is valid
        } catch (Exception e) {
            System.err.println("Invalid MP3 file: " + file.getName());
            e.printStackTrace();
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
    }
}
