package org.jrobot.game;

import org.jrobot.ogl.*;
import org.jrobot.log.Log;

import java.util.ListIterator;
import java.util.LinkedList;

/**
 * Game Score 
 *
 * @author savio
 * @version $Id: Score.java,v 1.7 2005/07/05 08:37:17 savio Exp $
 */

public class Score implements java.io.Serializable {
    /** Teams */   
    public TeamList teams;
    
    /** Maximum score */
    public int maxScore;

    /**
     * Class constructor
     *
     * @param _teams Team list
     */
    public Score(TeamList _teams, int max) {
        teams = _teams;
        maxScore = max;
    }
    
    /**
     * Score
     *
     * @param team Team
     * @param nInc Score increment
     */
    public void score(Team team, int nInc) {
        Team tempTeam;
        ListIterator teamsList = teams.listIterator(0);
        while (teamsList.hasNext()) {
            tempTeam = (Team) teamsList.next();
            
            if (tempTeam.compare(team)) {
                /* score!! */
                Log.debug("score!!");
                team.addScore(nInc);
            }
        }
    }
    
    /**
     * Set ScoreBoard
     * (XXX: another misleading class name)
     *
     * @param board Score board
     */
    public void setScoreBoard(scoreBoard board) {
        
        ListIterator teamsList = teams.listIterator(0);
        Team tempTeam;
        float finalScore;        
        int tempScore;
        
        while(teamsList.hasNext()) {
             tempTeam = (Team) teamsList.next();
             tempScore = tempTeam.getScore();
             finalScore = (float) tempScore/maxScore;
             board.Insert(tempTeam.teamName, tempTeam.rgbColor, finalScore);
        }
    }

    /**
     * Get total score by team
     *
     * @param team Team
     * 
     * @return Score
     */
    public float getScore(Team team) {
        float score = (float)team.getScore();
        float max = (float) maxScore;
        float ret = (float)score/max*(Game.getNTeams()+50); // XXX
        return ret;
    }
}
