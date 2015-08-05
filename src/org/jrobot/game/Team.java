package org.jrobot.game;

/**
 * Game Teams
 *
 * @author savio
 * @version $Id: Team.java,v 1.11 2005/07/05 08:37:17 savio Exp $
 */

public class Team implements java.io.Serializable {

    /**
     * RGB Color
     */
    protected float[] rgbColor;

    /**
     * Team Name
     */
    protected String teamName;

    /**
     * Player that leads this team
     */
    protected Player player;

    /**
     * Team score
     */
    protected int score;

    /**
     * Class constructor
     *
     * @param name  Team Name
     * @param color RGB Color
     */
    public Team(String name, float[] color) {
        teamName = name;
        rgbColor = color;
    }

    /*
     * Class constructor
     *
     * @param name Team Name
     * @param color RGB Color
     * @param pl Player
     */
    public Team(String name, float[] color, Player pl) {
        teamName = name;
        rgbColor = color;
        player = pl;
    }

    /**
     * Get color
     *
     * @return RGB Color
     */
    public float[] getColor() {
        return rgbColor;
    }

    /**
     * Get name
     *
     * @return Team Name
     */
    public String getName() {
        return teamName;
    }

    /**
     * Get player
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set player
     *
     * @param _player Player
     */
    public void setPlayer(Player _player) {
        player = _player;
    }

    /**
     * Set name
     *
     * @param _teamName Team Name
     */
    public void setName(String _teamName) {
        teamName = _teamName;
    }

    /**
     * Set color
     *
     * @param _rgbColor RGB Color
     */
    public void getColor(float[] _rgbColor) {
        rgbColor = _rgbColor;
    }

    /**
     * Is equals Player object?
     *
     * @param pl Player
     */
    public boolean equalsPlayer(Player pl) {
        return pl.equals(player);
    }

    /**
     * Since rgbColor is an ID for this class, compare()
     * is implemented using it as key. Also, this implementation
     * depends on the
     *
     * @param _team Team
     * @return True if they are equal
     */
    public boolean compare(Team _team) {
        for (int i = 0; i < 3; i++) {
            if (rgbColor[i] != _team.rgbColor[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Get score
     *
     * @return Score
     */
    public int getScore() {
        return score;
    }

    public void addScore(int n) {
        score += n;   
    }
}
