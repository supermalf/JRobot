package org.jrobot.game;

import java.rmi.server.ServerNotActiveException;
import java.rmi.RemoteException;

import org.jrobot.log.*;
import org.jrobot.ctrl.programmed.*;

/**
 * @author savio
 * @version $Id: RemoteGameImpl.java,v 1.21 2005/07/05 08:37:17 savio Exp $
 *
 * Game Data Access Object implementation.  
 *
 * This class provides  a wrapper that is exported  via RMI to whoever
 * wants to have access to the game kernel. The object works as a set of
 * game calls which tries to implement a direct access as secure as
 * possible.
 *
 **/

public class RemoteGameImpl extends java.rmi.server.UnicastRemoteObject 
    implements RemoteGame {

    /** 
     * Main game object. Private so users can only modify it by 
     * calling the calss methods.
     */
    private Game game;

    /**
     * RemoteGameImpl constructor. Called from org.jrobot.server.ServImpl.
     *
     * @param game a game instance
     */
    public RemoteGameImpl(Game game) throws RemoteException {
        super();
        this.game = game;
    }

    /**
     * Login
     *
     * @param playerName Player name
     * @param teamName   Team name
     * @param rgbColor   A float vector containing the 4 color parameters.
     * @return Player
     */
    public Player login(String playerName, String teamName, float[] rgbColor)
            throws RemoteException, ServerNotActiveException, GameException {
        String userSrc = getClientHost();
        return game.addPlayer(playerName, teamName, userSrc, rgbColor);
    }

    /**
     * Logout
     *
     * @param player Player
     * @return True if succeeded
     */
    public boolean logout(Player player) throws RemoteException, PlayerException {
        return game.delPlayer(player);
    }

    /**
     * Add Remote robot (also, a manual robot is binded as remote)
     *
     * @param robotName Robot name
     * @param player Player
     * @return True if succeeded
     */
    public boolean addRemoteRobot(String robotName, Player player) throws RemoteException {
        return game.addRemoteRobot(robotName, player);
    }

    /**
     * Add pre-programmed lua robot
     *
     * @param robotName Robot name
     * @param player Player
     * @param luaCode Lua code
     * @return True if succeeded
     */
    public boolean addLuaRobot(String robotName, Player player, String luaCode) throws RemoteException {
        boolean ret = false;
        try {
            ret = game.addLuaRobot(robotName, player, luaCode);
        } catch(LuaRobotException e) {
            Log.error("Could not create robot "+robotName+"; player "+player.getName());
        }
        return ret;
    }

    /**
     * Get number of allowed robots.
     *
     * @return Number of robots
     */
    public int getNRobots() throws RemoteException {
        return Game.getConfig().getNumbRobots();
    }

    /**
     * Get number of teams.
     *
     * @return Number of teams
     */
    public int getTeams() throws RemoteException {
        return Game.getConfig().getNumbTeams();
    }

    /**
     * Get Depth Map
     *
     * @return depth map
     */
    public float[][] getDepthMap() throws RemoteException {
        return Game.getDepthMap();
    }

    /**
     * Get map bounds
     *
     * @return Integer vector. {width,height}
     */
    public int[] getMapBounds() throws RemoteException {
        return Game.getMapBounds();
    }

    /**
     * Get cell size
     *
     * @return cell size
     */
    public int getCellSize() throws RemoteException {
        return Game.getCellSize();
    }

    /**
     * Get visual data
     *
     * @return Visual data
     */
    public GUIData getVisualData() throws RemoteException {
        return Game.getVisualData();
    }

    /**
     * Is game started
     *
     * @return True if started
     */
    public boolean isGameStarted() throws RemoteException {
        return Game.isGameStarted();
    }

}
