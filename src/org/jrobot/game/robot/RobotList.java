package org.jrobot.game.robot;

import java.util.*;
import java.rmi.RemoteException;

/**
 * This class implements  a list of robots. Notice  that this list may
 * hold objects  of type RobotThread,  not RobotImpl.  If you  want to
 * access  the  robot  within  the thread,  please  check  implemented
 * methods  in RobotThread.  You can  also use  getRobotbyCoord() when
 * needed.
 *
 * @author savio
 * @version $Id: RobotList.java,v 1.10 2005/07/05 03:22:54 savio Exp $
 */

public class RobotList extends java.util.LinkedList implements java.io.Serializable {
    
    public RobotList() {
        super();
    }

    /**
     * Get robot by coordinate
     *
     * @param x coordinate
     * @param y coordinate
     *
     * @return Robot
     */
    public Robot getRobotByCoord(int x, int y) throws RemoteException {
        Robot robot;
        ListIterator listIterator = this.listIterator(0);
        while(listIterator.hasNext()) {
            RobotThread robotThread = (RobotThread) listIterator.next();
            robot = robotThread.getRobot(); //XXX FIXME!! 
            try {
                if(x == robot.getHeight() && 
                   x == robot.getWidth()) {
                    return robot;
                }
            } catch(RemoteException e) {} 
        }
        return null;
    }
    
    /**
     * Name exists?
     *
     * @param name Name
     *
     * @return True if name already exists
     */
    public boolean nameExists(String name) {
        Robot robot;
        ListIterator listIterator = this.listIterator(0);
        while(listIterator.hasNext()) {
            RobotThread robotThread = (RobotThread) listIterator.next();
            robot = robotThread.getRobot();
            try {
                if(robot.getRName().equals(name)) {
                    return true;
                }
            } catch(RemoteException e) {} 
        }
        return false;
    }
}
