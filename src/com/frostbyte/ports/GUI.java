package com.frostbyte.ports;


import com.frostbyte.app.MusicPlayer;
import com.frostbyte.app.UserInterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class GUI implements UserInterface, ActionListener {
    //Creating Frame
    JFrame frame;

    //Creating Label for printing the selected song name
    JLabel songName;

    //Creating button for selecting a song
    JButton select;

    //Creating Panels
    JPanel playerPanel, controlPanel;

    //Creating icons for buttons
    Icon iconPlay, iconPause, iconResume, iconStop;

    //Creating buttons
    JButton play, pause, resume, stop;

    //Creating FileChooser for choosing the music mp3 file
    JFileChooser fileChooser;
    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    File myFile = null;
    String filename, filePath;
    long totalLength, pauseLength;

    private MusicPlayer player;

    public GUI(MusicPlayer player) {
        this.player = player;
    }

    @Override
    public void Init() {
        this.initUI();
        this.addActionEvents();
    }

    private void initUI() {

        //Setting songName Label to center
        songName = new JLabel("", SwingConstants.CENTER);

        //Creating button for selecting a song
        select = new JButton("Select Mp3");

        //Creating Panels
        playerPanel = new JPanel(); //Music Selection Panel
        controlPanel = new JPanel(); //Control Selection Panel

        //Creating icons for buttons
        iconPlay = new ImageIcon("icons/play-button.png");
        iconPause = new ImageIcon("icons/pause-button.png");
        iconStop = new ImageIcon("icons/stop-button.png");

        //Creating image buttons
        play = new JButton(iconPlay);
        pause = new JButton(iconPause);
        stop = new JButton(iconStop);

        //Setting Layout of PlayerPanel
        playerPanel.setLayout(new GridLayout(2, 1));

        //Addings components in PlayerPanel
        playerPanel.add(select);
        playerPanel.add(songName);

        //Setting Layout of ControlPanel
        controlPanel.setLayout(new GridLayout(1, 3));

        //Addings components in ControlPanel
        controlPanel.add(play);
        controlPanel.add(pause);
        controlPanel.add(stop);

        //Setting buttons background color
        play.setBackground(Color.WHITE);
        pause.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);

        //Initialing the frame
        frame = new JFrame();

        //Setting Frame's Title
        frame.setTitle("Music Player");

        //Adding panels in Frame
        frame.add(playerPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);

        //Setting Frame background color
        frame.setBackground(Color.white);
        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void addActionEvents() {
        //registering action listener to buttons
        select.addActionListener(this);
        play.addActionListener(this);
        pause.addActionListener(this);
        stop.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(select)) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("music"));
            fileChooser.setDialogTitle("Select Mp3");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Mp3 files", "mp3"));
            if (fileChooser.showOpenDialog(select) == JFileChooser.APPROVE_OPTION) {
                myFile = fileChooser.getSelectedFile();
                filename = fileChooser.getSelectedFile().getName();
                filePath = fileChooser.getSelectedFile().getPath();
                songName.setText("File Selected : " + filename);
            }
        }
        if (e.getSource().equals(play)) {
            if (filename != null) {
                this.player.PlaySong(myFile);
                songName.setText("Now playing : " + filename);
            } else {
                songName.setText("No File was selected!");
            }
        }
        if (e.getSource().equals(pause)) {
            if (filename != null) {
                this.player.Pause();
            }
        }

        if (e.getSource().equals(stop)) {
            if (player != null) {
                player.Stop();
                songName.setText("");
            }
        }
    }
}
