package org.jrobot.game.robot.cmd;

import org.jrobot.game.robot.Robot;
import org.jrobot.game.proper.Impact;
import org.jrobot.game.Game;
import org.jrobot.game.Gradient;
import org.jrobot.log.Log;
import java.rmi.RemoteException;


/**
 * GetProspect; prospect oil.
 *
 * @author savio
 * @version $Id: CmdProspect.java,v 1.9 2005/07/05 08:37:17 savio Exp $
 */


public class CmdProspect implements Command
{
    /* String thar represents the Command */
    public String cmdString = "prospect";


    /**
     * Class constructor.
     * Creates a new instance of CmdGradient
     *
     */
    public CmdProspect()
    {
       //cmdString = dir;
    }

    /**
     * Prospect with impact
     *
     * @param robot Robot
     */
    private void CmdGetProspect(Robot robot)
    {
        /* Calculating the imapct time for the action */
        Impact impact;
        impact   = Game.finalImpact(robot, toString());
        int lag  = DefaultSleepTime.getProspectLag();
            lag += impact.getErrorInc();

        /* Calculating & Updating things*/
        try
        {
            int x = robot.getWidth();
            int y = robot.getHeight();
            
            if(Game.getFlagPosition(x, y) != 0) {
                return;
            } 

            Thread.sleep(lag*1000);

            float val = Game.getPropValue("Pressure", x, y);
            int score = (int)(val*100);
            Log.debug("prospected: "+score);  //XXX
            robot.getTeam().addScore(score);
            //Game.score(robot.getTeam(), score);
            robot.score(score);
            Game.setPressureValue(x, y, 0.0f);
            Game.setFlagPosition(x, y, 1);

            /* Update visual data */
            Game.visualData.updateScore(robot.getTeam().getName(), robot.getTeam().getColor(),
                                        Game.getScore(robot.getTeam()));
            Game.visualData.addFlag(robot.getWidth(), robot.getHeight(), 
                                    robot.getTeam().getColor());

            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " FINISH to Prospect oil.");
        }
        catch (RemoteException e)
        {
            System.err.println(e);
        }
        catch(InterruptedException ie)
        {
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
         CmdGetProspect(robot);
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
