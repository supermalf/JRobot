package org.jrobot.ctrl;

import org.jrobot.log.Log;
import org.jrobot.game.robot.Robot;

import java.rmi.RemoteException;

/**
 * GetPressure Action Class
 *
 * @author Malf
 * @version $Id: GetPressure.java,v 1.3 2005/07/03 23:45:46 savio Exp $
 */


public class GetPressure implements Action {

    /**
     * Run Method
     * Executed the key action
     */
    public void run(Robot robot)
    {
        try
        {
            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " START to get Pressure.");

            /* Setting command to the robot thread */
            robot.setCommand("pressure");


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
        return "pressure";
    }

}
