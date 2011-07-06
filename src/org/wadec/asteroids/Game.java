package org.wadec.asteroids;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Wade C
 */
public class Game extends Component {

    private final Asteroids ast;
    private boolean active = false;

    public Game() {
        ast = new Asteroids();
    }

    @Override
    public void paint(Graphics g) {
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 640, 400);
        
        if (active) {
            return;
        }

        //menu
        generateMenu(g);
    }

    private void generateMenu(Graphics g) {
        g.setColor(Color.WHITE);

        //logo
        g.setFont(LOGO);
        g.drawString("AST3R0IDZ", 250, 30);

        //menu options
        g.setFont(NORMAL);
        g.drawString(">> Press 's' to start <<", 240, 100);
        g.drawString("Game by wadec", 270, 150);
        g.drawString("irc.strictfp.com / #nova", 240, 300);
    }
    public static final Font LOGO = new Font(Font.MONOSPACED, Font.BOLD, 26);
    public static final Font NORMAL = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
}
