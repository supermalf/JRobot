/********************************************
 *
 * This class implements a panel to OpenGL canvas
 *
 * @since JBobot v1.3, JDK v1.4
 * @version 2.00, 02/06/05
 * @author MALF
 *
 *******************************************/


package org.jrobot.gui.ogl;

import java.awt.*;
import javax.swing.*;

import net.java.games.jogl.*;
import org.jrobot.ogl.oglRenderer;


public class oglPanel extends JPanel implements oglFunctions {
    /**
     * Context objects
     */
    public static GLCanvas canvas;
    public static GLEventListener glEventListener;


    /* Window Default Values */
    public static int wndW = 800;
    public static int wndH = 800;

    /**
     * ogl constructor
     */
    public oglPanel()
    {
        super();

        /* Create Legend & canvas */
        canvas = GLDrawableFactory.getFactory().createGLCanvas(new GLCapabilities());
        canvas.addGLEventListener(new
                oglRenderer(canvas, 0, 0, 0, 0, 0, null));

        /* Add this to ogl Panel */
        setLayout(new java.awt.BorderLayout());
        add(canvas, BorderLayout.CENTER);

        /* Configure ogl Panel */
        setVisible(true);
        setOpaque(true);
    }

    public GLCanvas getCanvas() {
        return canvas;
    }

    public void newMap(int mapW, int mapH, final int cellSize, int wndW, int wndH, float[][] terrain) {
        //SetVisible (true);

        /* Setting Renderer*/
        oglRenderer.mapW = mapW;
        oglRenderer.mapH = mapH;
        oglRenderer.cellSize = cellSize;
        oglRenderer.wndW = wndW;
        oglRenderer.wndH = wndH;
        oglRenderer.terrain = terrain;
        oglRenderer.fovy = (float) (mapW + mapH) / 3.0f;

        /* Setting Draw*/
        //NO!

    }
}








