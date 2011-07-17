package org.wadec.asteroids;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Wade C
 */
public class SpaceCraft {

    private int heading = 0,//for the rotation
            currDir = ROT_NONE;
    private int momentum = 0,//for drift, trajectory
            speedDelay = 0;
    private boolean moving = false;
    private Point pos;

    public SpaceCraft() {
        pos = new Point(Game.FRAME_X / 2, Game.FRAME_Y / 2);
    }

    public void draw(Graphics g) {
        //update the header
        heading += currDir;

        //get plot
        Point tip = getPlot(heading),
                rWing = getPlot(heading - 130),
                lWing = getPlot(heading + 130);

        //check for collision
        if (colision(tip)) {
            System.exit(0);
        }

        //slow momentum
        if (!moving && momentum > 0) {
            if (speedDelay == SPEED_DELAY) {
                speedDelay = 0;
                momentum--;
            }
        }

        //draw craft
        g.drawLine(tip.x, tip.y, rWing.x, rWing.y);
        g.drawLine(tip.x, tip.y, lWing.x, lWing.y);
        g.drawLine(lWing.x, lWing.y, pos.x, pos.y);
        g.drawLine(rWing.x, rWing.y, pos.x, pos.y);

        //update coords
        pos = newPosition(tip);
    }

    public void rotate(int rotation) {
        currDir = rotation;
    }

    public void stop() {
        moving = false;
    }

    public void move() {
        if (momentum < MAX_SPEED) {
            momentum++;
        }
        moving = true;
    }

    private Point newPosition(Point tip) {
        int x = tip.x > pos.x ? 1 : tip.x < pos.x ? -1 : 0;
        int y = tip.y > pos.y ? 1 : tip.y < pos.y ? -1 : 0;
        return new Point(pos.x + x, pos.y + y);
    }

    private boolean colision(Point tip) {
        return (tip.x >= Game.FRAME_X && tip.y >= Game.FRAME_Y) || (tip.x <= 0 && tip.y <= 0);
    }

    private Point getPlot(int angle) {
        return new Point(
                (int) (SIZE * Math.cos(toRadian(angle)) + pos.x),
                (int) (SIZE * Math.sin(toRadian(angle)) + pos.y));
    }

    private float toRadian(int i) {
        return (float) (i * (Math.PI / 180));
    }

    //debug
    public int getHeading() {
        return heading;
    }
    private static final int SIZE = 10, MAX_SPEED = 5, SPEED_DELAY = 15;
    public static final int ROT_CLOCKWISE = 2, ROT_COUNTER = -2, ROT_NONE = 0;
}
