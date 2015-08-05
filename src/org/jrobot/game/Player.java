package org.jrobot.game;

import java.util.*;
import java.lang.*;

import org.jrobot.game.time.*;
import org.jrobot.log.*;

/**
 * Player; this class  handles player name, IP address  and Team.  all
 * operations check this object first to be sure the client is dealing
 * with  the  right   accesses.  The  IP  address  is   a  client  key
 * (hopefully).
 *
 * TODO: checar problemas como jogadores numa mesma rede, mascarados com
 * ip igual?! (possivel?) XXX
 *
 * @author savio
 * @version $Id: Player.java,v 1.18 2005/07/05 04:56:34 savio Exp $
 */

public class Player implements java.io.Serializable {

    /**
     * Player's name
     */
    protected String name;

    /**
     * Player's host
     */
    protected String host;

    /**
     * Player team
     */
    protected Team team;

    /**
     * Player password
     */
    protected String password;

    /**
     * Class constructor.
     * May only be called from outside org.jrobot.game package.
     *
     */
    public Player() {
        name = null;
        host = null;
        team = null;
        password = null;
    }

    /**
     * Class constructor
     *
     * @param _name Player's name
     * @param _host Identification, usually a IP address.
     */
    protected Player(String _name, String _host) {
        name = _name;
        host = _host;
    }

    /**
     * Class constructor
     *
     * @param _name Player's name
     * @param _host Identification, usually a IP address.
     * @param _team Player's team
     */
    protected Player(String _name, String _host, Team _team) {
        name = _name;
        host = _host;
        team = _team;
    }

    /**
     * Get player's name
     *
     * @return Player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get player's hostname
     *
     * @return Player's hostname
     */
    public String getHost() {
        return host;
    }

    /**
     * Get password
     *
     * @return Player's password
     */
    public String getPass() {
        return password;
    }

    /**
     * Get team
     *
     * @return Team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Set player's name
     *
     * @param _name player's name
     */
    public void setName(String _name) {
        name = _name;
    }

    /**
     * Set player's hostname
     *
     * @param _host player's hostname
     */
    public void setHost(String _host) {
        host = _host;
    }

    /**
     * Set password
     *
     * @param _pass User defined password
     */
    public void setPass(String _pass) {
        password = _pass;
    }

    /**
     * Set a team
     *
     * @param _team Team
     */
    public void setTeam(Team _team) {
        team = _team;
    }


    /**
     * Set a random password to player
     */
    public void setRandomPass() {
        Random rand = new Random();
        int len = 10 + Math.abs((rand.nextInt() % 10));
        char[] data = new char[len];

        for (int i = 0; i < len; i++) {
            data[i] = GameUtils.intToChar(rand.nextInt(62));
        }
        password = new String(data);
    }

    /**
     * Compares Player's name and ip addr (nice simple algorithm :P)
     *
     * @param object      Player object to be compared to this
     * @param compareTeam if true, tries to compare protected object Team too.
     * @return true is they are equal
     */
    public boolean compare(Player object, boolean compareTeam) {
        if (object.name.equals(name) && object.host.equals(host)) {
            if (compareTeam && object.team == team) {
                return true;
            } 
            return !compareTeam; // funciona? XXX
        }
        return false;
    }

    /**
     * Mirror. This function is almost like clone(), except that it does not
     * return an object; it copies all data from srcPlayer to the calling object.
     *
     * @param srcPlayer Source object from where the object gets all information
     */
    protected void mirror(Player srcPlayer) {
        if (srcPlayer != null) {
            password = srcPlayer.getPass();
            team = srcPlayer.getTeam();
            name = srcPlayer.getName();
            host = srcPlayer.getHost();
        }
    }
}
