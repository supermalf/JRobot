package org.jrobot.game.robot;

import org.jrobot.game.robot.cmd.CommandList;
import org.jrobot.game.robot.cmd.Command;

import java.rmi.RemoteException;

/**
 * Robot thread.
 *
 * @author savio
 * @version $Id: RobotThread.java,v 1.8 2005/07/05 03:22:54 savio Exp $
 */

public class RobotThread extends Thread implements java.io.Serializable {
    public Robot robot;
    public String commandString;
    public CommandList commandList;

    /**
     * Class constructor
     *
     * @param _robot Robot
     */
    public RobotThread(Robot _robot)
    {
        /* Instance of the Robot Thread*/
        robot = _robot;

        /* Setting  the Command String no thread process value */
        commandString = "-";

        try
        {
            _robot.setThread(this);
            robot.setReady(true);
            commandList = new CommandList();
        }
        catch(RemoteException e)
        {
            System.err.println(e);
        }
    }

    /**
     * Thread executes here
     */
    public void run()
    {
        /* Keep the robot thread alive */
        while (true)
        {
            try
            {
                if (commandString.equals("-"))
                {
                    Thread.sleep(10);                    
                    //robot.setReady(true);
                }
                else
                {
                    robot.setReady(false);
                    /* Getting the command to execute */
                    Command command = commandList.cmdGet(commandString);

                    if (command != null)
                        command.run(robot);

                    /* Clear the thread process */
                    commandString = "-";
                    robot.setReady(true);
                }
            }
            catch(Exception e)
            {
                System.err.println(e);

            }
       }
    }

    /**
     * Set command string
     *
     * @param command Command string
     * @return True if succeeded
     */
    public boolean setCommand(String command) {
        if(!commandString.equals("-")) {
            return false;
        }
        commandString = command;
        return true;
    }

    /**
     * Clear command string
     */
    private void clearCommand() {
        commandString = "-";
    }

    /**
     * Get robot
     */
    public Robot getRobot() {
        return robot;
    }
}
