package org.jrobot.game.proper;

import org.jrobot.game.robot.*;
import org.jrobot.game.Game;

import java.rmi.RemoteException;

/**
 * Depth
 *
 * @author savio
 * @version $Id: Depth.java,v 1.13 2005/07/03 23:45:46 savio Exp $
 */

public class Depth extends StaticProper {

    /**
     * This method returns the impact caused by the Depth
     * when GetPosition command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectPosition(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the Depth
     * when Turn command is performed.
     *
     * @param direction true = left, false = right
     * @param robot     Affect robot
     * @return Impact
     * @see Impact
     */
    public Impact affectTurn(boolean direction, Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the Depth
     * when Move command is performed.
     *
     * @param direction true = forward, false = backward
     * @param robot     Affect robot
     * @return Impact
     * @see Impact
     */
    public Impact affectMove(boolean direction, Robot robot)
    {
        //tempo de lag
        int tmpTime = 10;

        try
        {
            if ( (robot.getAngle() == 0 && direction)
                || (robot.getAngle() == 180 && !direction))
            {
                tmpTime += (int) ( ( Game.getPropValue("Depth", (robot.getWidth()+1),robot.getHeight())
                                   - Game.getPropValue("Depth", robot.getWidth(),robot.getHeight())) * 10.0f);
            }

            else if ( (robot.getAngle() == 90 && direction)
                    || (robot.getAngle() == 270 && !direction))
            {
                tmpTime += (int) ( ( Game.getPropValue("Depth", robot.getWidth(),(robot.getHeight()+1))
                                   - Game.getPropValue("Depth", robot.getWidth(),robot.getHeight())) * 10.0f);
            }

            else if ( (robot.getAngle() == 180 && direction)
                    || (robot.getAngle() == 0 && !direction))
            {
                tmpTime += (int) ( ( Game.getPropValue("Depth", (robot.getWidth()-1),robot.getHeight())
                                   - Game.getPropValue("Depth", robot.getWidth(),robot.getHeight())) * 10.0f);
            }

            else if ( (robot.getAngle() == 270 && direction)
                    || (robot.getAngle() == 90 && !direction))
            {
                tmpTime += (int) ( ( Game.getPropValue("Depth", robot.getWidth(),(robot.getHeight()-1))
                                   - Game.getPropValue("Depth", robot.getWidth(),robot.getHeight())) * 10.0f);
            }
        }
        catch(RemoteException e)
        {
            //XXX
        }

        return new Impact(tmpTime/4, 0, 0);   /* XXX TODO */
    }

    /**
     * This method returns the impact caused by the Depth
     * when Prospect command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectProspect(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the Depth
     * when Gradient command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectGradient(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the Depth
     * when Time command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectTime(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the Depth
     * when Pressure command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectPressure(Robot robot) {
        return null;
    }

    /**
     * Class constructor
     */
    public Depth() {
        super();
    }
}
