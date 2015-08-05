package org.jrobot;

import org.jrobot.game.*;
import org.jrobot.server.Serv;
import org.jrobot.server.ServImpl;
import org.jrobot.client.ClientConfig;
import org.jrobot.ctrl.manual.ManualImpl;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * JRobot.java
 * JRobot main class.
 *
 * @author $Author: savio $
 * @version $Id: JRobot.java,v 1.5 2005/07/05 08:37:18 savio Exp $
 */


public class JRobot
{
    /* Game Configuration*/
    public static GameConfig cfg;

    /* Creating Server */
    public static Serv serv;

    /* Creating Client */
    public static ClientConfig client;

    /* Creating Player */
    public static Player player;

    /* Manual Controler */
    public static ManualImpl manualControl;

    /* Remote Game */
    public static RemoteGame remoteGame;

    /* GUI DATA */
    public static GUIData guidata;

           

    public static void main(String[] args) throws IOException
    {

        /* Creating GUI */
        new GUILoad().load();

        /* Game Configuration*/
        //GameConfig cfg;// = new GameConfig();

        /* Creating Server */
        //Serv serv;// = new ServImpl(GameUtils.getDefLogPath());

        /* Creating Client */
        //ClientConfig client;

    }


}

 /*

        String[] dynPropers = new String[2];
        dynPropers[0] = new String("Pressure");
        dynPropers[1] = new String("Noise");

        GameConfig cfg = new GameConfig();

        cfg.setProperList(dynPropers);
        Serv serv;
        serv = new ServImpl(GameUtils.getDefLogPath());

        // order game start
        serv.startGame();

        while(true){}

*/