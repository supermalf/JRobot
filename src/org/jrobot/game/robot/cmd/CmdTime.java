package org.jrobot.game.robot.cmd;

import org.jrobot.game.robot.Robot;
import org.jrobot.game.proper.Impact;
import org.jrobot.game.Game;
import org.jrobot.log.Log;
import java.rmi.RemoteException;

/**
 * GetTime; Current time in seconds since game start.
 *
 * @author savio
 * @version $Id: CmdTime.java,v 1.10 2005/07/04 03:36:14 savio Exp $
 */


public class CmdTime implements Command
{
    /* String thar represents the Command */
    public String cmdString = "time";


    /**
     * Class constructor.
     * Creates a new instance
     *
     */
    public CmdTime()
    {
       //cmdString = dir;
    }

    /**
     * Get Time with impact
     *
     * @param robot Robot
     */
    private void CmdGetTime(Robot robot)
    {
        /* Calculating the imapct time for the action */
        Impact impact;
        impact   = Game.finalImpact(robot, toString());
        int lag  = DefaultSleepTime.getTimeLag();
            lag += impact.getErrorInc();

        /* Set Lag for the Action - Sleep Thread */
        try
        {
            Thread.sleep(lag*1000);
        }
        catch(InterruptedException ie)
        {
            System.err.println(ie);
        }

        /* Calculating & Updating things*/
        try
        {
            /* Updating the Robot */
            robot.setTime(Game.getElapsedTime());
            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " FINISH to get Time.");

        }
        catch (RemoteException e)
        {
            System.err.println(e);
        }
    }

    /**
     * Run method
     *
     * @param robot Robot
     */
    public void run(Robot robot)
    {
         CmdGetTime(robot);
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
