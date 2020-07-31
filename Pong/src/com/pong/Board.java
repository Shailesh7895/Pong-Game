package com.pong;

import javax.swing.JFrame;

public class Board extends JFrame{
    Board(){
    this.add(new Gameplay());
    this.setTitle("Pong");
    this.setResizable(true);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null); // center
    }
}

