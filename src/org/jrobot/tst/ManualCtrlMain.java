package org.jrobot.tst;

/**
 * manual Contoler test
 *
 * @author Malf
 * @version $Id: ManualCtrlMain.java,v 1.5 2005/07/04 03:36:15 savio Exp $
 */

import org.jrobot.ctrl.manual.ManualImpl;
import org.jrobot.game.robot.RobotImpl;
import org.jrobot.game.robot.RobotThread;
import org.jrobot.game.Team;
import org.jrobot.game.GameUtils;
import org.jrobot.server.Serv;
import org.jrobot.server.ServImpl;

import java.awt.event.KeyEvent;
import java.io.IOException;


public class ManualCtrlMain
{
    public static ManualImpl manualControl;

    public static void main(String[] args) throws IOException
    {
        Serv serv;
        serv = new ServImpl(GameUtils.getDefLogPath());

        Team rteam = new Team("team",null);
                             //rname, Team , width, height, depth, angle
        RobotImpl robo1    = new RobotImpl("Robo1", rteam, 3, 3, 0.9f, 180);
        RobotThread robot1 = new RobotThread(robo1);
        robot1.start();


        manualControl = new ManualImpl(robo1);
        manualControl.start();

        manualControl.typeKey(KeyEvent.VK_UP);   //Move Forward
        //ManualControl.typeKey(KeyEvent.VK_G);   //Get Gradient
        //ManualControl.typeKey(KeyEvent.VK_L);   //Get Position


        //a.KeyBind(KeyEvent.VK_L);           //Get Position
        //a.KeyBind(KeyEvent.VK_P);           //Get Pressure
        //a.KeyBind(KeyEvent.VK_SPACE);       //Get Prospect
        //a.KeyBind(KeyEvent.VK_T);           //Get Time
        //a.KeyBind(KeyEvent.VK_G);           //Get Gradient
        //a.KeyBind(KeyEvent.VK_UP);          //Move Forward
        //a.KeyBind(KeyEvent.VK_DOWN);        //Move Backward
        //a.KeyBind(KeyEvent.VK_LEFT);        //Turn Left
        //a.KeyBind(KeyEvent.VK_RIGHT);       //Turn Right

    }
}
