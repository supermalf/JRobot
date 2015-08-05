package org.jrobot.game;

import java.rmi.*;
import java.rmi.server.*;

/**
 * @author savio	
 * @version $Id: RemoteGame.java,v 1.19 2005/07/05 08:37:17 savio Exp $
 */
public interface RemoteGame extends Remote {

    /**
     * Register player and login
     *
     * @param playerName Player name
     * @param teamName Team name
     * @param rgbColor A float vector containing the 4 color parameters.
     *
     * @return Player
     */
    public Player login(String playerName, String teamName, float[] rgbColor) 
        throws RemoteException, ServerNotActiveException, GameException;

    /**
     * Remove player from server and logout
     *
     * @param player Player
     *
     * @return Returns true if player was found and removed.
     */
    public boolean logout(Player player) throws RemoteException, PlayerException;

    /**
     * Add Remote robot (also, a manual robot is binded as remote)
     *
     * @param robotName Robot name
     * @param player Player
     * @return True if succeeded
     */
    public boolean addRemoteRobot(String robotName, Player player) throws RemoteException;

    /**
     * Add pre-programmed lua robot
     *
     * @param robotName Robot name
     * @param player Player
     * @param luaCode Lua code
     * @return True if succeeded
     */
    public boolean addLuaRobot(String robotName, Player player, String luaCode) throws RemoteException;


    /**
     * Get number of allowed robots.
     *
     * @return Number of robots
     */
    public int getNRobots() throws RemoteException;

    /**
     * Get number of teams.
     *
     * @return Number of teams
     */
    public int getTeams() throws RemoteException;
    
    /**
     * Get Depth Map
     *
     * @return depth map
     */
    public float[][] getDepthMap() throws RemoteException;

    /**
     * Get map bounds
     *
     * @return Integer vector. {width,height}
     */
    public int[] getMapBounds() throws RemoteException;

   /**
     * Get cell size
     *
     * @return cell size
     */
    public int getCellSize() throws RemoteException;

    /**
     * Get visual data
     *
     * @return Visual data
     */
    public GUIData getVisualData() throws RemoteException ;

    /**
     * Is game started
     *
     * @return True if started
     */
    public boolean isGameStarted() throws RemoteException;

}
