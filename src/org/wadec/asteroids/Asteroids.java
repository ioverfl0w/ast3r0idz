package org.wadec.asteroids;

import java.awt.Graphics;

/**
 *
 * @author Wade C
 */
public class Asteroids {

    private Asteroid[] asteroids;

    public Asteroids(int diff) {
        asteroids = new Asteroid[diff * 2];
    }

    //updates here
    public void update() {
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] == null) {
                if (Game.RANDOM.nextInt(500) == 1) {
                    asteroids[i] = generate();
                }
                continue;
            }

            if (asteroids[i].compelte()) {
                asteroids[i] = null;
                continue;
            }
        }
    }

    public void paint(Graphics g) {
        for (Asteroid a : asteroids) {
            if (a != null) {
                a.draw(g);
            }
        }
    }

    // TODO generate asteroids moving NORTH to SOUTH
    private Asteroid generate() {
        int d = Game.RANDOM.nextBoolean() ? 1 : 0;//0 = L->R, 1 = R->L
        return new Asteroid(
                d == 0 ? 0 : FRAME_X, Game.RANDOM.nextInt(FRAME_Y), //start
                d == 0 ? FRAME_X : 0, Game.RANDOM.nextInt(FRAME_Y), //end
                Game.RANDOM.nextInt(6) + 1, //speed
                Game.RANDOM.nextInt(4) + 1);        //size
    }
    public static final int FRAME_X = 640, FRAME_Y = 400;
}
