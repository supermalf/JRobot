/**
 * @(#)oglRenderer.java 1.00 27/05/05
 *
 * TODO: Efeito similar a agua
 * TODO: funcao Draw.Robot receber tamanho
 * TODO: Classe de colisao
 * TODO: Camera fixa no robo
 * TODO: InputHandler para teclado e mouse?
 * TODO: Placar
 * TODO: Esquema de tirar .gl
 * TODO: Redimencionar robo para 5000 perde textura
 * TODO: Como simbolizar terreno jah explorardo?
 * TODO: (NERD) Fazer conta do mouse, TO DO abaixo
 * TODO: (NERD) Bandeira Billboard
 * TODO: Cursor explorardor
 * TODO: Timer display
 *
 * TODO: Funcoes basicas pedidas do robo, medir pressao....
 *       Instanciar varios robos, etc
 *
 */

package org.jrobot.ogl;

import java.awt.*;
import java.awt.event.*;
import java.util.ListIterator;
import java.util.LinkedList;
import java.rmi.RemoteException;

import net.java.games.jogl.*;
import net.java.games.jogl.util.GLUT;
import org.jrobot.game.GameUtils;
import org.jrobot.game.GUIData;
import org.jrobot.game.RemoteGame;
import org.jrobot.game.time.GameClock;
import org.jrobot.tst.Game;
import org.jrobot.ogl.de.LinkedList.pLinkedList;
import org.jrobot.log.*;
import org.jrobot.JRobot;


/********************************************
 *
 * This class implements the OpenGL renderer
 *
 * @since JBobot v0.1, JDK v1.4
 * @version 2.00, 27/05/05
 * @author $Author: Malf
 * @version $Id: oglRenderer.java,v 1.12 2005/07/05 08:50:15 savio Exp $
 *
 *******************************************/


