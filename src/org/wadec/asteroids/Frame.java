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
        try {
            while (true) {
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
            case KeyEvent.VK_U://up
                if (!game.isActive()) {
                    game.addDifficulty();
                    repaint();
                }
                return;
            case KeyEvent.VK_D://down
                if (!game.isActive()) {
                    game.delDifficulty();
                    repaint();
                } else { //ingame
                }
                return;
            default:
                game.printError("unknown option '" + e.getKeyChar() + "'");
                return;
        }
    }

    private void construct() {
        //jframe basics
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        //setResizable(false);
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
    }

    public void keyTyped(KeyEvent e) {//not used
    }
}
