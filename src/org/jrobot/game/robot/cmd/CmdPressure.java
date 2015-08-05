package org.jrobot.game.robot.cmd;

import org.jrobot.game.robot.Robot;
import org.jrobot.game.proper.Impact;
import org.jrobot.game.Game;
import org.jrobot.game.Gradient;
import org.jrobot.log.Log;
import java.rmi.RemoteException;

/**
 * GetPressure; Gives pressure in float.
 *
 * @author savio
 * @version $Id: CmdPressure.java,v 1.12 2005/07/04 03:36:14 savio Exp $
 */


public class CmdPressure implements Command
{
    /* String thar represents the Command */
    public String cmdString = "pressure";

    /**
     * Class constructor.
     * Creates a new instance
     *
     */
    public CmdPressure()
    {
       //cmdString = dir;
    }

    /**
     * Get Pressure the robot with impact
     *
     * @param robot Robot
     */
    private void CmdGetPressure(Robot robot)
    {
        /* Calculating the imapct time for the action */
        Impact impact = Game.finalImpact(robot, toString());
        int lag  = DefaultSleepTime.getPressureLag();
        lag += impact.getErrorInc();
        
        /* Calculating & Updating things*/
        try {
            float err = impact.getErrorInc();
            float val = Game.getPropValue("Pressure",robot.getHeight(),robot.getWidth());
            val *= (1.0f - err);            
            robot.setPressure(val);

            Thread.sleep(lag*1000);
            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " FINISH to get Pressure.");
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
         CmdGetPressure(robot);
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
