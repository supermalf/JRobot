package org.jrobot.game;

import org.jrobot.game.proper.*;
import org.jrobot.game.robot.*;
import org.jrobot.log.*;

import java.util.*;
import java.lang.*;

/**
 * This class holds a game Map containing all its properties and bounds.
 * These three properties are static, default and mandatory:
 * Pressure, Depth, Error
 *
 * @author savio
 * @version $Id: Map.java,v 1.35 2005/07/05 04:56:34 savio Exp $
 */

public class Map extends ClassLoader {

    /**
     * map height
     */
    public static int height;

    /**
     * map width
     */
    public static int width;

    /**
     * cell size
     */
    public static int n;

    /**
     * total number of properties
     */
    public static int nprops;

    /**
     * number of dynamic properties
     */
    public static int ndprops;

    /**
     * properties vector
     */
    public static Proper[] props;

    /**
     * map file
     */
    public static String mapFile;

    /**
     * robot position matrix
     */
    public int[][] robotPosition;

    /**
     * flag position matrix (explored cells)
     */
    public int[][] flagPosition;

    /**
     * Class constructor
     */
    public Map() {
    }

    /**
     * Return the sum of impactes done by all properties
     * of a map cell.
     *
     * @param robot Affected robot
     * @param cmd Command string 
     * @return Impact
     * @see org.jrobot.game.robot.cmd.CommandList
     */
    public Impact affectSum(Robot robot, String cmd) {
        Impact result = new Impact(0, 0, 0);

        for (int i = 0; i < nprops; i++) {
            result.sum(props[i].affectCmd(robot, cmd));
        }
        return result;
    }

    /**
     * Set robot position
     *
     * @param x coordinate
     * @param y coordinate
     * @param value 0 = empty cell, 1 = robot
     * @return True if succeeded changing its value
     */
    public boolean setRobotPosition(int x, int y, int value) {
        if(value < 0 || value > 1) 
            return false;
        robotPosition[x][y] = value;
        return true;
    }
    
    /**
     * Set flag position. All positions with flags are already 
     * explored cells.
     *
     * @param x coordinate
     * @param y coordinate
     * @param value 0 = empty cell, 1 = flag
     * @return True if succeeded changing its value
     */
    public boolean setFlagPosition(int x, int y, int value) {
        if(value < 0 || value > 1) 
            return false;
        flagPosition[x][y] = value;
        return true;
    }
    
    /**
     * Get robot position
     *
     * @param x coordinate
     * @param y coordinate
     * @return Value
     */
    public int getRobotPosition(int x, int y) {
        if((x > width || y > height) && x < 0 && y < 0) {
            Log.warning("Illegal getRobotPosition call: ("+x+","+y+")");
            return -1;
        }
        
        return robotPosition[x][y];
    }
    
    /**
     * Get flag position
     *
     * @param x coordinate
     * @param y coordinate
     * @return Value
     */
    public int getFlagPosition(int x, int y) {
       return flagPosition[x][y];
    }
    
    /**
     * Get property value by coordinate
     * 
     * @param x coordinate
     * @param y coordinate
     * @return Float
     */
    public float getPropValue(String property, int x, int y) {
        if(x < width && y < height && x >= 0 && y >= 0) {
            for(int i=0; i < props.length; i++) {
                if(property.equals(props[i].toString())) {
                    return props[i].getMapXY(x,y);
                }
            }
        }
        return 0.00f;
    }

    /**
     * Set pressure value by coordinate
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void setPressureValue(int x, int y, float value) {
        if((x < width || y < height) && x >= 0 && y >= 0) {
            for(int i=0; i < props.length; i++) {
                if(props[i].toString().equals("Pressure")) {
                    StaticProper pressure = (StaticProper) props[i];
                    pressure.setMapXY(x,y,value);
                    return;
                }
            }
        }
    }

    /**
     * Get depth map
     * 
     * @return Map matrix
     */
    public float[][] getDepthMap() {
        for(int i=0; i < props.length; i++) {
            if(props[i].toString().equals("Depth")) {
                StaticProper depth = (StaticProper) props[i];
                return depth.getMap();
            }
        }
        return null;
    }
    
    /**
     * Get total score
     *
     * @return Total score
     */
    public int getTotalScore() {
        float[][] pressureMap;
        int total = 0;

        for(int i=0; i < props.length; i++) {
            if(props[i].toString().equals("Pressure")) {
                StaticProper pressure = (StaticProper) props[i];
                pressureMap = pressure.getMap();
                if(pressureMap == null) {
                    Log.error("Could not Pressure.getMap()");
                    GameUtils.handleExit();
                }
                for(int w = 0; w < pressure.width; w++) {
                    for(int h = 0; h < pressure.height; h++) {
                        total += (int) 100*pressureMap[h][w];
                    }
                }
                return total;
            }
        }
        return 0;
    }

    /**
     * Get random empty position
     *
     * @return Integer vector giving the position. {width,height,angle}
     */
    public int[] getEmptyPosition() {
        Random rand = new Random();
        int maxTries = 20;
        int[] pos = new int[3];
         
        for(int i = 0; i < maxTries; i++) {
            /* get a pseudo-random position */
            pos[0] = (rand.nextInt() % width);
            pos[1] = (rand.nextInt() % height);
            pos[2] = 90*Math.abs((rand.nextInt() % 4));
            
            pos[0] = Math.abs(pos[0]);
            pos[1] = Math.abs(pos[1]);

            /* Check if the position is valid and empty */
            if(pos[0] < width && pos[1] < height) {             
                if(getRobotPosition(pos[0], pos[1]) == 0) {
                    return pos;
                }
            }
        }        
        /* nothing can be done, return an invalid position */
        int ret[] = {-1,-1,0};
        return ret;
    }

    /**
     * Get CellSize
     *
     * @return Integer cell size
     */  
    public int getCellSize ()
    {
        return n;
    }

}
