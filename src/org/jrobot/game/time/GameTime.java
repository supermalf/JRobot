/**
 * @author savio
 * @version $Id: GameTime.java,v 1.3 2005/07/05 03:22:54 savio Exp $
 */

package org.jrobot.game.time;

public class GameTime implements java.io.Serializable {

    /** Game time */
    public static int gameTime = 0;
    
    /** Elapsed time */
    public static int elapsed;
    
    /** Remaining time */
    public static int remaining;
    
    /**
     * Class constructor
     */
    public GameTime() {
    }
}
