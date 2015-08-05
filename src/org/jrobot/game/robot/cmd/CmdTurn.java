package org.jrobot.game.robot.cmd;

import org.jrobot.game.robot.Robot;
import org.jrobot.game.proper.Impact;
import org.jrobot.game.Game;
import org.jrobot.log.Log;
import java.rmi.RemoteException;

/**
 * Turn; Turn in a given direction.
 *
 * @author Malf
 * @version $Id: CmdTurn.java,v 1.5 2005/07/05 08:37:17 savio Exp $
 *
 * TODO XXX Implementar colisão de Robôs
 */


public class CmdTurn implements Command
{
    /* String thar represents the Command */
    public String cmdTurnString;


    /**
     * Class constructor.
     * Creates a new instance of CmdTurn
     *
     * @param dir Recived String command
     */
    public CmdTurn(String dir)
    {
       cmdTurnString = dir;
    }

    /**
     * Turn Left the robot with impact
     *
     * @param robot Robot
     */
    private void CmdTurnLeft(Robot robot)
    {
        /* Calculating the imapct time for the action */
        Impact impact;
        impact   = Game.finalImpact(robot, toString());
        int lag  = DefaultSleepTime.getTurnLag();  
            lag += impact.getTimeInc();

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
            /* Calculating the Action */
            int angle = robot.getAngle();
            int x = robot.getWidth();
            int y = robot.getHeight();
            float z = robot.getDepth();
            int angle_new = angle;
            int x_new = x;
            int y_new = y;
            float z_new = z;


            if (angle == 270) {
                angle_new = 0;
            } else {
                angle_new = angle + 90;
            }

            /* Updating the Robot */
            robot.setAngle(angle_new);
            /* update visual data */
            Game.visualData.updateRobot(robot.getRName(), robot.getTeam().getColor(),
                                        robot.getWidth(), robot.getHeight(), robot.getAngle());

            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " FINISH to Turn Left.");
        }
        catch (RemoteException e)
        {
            System.err.println(e);
        }
    }

    /**
     * Turn Right the robot with impact
     *
     * @param robot Robot
     */
    private void CmdTurnRight(Robot robot)
    {
        /* Calculating the imapct time for the action */
        Impact impact;
        impact  = Game.finalImpact(robot, toString());
        int lag = impact.getTimeInc();

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
            /* Calculating the Action */
            int angle = robot.getAngle();
            int x = robot.getWidth();
            int y = robot.getHeight();
            float z = robot.getDepth();
            int angle_new = angle;
            int x_new = x;
            int y_new = y;
            float z_new = z;


            if (angle == 0) {
                angle_new = 270;
            } else {
                angle_new = angle - 90;
            }


            /* Updating the Robot */
            robot.setAngle(angle_new);

            /* Updating the Game */
            //Game.UpdateRobot();          /* XXX todo */
            /* update visual data */
            Game.visualData.updateRobot(robot.getRName(), robot.getTeam().getColor(),
                                        robot.getWidth(), robot.getHeight(), robot.getAngle());

            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " FINISH to Turn Right.");

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
        String left  = new String("l");
        String right = new String("r");

        /* Call Command Move Foward */
        if (left.equals(cmdTurnString))
        {
           CmdTurnLeft(robot);
        }

        /* Call Command Move Backward */
        else if (right.equals(cmdTurnString))
        {
            CmdTurnRight(robot);
        }
    }

    /**
     * toString method
     *
     * @return String Command String
     */
    public String toString()
    {
        if(cmdTurnString == "l")
            return "turnLeft";
        else
            return "turnRight";
    }
}