public class oglRenderer implements GLEventListener, MouseListener, MouseMotionListener,
        MouseWheelListener, KeyListener
{

    /* OpenGL Context */
    private static GL gl;
    private static GLU glu;
    private static GLUT glut;
    public static GLCanvas canvas;

    /* Terrain param */
    public static int wndW, wndH, mapW, mapH, cellSize;
    public static float[][] terrain;

    /* Displays Lists */
    private static int terrainDL;
    private int robotDL;

    /* Global camera parameters */
    private float view_rotx = -10, view_roty = 0, view_rotz = 0;//-180;
    public static float fovy;//= 40.0f;
    private static float matrix[] = {1.0f, 0.0f, 0.0f, 0.0f,
                                     0.0f, 1.0f, 0.0f, 0.0f,
                                     0.0f, 0.0f, 1.0f, 0.0f,
                                     0.0f, 0.0f, 0.0f, 1.0f};

    /* Camera transformations */
    private float dx, dy, dz, rz;
    private float DZ = 0.5f, DL = 0.25f;


    /* Mouse manipulation */
    private int mouseX, mouseY;
    private int mouse_button;
    private float MouseDZ = 0.6f;
    private float lastx, lastz;


    /* FPS math */
    private int fpsFrame, FPS;
    private long lastCount1, lastCount2;

    /* Textures */
    private int texture;

    /* Modes */
    private static boolean HelpMode     = true;
    private static boolean ScoreMode    = true;
    private static boolean CommandMode  = true;
    private static boolean TimeMode     = true;
    private static boolean TrafficMode  = true;
    public  static boolean manualReady  = false;


    /* TODO: XXX Esses valores devem estar numa matriz individual para cada robo*/

    private static oglDraw Draw;
    private static oglText Text;
    private static scoreBoard ScoreBoard;
    public  static commandBoard CommandBoard;
    private static timeBoard TimeBoard;
    private static dynamics Dynamics;
    private static TrafficLightBoard TrafficLightBoard;
    private static pLinkedList Flags;
    private static pLinkedList Robots;

    public static float[] robot = {0.0f, 0.0f, 0.0f, 0.0f}; //X , Y, , Z , angle

    private static int timeDebug = 0;

    private static boolean start = true;

    /* Starting draw? */
    private static boolean draw = false;

    private static GUIData guidata;
    private static boolean load_guidata = true;


    /**
     * oglRenderer Constructor
     *
     * @param canvas   OpenGL context
     * @param mapW     Terrain width
     * @param mapH     Terrain height
     * @param cellSize Number of pixels of a cell
     * @param wndW     Window width
     * @param wndH     Window height
     * @param terrain  Matrix with the heght map
     *
     */

    public oglRenderer(GLCanvas canvas, int mapW, int mapH, int cellSize, int wndW, int wndH, float[][] terrain) {
        oglRenderer.canvas = canvas;
        oglRenderer.mapW = mapW;
        oglRenderer.mapH = mapH;
        oglRenderer.cellSize = cellSize;
        oglRenderer.wndW = wndW;
        oglRenderer.wndH = wndH;
        oglRenderer.terrain = terrain;
    }


    /**
     * Called by the drawable immediately after the OpenGL context is
     * initialized for the first time. Can be used to perform one-time OpenGL
     * initialization such as setup of lights and display lists.
     *
     * @param drawable The GLDrawable object.
     *
     */

    public void init(GLDrawable drawable) {
        /* Getting OpenGL Context */
        gl = drawable.getGL();
        glu = drawable.getGLU();
        glut = new GLUT();

        /* Creating Screen Objects */
        Draw = new oglDraw(gl, mapW, mapH, (float) cellSize / 1000.0F);
        Text = new oglText(gl, glut);
        ScoreBoard = new scoreBoard(gl, glut);
        CommandBoard = new commandBoard(gl, glut);
        TimeBoard = new timeBoard(gl, glut);
        TrafficLightBoard = new TrafficLightBoard(gl, glut);
        Dynamics = new dynamics(mapW, mapH, cellSize);
        Flags = new pLinkedList();

        //Log.addGUI(CommandBoard);

        gl.glShadeModel(GL.GL_SMOOTH);
        gl.glEnable(GL.GL_NORMALIZE);
        gl.glEnable(GL.GL_AUTO_NORMAL);

        /* Light 0 */
        float pos0[] = {-1, -1, 1, 1};
        float dif0[] = {0.2f, 0.3f, 0.4f, 1};
        float amb0[] = {0.2f, 0.2f, 0.2f, 1};
        float spe0[] = {1, 1, 1, 1};

        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, pos0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, dif0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, spe0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, amb0);


        /* Light 1 */
        float pos1[] = {0, 0, 0, 1};
        float dif1[] = {0.4f, 0.4f, 0.4f, 1};
        float amb1[] = {0.2f, 0.2f, 0.2f, 1};
        float spe1[] = {0.6f, 0.6f, 0.6f, 1};

        gl.glTranslated(0.0, 0.0, (double) -6);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, pos1);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, dif1);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, spe1);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, amb1);


        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glLightModeli(GL.GL_LIGHT_MODEL_LOCAL_VIEWER, 1);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_LIGHT1);

