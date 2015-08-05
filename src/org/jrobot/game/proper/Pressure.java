package org.jrobot.game.proper;

import org.jrobot.game.robot.*;
import org.jrobot.game.Game;

import java.rmi.RemoteException;

/**
 * Pressure
 *
 * XXX: IMPORTANTE: implementar affectGradient()
 *
 * @author savio
 * @version $Id: Pressure.java,v 1.15 2005/07/03 17:09:28 savio Exp $
 */

public class Pressure extends StaticProper {
    /**
     * This method returns the impact caused by the Pressure
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
     * This method returns the impact caused by the Pressure
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
     * This method returns the impact caused by the Pressure
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
     * This method returns the impact caused by the Pressure
     * when Prospect command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectProspect(Robot robot) {
        //tempo de lag
        int tmpTime = 0;
        
        try {
            tmpTime = (int) (( Game.getPropValue("Pressure", robot.getWidth(),robot.getHeight()) ) * 10.0f);
        } catch(RemoteException e) {
            //XXX
        }
        
        return new Impact(tmpTime, 0, 0);
    }

    /**
     * This method returns the impact caused by the Pressure
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
     * This method returns the impact caused by the Pressure
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
     * This method returns the impact caused by the Pressure
     * when Pressure command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectPressure(Robot robot) {
        return null;
    }

    public Pressure() {
        super();
    }
}
