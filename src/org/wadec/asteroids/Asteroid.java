package org.wadec.asteroids;

import java.awt.Graphics;

/**
 *
 * @author Wade C
 */
public class Asteroid {

    private final int[] source, dest;
    private int[] pos = new int[2];
    private int[][] design;//TODO generate random asteroid designs
    private float slope;
    private int speed = 0, delay = 0, size = 0;

    public Asteroid(int oX, int oY, int dX, int dY, int spee, int siz) {
        source = new int[]{oX, oY};//source of the asteroid
        dest = new int[]{dX, dY};//destination
        pos = source;
        slope = getSlope();
        speed = spee;
        size = siz * 2;
    }

    public void draw(Graphics g) {
        //draw the asteroid
        g.drawRoundRect(pos[0] - size, pos[1] - size, size * 2, size * 2, size * 2, size * 2);

        //move the asteroid along its path
        if (delay < speed) {
            delay++;
            return;
        }
        delay = 0;
        advance();
    }

    public boolean compelte() {
        return pos[0] == dest[0] && pos[1] == dest[1];
    }

    public void advance() {
        int x = source[0] == 0 ? pos[0] + 1 : pos[0] - 1;
        int y = (int) ((slope * (float) x) + (float) source[1]);
        pos = new int[]{x, y};
    }

    private float getSlope() {
        if (source[0] == 0) {
            return ((float) dest[1] - (float) source[1]) / (float) dest[0];
        } else {
            return ((float) source[1] - (float) source[1]) / (float) source[0];
        }
    }
}
