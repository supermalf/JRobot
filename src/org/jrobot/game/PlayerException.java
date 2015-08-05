package org.jrobot.game;

/**
 * Player exception.
 *
 * @author savio
 * @version $Id: PlayerException.java,v 1.2 2005/07/03 01:47:56 savio Exp $
 */

public class PlayerException extends GameException {
    /**
     * Class constructor
     *
     * @param f a string telling where the exception came from. this parameter
     *          helps to keep track of what is happening in runtime.
     * @param s any message explaining the exception
     */
    public PlayerException(String f, String s) {
        super(f, s);
    }
}
