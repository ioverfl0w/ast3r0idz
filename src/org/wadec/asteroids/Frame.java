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
        try {
            while (true) {
                long s = System.currentTimeMillis();

                //update game
                if (game.isActive()) {
                    game.process();
                }

                Thread.sleep(10 - (System.currentTimeMillis() - s));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("KEY:\t" + e.getKeyChar() + "\t" + e.getKeyCode());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_S://start
                game.printError("You can't do that yet!");
                return;
        }
    }

    private void construct() {
        //jframe basics
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        //setResizable(false);
        setSize(640, 400);

        //create game session
        game = new Game();
        game.setSize(640,400);
        add(game);
        game.setLocation(100,100);
    }

    public static void main(String[] args) {
        new Frame().run();
    }

    public void keyReleased(KeyEvent e) {//not used
    }

    public void keyTyped(KeyEvent e) {//not used
    }
}
