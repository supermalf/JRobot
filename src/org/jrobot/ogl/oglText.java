/**
 * @(#)oglText.java 1.00 27/05/05
 *
 */

package org.jrobot.ogl;

import net.java.games.jogl.GL;
import net.java.games.jogl.util.GLUT;

/**
 * *******************************************
 *
 * This class creates texts in OpenGL context
 *
 * @author MALF
 * @version 1.00, 27/05/05
 * @since JBobot v0.2, JDK v1.4
 *
 */


public class oglText {
    /* OpenGL context */
    private static GL gl;
    private static GLUT glut;

    private static float objectZ = 0.05f;      /* TODO: Conta para variacao de mapa */


    /**
     *
     * oglText Constructor
     *
     * @param gl   OpenGL context
     * @param glut Glut context
     *
     */

    oglText(GL gl, GLUT glut) {
        super();
        oglText.gl = gl;
        oglText.glut = glut;
    }


    /**
     *
     * Displays a text at the openGL canvas
     *
     * @param text  Text string
     * @param color Text color
     * @param x     X coord
     * @param y     Y coord
     *
     */

    public void ScreenText(String text, float[] color, float x, float y) {
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glDisable(GL.GL_LIGHTING);
        gl.glDisable(GL.GL_DEPTH_TEST);

        gl.glColor3fv(color);
        gl.glRasterPos3d((double) x, (double) y, 0.8);

        glut.glutBitmapString(gl, GLUT.BITMAP_HELVETICA_12, text);

        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPopMatrix();
    }


    /**
     * Displays a text above an object
     *
     * @param text  Text string
     * @param color Text color
     * 
     */

    public void ObjectText(String text, float[] color) {
        gl.glColor3fv(color);
        gl.glDisable(GL.GL_LIGHTING);

        gl.glRasterPos3d(0.0, 0.0, objectZ);
        glut.glutBitmapString(gl, GLUT.BITMAP_8_BY_13, text);

        gl.glEnable(GL.GL_LIGHTING);
    }
}
