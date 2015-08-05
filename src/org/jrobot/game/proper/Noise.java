package org.jrobot.game.proper;

import org.jrobot.game.robot.*;

/**
 * Noise
 *
 * @author savio
 * @version $Id: Noise.java,v 1.8 2005/07/03 17:09:28 savio Exp $
 */

public class Noise extends DynamicProper {

    /**
     * This method returns the impact caused by the Noise
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
     * This method returns the impact caused by the Noise
     * when Turn command is performed.
     *
     * @param direction true = left, false = right
     * @param robot     Affect robot
     * @return Impact
     * @see Impact
     */
    public Impact affectTurn(boolean direction, Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the Noise
     * when Move command is performed.
     *
     * @param direction true = forward, false = backward
     * @param robot     Affect robot
     * @return Impact
     * @see Impact
     */
    public Impact affectMove(boolean direction, Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the Noise
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
     * This method returns the impact caused by the Noise
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
     * This method returns the impact caused by the Noise
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
     * This method returns the impact caused by the Noise
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
     * A lame function example.
     */
    public float getMapXY(int x, int y) {
        return x + y;
    }

    /**
     * Class constructor
     */
    public Noise() {
        super();
    }
}
