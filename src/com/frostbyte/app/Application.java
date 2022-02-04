package com.frostbyte.app;

public class Application {
    private UserInterface gui;

    public Application(UserInterface gui) {
        this.gui = gui;
    }

    public void Run() {
        this.gui.Init();
    }
}
