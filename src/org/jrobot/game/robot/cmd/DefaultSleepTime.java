package org.jrobot.game.robot.cmd;

/**
 * Default Sleep Time of the robot's Actions
 *
 * @author Malf
 * @version $Id: DefaultSleepTime.java,v 1.4 2005/07/03 23:45:46 savio Exp $
 */

public class DefaultSleepTime
{
    /* Lag default time for the commands*/

    private static int[] lags = { 3, //#0 - Gradient
                                  0, //#1 - Move
                                  3, //#2 - Position
                                  3, //#3 - Pressure
                                  4, //#4 - Prospect
                                  3, //#5 - Time
                                  1  //#6 - Turn
                                  };

    /**
     * Default Gradient Lag
     *
     * @return lag
     */
    public static int getGradientLag()
    {
        return lags[0];
    }

    /**
     * Default Move Lag
     *
     * @return lag
     */
    public static int getMoveLag()
    {
        return lags[1];
    }

    /**
     * Default Position Lag
     *
     * @return lag
     */
    public static int getPositionLag()
    {
        return lags[2];
    }

    /**
     * Default Pressure Lag
     *
     * @return lag
     */
    public static int getPressureLag()
    {
        return lags[3];
    }

    /**
     * Default Prospect Lag
     *
     * @return lag
     */
    public static int getProspectLag()
    {
        return lags[4];
    }

    /**
     * Default Time Lag
     *
     * @return lag
     */
    public static int getTimeLag()
    {
        return lags[5];
    }

    /**
     * Default Turn Lag
     *
     * @return lag
     */
    public static int getTurnLag()
    {
        return lags[6];
    }
}
