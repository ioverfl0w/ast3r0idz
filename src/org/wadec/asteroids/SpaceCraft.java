package org.wadec.asteroids;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Wade C
 */
public class SpaceCraft {

    private int momentum = 0, heading = 0, currDir = ROT_NONE;
    private Point pos;

    public SpaceCraft() {
        pos = new Point(Game.FRAME_X / 2, Game.FRAME_Y / 2);
    }

    public void draw(Graphics g) {
        //update the header
        heading += currDir;
        
        //draw the craft
        Point tip = getPlot(heading);
        Point rWing = getPlot(heading - 130);
        Point lWing = getPlot(heading + 130);
        g.drawLine(tip.x, tip.y, rWing.x, rWing.y);
        g.drawLine(tip.x, tip.y, lWing.x, lWing.y);
        g.drawLine(lWing.x, lWing.y, pos.x, pos.y);
        g.drawLine(rWing.x, rWing.y, pos.x, pos.y);
    }
    
    public void rotateClockwise() {
        currDir = ROT_CLOCKWISE;
    }
    
    public void rotateCounterClockwise() {
        currDir = ROT_COUNTER;
    }
    
    public void resetRotation() {
        currDir = ROT_NONE;
    }

    private Point getPlot(int angle) {
        return new Point(
                (int) (SIZE * Math.cos(toRadian(angle)) + pos.x),
                (int) (SIZE * Math.sin(toRadian(angle))) + pos.y);
    }

    private float toRadian(int i) {
        return (float) (i * (Math.PI / 180));
    }
    
    //debug
    public int getHeading() {
        return heading;
    }
    private static final int SIZE = 10;
    public static final int ROT_CLOCKWISE = 2, ROT_COUNTER = -2, ROT_NONE = 0;
}
