package org.jrobot.ctrl;

import org.jrobot.log.Log;
import org.jrobot.game.robot.Robot;
import org.jrobot.ctrl.manual.*;

import java.rmi.RemoteException;

/**
 * GetGradient Action Class
 *
 * @author Malf
 * @version $Id: GetGradient.java,v 1.4 2005/07/03 23:45:45 savio Exp $
 */


public class GetGradient implements Action {

    /**
     * Run Method
     * Executed the key action
     */
    public void run(Robot robot)
    {
        try
        {
            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " START to get Gradient.");

            /* Setting command to the robot thread */
            robot.setCommand("gradient");


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
        return "gradient";
    }
}
