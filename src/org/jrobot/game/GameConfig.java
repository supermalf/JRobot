package org.jrobot.game;

/**
 * Game Configurations
 * <p/>
 * The default configuration is:
 * Lua map file.....: mapFile = "default.lua"
 * Default game name: gameName = "JRobot v2.0"
 * Property list....: properList = null
 * Max numb of teams: nTeams = 2
 * Number of robots.: nRobots = 5
 * Default game time: gameTime = 3600
 * Allow manual Eng?: allowManual = true
 *
 * @author savio
 * @version $Id: GameConfig.java,v 1.19 2005/07/05 01:00:53 savio Exp $
 */

/*
 * TODO: implementar um gancho com  as strings das classes que vao ser
 * carregadas  antes do jogo  comecar. esses  nomes sao  passados pela
 * GUI.
 */

public class GameConfig {
    /* default configuration */
    private static String mapFile = "default.lua";
    public static String gameName = GameUtils.getGameName();
    public static String gameVersion = "v1.8";

    /* XXX implementar acessos `a properList (constructor, get*, etc) */
    private static String[] properList = null;
    private static int nTeams = 15;
    private static int nRobots = 5;
    private static int gameTime = 1800;
    private static boolean allowManual = true;

    /**
     * Class constructor.
     * Creates a new instance of game configuration
     *
     * @param _map        Lua map file to be loaded
     * @param _properList List of dynamic property names to be loaded
     * @param _name       Game name. This will be used as label to JPanel
     * @param _teams      Maximum number of teams allowed in this game
     * @param _robots     Exact number of robots a team need to be accepted
     * @param _gametime   Game time in seconds
     * @param _allow      Does this game allows a manual engine?
     */
    public GameConfig(String _map, String[] _properList, String _name,
                      int _teams, int _robots, int _gametime, boolean _allow) {
        if (_map != null) {
            mapFile = _map;
        }
        if (_name != null) {
            gameName = _name;
        }
        if (_teams != 0) {
            nTeams = _teams;
        }
        if (_robots != 0) {
            nRobots = _robots;
        }
        if (_gametime != 0) {
            gameTime = _gametime;
        }
        allowManual = _allow;
        properList = _properList;
    }

    /**
     * Class constructor.
     * Creates a new instance of game configuration
     * Keep all other configurations default
     *
     * @param _map   Lua map file to be loaded
     * @param _allow Does this game accept a manual engine?
     */
    public GameConfig(String _map, boolean _allow) {
        if (_map != null) {
            mapFile = _map;
        }
        allowManual = _allow;
    }

    /**
     * Class constructor.
     * Creates a new instance of game configuration
     * Use this method only if you want to keep the default
     * configuration but wants to deny manual engines.
     *
     * @param _allow Does this game accept a manual engine?
     */
    public GameConfig(boolean _allow) {
        allowManual = _allow;
    }

    /**
     * Class constructor.
     * Creates a new instance of game configuration with dynamic properties,
     * and keep other configurations default.
     *
     * @param _properList A list of names of dynamic properties to be loaded.
     */
    public GameConfig(String[] _properList) {
        properList = _properList;
    }

    /**
     * Class constructor.
     * Creates a new instance of game configuration
     * Use default configuration.
     */
    public GameConfig() {/* use default configuration */
    }


    /**
     * Get game name
     *
     * @return A string with the game name
     */
    public String getName() {
        return gameName;
    }

    /**
     * Get map filename
     *
     * @return Map file path as String
     */
    public String getMapFile() {
        return mapFile;
    }

    /**
     * Get number of teams
     *
     * @return the maximum number of teams allowed to play
     */
    public int getNumbTeams() {
        return nTeams;
    }

    /**
     * Get number of robots
     *
     * @return the exact number of robots a team may have
     */
    public int getNumbRobots() {
        return nRobots;
    }

    /**
     * Get game time (not current game time, just the pre-defined)
     *
     * @return the total game time set to this game
     */
    public int getGameTime() {
        return gameTime;
    }

    /**
     * Get the list of names of dynamic properties to be loaded.
     *
     * @return A string vector with all property names.
     */
    public String[] getProperList() {
        return properList;
    }

    /**
     * Toggle manual on/off
     *
     * @return True if allowed, false if not.
     */
    public boolean getManual() {
        return allowManual;
    }

    /**
     * Set map file path
     *
     * @param _map Map file path
     */
    public void setMapFile(String _map) {
        mapFile = _map;
    }

    /**
     * Set game name
     *
     * @param _name Game name
     */
    public void setName(String _name) {
        gameName = _name;
    }

    /**
     * Set list of dynamic properties
     *
     * @param _list List of names of dynamic properties
     */
    public void setProperList(String[] _list) {
        properList = _list;
    }

    /**
     * Set maximum number of teams allowed to play
     *
     * @param _teams Number of teams
     */
    public void setNumbTeams(int _teams) {
        nTeams = _teams;
    }

    /**
     * Set number of robots for each team
     *
     * @param _robots Number of robots
     */
    public void setNumbRobots(int _robots) {
        nRobots = _robots;
    }

    /**
     * Set game time
     *
     * @param _time Game time in seconds
     */
    public void setGameTime(int _time) {
        gameTime = _time;
    }

    /**
     * Toggle manual on/off
     *
     * @param _state True if allowed, false if not.
     */
    public void setManual(boolean _state) {
        allowManual = _state;
    }
}
