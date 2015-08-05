package org.jrobot.game;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Teams List
 *
 * @author savio
 * @version $Id: TeamList.java,v 1.9 2005/07/05 03:22:54 savio Exp $
 */

public class TeamList extends LinkedList implements java.io.Serializable {

    /**
     * Class constructor
     */
    public TeamList() {
        super();
    }

    /**
     * Check if color is already taken
     *
     * @param rgbColor RGB Color
     * @return True if color is already taken
     */
    public boolean colorIsTaken(float[] rgbColor) {
        Team tempTeam;
        ListIterator list = this.listIterator(0);

        while (list.hasNext()) {
            tempTeam = (Team) list.next();
            if (rgbColor == tempTeam.getColor()) {
                return true;
            }
        }
        return false;
    }
}
