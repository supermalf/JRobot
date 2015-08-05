package org.jrobot.game;

import java.rmi.RemoteException;
import org.jrobot.game.robot.*;

/**
 * Gradient
 *
 * @author savio
 * @author tucano
 * @version $Id: Gradient.java,v 1.4 2005/07/05 02:12:37 savio Exp $
 */

public class Gradient implements java.io.Serializable {

    /** Normal gradient */
    private float normal;
    
    /** Axial gradient */
    private float axial;
    
    /** Robot */
    private Robot robot;
    
    /**
     * Get normal gradient
     *
     * @return Normal gradient
     */
    public float getNormal() {
        return normal;
    }

    /**
     * Get axial gradient
     *
     * @return Axial gradient
     */
    public float getAxial() {
        return axial;
    }

    /**
     * Calculate gradient
     *
     * @param x1 coordinate
     * @param y1 coordinate
     * @param x2 coordinate
     * @param y2 coordiante
     *
     * @return Gradient
     */
    public float calc(int x1, int y1, int x2, int y2) {
        return Game.getPropValue("Pressure",x1, y1) - Game.getPropValue("Pressure",x2, y2);
    }

    /**
     * Clear gradient
     *
     */
    public void clear() {
        normal = 0;
        axial  = 0;
    }

    /**
     * Get clone
     *
     * @return Object clone
     */
    public Object clone() {         
        return new Gradient(robot);
    }


    /**
     * Flush
     */
    public void flush() {
        try {
            int x = robot.getWidth();
            int y = robot.getHeight();
            int angle = robot.getAngle();
            
            if(angle == 0) { 
                normal = calc(x,y-1,x,y+1);
                axial  = calc(x+1,y,x-1,y);  
            }
            else if(angle == 90) { 
                normal = calc(x+1,y,x-1,y); 
                axial  = calc(x,y+1,x,y-1); 
            }
            else if(angle == 180) {
                normal = calc(x,y+1,x,y-1); 
                axial  = calc(x-1,y,x+1,y); 
            }
            else if(angle == 270) {
                normal = calc(x-1,y,x+1,y); 
                axial  = calc(x,y-1,x,y+1); 
            }
        } catch(RemoteException e) {
        }
    }

    /**
     * Class constructor
     *
     * @param _robot Robot
     */
    public Gradient(Robot _robot) {
        robot = _robot;
        flush();
    }
}
