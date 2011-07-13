package org.wadec.asteroids;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Wade C
 */
public class Game extends Component {

    private Asteroids ast = null;
    private SpaceCraft craft = null;
    private boolean active = false, force = false;
    private int difficulty = 10, score = 0;
    private BufferedImage buff = null;

    public Game() {
        force = true;
        System.out.println("game session started");
    }

    public void create() {
        ast = new Asteroids(difficulty);
        craft = new SpaceCraft();
        score = 0;
    }

    public void process() {
        //update asteroids
        if (ast.needUpdate()) {
            ast.update();
        }

        //paint frame
        buff = new BufferedImage(FRAME_X, FRAME_Y, BufferedImage.TYPE_INT_RGB);
        draw(buff.getGraphics());//create the frame
        repaint();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean bool) {
        active = bool;
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
    
    public SpaceCraft getCraft() {
        return craft;
    }

    @Override
    public void paint(Graphics g) {
        //print the buffered frame
        if (buff != null) {
            g.drawImage(buff, 0, 0, this);
        }

        if (force) {
            draw(g);
        }
    }

    public void draw(Graphics g) {
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 640, 400);

        if (active) {
            g.setColor(Color.DARK_GRAY);
            g.drawString("score: " + score, 5, 15);
            g.drawString("asteroids: " + ast.count, 5, 25);
            g.drawString("heading: " + craft.getHeading(), 5, 35);
            ast.paint(g);//draw asteroids
            g.setColor(Color.WHITE);
            craft.draw(g);//draw spacecraft
            //ast.paint(g);//draw asteroids
            return;
        }

        g.setColor(Color.WHITE);

        //menu
        generateMenu(g);
    }

    private void generateMenu(Graphics g) {
        //logo
        g.setFont(LOGO);
        g.drawString("AST3R0IDZ", 250, 30);

        //menu options
        g.setFont(NORMAL);
        g.drawString(">> Press 's' to start <<", 240, 100);
        g.drawString("use A, W, D, and [space bar] to move!", 210, 120);
        g.drawString("Set difficulty (u)p or (d)own", 230, 170);
        g.drawString("(number of asteroids at once)", 220, 190);
        g.drawString("Current Difficulty: " + difficulty, 240, 210);
        g.drawString("A game by wadec", 250, 270);
        g.drawString("irc.strictfp.com / #nova", 240, 290);
    }
    public static final Font LOGO = new Font(Font.MONOSPACED, Font.BOLD, 26);
    public static final Font NORMAL = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    public static final Random RANDOM = new Random();
    public static final int FRAME_X = 640, FRAME_Y = 400;
}
