/**
 * @(#)scoreBoard.java 1.00 29/05/05
 *
 */


package org.jrobot.ogl;

import net.java.games.jogl.GL;
import net.java.games.jogl.util.GLUT;
import org.jrobot.game.GUIData;


/**
 *
 * This class creates the GetTime board of the game
 *
 * @author MALF
 * @version 2.00, 01/06/05
 * @since JBobot v0.4, JDK v1.4
 *
 */

public class timeBoard {

    /* OpenGL context */
    private static GL gl;

    private static oglText Text;

    private static boolean show = true;


    /**
     * commandBoard Constructor
     *
     * @param gl   OpenGL context
     * @param glut Glut context
     *
     */

    timeBoard(GL gl, GLUT glut) {
        timeBoard.gl = gl;

        Text = new oglText(gl, glut);

    }

    /**
     *
     * Displays / Hide the command board
     *
     * @param show Displays / Hide the command board
     *
     */

    public void Show(boolean show, int time) {
        float[] color = {0.0f, 0.0f, 0.0f};

        timeBoard.show = show;

        if (timeBoard.show) {
            CreateTimePanel();

            //Main command  Title
            Text.ScreenText("# TIME: " + time, color, 0.68f, 0.9f);

        }
    }


    /**
     *
     * Creates teh command Panel
     *
     */

    private void CreateTimePanel() {
        float x = 0.8f;
        float dx = 0.33f;
        float y = 1.02f;
        float dy = 0.125f;

        float color[] = {0.2f, 0.2f, 0.2f};

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
