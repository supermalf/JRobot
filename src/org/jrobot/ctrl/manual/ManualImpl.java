package org.jrobot.ctrl.manual;

/**
 * Manual controller. This controller receives instructions passed by
 * the user from the GUI and sends it to a Robot object.
 *
 * @author Malf
 * @version $Id: ManualImpl.java,v 1.3 2005/07/05 02:12:37 savio Exp $
 */

import org.jrobot.game.robot.Robot;
import org.jrobot.log.Log;
import org.jrobot.ctrl.Action;
import org.jrobot.ogl.oglRenderer;

import java.rmi.RemoteException;


public class ManualImpl extends Thread
{
    /* Controlled Robot */
    private Robot controledRobot;

    /* List of avaliable command keys */
    private KeyList keyList;

    /* Flag to new key typed */
    private boolean keyTyped;

   /* Code of the last key typed */
    private int keyCode;

    /**
     * Class constructor.
     * Creates a new instance of manual driver implementation
     *
     * @param robot The instance of Robot
     */
    public ManualImpl(Robot robot)
    {
        /* Give the Robot to the controler */
        controledRobot = robot;

        /* Creating List of keys */
        keyList = new KeyList();

        /* Setting flag no Key Typed*/
        keyTyped = false;

        /* Setting Key Code*/
        keyCode = 0;
    }



    /**
     * Thread executes here
     */
    public void run()
    {
        while (true)
        {
           if(keyTyped)
           {
               KeyBind(keyCode);
               keyTyped = false;
           }
           try {
             Thread.sleep(100);
           } catch(InterruptedException e) {}
        }
    }


    /**
     * Key Typed
     *
     * @param key The code of the key typed
     */
    public void typeKey(int key)
    {
       keyCode  = key;
       keyTyped = true;
    }


    /**
     * Key Bind
     * Fetch the key and run the command action of the key.
     *
     * @param keyCode The code of the key
     */
    private void KeyBind(int keyCode)
    {
        /* Fetching command to the manual driver */
        Action keyaction = KeyList.KeyFetchCommand(keyCode);

        /* Executes command */
        if (keyaction != null) {
            keyaction.run(controledRobot);
            try {
                Thread.sleep(1000);

                while(!controledRobot.getReady()) {
                    /* 100 nao e' atoa! XXX */
                    Thread.sleep(100);
                }
            } catch(Exception e) {}
        }
        else
        {
            Log.error("Invalid Command!");
            return;
        }

        /* READY Traffic Lights */
        oglRenderer.manualReady = true;


        /* Getting the values */
        if(keyaction.toString() == "position")
        {
           returnPosition();
        }
        else if (keyaction.toString() == "pressure")
        {
           returnPressure();
        }
        else if (keyaction.toString() == "gradient")
        {
           returnGradient();
        }
        else if (keyaction.toString() == "time")
        {
           returnTime();
        }
        else if (keyaction.toString() == "prospect")
        {
           returnProspect();
        }
    }

     /**
     * Return Position
     * Fetch the value & print
     *
     */
    private void returnPosition()
    {
        try
        {
            /* Print in the Log / Screen */
            Log.game(controledRobot.getRName()+"@"+controledRobot.getTeamName()+
                     " - Position:["+controledRobot.getWidth()+","+ controledRobot.getHeight()+"]");

            /* Fetching */
            //Not Necessary
        }
        catch (RemoteException e)
        {
            System.err.println(e);
        }
    }

 /**
     * Return Pressure
     * Fetch the value & print
     *
     */
    private void returnPressure()
    {
        try
        {
            /* Print in the Log / Screen */
            Log.game(controledRobot.getRName()+"@"+controledRobot.getTeamName()+
                     " - Pressure:"+controledRobot.getPressure());

            /* Fetching */
            controledRobot.fetchPressure();
        }
        catch (RemoteException e)
        {
            System.err.println(e);
        }
    }

     /**
     * Return Gradient
     * Fetch the value & print
     *
     */
    private void returnGradient()
    {
        try
        {
            /* Print in the Log / Screen */
            Log.game(controledRobot.getRName()+"@"+controledRobot.getTeamName()+
                     " - Gradient:["+controledRobot.getGradient().getAxial()+","+
                     controledRobot.getGradient().getNormal()+"]");

            /* Fetching */
            controledRobot.fetchGradient();
        }
        catch (RemoteException e)
        {
            System.err.println(e);
        }
    }

    /**
     * Return Time
     * Fetch the value & print
     *
     */
    private void returnTime()
    {
        try
        {
            /* Print in the Log / Screen */
            Log.game(controledRobot.getRName()+"@"+controledRobot.getTeamName()+
                     " - Time:"+controledRobot.getTime());

            /* Fetching */
            //Not Necessary?
        }
        catch (RemoteException e)
        {
            System.err.println(e);
        }
    }


     /**
     * Return Gradient
     * Fetch the value & print
     *
     */
    private void returnProspect()
    {
        try
        {
            /* Print in the Log / Screen */
           // Log.game(controledRobot.getRName()+"@"+controledRobot.getTeamName()+
           //          " - Prospect:/"+controledRobot.prospect);        /* todo XXX*/

            /* Fetching */
            //Not Necessary?
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }


}



