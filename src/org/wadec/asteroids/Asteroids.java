package org.wadec.asteroids;

import java.awt.Graphics;

/**
 *
 * @author Wade C
 */
public class Asteroids {

    private Asteroid[] asteroids;
    public int count;

    public Asteroids(int diff) {
        asteroids = new Asteroid[diff];
        count = 0;
    }

    public boolean needUpdate() {
        for (Asteroid a : asteroids) {
            if (a == null || a.compelte()) {
                return true;
            }
        }
        return false;
    }

    //updates here
    public void update() {
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] == null) {
                if (Game.RANDOM.nextInt(100) == 1) {
                    asteroids[i] = generate();
                    count++;
                }
                continue;
            }

            if (asteroids[i].compelte()) {
                asteroids[i] = null;
                count--;
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
                d == 0 ? 0 : Game.FRAME_X, Game.RANDOM.nextInt(Game.FRAME_Y), //start
                d == 0 ? Game.FRAME_X : 0, Game.RANDOM.nextInt(Game.FRAME_Y), //end
                Game.RANDOM.nextInt(6) + 2, //speed
                Game.RANDOM.nextInt(4) + 2);        //size
    }
}
