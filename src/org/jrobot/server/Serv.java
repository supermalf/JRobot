package org.jrobot.server;

/**
 * Interface used to implement JRobot servers.
 *
 * @author savio
 * @version $Id: Serv.java,v 1.6 2005/07/05 02:12:38 savio Exp $
 * @see ServImpl
 */

public interface Serv {

    /**
     * Shutdown server
     */
    void shutdown();
    
    /** 
     * Start game
     */
    void startGame();

    /**
     * Game started?
     */
    public boolean isGameStarted();
}
