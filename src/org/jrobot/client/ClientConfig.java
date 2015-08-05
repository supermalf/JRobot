package org.jrobot.client;

/**
 * Cient Configurations
 *
 * @author Tucano
 * @version $Id: ClientConfig.java,v 1.2 2005/07/04 22:20:22 savio Exp $
 */

public class ClientConfig
{

    protected String hostname;
    protected String playername;
    protected String teamname;
    protected float[] color;
    protected String[] robotNames;
    protected String[] roboType;

    /**
     * Class constructor.
     * Creates a new instance of client configuration
     *
     * @param _hostname     Server's IP Address
     * @param _playername   Player's name
     * @param _teamname     Team's name
     * @param _color        Team's color
     */
    public ClientConfig(String _hostname, String _playername, String _teamname, float[] _color)
    {
        hostname    = _hostname;
        playername  = _playername;
        teamname    = _teamname;
        color       = _color;
    }

    /**
     * Get host name
     *
     * @return A string with the host name
     */
    public String getHostName() {
        return hostname;
    }

    /**
     * Get player name
     *
     * @return A string with the player name
     */
    public String getPlayerName() {
        return playername;
    }

    /**
     * Get team name
     *
     * @return A string with the team name
     */
    public String getTeamName() {
        return teamname;
    }

    /**
     * Get color
     *
     * @return A float[] with the color
     */
    public float[] getColor() {
        return color;
    }

    /**
     * Get Robot Name
     *
     * @return A string with the robot's name
     */
    public String getRobotName(int i) {
        return robotNames[i];
    }

    /**
     * Get Robot type
     *
     * @return A string with the robot's type
     */
    public String getRobotType(int i) {
        return roboType[i];
    }

     /**
     * Get All Robot Names
     *
     * @return A string with all the robot's names
     */
    public String[] getRobotName() {
        return robotNames;
    }

    /**
     * Get All Robot type
     *
     * @return A string with all the robot's types
     */
    public String[] getRobotType() {
        return roboType;
    }


    /**
     * Set host name
     *
     * @param _hostname A string with the host name
     */
    public void setHostName(String _hostname) {
        hostname = _hostname;
    }

    /**
     * Set player name
     *
     * @param _playername A string with the player name
     */
    public void setPlayerName(String _playername) {
        playername = _playername;
    }

    /**
     * Set team name
     *
     * @param _teamname A string with the team name
     */
    public void setTeamName(String _teamname) {
        teamname = _teamname;
    }

    /**
     * Set color
     *
     * @param _color A float[] with the color
     */
    public void setColor(float[] _color) {
        color = _color;
    }

    /**
     * Set Robots's Name
     *
     * @param _RobotName A string[] with the robots's name
     */
    public void setRobotName(String[] _RobotName) {
        robotNames = _RobotName;
    }

    /**
     * Set Robots's type
     *
     * @param _RobotType A string with the robots's type
     */
    public void setRobotType(String[] _RobotType) {
        roboType = _RobotType;
    }

}
