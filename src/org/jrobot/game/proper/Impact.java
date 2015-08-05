package org.jrobot.game.proper;

/**
 * Impact, holds a set of impacts cause by a cell property.
 *
 * @author savio
 * @version $Id: Impact.java,v 1.11 2005/07/04 03:36:14 savio Exp $
 */

public class Impact {

    /**
     * GetTime increment
     */
    static int timeInc;

    /**
     * Error increment
     */
    static float errorInc;

    /**
     * Noise increment
     */
    static int noiseInc;

    /**
     * Class constructor
     *
     * @param _time  GetTime increment for a given command
     * @param _err   Error related to a expected answer
     * @param _noise Applied to `message' command.
     */
    public Impact(int _time, float _err, int _noise) {
        timeInc = _time;
        errorInc = _err;
        noiseInc = _noise;
        
        if(errorInc > 1.0f) {
            errorInc = 1.0f;
        }
    }

    /**
     * Get time increment
     *
     * @return GetTime increment caused by cell property
     */
    public int getTimeInc() {
        return timeInc;
    }

    /**
     * Get error
     *
     * @return Error caused by cell property
     */
    public float getErrorInc() {
        return errorInc;
    }

    /**
     * Get noise increment
     *
     * @return Noise increment caused by cell property
     */
    public int getNoiseInc() {
        return noiseInc;
    }

    /**
     * Set time increment
     *
     * @param _timeInc increment caused by cell property
     */
    public void setTimeInc(int _timeInc) {
        timeInc = _timeInc;
    }

    /**
     * Set error
     *
     * @param _errorInc caused by cell property
     */
    public void setErrorInc(float _errorInc) {
        errorInc = _errorInc;
        if(errorInc > 1.0f) {
            errorInc = 1.0f;
        }
    }

    /**
     * Set noise increment
     *
     * @param _noiseInc increment caused by cell property
     */
    public void setNoiseInc(int _noiseInc) {
        noiseInc = _noiseInc;
    }

    /**
     * Adds two Impacts. The result is recorded on the object
     * that calls this method.
     *
     * @param _impact An object of type Impact
     */
    public void sum(Impact _impact) {
        if(_impact != null) {
            timeInc += _impact.getTimeInc();
            errorInc *= _impact.getErrorInc();
            noiseInc *= _impact.getNoiseInc();
        }
    }
}
