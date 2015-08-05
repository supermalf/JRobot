package org.jrobot.game.robot.cmd;

import org.jrobot.game.robot.Robot;
import org.jrobot.game.proper.Impact;
import org.jrobot.game.Game;
import org.jrobot.log.Log;
import java.rmi.RemoteException;


/**
 * Move; Move in a given direction.
 *
 * @author Malf
 * @version $Id: CmdMove.java,v 1.13 2005/07/05 03:22:54 savio Exp $
 *
 * TODO XXX Implementar colisão de Robôs
 */


public class CmdMove implements Command
{
    /* String thar represents the Command */
    public String cmdMoveString;


    /**
     * Class constructor.
     * Creates a new instance
     *
     * @param dir Recived String command
     */
    public CmdMove(String dir)
    {
       cmdMoveString = dir;
    }

    /**
     * Move foward the robot with impact
     *
     * @param robot Robot
     */
    private void CmdMoveFoward(Robot robot)
    {
        /* Calculating the imapct time for the action */
        Impact impact;
        impact   = Game.finalImpact(robot, toString());
        int lag  = DefaultSleepTime.getMoveLag();     
            lag += impact.getTimeInc();

        /* Calculating & Updating things*/
        try
        {
            /* Calculating the Action */
            int angle = robot.getAngle();
            int x = robot.getWidth();
            int y = robot.getHeight();
            float z = robot.getDepth();
            int x_new = x;
            int y_new = y;
            float z_new = z;

            if (angle == 0) {
                x_new = x + 1;
                y_new = y;
            } else if (angle == 90) {
                x_new = x;
                y_new = y + 1;
            } else if (angle == 180) {
                x_new = x - 1;
                y_new = y;
            } else if (angle == 270) {
                x_new = x;
                y_new = y - 1;
            } else {
                Log.bug("Wrong value found for angle, robot: "+ robot.getRName());
            }
            
            /* check collision */
            int[] mapBound = Game.getMapBounds();
            
            if(x_new > mapBound[0] || x_new < 0 || 
               y_new > mapBound[1] || y_new < 0) {
                /* invalid coordinate */
                return;
            } else if(Game.getRobotPosition(x_new, y_new) != 0) {
                /* cell already busy */
                return;
            }
            
            Thread.sleep(lag*1000);
            
            /* If everything is ok then just commit the movement */

            robot.setWidth(x_new);
            robot.setHeight(y_new);

            Game.setRobotPosition(x,y,0);
            Game.setRobotPosition(x_new,y_new,0);
            
            /* update visual data */
            Game.visualData.updateRobot(robot.getRName(), robot.getTeam().getColor(), 
                                        robot.getWidth(), robot.getHeight(), robot.getAngle());

            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " FINISH Move Foward.");

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
     * Move backward the robot with impact
     *
     * @param robot Robot
     */
    private void CmdMoveBackward(Robot robot)
    {
        /* Calculating the imapct time for the action */
        Impact impact;
        impact  = Game.finalImpact(robot, toString());
        int lag = impact.getTimeInc();

        /* Calculating & Updating things*/
        try
        {
            /* Calculating the Action */
            int angle = robot.getAngle();
            int x = robot.getWidth();
            int y = robot.getHeight();
            float z = robot.getDepth();
            int x_new = x;
            int y_new = y;
            float z_new = z;

            if (angle == 0) {
                x_new = x - 1;
                y_new = y;
            } else if (angle == 90) {
                x_new = x;
                y_new = y - 1;
            } else if (angle == 180) {
                x_new = x + 1;
                y_new = y;
            } else if (angle == 270) {
                x_new = x;
                y_new = y + 1;
            } else {
                Log.bug("Wrong value found for angle, robot: "+ robot.getRName());
            }
            
            /* check collision */
            int[] mapBound = Game.getMapBounds();
            
            if(x_new > mapBound[0] || x_new < 0 || 
               y_new > mapBound[1] || y_new < 0) {
                /* invalid coordinate */
                return;
            } else if(Game.getRobotPosition(x_new, y_new) != 0) {
                /* cell already busy */
                return;
            }
            
            Thread.sleep(lag*1000);
            
            /* If everything is ok then just commit the movement */

            robot.setWidth(x_new);
            robot.setHeight(y_new);

            Game.setRobotPosition(x,y,0);
            Game.setRobotPosition(x_new,y_new,0);
            
            /* update visual data */
            Game.visualData.updateRobot(robot.getRName(), robot.getTeam().getColor(), 
                                        robot.getWidth(), robot.getHeight(), robot.getAngle());
            /* Logging... */
            Log.debug("Robot " + robot.getRName() + " FINISH Move Backward.");
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
        String forward = new String("f");
        String backward = new String("b");

        /* Call Command Move Foward */
        if (forward.equals(cmdMoveString))
        {
           CmdMoveFoward(robot);
        }

        /* Call Command Move Backward */
        else if (backward.equals(cmdMoveString))
        {
            CmdMoveBackward(robot);
        }
    }

    /**
     * toString method
     *
     * @return String Command String
     */
    public String toString()
    {
        if(cmdMoveString == "f")
            return "moveForward";
        else
            return "moveBackward";
    }
}
