package backend;

import javazoom.jl.player.Player;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class AudioPlayer {
    private AdvancedPlayer advancedPlayer;
    public long getPlaybackPosition() {
        long mediaPlayer = 0;
        return mediaPlayer != 0 ? mediaPlayer / 1000 : 0;
    }

    public void playSong(File file) {

        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            advancedPlayer = new AdvancedPlayer(bis);
            new Thread(() -> {
                try {
                    advancedPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//

    public void stopSong() {
        if (advancedPlayer != null) {
            advancedPlayer.close();
        }

    }

    public boolean isPlaying() {
        return false;
    }

    public void setPlaybackPosition(int newPosition) {
    }
}
