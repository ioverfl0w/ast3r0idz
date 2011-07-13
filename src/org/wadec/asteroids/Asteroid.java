package org.wadec.asteroids;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Wade C
 */
public class Asteroid {

    private final Point source, dest;
    private Point pos;
    private int[][] design;//TODO generate random asteroid designs
    private float slope;
    private int speed = 0, delay = 0, size = 0;

    public Asteroid(int oX, int oY, int dX, int dY, int spee, int siz) {
        source = new Point(oX, oY);//source of the asteroid
        dest = new Point(dX, dY);//destination
        pos = source;
        slope = getSlope();
        speed = spee;
        size = siz * 2;
    }

    public void draw(Graphics g) {
        //draw the asteroid
        g.drawRoundRect(pos.x - size, pos.y - size, size * 2, size * 2, size * 2, size * 2);

        //move the asteroid along its path
        if (delay < speed) {
            delay++;
            return;
        }
        delay = 0;
        advance();
    }

    public boolean compelte() {
        return (pos.x == dest.x && pos.y == dest.y) || (source.x == Game.FRAME_X && pos.x < dest.x);
    }

    public void advance() {
        int x = source.x == 0 ? pos.x + 1 : pos.x - 1;
        int y = Math.round((slope * (float) x) + (float) source.y);
        pos = new Point(x, y);
    }

    private float getSlope() {
        if (source.x == 0) {
            return ((float) dest.y - (float) source.y) / (float) dest.x;
        } else {
            return ((float) source.y - (float) dest.y) / (float) source.x;
        }
    }
}
