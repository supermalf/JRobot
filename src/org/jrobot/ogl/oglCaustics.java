/**
 * @(#)oglCaustics.java 1.00 29/05/05
 *
 */


package org.jrobot.ogl;

import net.java.games.jogl.GL;
import net.java.games.jogl.GLU;
import net.java.games.jogl.util.GLUT;


/**
 * This class implements:
 * - Underwater caustics effect
 *
 * @author MALF
 * @version 1.00, 29/05/05
 * @since JBobot v0.4, JDK v1.4
 */


public class oglCaustics {

    /* OpenGL Context */
    //private GL gl;
    //private GLU glu;

    static float causticScale = 1.0f;


    static int PASS_NORMAL = 0;    //??
    static int PASS_CAUSTIC = 1;


    static boolean HaveTexObj = false;
    static int currentCaustic = 0;
    static float lightPosition[];

    static float lightAngle = 0.0f, lightHeight = 20;
    static float angle = -150;   /* in degrees */
    static float angle2 = 30;   /* in degrees */


    public static void drawScene(GL gl, GLU glu, int pass)
    {
        /* The 0.03 in the Y column is just to shift the texture coordinates
        a little based on Y (depth in the water) so that vertical faces
        (like on the cube) do not get totally vertical caustics. */

        float sPlane[] = {0.05f, 0.03f, 0.0f, 0.0f};
        float tPlane[] = {0.0f, 0.03f, 0.05f, 0.0f};

        /* The causticScale determines how large the caustic "ripples" will
       be.  See the "Increate/Decrease ripple size" menu options. */

        sPlane[0] = 0.05f * causticScale;
        sPlane[1] = 0.03f * causticScale;

        tPlane[1] = 0.03f * causticScale;
        tPlane[2] = 0.05f * causticScale;


        if (pass == PASS_CAUSTIC) {
            /* Set current color to "white" and disable lighting
               to emulate OpenGL 1.1's GL_REPLACE texture environment. */
            gl.glColor3f(1.0f, 1.0f, 1.0f);
            gl.glDisable(GL.GL_LIGHTING);


            /* Generate the S & T coordinates for the caustic textures
               from the object coordinates. */

            gl.glTexGeni(GL.GL_S, GL.GL_TEXTURE_GEN_MODE, GL.GL_OBJECT_LINEAR);
            gl.glTexGeni(GL.GL_T, GL.GL_TEXTURE_GEN_MODE, GL.GL_OBJECT_LINEAR);
            gl.glTexGenfv(GL.GL_S, GL.GL_OBJECT_PLANE, sPlane);
            gl.glTexGenfv(GL.GL_T, GL.GL_OBJECT_PLANE, tPlane);
            gl.glEnable(GL.GL_TEXTURE_GEN_S);
            gl.glEnable(GL.GL_TEXTURE_GEN_T);

            if (HaveTexObj) {
                gl.glBindTexture(GL.GL_TEXTURE_2D, currentCaustic + 1);
            } else {
                gl.glCallList(currentCaustic + 101);
            }
        }

        drawFloor(gl, pass);
        drawObject(gl, glu, pass);

        if (pass == PASS_CAUSTIC) {
            gl.glEnable(GL.GL_LIGHTING);
            gl.glDisable(GL.GL_TEXTURE_GEN_S);
            gl.glDisable(GL.GL_TEXTURE_GEN_T);
        }
    }


    public static void display(GL gl, GLU glu) {

        /* Clear depth and color buffer. */
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        /* Reposition the light source. */

        lightPosition = new float[5];

        lightPosition[0] = 12.0f * (float) Math.cos(lightAngle);
        lightPosition[1] = lightHeight;
        lightPosition[2] = 12.0f * (float) Math.sin(lightAngle);
        //if (directionalLight) {
        //lightPosition[3] = 0.0f;
        //} else {
        lightPosition[3] = 1.0f;
        //}

        gl.glPushMatrix();
        /* Perform scene rotations based on user mouse input. */
        gl.glRotatef(angle2, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(angle, 0.0f, 1.0f, 0.0f);

        /* GetPosition the light again, after viewing rotation. */
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPosition);

        /* Draw the light location. */
        //drawLightLocation();

        /* Normal pass rendering the scene (caustics get added
           after this pass). */
        drawScene(gl, glu, PASS_NORMAL);

        //       if (showCaustics) {
        /* Disable depth buffer update and exactly match depth
       buffer values for slightly faster rendering. */
        gl.glDepthMask(false);
        gl.glDepthFunc(GL.GL_EQUAL);

        /* Multiply the source color (from the caustic luminance
       texture) with the previous color from the normal pass.  The
       caustics are modulated into the scene. */
        gl.glBlendFunc(GL.GL_ZERO, GL.GL_SRC_COLOR);
        gl.glEnable(GL.GL_BLEND);

        drawScene(gl, glu, PASS_CAUSTIC);

        /* Restore fragment operations to normal. */
        gl.glDepthMask(true);
        gl.glDepthFunc(GL.GL_LESS);
        gl.glDisable(GL.GL_BLEND);
//        }
        gl.glPopMatrix();

    }


/* Draw a floor (possibly textured). */
    static void drawFloor(GL gl, int pass) {
        /*
      if (pass == PASS_NORMAL) {
        if (HaveTexObj)
          gl.glBindTexture(GL_TEXTURE_2D, 100);
        else
          glCallList(100);
      }
      */

        /* The glTexCoord2f calls get ignored when in texture generation
           mode (ie, when modulating in caustics). */

        float floorVertices[][] = {
            {-20.0f, 0.0f, 20.0f},
            {20.0f, 0.0f, 20.0f},
            {20.0f, 0.0f, -20.0f},
            {-20.0f, 0.0f, -20.0f},
        };

        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3fv(floorVertices[0]);
        gl.glTexCoord2f(0.0f, 2.0f);
        gl.glVertex3fv(floorVertices[1]);
        gl.glTexCoord2f(2.0f, 2.0f);
        gl.glVertex3fv(floorVertices[2]);
        gl.glTexCoord2f(2.0f, 0.0f);
        gl.glVertex3fv(floorVertices[3]);
        gl.glEnd();

    }

    static void drawObject(GL gl, GLU glu, int pass) {
        GLUT glut = new GLUT();

        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glPushMatrix();

        glut.glutSolidSphere(glu, 6.0f, 12, 12);

        gl.glPopMatrix();

    }

}
















