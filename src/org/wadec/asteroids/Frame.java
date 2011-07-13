package org.wadec.asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Wade C
 */
public class Frame extends JFrame implements KeyListener, Runnable {

    private Game game;

    public Frame() {
        setTitle("ast3r0idz");
        construct();
        setVisible(true);
    }

    public void run() {
        long c = 0;
        try {
            while (true) {
                //frees up memory some (every 5 seconds)
                if (c == 500) {
                    System.gc();
                    c = 0;
                }
                c++;

                //update game
                if (game.isActive()) {
                    game.process();
                }

                Thread.sleep(10);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println("KEY:\t" + e.getKeyChar() + "\t" + e.getKeyCode());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_S://start
                if (!game.isActive()) {
                    game.create();
                    game.setActive(true);
                }
                return;
            case KeyEvent.VK_Q://quit
                if (game.isActive()) {
                    game.setActive(false);
                    repaint();
                }
                return;
            case KeyEvent.VK_U://difficulty ++
                if (!game.isActive()) {
                    game.addDifficulty();
                    repaint();
                }
                return;
            case KeyEvent.VK_D://difficulty --
                if (!game.isActive()) {
                    game.delDifficulty();
                    repaint();
                } else { //ingame
                    game.getCraft().rotateClockwise();
                }
                return;
            case KeyEvent.VK_A://left rotate
                if (game.isActive()) {
                    game.getCraft().rotateCounterClockwise();
                }
                return;
        }
    }

    private void construct() {
        //jframe basics
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setResizable(false);
        setSize(640, 433);

        //create game session
        game = new Game();
        add(game);
        game.setLocation(0, 0);
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        new Thread(frame).start();//start game engine
    }

    public void keyReleased(KeyEvent e) {//not used
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A://rotation
            case KeyEvent.VK_D://rotation
                if (game.isActive()) {
                    game.getCraft().resetRotation();
                }
                return;
        }
    }

    public void keyTyped(KeyEvent e) {//not used
    }
}
