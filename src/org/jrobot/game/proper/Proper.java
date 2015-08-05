/**
 * Property interface
 *
 * @author savio	
 * @version $Id: Proper.java,v 1.16 2005/07/03 23:06:10 savio Exp $
 */
package org.jrobot.game.proper;

import org.jrobot.game.robot.*;

public interface Proper {

    /**
     * This method returns the impact caused by the Proper
     * when Position command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    Impact affectPosition(Robot robot);

    /**
     * This method returns the impact caused by the Proper
     * when Gradient command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    Impact affectGradient(Robot robot);

    /**
     * This method returns the impact caused by the Proper
     * when Prospect command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    Impact affectProspect(Robot robot);

    /**
     * This method returns the impact caused by the Proper
     * when Move command is performed.
     *
     * @param direction true = foward, false = backward
     * @param robot     Affected robot
     * @return Impact
     * @see Impact
     */
    Impact affectMove(boolean direction, Robot robot);

    /**
     * This method returns the impact caused by the Proper
     * when Turn command is performed.
     *
     * @param direction true = left, right = false
     * @param robot     Affected robot
     * @return Impact
     * @see Impact
     */
    Impact affectTurn(boolean direction, Robot robot);

    /**
     * This method returns the impact caused by the Proper
     * when Time command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    Impact affectTime(Robot robot);

    /**
     * This method returns the impact caused by the Proper
     * when Pressure command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    Impact affectPressure(Robot robot);

    /**
     * Return the property value on a given matrix coordinate.
     *
     * @param x height
     * @param y width
     * @return Proper numeric value
     */
    public float getMapXY(int x, int y);

    /**
     * Returns the Proper name
     *
     * @return Key string related to the object.
     */
    public String toString();

    /**
     * Set map bounds
     *
     * @param height Height
     * @param width  Width
     */
    public void setSize(int height, int width);

    /**
     * Set Proper map
     *
     * @param matrix a float matrix
     */
    public void setMap(float[][] matrix, int w, int h);

    /**
     * Class constructor
     *
     * @param name Key string. This will be the Proper name.
     */
    public void setName(String name);

    /**
     * Affect command; detects which command is processed
     * and return an Impact related to that.
     *
     * @param robot Robot affected by command
     * @param cmd   Command string
     * @return Impact
     */
    public Impact affectCmd(Robot robot, String cmd);
}
