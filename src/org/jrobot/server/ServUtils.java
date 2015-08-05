package org.jrobot.server;

import org.jrobot.log.*;
import org.jrobot.game.*;

/**
 * This class implements some wrappers to help server manipulation.
 *
 * @author savio
 * @version $Id: ServUtils.java,v 1.3 2005/07/03 01:47:58 savio Exp $
 */
public class ServUtils {

    /**
     * Start new log session. This wrapper calls Log.start()
     *
     * @param logFile Log file
     * @see org.jrobot.log.Log
     */
    public static void startLog(String logFile) {
        Log.start(logFile);
    }

    /**
     * Reads a file with configuration and load to GameConfig object.
     * (already under construction XXX)
     *
     * @param confFile Config file
     * @return GameConfig with all stuff set
     */
    public static GameConfig readConfigFile(String confFile) {
        /* XXX TODO (vai precisar?) */
        return null;
    }
}
