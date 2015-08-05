package org.jrobot.game.proper;

import org.jrobot.game.robot.*;

/**
 * Static Property abstract class
 * <p/>
 * There are two types of property.
 * 1) Static property:  The property object holds a matrix with all
 * cells values for its domain. Usually used to
 * handle those map layers fetched from lua map
 * file.
 * 2) Dynamic property: Proper keeps 'float[][] map' unused implementing
 * a specific function f(height,width) that returns
 * the cells values.
 * <p/>
 * Both implementation use Proper interface.
 * <p/>
 * HOW-TO Implement a static property class:
 * <p/>
 * i)   make sure your class extends StaticProper
 * ii)  implement all abstract functions (optionally override methods)
 * iii) load it from the game GUI.
 * <p/>
 * Notice that, by default, the implemented property constructor does not
 * take any argument. This is pre-defined to help late binding facility.
 *
 * @author savio
 * @version $Id: StaticProper.java,v 1.18 2005/07/04 03:36:14 savio Exp $
 */

public abstract class StaticProper implements Proper {

    /**
     * The map itself
     */
    protected float[][] map;

    /**
     * Property name
     */
    public String name;

    /**
     * height
     */
    public int height;

    /**
     * width
     */
    public int width;

    /**
     * Set property name
     *
     * @param _name Key string. This will be the property name.
     */
    public void setName(String _name) {
        name = _name;
    }

    /**
     * Set property map
     *
     * @param _map a float matrix
     */
    public void setMap(float[][] _map, int w, int h) {
        //map = (float[][])_map.clone(); // ?!?!?!? XXX
        map = new float[h][w];
        for(int i=0;i<h;i++) {
            for(int j=0;j<w;j++) {
                map[i][j] = _map[i][j];
            }
        }
    }

    /**
     * Set map bounds
     *
     * @param _height height
     * @param _width  width
     */
    public void setSize(int _height, int _width) {
        height = _height;
        width = _width;
    }

    /**
     * Returns the property name
     *
     * @return Key string related to the object.
     */
    public String toString() {
        return name;
    }

    /**
     * Returns the whole property map.
     *
     * @return The whole map
     */
    public float[][] getMap() {
        return map;
    }

    /**
     * Return the property value on a given matrix coordinate.
     *
     * @param x height
     * @param y width
     * @return property numeric value
     */
    public float getMapXY(int x, int y) {
        return map[x][y];
    }

    /**
     * Static properties can change their cell values; This method
     * sets a new value to a position.
     *
     * @param x height
     * @param y width
     * @param value New value
     */
    public void setMapXY(int x, int y, float value) {
        map[x][y] = value;
    }

    /**
     * This method returns the impact caused by the property
     * when Position command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectPosition(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the property
     * when Turn command is performed.
     *
     * @param direction true = left, false = right
     * @param robot     Robot
     * @return Impact
     * @see Impact
     */
    public Impact affectTurn(boolean direction, Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the property
     * when Move command is performed.
     *
     * @param direction true = forward, false = backward
     * @param robot     Robot
     * @return Impact
     * @see Impact
     */
    public Impact affectMove(boolean direction, Robot robot) {
        return null;
    }


    /**
     * This method returns the impact caused by the property
     * when Prospect command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectProspect(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the property
     * when Gradient command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectGradient(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the property
     * when Time command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectTime(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the property
     * when Pressure command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectPressure(Robot robot) {
        return null;
    }

    /**
     * Affect command; detects which command is processed
     * and return an Impact related to that.
     *
     * @param robot Robot affected by command
     * @param cmd   Command string
     * @return Impact
     * @see org.jrobot.game.robot.cmd.CommandList
     */

    /*
     * XXX: implementar sobrecarga pra passar informacao do
     *      robo pras affect*()s?
     * XXX: verificar consistencia (nomes) com CommandList
     */

    public Impact affectCmd(Robot robot, String cmd) {

        if (cmd.equals("turnLeft")) {
            return affectTurn(true, robot);
        } else if (cmd.equals("turnRight")) {
            return affectTurn(false, robot);
        } else if (cmd.equals("moveForward")) {
            return affectMove(true, robot);
        } else if (cmd.equals("moveBackward")) {
            return affectMove(false, robot);
        } else if (cmd.equals("position")) {
            return affectPosition(robot);
        } else if (cmd.equals("gradient")) {
            return affectGradient(robot);
        } else if (cmd.equals("time")) {
            return affectTime(robot);
        } else if (cmd.equals("prospect")) {
            return affectProspect(robot);
        } else if (cmd.equals("pressure")) {
            return affectPressure(robot);
        }
        return null;
    }
}
