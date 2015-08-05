package org.jrobot.tst;

import org.jrobot.game.robot.RobotImpl;
import org.jrobot.game.robot.RobotThread;
import org.jrobot.game.robot.Robot;
import org.jrobot.game.*;
import org.jrobot.server.ServImpl;
import org.jrobot.server.Serv;

import java.io.IOException;
import java.rmi.Naming;


public class RobotMain {

    public static void main(String[] args) throws Exception {
        String gameName = new String(GameUtils.getGameName());
        RemoteGame game = (RemoteGame) Naming.lookup("rmi://localhost/" + gameName);

        Serv serv;
        serv = new ServImpl(GameUtils.getDefLogPath());

        Team rteam = new Team("team",null);

        try {
            Robot robo1 = new RobotImpl("diogo1", rteam, 0, 0, 0, 0);
            Robot robo2 = new RobotImpl("diogo2", rteam, 0, 0, 0, 0);

            RobotThread robot1 = new RobotThread(robo1);
            RobotThread robot2 = new RobotThread(robo2);

            robot1.start();
            robot2.start();


        } catch (Exception e) {
            System.out.println("error : " + e);

        }

    }
}
