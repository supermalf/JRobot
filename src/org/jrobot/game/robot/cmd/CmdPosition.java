package org.jrobot.game.robot.cmd;

import org.jrobot.game.robot.Robot;
import org.jrobot.game.proper.Impact;
import org.jrobot.game.Game;
import org.jrobot.game.Gradient;
import org.jrobot.log.Log;
import java.rmi.RemoteException;

/**
 * GetPosition; Gives the current (x,y,z) position.
 *
 * @author Malf
 * @version $Id: CmdPosition.java,v 1.11 2005/07/04 03:36:14 savio Exp $
 */


public class CmdPosition implements Command
{
    /* String thar represents the Command */
    public String cmdString = "gradient";

    /**
     * Class constructor.
     * Creates a new instance
     *
     */
    public CmdPosition()
    {
       //cmdString = dir;
    }

    /**
     * Get GetGradient the robot with impact
     *
     * @param robot Robot
     */
    private void CmdGetGradient(Robot robot)
    {
        /* Calculating the imapct time for the action */
        Impact impact;
        impact   = Game.finalImpact(robot, toString());
        int lag  = DefaultSleepTime.getGradientLag();
        lag += impact.getErrorInc();
        
        /* Calculating & Updating things*/
        try {
            Thread.sleep(lag*1000);
            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " FINISH to get Position.");
        }
        catch (RemoteException e) {
            System.err.println(e);
        }
        catch(InterruptedException ie) {
            System.err.println(ie);
        }
    }


    /**
     * Run method
     *
     * @param robot Robot
     */
    public void run(Robot robot)
    {
         CmdGetGradient(robot);
    }

    /**
     * toString method
     *
     * @return String Command String
     */
    public String toString()
    {
        return cmdString;
    }
}
