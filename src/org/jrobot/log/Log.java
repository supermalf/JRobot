package org.jrobot.log;

import java.io.*;
import java.text.*;
import java.util.Date;

import org.jrobot.game.*;
import org.jrobot.ogl.*;
import org.jrobot.gui.LogDialog;

/**
 * This class handles all log facilities.
 *
 * XXX tratar catches
 *
 * @author savio
 * @version $Id: Log.java,v 1.15 2005/07/04 23:22:53 savio Exp $
 */

public class Log {
  
    /**
     * Log file descriptor used to write all data
     */
    private static PrintWriter logFile;

    /**
     * If doLog = true, output to logFile, else, do nothing.
     */
    private static boolean doLog = false;

    /**
     * Command board on?
     */
    private static boolean doCmdBoard;

    /**
     * Starts new log session.
     *
     * @param filePath File path (null not accepted).
     */
    public static void start(String filePath) {
        try {
            logFile = new PrintWriter(new FileWriter(filePath, true), true);
            doLog = true;
            if (!GameUtils.consoleMode()) {
                doCmdBoard = true;
            }

            Log.info("==== LOG STARTED ====");
        } catch (Exception e) {
            System.err.println("error : Could not open log file " + filePath
                    + ".");
        }
    }

    /**
     * Closes current log session
     */
    public static void close() {
        try {
            Log.info("==== LOG STOPPED ====");
            logFile.close();
        } catch (Exception e) {
        }
    }

    /**
     * Outputs a message with priority: NOTICE.
     * example: event messages, server information and logs, etc.
     *
     * @param msg output message
     */
    public static void notice(String msg) {
        if (doLog) {
            try {
                logFile.println(logTime() + ":[NOTICE]:" + msg);

                if(GameUtils.verbose())
                {
                    System.out.println("[NOTICE]:" + msg);
                    LogDialog.setStringLog(logTime() + ":[NOTICE]:" + msg);
                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * Outputs a message with priority: ALERT.
     * example: usually used when something not expected happens; but is
     * not either an error nor an illegal behavior
     *
     * @param msg output message
     */
    public static void alert(String msg) {
        if (doLog) {
            try {
                logFile.println(logTime() + ":[ALERT]:" + msg);

                if(GameUtils.verbose())
                {
                    System.out.println("[ALERT]:" + msg);
                    LogDialog.setStringLog(logTime() + ":[ALERT]:" + msg);
                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * Outputs a message with priority: INFO.
     *
     * @param msg output message
     *            example: usefull information such as idle time, amount of memory
     *            used, etc.
     */
    public static void info(String msg) {
        if (doLog) {
            try {
                logFile.println(logTime() + ":[INFO]:" + msg);

                if(GameUtils.verbose())
                {
                    System.out.println("[INFO]:" + msg);
                    LogDialog.setStringLog(logTime() + ":[INFO]:" + msg);
                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * Outputs a message with priority: DEBUG.
     * example: debug messages
     *
     * @param msg output message
     */
    public static void debug(String msg) {
        if (doLog && GameUtils.debugMe()) {
            try {
                logFile.println(logTime() + ":[DEBUG]:" + msg);

                if(GameUtils.verbose())
                {
                    System.out.println("[DEBUG]:" + msg);
                    LogDialog.setStringLog(logTime() + ":[DEBUG]:" + msg);
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Outputs a message with priority: WARNING.
     * example: general warnings
     *
     * @param msg output message
     */
    public static void warning(String msg) {
        if (doLog) {
            try {
                logFile.println(logTime() + ":[WARN]:" + msg);

                if(GameUtils.verbose())
                {
                    System.out.println("[WARN]:" + msg);
                    LogDialog.setStringLog(logTime() + ":[WARN]:" + msg);
                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * Outputs a message with priority: ERROR.
     * example: general errors
     *
     * @param msg output message
     */
    public static void error(String msg) {
        if (doLog) {
            try {
                logFile.println(logTime() + ":[ERROR]:" + msg);

                if(GameUtils.verbose())
                {
                    System.out.println("[ERROR]:" + msg);
                    LogDialog.setStringLog(logTime() + ":[ERROR]:" + msg);
                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * Outputs a message with priority: SEC.
     * example: warnings and alerts related to security issues, such as
     * user trying to read data with wrong permission, etc.
     *
     * @param msg output message
     */
    public static void security(String msg) {
        if (doLog) {
            try {
                logFile.println(logTime() + ":[SEC]:" + msg);

                if(GameUtils.verbose())
                {
                    System.out.println("[SEC]:" + msg);
                    LogDialog.setStringLog(logTime() + ":[SEC]:" + msg);
                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * Outputs a message with priority: GAME.
     * example: general game messages. this method is used to keep
     * track of game commands, messages, etc.
     *
     * @param msg output message
     */
    public static void game(String msg) {
        if (doLog) {
            try {
                logFile.println(logTime() + ":[GAME]:" + msg);

                if(GameUtils.verbose())
                {
                    System.out.println("[GAME]:" + msg);
                    LogDialog.setStringLog(logTime() + ":[GAME]:" + msg);
                }

                if (doCmdBoard) {
                    oglRenderer.CommandBoard.Insert(msg);
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Outputs a message with priority: BUG.
     * example: any potential bug found in run time.
     *
     * @param msg output message
     */
    public static void bug(String msg) {
        if (doLog) {
            try {
                logFile.println(logTime() + ":[BUG]:Please report this bug:" + msg);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Return current log entry time as string
     *
     * @return Log time as string
     */
    private static String logTime() {
        SimpleDateFormat logtime = new SimpleDateFormat("[MMM dd yyyy HH:mm:ss]");
        return logtime.format(new Date());
    }
}
