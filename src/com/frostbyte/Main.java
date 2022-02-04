package com.frostbyte;

import com.frostbyte.adapters.LocalPlayer;
import com.frostbyte.app.Application;
import com.frostbyte.app.MusicPlayer;
import com.frostbyte.ports.GUI;

public class Main {

    public static void main(String[] args) {
        // build application components
        MusicPlayer player = new LocalPlayer();
        GUI gui = new GUI(player);
        Application app = new Application(gui);
        // run UI
        app.Run();
    }
}
