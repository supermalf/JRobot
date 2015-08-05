package org.jrobot.ctrl;

import org.jrobot.log.Log;
import org.jrobot.game.robot.Robot;
import org.jrobot.game.Game;
import org.jrobot.ctrl.manual.*;

import java.rmi.RemoteException;

/**
 * Turn Left Action Class
 *
 * @author Malf
 * @version $Id: TurnLeft.java,v 1.5 2005/07/03 23:45:46 savio Exp $
 */


public class TurnLeft implements Action {

    /**
     * Run Method
     * Executed the key action
     */
     public void run(Robot robot)
    {
        try
        {
            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " START to Turn Left.");

            /* Setting command to the robot thread */
            robot.setCommand("turnLeft");


        } catch (RemoteException e) {
            System.err.println(e);
        }
    }

    /**
     * toString method
     *
     * @return String Command String
     */
    public String toString()
    {
        return "turnLeft";
    }
}
