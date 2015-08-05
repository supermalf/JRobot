/* XXX thread must be implemented here. (ServImpl calls exit()) */
package org.jrobot.server;

import java.rmi.*;
import java.rmi.server.*;

import org.jrobot.log.*;
import org.jrobot.game.*;
import org.jrobot.gui.ConnectDialog;
import org.jrobot.ogl.oglRenderer;
import org.jrobot.JRobot;

/**
 * Standard JRobot server implementation.
 *
 * @author savio
 * @version $Id: ServImpl.java,v 1.18 2005/07/05 08:37:18 savio Exp $
 */
public class ServImpl implements Serv {

    /**
     * Game configuration object
     */
    private static GameConfig gameCfg;

    /**
     * Game object
     */
    private static Game game;

    /**
     * Game data access object, used as a server/client `man-in-the-middle'
     */
    private static RemoteGame remoteGame;


    public static boolean serverStarted = false;

    /**
     * Class constructor
     *
     * @param gameCfg given game configuration. server starts this game.
     * @param logFile default log file (null accepted).
     * @throws RemoteException If some connection problem occurs
     */
    public ServImpl(GameConfig gameCfg, String logFile) throws RemoteException {
        if (logFile != null) {
            ServUtils.startLog(logFile);
        }

        game = new Game(gameCfg);
        remoteGame = new RemoteGameImpl(game);

        try {
            Log.debug("Registering game as: " + GameUtils.getGameName());
            Naming.rebind(GameUtils.getGameName(), remoteGame);
            Log.game("Server is up and running!");
            if (GameUtils.consoleMode() == false) {
                //javax.swing.JOptionPane.showMessageDialog(null, "Server Started!");
            } else {
                System.out.println("Server Started!");
            }
            serverStarted = true;
        } catch (Exception e) {
            Log.error("Could not start server : " + e);
            e.printStackTrace();
            serverStarted = false;
            GameUtils.handleExit();
        }
    }

    /**
     * Class constructor
     *
     * @param logFile default log file (null accepted).
     * @throws RemoteException If some connection problem occurs
     */
    public ServImpl(String logFile) throws RemoteException {
        if (logFile != null) {
            ServUtils.startLog(logFile);
        }

        game = new Game(new GameConfig());
        remoteGame = new RemoteGameImpl(game);

        try {
            Naming.rebind(GameUtils.getGameName(), remoteGame);
            Log.game("Server is up and running!");
            if (GameUtils.consoleMode() == false) {
                //javax.swing.JOptionPane.showMessageDialog(null, "Server Started!");
            } else {
                System.out.println("Server Started!");
            }
            serverStarted = true;

        } catch (Exception e) {
            Log.error("Could not start server : " + e);
            e.printStackTrace();
            serverStarted = false;
            GameUtils.handleExit();
        }
    }

    /**
     * Unbind and unexport object from rmi registry, close log file
     * and exit.
     */
    public void shutdown() {
        try {
            Naming.unbind(GameUtils.getGameName());
            UnicastRemoteObject.unexportObject(remoteGame, true);
            Game.endGame();
            game = null;
            Log.game("Exiting JRobot server...");
            Log.close();
            serverStarted = false;
            //GameUtils.handleExit();
        } catch (Exception e) {
            Log.error("Error shutting down server : " + e);
            GameUtils.handleExit();
        }
    }

    /**
     * Start game
     */
    public void startGame() {
        if(game == null) {
            Log.debug("Attempting to start game with null object");
            return;
        }
        game.startGame();
        
        if(JRobot.manualControl != null)
            oglRenderer.manualReady = true;
    }

    /**
     * Game started?
     */
    public boolean isGameStarted() {
        return Game.isGameStarted();
    }
}
