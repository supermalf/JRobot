package org.jrobot.ctrl;


/**
 * Interface Action
 *
 *
 * @author Malf
 * @version $Id: Action.java,v 1.1 2005/07/03 21:33:25 savio Exp $
 */

import org.jrobot.game.robot.Robot;

public interface Action 
 {
   /**
     * Run method
     *
     */
    public void run(Robot robot);

    /**
     * toString method
     *
     * @return String Command String
     */
    public String toString();
}
