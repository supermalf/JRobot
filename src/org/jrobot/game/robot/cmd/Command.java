package org.jrobot.game.robot.cmd;

import org.jrobot.game.robot.Robot;

/**
 * Command interface
 * <p/>
 * Known bug: not all Cmd* deals with all Impact items so, sometimes
 * implementing affect functions in properties is no big deal.
 *
 * @author savio
 * @version $Id: Command.java,v 1.9 2005/07/04 03:36:15 savio Exp $
 */

public interface Command {
    public void run(Robot robott);

    public String toString();
}
