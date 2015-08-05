package org.jrobot.game.proper;

import org.jrobot.game.robot.*;
import org.jrobot.game.robot.cmd.*;

/**
 * Dynamic Property abstract class.
 *
 * There are two types of  property.  

 * 1) Static  property: The property  object holds  a matrix  with all
 * cells  values for  its domain.  Usually  used to  handle those  map
 * layers fetched from lua map file.
 * <p/>
 * 2) Dynamic   property:   Proper   keeps  'float[][]   map'   unused
 * implementing a  specific function f(height,width)  that returns the
 * cells values.
 * <p/>
 * Both implementation use Proper interface.
 * <p/>
 * HOW-TO Implement a dynamic property class:
 * <p/>
 * i)   make sure your class extends DynamicProper
 * ii)  implement all abstract functions (optionally override methods)
 * iii) load it from the game GUI.
 * <p/>
 * Notice that, by default, the implemented property constructor does not
 * take any argument. This is pre-defined to help late binding facility.
 * <p/>
 * ProperUtils.loadProper(); uses java reflection to load a preprogrammed
 * property into the game. The GUI provides a form to add it automatically.
 *
 * @author savio
 * @version $Id: DynamicProper.java,v 1.17 2005/07/03 23:06:10 savio Exp $
 */

/*
 * XXX IMPORTANT: for every new command created, add a new affect*() method.
 * o design precisa ser consistente!!!
 */

public abstract class DynamicProper implements Proper {

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
     * Set map bounds
     *
     * @param _height height
     * @param _width  width
     */
    public void setSize(int _height, int _width) {
        height = _height;
        width = _width;
    }

    public void setMap(float[][] notused, int not, int used) {
        /** does nothing */
        return;
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
     * Function f(x,y) that implements the dynamic property.
     * This function returns a value for each cell in a given
     * domain.
     *
     * @param x height
     * @param y width
     * @return property numeric value
     */
    public abstract float getMapXY(int x, int y);


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
     * @see CommandList
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
