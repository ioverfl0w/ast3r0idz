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

    private Asteroids ast = null;
    private boolean active = false;
    private String error = null;
    private int difficulty = 10, score = 0;

    public Game() {
        System.out.println("game session started");
    }

    public void create() {
        ast = new Asteroids(difficulty);
        score = 0;
    }

    public void process() {
        //do game checks
        if (ast.needUpdate()) {
            ast.update();
        }

        //paint frame
        repaint();
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

    public void addDifficulty() {
        difficulty++;
    }

    public void delDifficulty() {
        if (difficulty - 1 < 5) {
            return;
        }
        difficulty--;
    }

    public void printError(String err) {
        error = err;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 640, 400);

        if (active) {
            g.setColor(Color.DARK_GRAY);
            g.drawString("score: " + score, 5, 15);
            g.setColor(Color.WHITE);
            ast.paint(g);//draw asteroids
            return;
        }

        g.setColor(Color.WHITE);

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
        g.drawString("use WASD to play", 250, 120);
        g.drawString("Set difficulty (u)p or (d)own", 230, 170);
        g.drawString("(number of asteroids at once)", 220, 190);
        g.drawString("Current Difficulty: " + difficulty, 240, 210);
        g.drawString("A game by wadec", 250, 270);
        g.drawString("irc.strictfp.com / #nova", 240, 290);
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
