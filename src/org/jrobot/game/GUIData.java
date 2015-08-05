package org.jrobot.game;

import java.util.LinkedList;
import java.util.ListIterator;

public class GUIData implements java.io.Serializable {

    /**
     * Clock
     */
    public static int clock;

    /**
     * Robots
     */
    public static GUIRobotList robots;
    
    /**
     * Score list
     */
    public static GUIScoreList scoreList;

    /**
     * Flags
     */
    public static GUIFlagList flags;

    /**
     * Class constructor
     */
    public GUIData() {
       if(robots == null && scoreList==null && flags==null) {
           robots = new GUIRobotList();
           scoreList = new GUIScoreList();
           flags = new GUIFlagList();
       }
    }

    /**
     * Adds a robot
     *
     * @param _name Name
     * @param _color Color
     * @param w Width
     * @param h Height
     * @param a Angle
     */
    public void addRobot(String _name, float[] _color, int w, int h, int a) {
        GUIRobot newRobot = new GUIRobot(_name, _color, w, h, a);
        robots.add(newRobot);
    }

    /**
     * Updates a robot
     *
     * @param _name Name
     * @param _color Color
     * @param w Width
     * @param h Height
     * @param a Angle
     */
    public void updateRobot(String _name, float[] _color, int w, int h, int a) {
        ListIterator robotsIterator = robots.listIterator(0);
        GUIRobot robot;
        while(robotsIterator.hasNext()) {
            robot = (GUIRobot) robotsIterator.next();
            if(robot.name.equals(_name) && robot.color == _color) {
                robot.width = w;
                robot.height = h;
                robot.angle = a;
            }
        }
        robotsIterator = null;
        robot = null;
    }
    
    /** 
     * Add new score
     *
     * @param _name Team name
     * @param _color Color
     */
    public void addScore(String _name, float[] _color) {
        ListIterator list = scoreList.listIterator(0);
        GUIScore score;
        while(list.hasNext()) {
            score = (GUIScore) list.next();
            if(score.teamName.equals(_name) &&
               score.color[0] == _color[0] &&
               score.color[1] == _color[1] &&
               score.color[2] == _color[2])
                return;
        }
        GUIScore newScore = new GUIScore(_name, _color, 0);
        System.out.println(_name+"-"+_color[0]);
        scoreList.add(newScore);
    }
    
    /**
     * Update score
     *
     * @param _team Team name
     * @param _color Color
     * @param newScore Score
     */
    public void updateScore(String _team, float[] _color, float newScore) {
        ListIterator scoreIterator = scoreList.listIterator(0);
        GUIScore score;
        while(scoreIterator.hasNext()) {
            score = (GUIScore) scoreIterator.next();
            if(score.teamName.equals(_team) && score.color == _color) {
                score.score = newScore;
            }
        }
        scoreIterator = null;
        score = null;
    }

    /**
     * Add a new flag
     *
     * @param w Width
     * @param h Height
     * @param color Color
     */
    public void addFlag(int w, int h, float[] color) {
        GUIFlag newFlag = new GUIFlag(w, h, color);
        flags.add(newFlag);
    }
    
    /**
     * Update clock
     *
     * @param _time Remaining time
     */
    public void updateClock(int _time) {
        clock = _time;
    }

    /**
     * Class GUIRobot
     */
    public class GUIRobot implements java.io.Serializable {
        public float[] color;
        public String name;
        public int width;
        public int height;
        public int angle;
        
        /**
         * Class constructor
         * @param _name Name
         * @param _color Color
         * @param w Width
         * @param h Height
         * @param a Angle
         */
        public GUIRobot(String _name, float[] _color, int w, int h, int a) {
            name = _name;
            color = _color;
            width = w;
            height = h;
            angle = a;
        }
    } 

    /**
     * Class GUIScore
     */
    public class GUIScore implements java.io.Serializable {
        public String teamName;
        public float[] color;
        public float score;
        
        /**
         * Class constructor
         *
         * @param _name Name
         * @param _color Color
         * @param _score Score
         */
        public GUIScore(String _name, float[] _color, int _score) {
            teamName = _name;
            color = _color;
            score = _score;
        }
    }
    
    /**
     * Class GUIFlag
     */
    public class GUIFlag implements java.io.Serializable {
        public int width;
        public int height;
        public float[] color;

        /**
         * Class constructor
         *
         * @param w Width
         * @param h Height
         * @param _color Color
         */
        public GUIFlag(int w, int h, float[] _color) {
            width = w;
            height = h;
            color = _color;
        }
    }
    
    /**
     * GUIScoreList class
     */
    public class GUIScoreList extends LinkedList implements java.io.Serializable {
    }

    /**
     * GUIRobotList class
     */
    public class GUIRobotList extends LinkedList implements java.io.Serializable {
    }

    /**
     * GUIFlagList class
     */
    public class GUIFlagList extends LinkedList implements java.io.Serializable {
    } 
}
