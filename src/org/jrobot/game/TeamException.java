package org.jrobot.game;

/**
 * Team exception.
 *
 * @author savio
 * @version $Id: TeamException.java,v 1.2 2005/07/03 01:47:57 savio Exp $
 */

public class TeamException extends GameException {
    /**
     * Class constructor
     *
     * @param f a string telling where the exception came from. this parameter
     *          helps to keep track of what is happening in runtime.
     * @param s any message explaining the exception
     */
    public TeamException(String f, String s) {
        super(f, s);
    }
}
