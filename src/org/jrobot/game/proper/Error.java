package org.jrobot.game.proper;

import org.jrobot.game.robot.Robot;

import java.rmi.RemoteException;

import org.jrobot.game.*;

public class Error extends StaticProper{
    /**
     * This method returns the impact caused by the Error
     * when Position command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectPosition(Robot robot) {
        return null;
    }

    /**
     * This method returns the impact caused by the Error
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
     * This method returns the impact caused by the Error
     * when Move command is performed.
     *
     * @param direction true = forward, false = backward
     * @param robot     Affect robot
     * @return Impact
     * @see Impact
     */
    public Impact affectMove(boolean direction, Robot robot){
        return null;
    }

    /**
     * This method returns the impact caused by the Error
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
     * This method returns the impact caused by the Error
     * when Gradient command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectGradient(Robot robot) {
        //tempo de lag
        int tmpError = 0;
        try
            {
            tmpError = (int)(( (Game.getPropValue("Error", robot.getWidth()+1 ,robot.getHeight()  ) )
                             + (Game.getPropValue("Error", robot.getWidth()-1 ,robot.getHeight()  ) )
                             + (Game.getPropValue("Error", robot.getWidth()   ,robot.getHeight()+1) )
                             + (Game.getPropValue("Error", robot.getWidth()   ,robot.getHeight()-1) ) ) * 10.0f);
        }
        catch(RemoteException e)
        {
            //XXX
        }
        return new Impact(0, tmpError, 0);
    }

    /**
     * This method returns the impact caused by the Error
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
     * This method returns the impact caused by the Error
     * when Pressure command is performed.
     *
     * @param robot Affected robot
     * @return Impact
     * @see Impact
     */
    public Impact affectPressure(Robot robot)
    {
        //tempo de lag
        int tmpError = 0;
        try
        {
            tmpError = (int) ( (Game.getPropValue("Error", robot.getWidth(),robot.getHeight())) * 10.0f);
        }
        catch(RemoteException e)
        {
            //XXX
        }
        return new Impact(0, tmpError, 0);
    }

    /**
     * Class constructor
     */
    public Error() {
        super();
    }

}
