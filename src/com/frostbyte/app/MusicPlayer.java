package com.frostbyte.app;

import java.io.File;

public interface MusicPlayer {
    void PlaySong(File song);
    void Pause();
    void Stop();
}
