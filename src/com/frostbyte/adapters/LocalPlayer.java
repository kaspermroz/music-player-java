package com.frostbyte.adapters;

import com.frostbyte.app.MusicPlayer;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LocalPlayer implements MusicPlayer {
    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    File myFile = null;
    long totalLength, pauseLength;
    Player player;

    public LocalPlayer() {
        pauseLength = 0;
        totalLength = 0;
    }

    @Override
    public void PlaySong(File song) {
        Runnable runnablePlay = () -> {
            try {
                fileInputStream = new FileInputStream(song);
                if (pauseLength > 0) {
                    fileInputStream.skip(totalLength - pauseLength);
                }
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                player = new Player(bufferedInputStream);
                totalLength = fileInputStream.available();

                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread playThread = new Thread(runnablePlay);
        playThread.start();
    }

    @Override
    public void Pause() {
        try {
            pauseLength = fileInputStream.available();
            this.player.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void Stop() {
       this.totalLength = 0;
       this.pauseLength = 0;
       if (this.player != null) {
           this.player.close();
       }
    }
}
