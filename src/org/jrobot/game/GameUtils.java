package org.jrobot.game;

/*
 * This class handles wrappers to help manipulating the game from host 
 * computer.
 *
 * @author savio
 * @version $Id: GameUtils.java,v 1.41 2005/07/05 08:50:15 savio Exp $
 */

public abstract class GameUtils {

    /* XXX remover essa variavel (e dependencias) -- */
    /* true = linux, false = windows, e chega de ficar mudando ponto ponto */
    private static boolean ROOT = false;

    private static char[] charTable = {'a','b','c','d','e','f','g','h','i','j','k',
                                       'l','m','n','o','p','q','r','s','t','u','v',
                                       'w','x','y','z','A','B','C','D','E','F','G',
                                       'H','I','J','K','L','M','N','O','P','Q','R',
                                       'S','T','U','V','X','W','Y','Z','0','1','2',
                                       '3','4','5','6','7','8','9'}; // 62 chars

    /* Avaliable proper List */
    //TODO XXX Colocar mais propers
    private static String[] dyn_properList = {"Noise", "Depth", "Pressure"};

    /**
     * Get default map path
     *
     * @return Map path
     */
    public static String getMapPath() {
        if (ROOT)
            return "../maps/";
        else
            return "./maps/";
    }

    /**
     * Get default script path
     *
     * @return Lua script path
     */
    public static String getScriptPath() {
        if (ROOT)
            return "../src/lua/";
        else
            return "./src/lua/";
    }

    /**
     * Get default icon path
     *
     * @return Icon script path
     */
    public static String getIconPath() {
        if (ROOT)
            return "../ico/";
        else
            return "./ico/";
    }

    /**
     * Get default log path
     *
     * @return Log path
     */
    public static String getDefLogPath() {
        if (ROOT)
            return "/tmp/log.txt";
        else
            return "./log.txt";
    }

    /**
     * Get default texture path
     *
     * @return Icon texture path
     */
    public static String getTexturePath() {
        if (ROOT)
            return "../tex/";
        else
            return "./tex/";
    }

    /**
     * Is debug mode on?
     *
     * @return true if yes, false if no
     */
    public static boolean debugMe() {
        return true;
    }

    /**
     * Is verbose mode on?
     *
     * @return true if yes, false if no
     */
    public static boolean verbose() {
        return true;
    }

    /**
     * Game name
     *
     * @return Game name
     */
    public static String getGameName() {
        return "JRobotGame";
    }

    /**
     * Run server in console only?
     *
     * @return yes/no
     */
    public static boolean consoleMode() {
        return true;
    }

    /**
     * Toggle secure mode
     *
     * @return yes/no
     */
    public static boolean secureMode() {
        return false;
    }

    /**
     * Handle program exit
     */
    public static void handleExit() {
        System.out.println("Good Bye!");
        System.exit(0);
    }

    /**
     * Int to char
     *
     * @param n Char table index
     * @return Character
     */
    public static char intToChar(int n) {
        n = n % 62;
        return charTable[n];
    }

    /**
     * Handle program exit
     *
     * @return Frame Title
     */
    public static String getFrameTitle() {
        return GameConfig.gameName + " " + GameConfig.gameVersion;
    }

    /**
     * Handle program exit
     *
     * @return Frame Title
     */
    public static String[] getProperList() {
        return dyn_properList;
    }
}
