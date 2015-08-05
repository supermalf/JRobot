/**
 * @(#)dynamics.java 2.00 29/05/05
 *
 */


package org.jrobot.ogl;

import org.jrobot.tst.Game;

/* XXX TODO */


/**
 * This class implements:
 * - Colision test
 * - Place objects at random positions
 * - First Person movement
 *
 * @author MALF
 * @version 2.00, 02/06/05
 * @since JBobot v0.3, JDK v1.4
 *
 */

public class dynamics {

    private static float step;
    private static float step_z;


    /**
     * dynamics Constructor
     *
     * @param width    Terrain width
     * @param height   Terrain height
     * @param cellSize Number of pixels of a cell
     *
     */

    public dynamics(int width, int height, float cellSize) {
        super();

        step = cellSize / 1000.0f;
        step_z = 0.025f;
    }


    /**
     * First Person movement: Rotate left the object
     *
     * @param object [0] X coord
     *               [1] Y coord
     *               [2] Z coord
     *               [3] angle
     *
     */

    public void RotateLeft(float[] object) {
        float angle = object[3];

        if (angle == 270.0f)
            angle = 0.0f;

        else
            angle += 90.0f;

        object[3] = angle;
    }

    /**
     * First Person movement: Rotate right the object
     *
     * @param object [0] X coord
     *               [1] Y coord
     *               [2] Z coord
     *               [3] angle
     */

    public void RotateRight(float[] object) {
        float angle = object[3];

        if (angle == 0.0f)
            angle = 270.0f;

        else
            angle -= 90.0f;

        object[3] = angle;
    }

    /**
     * First Person movement: Move forward the object
     *
     * @param object [0] X coord
     *               [1] Y coord
     *               [2] Z coord
     *               [3] angle
     */

    public boolean MoveForward(float[] object) {
        float x = object[0];
        float y = object[1];
        float angle = object[3];


        if (angle == 0.0f)
            x += step;

        if (angle == 90.0f)
            y += step;

        if (angle == 180.0f)
            x -= step;

        if (angle == 270.0f)
            y -= step;


        float rx = Math.round(x * 1000.0f);
        rx /= 1000.0f;
        x = rx;
        float ry = Math.round(y * 1000.0f);
        ry /= 1000.0f;
        y = ry;


        if (testAllCollisions(x, y) == false) {

            int xm = (int) (Math.round(-(((oglDraw.x0 - x) / step))));
            int ym = (int) (Math.round(-(((oglDraw.y0 - y) / step))));

            object[0] = x;
            object[1] = y;
            object[2] = (oglRenderer.getDepth(xm, ym)) + (step / 2.0f);
            object[3] = angle;


            /* */
            jumpFlag(xm, ym);


            /* sucesseful moviment */
            return true;
        } else
            return false;

    }

    /**
     * First Person movement: Move backward the object
     *
     * @param object [0] X coord
     *               [1] Y coord
     *               [2] Z coord
     *               [3] angle
     */

    public boolean MoveBackward(float[] object) {
        float x = object[0];
        float y = object[1];
        float angle = object[3];

        if (angle == 0.0f)
            x -= step;

        if (angle == 90.0f)
            y -= step;

        if (angle == 180.0f)
            x += step;

        if (angle == 270.0f)
            y += step;


        float rx = Math.round(x * 1000.0f);
        rx /= 1000.0f;
        x = rx;
        float ry = Math.round(y * 1000.0f);
        ry /= 1000.0f;
        y = ry;


        if (testAllCollisions(x, y) == false) {
            int xm = (int) (Math.round(-(((oglDraw.x0 - x) / step))));
            int ym = (int) (Math.round(-(((oglDraw.y0 - y) / step))));

            object[0] = x;
            object[1] = y;
            object[2] = (oglRenderer.getDepth(xm, ym)) + (step / 2.0f);
            object[3] = angle;

            /* */
            jumpFlag(xm, ym);

            /* sucesseful moviment */
            return true;
        } else
            return false;

    }

    /**
     * First Person movement: Move upward the object
     *
     * @param object [0] X coord
     *               [1] Y coord
     *               [2] Z coord
     *               [3] angle
     */

    public void MoveUpward(float[] object) {
        float z = object[2];

        z += step_z;

        object[2] = z;
    }

    /**
     * First Person movement: Move downward the object
     *
     * @param object [0] X coord
     *               [1] Y coord
     *               [2] Z coord
     *               [3] angle
     */

    public void MoveDownward(float[] object) {
        float z = object[2];

        z -= step_z;

        object[2] = z;
    }


    /**
     * Set position:
     * - Place the robots in x,y position without checking collision
     *
     * Orientation:
     *
     * __ __ __ __
     * |  |  |  |  |  |
     * |__|__|__|__|
     * |  |  |  |  |  'S' represents the (0,0)
     * |__|__|__|__|   in a 4x4 map for example.
     * |  |  |  |  |
     * |__|__|__|__|
     * |  |  |  |  |
     * |S_|__|__|__|
     *
     * @param object [0] X coord
     *               [1] Y coord
     *               [2] Z coord
     *               [3] angle
     * @param xm     Object X new position
     * @param ym     Object Y new position
     * 
     */


    public static void SetPosition(float[] object, int xm, int ym, int angle) {
        object[0] = oglDraw.x0 + (step * xm);
        object[1] = oglDraw.y0 + (step * ym);
        object[2] = (oglRenderer.getDepth(xm, ym)) + (step / 2.0f);
        //object[3] = (float) ((int) Math.random() % 4) * 90;
        object[3] = (float) angle;
    }


    public static boolean testAllCollisions(float x, float y) {

        return testMapCollision(x, y);

    }

    public static boolean testMapCollision(float x, float y) {
        if ((x < oglDraw.x0) || (y < oglDraw.y0) ||
                (x > oglDraw.xMax) || (y > oglDraw.yMax))
            return true;  //had a collision

        else
            return false;
    }

    public static boolean testRobotCollision(float x, float y) {

        //Game controls this

        return false;
    }

    public static void jumpFlag(int xm, int ym) {
        float flagSize = oglDraw.getFlagSize();

        if (Game.explored[xm][ym] == 1)
            oglRenderer.robot[2] += flagSize;

    }


}
