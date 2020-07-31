package com.pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener, KeyListener{
    public int width = 600;
    public int height = 400;
    public int player1 = 10;
    public int player2 = 10;
    public int unit = 10;
    public int count = 0;
    public int ballx = 30;
    public int bally = 40;
    public int dirX = -1;
    public int dirY = -2;
    public boolean running = true;
    public int delay = 10;
    Timer timer;
    public Gameplay() {
     addKeyListener(this);
    this.setPreferredSize(new Dimension(width,height));
    this.setBackground(Color.black);
    this.setFocusable(true);
    StartGame();
    }
    public void StartGame(){
    running = true;
    timer = new Timer(delay,this);
    timer.start();
    }
    public void move(){
        if(running){
        ballx += dirX;
        bally += dirY;
        
//        if(ballx<=0){
//        dirX = -dirX;
//        }
//        if(ballx>=600){
//        dirX = -dirX;
//        }
        if(bally<=0){
        dirY = -dirY;
        }
        if(bally>=400){
        dirY = -dirY;
        }
        if(new Rectangle(ballx, bally, unit, unit).intersects(new Rectangle(0, player1, 10, 50))){
        count++;
        dirX = -dirX;
        }
        else if(new Rectangle(ballx, bally, unit, unit).intersects(new Rectangle(width-10, player2, 10, 50))){
        count++;
        dirX = -dirX;
        }
        }
        repaint();
    }
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    draw(g);
    }
    public void gameOver(){
    if(ballx<0){
    running = false;
    }
    if(ballx>width){
    running = false;
    }
    }
    public void draw(Graphics g){
    if(running){
    g.setColor(Color.WHITE);
    g.drawString("A S", 270, 10);
    g.drawString("K L", 310, 10);
    g.setColor(Color.gray);
    g.drawLine(width/2,0,width/2,height);
    g.setColor(Color.yellow);
    g.fillOval(ballx, bally, unit, unit);
    g.setColor(Color.red);
    g.fillRect(0, player1, 10, 50);
    g.setColor(Color.blue);
    g.fillRect(width-10, player2, 10, 50);
    }else{
    g.setColor(Color.red);
    g.setFont( new Font("Ink Free",Font.BOLD, 75));
    g.drawString("GAME OVER !!", 40, 190);
    g.setColor(Color.white);
    g.setFont( new Font("Ink Free",Font.BOLD, 40));
    g.drawString("SCORE: " +count, 200, 260);
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        gameOver();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_S && player1<=height){
            running = true;
            player1 += 20;
            
        }else if(e.getKeyCode()==KeyEvent.VK_A && player1>=0){
            running = true;
            player1 -= 20;
        }
        if(e.getKeyCode()==KeyEvent.VK_L && player2<=height){
            running = true;
            player2 += 20;
            
        }else if(e.getKeyCode()==KeyEvent.VK_K && player2>=0){
            running = true;
            player2 -= 20;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