/*
        //Make the terrain - Display List
        terrainDL = gl.glGenLists(1);
        {
            gl.glNewList(terrainDL, GL.GL_COMPILE);
            Image.GaussFilter(terrain, mapW, mapH);


            Draw.WireframeMode = false;
            //Draw.Grid();
            Draw.Terrain(terrain);
            gl.glEndList();
        }
*/
        //Creating the Robot texture
        float[] color = {1.0f, 1.0f, 1.0f, 1.0f};
        Image.makeTexture2D(gl, glu, texture, color, GameUtils.getTexturePath() + "robots.png");

        makeRobot();
        //makeTerrain();
        //setDraw(false);




        /* Attaching methods */
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addMouseWheelListener(this);
        drawable.addKeyListener(this);
    }


    public static void makeTerrain()
    {
        //gl.glDeleteLists(terrainDL, 1);

        //terrainDL = gl.glGenLists(1);
        //{
          //  gl.glNewList(terrainDL, GL.GL_COMPILE);
            Image.GaussFilter(terrain, mapW, mapH);

            Draw.WireframeMode = false;
            //Draw.Grid();
            Draw.Terrain(terrain);
           // gl.glEndList();
        //}


     }

    private void makeRobot()
    {
        /* Make the Robot - Display List */

        float[] color = {1.0f, 1.0f, 1.0f, 1.0f};

        robotDL = gl.glGenLists(1);
        {
            gl.glNewList(robotDL, GL.GL_COMPILE);
            //Draw = new oglDraw(gl,mapW, mapH, (float)cellSize/1000.0F);
            Draw.Robot(color, (float) cellSize / 5000.0f);
            gl.glEndList();
        }
    }

    public static void setDraw (boolean set)
    {
        draw = set;
    }


    /**
     * Called by the drawable during the first repaint after the component has
     * been resized. The client can update the viewport and view volume of the
     * window appropriately, for example by a call to
     * GL.glViewport(int, int, int, int); note that for convenience the component
     * has already called GL.glViewport(int, int, int, int)(x, y, width, height)
     * when this method is called, so the client may not have to do anything in
     * this method.
     *
     * @param drawable The GLDrawable object.
     * @param x        The X Coordinate of the viewport rectangle.
     * @param y        The Y coordinate of the viewport rectanble.
     * @param width    The new width of the window.
     * @param height   The new height of the window.
     *
     */

    public void reshape(GLDrawable drawable, int x, int y, int width, int height) {
        gl = drawable.getGL();
        glu = drawable.getGLU();

        wndH = height;
        wndW = width;

        gl.glViewport(0, 0, width, height);

        if(draw)
            gl.glClearColor(0.008f, 0.62f, 0.8f, 1.0F);

        else
            gl.glClearColor(0.25f, 0.25f, 0.25f, 1.0F);

        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_LIGHT1);
        gl.glEnable(GL.GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE);
    }


    /**
     * OpenGL tradicional loop.
     * Called by the drawable to initiate OpenGL rendering by the client.
     * After all GLEventListeners have been notified of a display event, the
     * drawable will swap its buffers if necessary.
     *
     * @param drawable The GLDrawable object.
     *
     */

    public void display(GLDrawable drawable)
    {
        /* Getting GUI DATA*/
       if(JRobot.remoteGame != null)
       {
           try
           {

                if (JRobot.remoteGame.isGameStarted())
                {
                        GUIData guidata = JRobot.remoteGame.getVisualData();
                        JRobot.guidata = guidata;
                        load_guidata = false;

                }
            }
           catch(RemoteException e)
           {
              System.err.println(e);
           }
       }



        gl = drawable.getGL();

        int vp[] = new int[5];

        /* Background */
        if(draw)
            gl.glClearColor(0.008f, 0.62f, 0.8f, 1.0F);

        else
            gl.glClearColor(0.25f, 0.25f, 0.25f, 1.0F);
        

        gl.glGetIntegerv(GL.GL_VIEWPORT, vp);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(fovy, (float) vp[2] / vp[3], 1.0, 100.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glMultMatrixf(matrix);
        glu.gluLookAt(0, 2, 10, -0.1, -0.1, 0, 0, 1, 0);


        /* Rotation Matrix */
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(view_rotz, 0.0f, 0.0f, 1.0f);


        /* Fixing terrain BUG */
        if (terrain == null)
        {
            Log.bug("Error: JOGL BUG! Exiting...");
            System.err.println("Error: JOGL BUG! Exiting...");
            GameUtils.handleExit();
            //terrain = Game.terrain;
        }


        /* Draw scene */
        gl.glPushMatrix();


        if(draw)
        {


 /*
    //TEAM COLORS
            float[] color1 = {1.0f, 0.2f, 0.2f, 1.0f};



            //Flags
            for (int i = 0; i < Flags.size(); i++) {
                gl.glPushMatrix();

                gl.glTranslatef(Flags.peek(i, 'x'), Flags.peek(i, 'y'), Flags.peek(i, 'z'));
                Draw.Flag(color1, (float) cellSize / 1000, 0.1f);

                gl.glPopMatrix();
            }

 */

            /* Flag */
            if(JRobot.remoteGame != null)
            {
            try {
                if (JRobot.remoteGame.isGameStarted() && JRobot.guidata.flags!=null )
                {
                    //LinkedList list = guidata.scoreList;
                    ListIterator flagIterator = JRobot.guidata.flags.listIterator(0);
                    GUIData.GUIFlag flag;

                    while(flagIterator.hasNext())
                    {
                        flag = (GUIData.GUIFlag) flagIterator.next();


                        float[] pos = new float[4];
                        dynamics.SetPosition(pos, flag.width, flag.height, 0);

                        gl.glPushMatrix();
                        gl.glTranslatef(pos[0], pos[1], pos[2]);
                        Draw.Flag(flag.color, (float) cellSize / 1000, 0.1f);
                        gl.glPopMatrix();

                    }
                }
            } catch (RemoteException e) {}
            }




            /* UnderWater Effect - OFF */
            //oglCaustics.display(gl, glu);


            /* Draw Terrain */
            Draw.Terrain(terrain);


            /* Score */
            if(JRobot.remoteGame != null)
            {
                try {
                if (JRobot.remoteGame.isGameStarted() && JRobot.guidata.scoreList!=null )
                {
                    //LinkedList list = guidata.scoreList;
                    ListIterator scoreIterator = JRobot.guidata.scoreList.listIterator(0);
                    GUIData.GUIScore score;

                    while(scoreIterator.hasNext())
                    {
                        score = (GUIData.GUIScore) scoreIterator.next();
                        ScoreBoard.Insert(score.teamName, score.color, score.score);
                    }
                }
                } catch(RemoteException e) {}
            }
            ScoreBoard.Show(ScoreMode);


            /* Command Board */
            CommandBoard.Show(CommandMode);


            /* Time */

            try{

                if (JRobot.remoteGame.isGameStarted() )
                {
                    TimeBoard.Show(TimeMode, JRobot.guidata.clock);
                }
            }
            catch(RemoteException e)
            {
                //XXX
            }

            /* Traffic Lights */
            if(JRobot.manualControl != null)
                TrafficLightBoard.Show(TrafficMode, manualReady);



            gl.glPopMatrix();




            /* Robots */
            oglDraw Draw = new oglDraw(gl, mapW, mapH, (float) cellSize / 1000);

            gl.glEnable(GL.GL_TEXTURE_2D);
            if(JRobot.remoteGame != null)
            {
                try {
                if (JRobot.remoteGame.isGameStarted() && JRobot.guidata.robots!=null )
                {
                    ListIterator robotsIterator = JRobot.guidata.robots.listIterator(0);
                    GUIData.GUIRobot robot;
                    while(robotsIterator.hasNext())
                    {
                        robot = (GUIData.GUIRobot) robotsIterator.next();
                                                 //terrain[robot.width][robot.height]

                        float[] pos = new float[4];
                        dynamics.SetPosition(pos, robot.width, robot.height, robot.angle);


                        gl.glPushMatrix();
                        gl.glTranslatef(pos[0], pos[1], pos[2]);    //Movimento no eixo
                        gl.glRotatef(pos[3], 0.0f, 0.0f, 1.0f);     //Rotacao

                        Draw.Robot(robot.color, cellSize / 4000.0f);
                        Text.ObjectText(robot.name, robot.color);

                         gl.glPopMatrix();
                    }
                }
                } catch(RemoteException e) {}
            }
            gl.glDisable(GL.GL_TEXTURE_2D);


            /* Mouse/Keyboard Transformations */
            gl.glPushMatrix();
            gl.glLoadIdentity();
            gl.glTranslatef(dx, dy, dz);
            gl.glRotatef(rz, 0.0f, 0.0f, 1.0f);
            gl.glMultMatrixf(matrix);
            gl.glGetFloatv(GL.GL_MODELVIEW_MATRIX, matrix);
            gl.glPopMatrix();

            /* Clear Transformations */
            dx = dy = dz = rz = 0.0f;

            /* Show/Hide Help */
            if (HelpMode)
                showhelp();

            /* Calculate FPS */
            mFPS();

            gl.glPopMatrix();

        }
    }

    /**
     * Called when the display mode has been changed.
     * <B>!! CURRENTLY UNIMPLEMENTED IN JOGL !!</B>
     *
     * @param drawable      The GLDrawable object.
     * @param modeChanged   Indicates if the video mode has changed.
     * @param deviceChanged Indicates if the video device has changed.
     *
     */

    public void displayChanged(GLDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }


    /**
     *
     * Handler called when the mouse button is pressed
     * over the RoundButton.  Again this is called if the
     * button is pressed anywhere within the bounding
     * rectangle.
     *
     * @param e reference to a MouseEvent object describing
     *          the mouse press.
     *
     */
    public void mousePressed(MouseEvent e)
    {
        mouse_button = e.getButton();

        /* Left Mouse Button - ROTATION */
        if (mouse_button == 1) {
            lastx = (float) e.getX();
            lastz = (float) e.getY();
        }

        /* Right Mouse Button - MOVIMENT */
        if (mouse_button == 3) {
            canvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }


    /**
     *
     * Handler called when the mouse button.
     *
     * @param e reference to a MouseEvent object describing
     *          the mouse release.
     *
     */
    public void mouseReleased(MouseEvent e) {
        canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }


    /**
     *
     * Called when the mouse is dragged.
     *
     * @param e MouseEvent that describes the mouse dragging.
     *
     */

    public void mouseDragged(MouseEvent e) {
        /* Left Mouse Button */
        if (mouse_button == 1) {
            int x = e.getX();
            int y = e.getY();
            Component component = e.getComponent();
            Dimension dim = component.getSize();
            int width = dim.width;
            int height = dim.height;

            view_rotz += 360.0f * ((float) (x - lastx) / (float) width);
            view_rotx += 360.0f * ((float) (y - lastz) / (float) height);

            //Remember where
            lastx = (float) x;
            lastz = (float) y;
        }

        /* Right Mouse Button */
        if (mouse_button == 3) {
            int x = e.getX();
            int y = e.getY();

            float mouse_speed = (mapW + mapH) / 10.f;            /* TODO: (NERD) Verificar essa conta */

            //Right move
            if (x > mouseX) {
                dx = (x - mouseX) * mouse_speed / wndW;
                mouseX = x;
            }

            //Left move
            else if (x < mouseX) {
                dx = (x - mouseX) * mouse_speed / wndW;
                mouseX = x;
            }

            //Top move
            if (y > mouseY) {
                dy = (mouseY - y) * mouse_speed / wndH;
                mouseY = y;
            }
            //Bottom move
            else if (y < mouseY) {
                dy = (mouseY - y) * mouse_speed / wndH;
                mouseY = y;
            }
        }
    }


    /**
     *
     * Called when the mouse is moved.
     *
     * @param e MouseEvent that describes the mouse motion.
     *
     */

    public void mouseMoved(MouseEvent e) {
    }


    /**
     *
     * Handler called when the mouse enters the
     * RoundButton.
     *
     * @param e reference to a MouseEvent object describing
     *          the mouse entry.
     *
     */

    public void mouseEntered(MouseEvent e) {
    }


    /**
     *
     * Handler called when the mouse enters the
     * RoundButton.
     *
     * @param e reference to a MouseEvent object describing
     *          the mouse entry.
     *
     */

    public void mouseExited(MouseEvent e) {}


    /**
     *
     * Handler called when the mouse enters the
     * RoundButton.
     *
     * @param e reference to a MouseEvent object describing
     *          the mouse entry.
     *
     */

    public void mouseClicked(MouseEvent e) {
    }


    /**
     *
     * Handler called when the mouse enters the
     * RoundButton.
     *
     * @param e reference to a MouseEvent object describing
     *          the mouse entry.
     *
     */

    public void mouseWheelMoved(MouseWheelEvent e) {
        int rot;
        rot = e.getWheelRotation();

        /* Wheel Foward - ZOOM IN*/
        if (rot == -1)
            fovy -= MouseDZ;

        /* Wheel Back - ZOOM OUT*/
        else
            fovy += MouseDZ;
    }


    /**
     *
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param event The KeyEvent.
     *
     */

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        char Charkey = event.getKeyChar();

/*

        if (key == KeyEvent.VK_M) {
            //dynamics.SetPosition(robot, 0, 0);
        }

        if (key == KeyEvent.VK_HOME)
        {

            Flags.insertEnd(robot[0], robot[1], robot[2]);

            //Nao preciso setar?!
            float x = robot[0];
            float y = robot[1];
            float rx = Math.round(x * 1000.0f);
            rx /= 1000.0f;
            x = rx;
            float ry = Math.round(y * 1000.0f);
            ry /= 1000.0f;
            y = ry;
            int xm = (int) (Math.round(-(((oglDraw.x0 - x) / (cellSize / 1000.0f)))));
            int ym = (int) (Math.round(-(((oglDraw.y0 - y) / (cellSize / 1000.0f)))));
            Game.explored[xm][ym] = 1;
        }

        */

        if (key == KeyEvent.VK_ESCAPE) {
            GameUtils.handleExit();
        }

        if (key == KeyEvent.VK_F1) {
            HelpMode = !HelpMode;
        }

        if (key == KeyEvent.VK_F2) {
            ScoreMode = !ScoreMode;
        }

        if (key == KeyEvent.VK_F3) {
            CommandMode = !CommandMode;
        }

        if (key == KeyEvent.VK_F4) {
            TimeMode = !TimeMode;
        }

        if (Charkey == '+') {
            fovy -= DZ;
        }

        if (Charkey == '-') {
            fovy += DZ;
        }

        if (key == KeyEvent.VK_W) {
            dz += DZ;
        }

        if (key == KeyEvent.VK_S) {
            dz -= DZ;
        }

        if (key == KeyEvent.VK_A) {
            dx -= DL;
        }

        if (key == KeyEvent.VK_D) {
            dx += DL;
        }

        if (key == KeyEvent.VK_R) {
            dy += DL;
        }

        if (key == KeyEvent.VK_F) {
            dy -= DL;
        }


        /* Manual Controler
            - ON when manualcontrol and ready */
        try {
        if ((JRobot.manualControl != null) && (manualReady == true) && JRobot.remoteGame.isGameStarted())

        {
            /* Blocking command */
            manualReady = false;

            /* Turn Left */
            if (key == KeyEvent.VK_LEFT) {
                //Dynamics.RotateLeft(robot);
                JRobot.manualControl.typeKey(key);
            }
            /* Turn Right */
            else if (key == KeyEvent.VK_RIGHT) {
                //Dynamics.RotateRight(robot);
                JRobot.manualControl.typeKey(key);
            }
            /* Move Forward */
            else if (key == KeyEvent.VK_UP) {
                //Dynamics.MoveForward(robot);
                JRobot.manualControl.typeKey(key);
            }
            /* Move Backward */
            else if (key == KeyEvent.VK_DOWN) {
                //Dynamics.MoveBackward(robot);
                JRobot.manualControl.typeKey(key);
            }
            /* Get Gradient */
            else if (key == KeyEvent.VK_G) {
                JRobot.manualControl.typeKey(key);
            }
            /* Get Position */
            else if (key == KeyEvent.VK_L) {
                JRobot.manualControl.typeKey(key);
            }
            /* Get Pressure */
            else if (key == KeyEvent.VK_P) {
                JRobot.manualControl.typeKey(key);
            }
            /* Prospect*/
            else if (key == KeyEvent.VK_SPACE) {
                JRobot.manualControl.typeKey(key);
            }
            /* Get Time*/
            else if (key == KeyEvent.VK_T) {
                JRobot.manualControl.typeKey(key);
            }
        }
        }
        catch(RemoteException e) {
        }
        /* Not Used anymore!*/
        /*
        if (key == KeyEvent.VK_PAGE_UP) {
            Dynamics.MoveUpward(robot);
        }

        if (key == KeyEvent.VK_PAGE_DOWN) {
            Dynamics.MoveDownward(robot);
        }
        */

        if (key == KeyEvent.VK_END) {
            //manualReady = !manualReady;
            float[] color2 = {0.0f, 1.0f, 1.0f};
            ScoreBoard.Insert("TEAM NERD", color2, 0.4f);
            //Bloquar acoes do teclado
        }
    }


    /**
     *
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param event The KeyEvent.
     *
     */

    public void keyReleased(KeyEvent event) {
    }


    /**********************************************************************
     *
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param event The KeyEvent.
     *
     * *********************************************************************/


    public void keyTyped(KeyEvent event) {
    }


    /**
     * Show the help text
     *
     */

    void showhelp()
    {
        //oglText Text = new oglText(gl, glut);
        float[] color = {1.0f, 1.0f, 1.0f};

        /* Manual Control Help */
        if (JRobot.manualControl != null)
        {
            Text.ScreenText("FPS: " + FPS, color,                       -0.95f, -0.33f);
            Text.ScreenText("** Help(F1) - Commands list: **", color,   -0.95f, -0.38f);
            Text.ScreenText("F2: Show/Hide Score Board", color,         -0.95f, -0.43f);
            Text.ScreenText("F3: Show/Hide Commands", color,            -0.95f, -0.48f);
            Text.ScreenText("F4: Show/Hide GetTime", color,             -0.95f, -0.53f);
            Text.ScreenText("W,S,A,D,R,F: move viewer", color,          -0.95f, -0.58f);
            Text.ScreenText("+,-: zoom in/out", color,                  -0.95f, -0.63f);
            Text.ScreenText("arrow keys: move robot", color,            -0.95f, -0.68f);
            Text.ScreenText("G: Get Gradient", color,                   -0.95f, -0.73f);
            Text.ScreenText("L: Get Position", color,                   -0.95f, -0.78f);
            Text.ScreenText("P: Get Pressure", color,                   -0.95f, -0.83f);
            Text.ScreenText("T: Get Time", color,                       -0.95f, -0.88f);
            Text.ScreenText("SPACE: Prospect", color,                   -0.95f, -0.93f);
            Text.ScreenText("ESC: exit", color,                         -0.95f, -0.98f);
        }
        /* Manual OFF */
        else
        {
            Text.ScreenText("FPS: " + FPS, color,                       -0.95f, -0.63f);
            Text.ScreenText("** Help(F1) - Commands list: **", color,   -0.95f, -0.68f);
            Text.ScreenText("F2: Show/Hide Score Board", color,         -0.95f, -0.73f);
            Text.ScreenText("F3: Show/Hide Commands", color,            -0.95f, -0.78f);
            Text.ScreenText("F4: Show/Hide GetTime", color,             -0.95f, -0.83f);
            Text.ScreenText("W,S,A,D,R,F: move viewer", color,          -0.95f, -0.88f);
            Text.ScreenText("+,-: zoom in/out", color,                  -0.95f, -0.93f);
            Text.ScreenText("ESC: exit", color,                         -0.95f, -0.98f);
        }

    }


    /**
     * Calculates the scene frame per second
     *
     */

    private void mFPS() {
        //FPS
        fpsFrame++;
        lastCount1 = System.currentTimeMillis();

        if (lastCount1 - lastCount2 > 1000) {
            FPS = (int) ((double) fpsFrame * 1000.0 / (double) (lastCount1 - lastCount2));
            lastCount2 = lastCount1;
            fpsFrame = 0;
        }
        //oglText.putstring("FPS: " + FPS, -0.95f,-0.51f);
    }

    /**
     *
     * Set Texture id
     *
     * @param texture Texture id
     *
     */

    public void setTexture(int texture) {
        this.texture = texture;
    }

    /**
     *
     * Exports command list to other objects
     *
     */

    public static void showHelp() {
        HelpMode = !HelpMode;
    }

    /**
     * Exports time to other objects
     *
     */

    public static void showTime() {
        TimeMode = !TimeMode;
    }

    /**
     *
     * Exports score to other objects
     *
     */

    public static void showScore() {
        ScoreMode = !ScoreMode;
    }

    /**
     *
     * Exports score to other objects
     *
     */

    public static void showCommandList() {
        CommandMode = !CommandMode;
    }

    public static float getDepth(int x, int y) {



        try {
            return terrain[x][y];
        } catch (Exception e) {
            System.err.println("Error: Not valid depth position!");
            return 0.0f;
        }
    }
}


