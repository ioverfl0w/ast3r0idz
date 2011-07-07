package org.wadec.asteroids;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Wade C
 */
public class Game extends Component {

    private final Asteroids ast;
    private boolean active = true;
    private String error = null;
    private Asteroid test = new Asteroid(0, 200, 640, 0, 10, 3);

    public Game() {
        ast = new Asteroids();
    }

    public void process() {
        //draw the current frame
        repaint();

        //do game checks
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean bool) {
        active = bool;
        if (active) {
            error = null;
        }
    }

    public void printError(String err) {
        error = err;
        System.out.println("ERROR: " + error);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 640, 400);
        g.setColor(Color.RED);//white for game mode, red debug

        test.draw(g);

        if (active) {
            return;
        }

        //menu
        generateMenu(g);

        //error
        if (error != null) {
            printError(g);
        }
    }

    private void generateMenu(Graphics g) {
        //logo
        g.setFont(LOGO);
        g.drawString("AST3R0IDZ", 250, 30);

        //menu options
        g.setFont(NORMAL);
        g.drawString(">> Press 's' to start <<", 240, 100);
        g.drawString("Game by wadec", 270, 150);
        g.drawString("irc.strictfp.com / #nova", 240, 300);
    }

    private void printError(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(LOGO);
        g.drawString(error, 370 - (error.length() * 10), 350);
    }
    public static final Font LOGO = new Font(Font.MONOSPACED, Font.BOLD, 26);
    public static final Font NORMAL = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    public static final Random RANDOM = new Random();
}
