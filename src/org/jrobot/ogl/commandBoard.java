/**
 * @(#)commandBoard.java 1.00 29/05/05
 *
 */

package org.jrobot.ogl;

import net.java.games.jogl.GL;
import net.java.games.jogl.util.GLUT;

/**
 * This class creates the Command board of the game.
 * Shows to the user the last 5 actions.
 *
 * @author MALF
 * @version 1.00, 29/05/05
 * @since JBobot v0.4, JDK v1.4
 *
 */

public class commandBoard {

    /* OpenGL context */
    private static GL gl;

    private static oglText Text;

    private static boolean show = true;

    private static int MSGS = 5;
    private static int LastMsg = -1;

    private static String[] Commands;


    /**
     * CommandBoard Constructor
     *
     * @param gl   OpenGL context
     * @param glut Glut context
     *
     */

    commandBoard(GL gl, GLUT glut) {
        commandBoard.gl = gl;

        Text = new oglText(gl, glut);

        Commands = new String[MSGS];

        for (int i = 0; i < MSGS; i++)
            Commands[i] = "";
    }

    /**
     * Displays / Hide the command board
     *
     * @param show Displays / Hide the command board
     *
     */

    public void Show(boolean show) {
        float[] color = {0.0f, 0.0f, 0.0f};
        float x = -0.25f;
        float y = -0.65f;

        commandBoard.show = show;

        if (commandBoard.show) {
            CreateCommandPanel();

            //Main command  Title
            Text.ScreenText("# GAME LOG: ", color, -0.25f, -0.55f);


            for (int i = 0; i < MSGS; i++) {
                Text.ScreenText(Commands[i], color, x, y - (0.05f * i));

            }
        }
    }


    /**
     * Inserts a mesage in the command line
     *
     * @param message Text
     *
     */

    public void Insert(String message) {
        if (show) {
            if ((LastMsg + 1) < MSGS) {
                Commands[LastMsg + 1] = message;
                LastMsg++;
            } else {
                String[] Aux = new String[MSGS];

                Aux = Commands;

                Commands[0] = Aux[1];
                Commands[1] = Aux[2];
                Commands[2] = Aux[3];
                Commands[3] = Aux[4];
                Commands[4] = message;
            }
        }
    }

    /**
     *
     * Creates teh command Panel
     * 
     */

    private void CreateCommandPanel() {
        float x = -0.35f;
        float dx = 1.5f;
        float y = -1.1f;
        float dy = 0.5f;

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
