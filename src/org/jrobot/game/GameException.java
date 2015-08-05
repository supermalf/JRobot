package org.jrobot.game;

import org.jrobot.log.*;

/**
 * Game exception. Generates an exception, serializes and make log entries.
 *
 * @author savio
 * @version $Id: GameException.java,v 1.4 2005/07/03 01:47:56 savio Exp $
 */
public class GameException extends Exception implements java.io.Serializable {

    /**
     * Class constructor
     *
     * @param f a string telling where the exception came from. this parameter
     *          helps to keep track of what is happening in runtime.
     * @param s any message explaining how this exception occurred.
     */
    public GameException(String f, String s) {
        super(s);
        Log.debug("Exception received : " + f + " : " + s);
    }
}
