package org.wadec.asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Wade C
 */
public class Frame extends JFrame implements KeyListener {

    private Game game;

    public Frame() {
        setTitle("ast3r0idz");
        construct();
        setVisible(true);
    }

    public void run() {
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("KEY:\t" + e.getKeyChar() + "\t" + e.getKeyCode());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_S://start
                return;
        }
    }

    private void construct() {
        //jframe basics
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setResizable(false);
        setSize(640, 400);

        //create game session
        game = new Game();
        add(game);
    }

    public static void main(String[] args) {
        new Frame().run();
    }

    public void keyReleased(KeyEvent e) {//not used
    }

    public void keyTyped(KeyEvent e) {//not used
    }
}
