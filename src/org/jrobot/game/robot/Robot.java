package org.jrobot.game.robot;

import org.jrobot.game.Team;
import org.jrobot.game.Gradient;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Robot interface
 *
 * @author savio
 * @version $Id: Robot.java,v 1.15 2005/07/04 03:36:14 savio Exp $
 */

public interface Robot extends Remote {

    /**
     * Get robot's name
     *
     * @return Robot's name
     */
    public String getRName() throws RemoteException;

    /**
     * Get team
     *
     * @return Robot's team
     */
    public Team getTeam() throws RemoteException;

     /**
     * Get team name
     *
     * @return Robot's team name
     */
    public String getTeamName() throws RemoteException;

    /**
     * Get width
     *
     * @return Robot's width
     */
    public int getWidth() throws RemoteException;

    /**
     * Get height
     *
     * @return Robot's height
     */
    public int getHeight() throws RemoteException;

    /**
     * Get depth
     *
     * @return Robot's depth
     */
    public float getDepth() throws RemoteException;

    /**
     * Get angle
     *
     * @return Robot's angle
     */
    public int getAngle() throws RemoteException;

    /**
     * Get Gradient
     *
     * @return Robot's gradient
     */
    public Gradient getGradient() throws RemoteException;

    /**
     * Get Pressure
     *
     * @return Robot's pressure
     */
    public float getPressure() throws RemoteException;

    /**
     * Get Time
     *
     * @return Game's last time
     */
    public int getTime() throws RemoteException;

   /**
    * Get Ready
    *
    * @return Ready flag
    */
   public boolean getReady() throws RemoteException;

    /**
     * Set name
     *
     * @param rname Robot's name
     */
    public void setRName(String rname) throws RemoteException;

    /**
     * Set team
     *
     * @param rteam Robot's team
     */
    public void setTeam(Team rteam) throws RemoteException;

    /**
     * Set width
     *
     * @param rwidth Robot's width
     */
    public void setWidth(int rwidth) throws RemoteException;

    /**
     * Set height
     *
     * @param rheight Robot's height
     */
    public void setHeight(int rheight) throws RemoteException;

    /**
     * Set depth
     *
     * @param rdepth Robot's depth
     */
    public void setDepth(int rdepth) throws RemoteException;

    /**
     * Set angle
     *
     * @param rangle Robot's angle
     */
    public void setAngle(int rangle) throws RemoteException;

    /**
     * Set Gradient
     *
     */
    public void setGradient() throws RemoteException;

    /**
     * Set Pressure
     *
     * @param rpressure Robot's pressure
     */
    public void setPressure(float rpressure) throws RemoteException;

    /**
     * Get Time
     *
     * @param rtime Game's last time
     */
    public void setTime(int rtime) throws RemoteException;


    /**
     * Set parent thread
     *
     * @param _thread Thread
     */
    public void setThread(RobotThread _thread) throws RemoteException;

    /**
     * Get parent thread
     *
     * @return Thread
     */
    public RobotThread getThread() throws RemoteException;

    /**
     * Set command to be executed
     *
     * @param command Command string
     * @return True if succeeded
     */
    public boolean setCommand(String command) throws RemoteException;

    /**
     * Fetch GetGradient
     *
     * @return Robot's gradient
     */
    public Gradient fetchGradient() throws RemoteException;

    /**
     * Fetch Pressure
     *
     * @return Robot's pressure
     */
    public float fetchPressure() throws RemoteException;


   /**
    * Set Ready
    *
    * @param flag Ready
    */
   public void setReady(boolean flag) throws RemoteException;

   /**
    * Score
    *
    * @param nInc Increment
    */
    public void score(int nInc) throws RemoteException;
}
