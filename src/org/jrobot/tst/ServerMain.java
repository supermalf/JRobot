/*
 * @author Malf
 * @version $Id: ServerMain.java,v 1.13 2005/07/05 04:56:35 savio Exp $
 */

package org.jrobot.tst;

import java.io.*;

import org.jrobot.server.*;
import org.jrobot.game.*;
import org.jrobot.gui.ConnectDialog;
import org.jrobot.JRobot;
import org.jrobot.client.ClientConfig;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        String[] dynPropers = new String[2];

        dynPropers[0] = new String("Pressure");
        dynPropers[1] = new String("Noise");


        GameConfig cfg = new GameConfig();
        //ClientConfig client = new ClientConfig("localhoast", "MALF", "TIMAO", null);

        cfg.setProperList(dynPropers);
        Serv serv;
        serv = new ServImpl(GameUtils.getDefLogPath());
        
        // order game start
        Thread.sleep(60*1000);
        System.out.println("starting game...");
        serv.startGame();

        while(true)
        {
            Thread.sleep(10);
        }
        //serv = new ServImpl("log.txt");
        //ConnectDialog a = new ConnectDialog();    //fields
        //a.show();
    }
}
