/**
 * @(#)scoreBoard.java 1.00 29/05/05
 *
 */


package org.jrobot.ogl;

import net.java.games.jogl.GL;
import net.java.games.jogl.util.GLUT;
import org.jrobot.JRobot;

import java.rmi.RemoteException;

/**
 * *******************************************
 *
 * This class creates the Score board of the game
 *
 * @author MALF
 * @version 2.00, 29/05/05
 * @since JBobot v0.4, JDK v1.4
 */

public class scoreBoard {

    /* OpenGL context */
    private static GL gl;


    private static oglText Text;

    private static String teamNames[];
    private static float teamColors[][];
    private static float teamScore[];
    private static int teamsInGame = 0;

    private static boolean show = true;

    private static int teams=4;


    /**
     *
     * scoreBoard Constructor
     *
     * @param gl   OpenGL context
     * @param glut Glut context
     *
     */

    scoreBoard(GL gl, GLUT glut)
    {
        scoreBoard.gl = gl;

        Text = new oglText(gl, glut);

        teamNames = new String[teams];
        teamColors = new float[teams][4];
        teamScore = new float[teams];
    }

    /**
     * Inserts a team in the game
     *
     * @param team  Team name
     * @param color Team color
     * @param score Team actual score
     *
     */

    public void Insert(String team, float[] color, float score) {

        if (show) {
            if ((teamsInGame + 1) <= teams) {

                teamNames[teamsInGame] = team;
                teamColors[teamsInGame] = color;
                teamScore[teamsInGame] = score;

                teamsInGame++;
            } else
                System.err.println("Error: The limit of teams in game is " + teams + "!");
        }
    }

    /**
     *
     * Displays / Hide the score board
     *
     * @param show Displays / Hide the score board
     *
     */

    public void Show(boolean show) {
        float[] color = {1.0f, 1.0f, 1.0f};

        scoreBoard.show = show;

        if (scoreBoard.show) {
            //Main Score Title
            Text.ScreenText("# COLECTED OIL: ", color, -0.99f, 0.925f);

            //Displays teams names & score
            for (int i = 0; i < teamsInGame; i++) {
                //Name
                CreateName(teamNames[i], i);

                //Score
                CreateBar(teamColors[i], teamScore[i], i);
            }

            teamsInGame = 0;
        }
    }


    /**
     *
     * Displays the team name in the screen
     *
     * @param team       Team name
     * @param teamNumber Team text relative screen positon
     *
     */

    private void CreateName(String team, int teamNumber) {
        float[] color = {0.0f, 0.0f, 0.0f};
        float y = 0.835f - (float) teamNumber / 12;
        float x = -0.94f;

        Text.ScreenText(team, color, x, y);
    }


    /**
     * Displays the team score in the screen
     *
     * @param color      Team color
     * @param score      Team actual score (1.0f represents 100%)
     * @param teamNumber Team text relative screen positon
     * 
     */

    private void CreateBar(float[] color, float score, int teamNumber) {
        float x = -1.15f;
        float dx = score;

        float y = 0.99f - (float) teamNumber / 10;
        float dy = 0.045f;


        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(-1.2, 1.2, -1.2, 1.2, -1, 1);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL.GL_BLEND);
        gl.glDisable(GL.GL_DEPTH_TEST);
        gl.glDisable(GL.GL_LIGHTING);
        gl.glColor4f(color[0], color[1], color[2], 0.3f);

        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(x, y, 0);
        gl.glVertex3f(x + dx, y, 0);
        gl.glVertex3f(x + dx, y + dy, 0);
        gl.glVertex3f(x, y + dy, 0);
        gl.glEnd();

        gl.glColor4f(color[0], color[1], color[2], 0.5f);

        gl.glBegin(GL.GL_LINES);
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(x, y, 0);
        gl.glVertex3f(x + dx, y, 0);
        gl.glVertex3f(x + dx, y, 0);
        gl.glVertex3f(x + dx, y + dy, 0);
        gl.glVertex3f(x + dx, y + dy, 0);
        gl.glVertex3f(x, y + dy, 0);
        gl.glVertex3f(x, y + dy, 0);
        gl.glVertex3f(x, y, 0);
        gl.glEnd();

        gl.glDisable(GL.GL_BLEND);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glPopMatrix();
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL.GL_MODELVIEW);
    }

}
