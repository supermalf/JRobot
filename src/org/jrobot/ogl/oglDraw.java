/**
 * @(#)oglDraw.java 2.00 27/05/05
 *
 * TODO: Erro na fincao de caixa: escala 0.05-40x40 (Erro do glPolygon)
 * TODO: (NERD) Reduzir tamando do robo, de um paralel. para um cubo...
 */


package org.jrobot.ogl;

import net.java.games.jogl.GL;

/**
 * This class implements new OpenGL objects
 *
 * @author MALF
 * @author TUCANO
 * @version 2.00, 27/05/05
 * @since JBobot v0.1, JDK v1.4
 */


public class oglDraw implements oglObjects {
    public boolean WireframeMode;

    private int width, height;
    private static float cellSize, posX, posY, posY2, posZ;
    private GL gl;

    public static float x0, y0, xMax, yMax;


    /**
     * oglDraw Constructor
     *
     * @param gl       OpenGL context
     * @param width    Terrain width
     * @param height   Terrain height
     * @param cellSize Number of pixels of a cell
     *
     */

    public oglDraw(GL gl, int width, int height, float cellSize) {
        super();

        this.gl = gl;
        this.width = width;
        this.height = height;
        oglDraw.cellSize = cellSize;

        posX = -((float) width * cellSize) / 2.0F;
        posY = -((float) height * cellSize) / 2.0F;
        posZ = (posX + posY) / 4.0F;

        xMax = (((float) width * cellSize) / 2.0F) - cellSize;
        yMax = (((float) height * cellSize) / 2.0F) - cellSize;

        x0 = (((float) width * -cellSize) / 2.0F);
        y0 = (((float) height * -cellSize) / 2.0F);
    }


    /**
     * Draw the terrain
     *
     * @param terrain Matrix with the heght map
     *
     */

    public void Terrain(float[][] terrain) {
        int i, j;
        float[][][] normal;
        normal = new float[width + 1][height + 1][3];

        /* Calculate the cells light normals*/
        TerrainNormal(terrain, normal);

        /* Draw the Terrain Box*/
        TerrainBox(terrain);

        /* Draw the Terrain */

        for (i = 0; i < width - 1; i++) {
            if (WireframeMode)
                gl.glBegin(GL.GL_LINE_STRIP);

            else
                gl.glBegin(GL.GL_QUAD_STRIP);

            posY2 = posY;

            for (j = 0; j < height; j++) {
                gl.glColor3f(0.0F, terrain[i][j], 0.5f);
                gl.glNormal3f(normal[i][j][0], normal[i][j][1], normal[i][j][2]);
                gl.glVertex3f(posX, posY2, terrain[i][j]);

                gl.glColor3f(0.0F, terrain[i + 1][j], 0.5f);
                gl.glNormal3f(normal[i + 1][j][0], normal[i][j][1], normal[i][j][2]);
                gl.glVertex3f(posX + cellSize, posY2, terrain[i + 1][j]);

                posY2 += cellSize;
            }

            gl.glEnd();
            posX += cellSize;
        }

        //  gl.glPopMatrix();
    }

    /**
     * Calculate the light normal to every cell
     *
     * @param terrain Matrix with the heght map
     * @param normal  Matrix with the normal map
     *
     */

    private void TerrainNormal(float[][] terrain, float[][][] normal) {
        int i, j;
        float norma;
        float ny, nx;
        float f0, f1;

        /* Calculate the Edges normals
        for (i=0; i<width; i++)
        {
            normal[i][0][0] = 0.0f;
            normal[i][0][1] = 0.0f;
            normal[i][0][2] = 1.0f;

            normal[i][height][0] = 0.0f;
            normal[i][height][1] = 0.0f;
            normal[i][height][2] = 1.0f;

            normal[0][i][0] = 0.0f;
            normal[0][i][1] = 0.0f;
            normal[0][i][2] = 1.0f;

            normal[width][i][0] = 0.0f;
            normal[width][i][1] = 0.0f;
            normal[width][i][2] = 1.0f;
        }*/

        /* Calculate the Center normals */
        for (i = 1; i < width - 1; i++) {
            for (j = 1; j < height - 1; j++) {

                /* X GetGradient */
                f0 = terrain[i][j + 1];
                f1 = terrain[i][j - 1];
                ny = (f1 - f0) / (2.0f * cellSize);

                /* Y GetGradient */
                f0 = terrain[i + 1][j];
                f1 = terrain[i - 1][j];
                nx = (f1 - f0) / (2.0f * cellSize);

                norma = (float) Math.sqrt((double) (nx * nx * ny * ny + 1.0f));
                nx /= norma;
                ny /= norma;

                /* Saving the normals */
                normal[i][j][0] = nx;
                normal[i][j][1] = ny;
                normal[i][j][2] = 1.0f / norma;
            }
        }
    }


    /**
     *
     * Draw the terrain box
     *
     * @param terrain Matrix with the heght map
     *
     */

    private void TerrainBox(float[][] terrain) {
        int b;

        if (WireframeMode)
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);


        /* Front */
        gl.glColor3f(0.48f, 0.48f, 0.48f);
        gl.glBegin(GL.GL_POLYGON);

        gl.glVertex3f(-(posX + cellSize), posY, posZ);
        gl.glVertex3f(posX, posY, posZ);
        gl.glVertex3f(posX, posY, 0.0f);
        b = 0;
        for (float a = posX; a < -(posX + 0.02f); a += cellSize) {
            gl.glVertex3f(a, posY, terrain[b][0]);
            b++;
        }
        gl.glEnd();


        /* Back */
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3f(-(posX + cellSize), -(posY + cellSize), posZ);
        gl.glVertex3f(posX, -(posY + cellSize), posZ);
        gl.glVertex3f(posX, -(posY + cellSize), 0.0f);
        b = 0;
        for (float a = posX; a < -(posX + 0.02f); a += cellSize) {
            gl.glVertex3f(a, -(posY + cellSize), terrain[b][height - 1]);
            //System.out.println("terrain[b][39]:" + (terrain[b][height-1]));
            b++;
        }
        gl.glEnd();


        /* Right */
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        gl.glBegin(GL.GL_POLYGON);

        gl.glVertex3f(-(posX + cellSize), -(posY + cellSize), posZ);
        gl.glVertex3f(-(posX + cellSize), posY, posZ);
        gl.glVertex3f(-(posX + cellSize), posY, 0.0f);

        b = 0;
        for (float a = posY; a < -(posY + 0.02f); a += cellSize) {
            gl.glVertex3f(-(posX + cellSize), a, terrain[width - 1][b]);
            b++;
        }
        gl.glEnd();


        /* Left*/
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3f(posX, -(posY + cellSize), posZ);
        gl.glVertex3f(posX, posY, posZ);
        gl.glVertex3f(posX, posY, 0.0f);
        b = 0;
        for (float a = posY; a < -(posY + 0.02f); a += cellSize) {
            gl.glVertex3f(posX, a, terrain[0][b]);
            b++;
        }
        gl.glEnd();


        /* top */
        gl.glColor3f(0.2f, 0.2f, 0.2f);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(posX, posY, posZ);
        gl.glVertex3f(-(posX + cellSize), posY, posZ);
        gl.glVertex3f(-(posX + cellSize), -(posY + cellSize), posZ);
        gl.glVertex3f(posX, -(posY + cellSize), posZ);
        gl.glVertex3f(posX, posY, posZ);
        gl.glEnd();

    }


    /**
     *
     * Draw the robot
     *
     * @param color robot's color
     * @param size  robot's size
     *
     */

    public void Robot(float[] color, float size) {
        float tam = size * 0.5f;
        gl.glBegin(GL.GL_QUADS);

        /**********************************************************************
         comeco paralelepipedo
         /**********************************************************************/

        //Chao
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glNormal3f(0.0F, 0.0F, -1.0f);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, -2.0f * tam);
        gl.glVertex3f(0.5f * tam, -2.0f * tam, -2.0f * tam);
        gl.glVertex3f(0.5f * tam, 2.0f * tam, -2.0f * tam);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, -2.0f * tam);
        gl.glColor3fv(color);

        //Teto
        gl.glNormal3f(1.0f, 0.0F, 1.0f);
        gl.glTexCoord2f(0.43f, 0.415f);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.97f, 0.415f);
        gl.glVertex3f(0.5f * tam, -2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.97f, 0.65f);
        gl.glVertex3f(0.5f * tam, 2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.43f, 0.65f);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, 2.0f * tam);


        //Lado E
        gl.glNormal3f(-1.0f, 0.0F, 0.0F);
        gl.glTexCoord2f(0.361f, 0.165f);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, -2.0f * tam);
        gl.glTexCoord2f(0.361f, 0.347f);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.049f, 0.347f);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.049f, 0.165f);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, -2.0f * tam);

        //Frente
        gl.glNormal3f(0.0F, -1.0f, 0.0F);
        gl.glTexCoord2f(0.96f, 0.965f);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.42f, 0.965f);
        gl.glVertex3f(0.5f * tam, -2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.42f, 0.73f);
        gl.glVertex3f(0.5f * tam, -2.0f * tam, -2.0f * tam);
        gl.glTexCoord2f(0.96f, 0.73f);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, -2.0f * tam);

        //Tras
        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glTexCoord2f(0.96f, 0.73f);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, -2.0f * tam);
        gl.glTexCoord2f(0.96f, 0.965f);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.42f, 0.965f);
        gl.glVertex3f(0.5f * tam, 2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.42f, 0.73f);
        gl.glVertex3f(0.5f * tam, 2.0f * tam, -2.0f * tam);

        /**********************************************************************
         fim Paralelepipedo
         /**********************************************************************/

        /**********************************************************************
         comeco trapezio
         /**********************************************************************/

        gl.glColor3f(1.0f, 1.0f, 1.0f);

        //Teto
        gl.glNormal3f(1.0f, 0.0F, 1.0f);
        gl.glTexCoord2f(0.420f, 0.378f);
        gl.glVertex3f(0.5f * tam, -2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.420f, 0.202f);
        gl.glVertex3f(2.5f * tam, -2.0f * tam, tam);
        gl.glTexCoord2f(0.660f, 0.202f);
        gl.glVertex3f(2.5f * tam, 2.0f * tam, tam);
        gl.glTexCoord2f(0.660f, 0.378f);
        gl.glVertex3f(0.5f * tam, 2.0f * tam, 2.0f * tam);



        //Lado D
        gl.glNormal3f(1.0f, 0.0F, 0.0F);
        gl.glTexCoord2f(0.970f, 0.015f);
        gl.glVertex3f(2.5f * tam, 2.0f * tam, -2.0f * tam);
        gl.glTexCoord2f(0.970f, 0.195f);
        gl.glVertex3f(2.5f * tam, 2.0f * tam, tam);
        gl.glTexCoord2f(0.700f, 0.195f);
        gl.glVertex3f(2.5f * tam, -2.0f * tam, tam);
        gl.glTexCoord2f(0.700f, 0.015f);
        gl.glVertex3f(2.5f * tam, -2.0f * tam, -2.0f * tam);

        //Frente
        gl.glNormal3f(0.0F, -1.0f, 0.0F);
        gl.glTexCoord2f(0.304f, 0.975f);
        gl.glVertex3f(0.5f * tam, -2.0f * tam, 2.0f * tam);
        gl.glTexCoord2f(0.065f, 0.735f);
        gl.glVertex3f(2.5f * tam, -2.0f * tam, tam);  //0
        gl.glTexCoord2f(0.065f, 0.520f);
        gl.glVertex3f(2.5f * tam, -2.0f * tam, -2.0f * tam);
        gl.glTexCoord2f(0.304f, 0.520f);
        gl.glVertex3f(0.5f * tam, -2.0f * tam, -2.0f * tam);

        //Tras
        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glTexCoord2f(0.304f, 0.520f);
        gl.glVertex3f(0.5f * tam, 2.0f * tam, -2.0f * tam);  //2,-1
        gl.glTexCoord2f(0.304f, 0.975f);
        gl.glVertex3f(0.5f * tam, 2.0f * tam, 2.0f * tam);   //2,1
        gl.glTexCoord2f(0.065f, 0.735f);
        gl.glVertex3f(2.5f * tam, 2.0f * tam, tam);   //3,0  ->3,1
        gl.glTexCoord2f(0.065f, 0.520f);
        gl.glVertex3f(2.5f * tam, 2.0f * tam, -2.0f * tam);   //3,-1


        //Chao
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glNormal3f(0.0F, 0.0F, -1.0f);
        gl.glVertex3f(0.5f * tam, -2.0f * tam, -2.0f * tam);
        gl.glVertex3f(2.5f * tam, -2.0f * tam, -2.0f * tam);
        gl.glVertex3f(2.5f * tam, 2.0f * tam, -2.0f * tam);
        gl.glVertex3f(0.5f * tam, 2.0f * tam, -2.0f * tam);
        gl.glColor3f(1.00f, 1.00f, 1.00f);

        /**********************************************************************
         fim Trapezio
         /**********************************************************************/

        /**********************************************************************
         comeco traseira direita
         /**********************************************************************/
        //gl.glColor3f(1.0f,0.0f,0.0f);

        //Teto
        gl.glNormal3f(1.0f, 0.0F, 1.0f);
        gl.glVertex3f(-3.5f * tam, -2.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, -1.0f * tam, 0.5f * tam);
        gl.glVertex3f(-3.5f * tam, -1.0f * tam, 0.5f * tam);

        //Chao
        gl.glNormal3f(0.0F, 0.0F, -1.0f);
        gl.glVertex3f(-3.5f * tam, -2.0f * tam, -0.5f * tam);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, -0.5f * tam);
        gl.glVertex3f(-2.5f * tam, -1.0f * tam, -0.5f * tam);
        gl.glVertex3f(-3.5f * tam, -1.0f * tam, -0.5f * tam);

        //Lado E
        gl.glNormal3f(-1.0f, 0.0F, 0.0F);
        gl.glTexCoord2f(0.717f, 0.218f);
        gl.glVertex3f(-3.5f * tam, -1.0f * tam, -0.5f * tam);
        gl.glTexCoord2f(0.717f, 0.401f);
        gl.glVertex3f(-3.5f * tam, -1.0f * tam, 0.5f * tam);
        gl.glTexCoord2f(0.936f, 0.401f);
        gl.glVertex3f(-3.5f * tam, -2.0f * tam, 0.5f * tam);
        gl.glTexCoord2f(0.936f, 0.218f);
        gl.glVertex3f(-3.5f * tam, -2.0f * tam, -0.5f * tam);

        //Frente
        gl.glNormal3f(0.0F, -1.0f, 0.0F);
        gl.glVertex3f(-3.5f * tam, -2.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, -2.0f * tam, -0.5f * tam);
        gl.glVertex3f(-3.5f * tam, -2.0f * tam, -0.5f * tam);

        //Tras
        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glVertex3f(-3.5f * tam, -1.0f * tam, -0.5f * tam);
        gl.glVertex3f(-3.5f * tam, -1.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, -1.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, -1.0f * tam, -0.5f * tam);

        /**********************************************************************
         fim traseira direita
         /**********************************************************************/

        /**********************************************************************
         comeco traseira esquerda
         /**********************************************************************/
        //gl.glColor3f(1.0f,0.0f,0.0f);

        //Teto
        gl.glNormal3f(1.0f, 0.0F, 1.0f);
        gl.glVertex3f(-3.5f * tam, 1.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, 1.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, 0.5f * tam);
        gl.glVertex3f(-3.5f * tam, 2.0f * tam, 0.5f * tam);

        //Chao
        gl.glNormal3f(0.0F, 0.0F, -1.0f);
        gl.glVertex3f(-3.5f * tam, 1.0f * tam, -0.5f * tam);
        gl.glVertex3f(-2.5f * tam, 1.0f * tam, -0.5f * tam);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, -0.5f * tam);
        gl.glVertex3f(-3.5f * tam, 2.0f * tam, -0.5f * tam);

        //Lado E
        gl.glNormal3f(-1.0f, 0.0F, 0.0F);
        gl.glTexCoord2f(0.717f, 0.218f);
        gl.glVertex3f(-3.5f * tam, 2.0f * tam, -0.5f * tam);
        gl.glTexCoord2f(0.717f, 0.401f);
        gl.glVertex3f(-3.5f * tam, 2.0f * tam, 0.5f * tam);
        gl.glTexCoord2f(0.936f, 0.401f);
        gl.glVertex3f(-3.5f * tam, 1.0f * tam, 0.5f * tam);
        gl.glTexCoord2f(0.936f, 0.218f);
        gl.glVertex3f(-3.5f * tam, 1.0f * tam, -0.5f * tam);

        //Frente
        gl.glNormal3f(0.0F, -1.0f, 0.0F);
        gl.glVertex3f(-3.5f * tam, 1.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, 1.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, 1.0f * tam, -0.5f * tam);
        gl.glVertex3f(-3.5f * tam, 1.0f * tam, -0.5f * tam);

        //Tras
        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glVertex3f(-3.5f * tam, 2.0f * tam, -0.5f * tam);
        gl.glVertex3f(-3.5f * tam, 2.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, 0.5f * tam);
        gl.glVertex3f(-2.5f * tam, 2.0f * tam, -0.5f * tam);

        /**********************************************************************
         fim traseira esquerda
         /**********************************************************************/
        gl.glEnd();
    }


    public void Grid() {
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        for (float a = (posX - (cellSize / 2.0f)); a <= -posX; a += cellSize) {
            gl.glVertex3f(a, (posY - (cellSize / 2.0f)), 0.1f);
            gl.glVertex3f(a, -(posY + cellSize - (cellSize / 2.0f)), 0.1f);
            //System.out.println("a:" + a );
        }

        for (float b = (posY - (cellSize / 2.0f)); b <= -posY; b += cellSize) {
            gl.glVertex3f((posX - (cellSize / 2.0f)), b, 0.1f);
            gl.glVertex3f(-(posX + cellSize - (cellSize / 2.0f)), b, 0.1f);
            //System.out.println("b:" + (-(posY+0.025f * (cellSize * 10.0f))));
            //System.out.println("b:" + b );
        }
        gl.glEnd();
    }


    /**
     *
     * Draw a flag. Simbolizes the explored area
     *
     * @param color    Flag color
     * @param cellSize Cell size
     * @param Zpos     Flag Z position
     *
     */

    public void Flag(float[] color, float cellSize, float Zpos) {
        float x1, x2, y1, y2, z1, z2;

        /* Flag bar*/
        x1 = -((cellSize / 20.0f) + (cellSize / 40.0f));
        x2 = ((cellSize / 20.0f) + (cellSize / 40.0f));
        y1 = -((cellSize / 20.0f) + (cellSize / 40.0f));
        y2 = ((cellSize / 20.0f) + (cellSize / 40.0f));
        z1 = -0.05f;//-((0.0010f*cellSize) + 0);
        z2 = ((0.60f * cellSize) + 0);

        gl.glColor3f(0.3f, 0.3f, 0.3f);

        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0.0F, 0.0F, -1.0f);
        gl.glVertex3f(x1, y1, z1);
        gl.glVertex3f(x2, y1, z1);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x1, y2, z1);

        gl.glNormal3f(1.0f, 0.0F, 1.0f);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x1, y2, z2);

        gl.glNormal3f(0.0F, -1.0f, 0.0F);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y1, z1);
        gl.glVertex3f(x1, y1, z1);

        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x1, y2, z2);
        gl.glVertex3f(x1, y2, z1);

        gl.glNormal3f(-1.0f, 0.0F, 0.0F);
        gl.glVertex3f(x1, y2, z1);
        gl.glVertex3f(x1, y2, z2);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x1, y1, z1);

        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y1, z1);
        gl.glEnd();


        /* Flag L*/
        x1 = -(cellSize / 2.50f);
        x2 = (cellSize / 2.50f);
        y1 = -((cellSize / 20.0f) + (cellSize / 40.0f));
        y2 = ((cellSize / 20.0f) + (cellSize / 40.0f));
        //z1 =  ((0.500f*cellSize) + Zpos);
        //z2 =  ((1.000f*cellSize) + Zpos);

        z1 = ((0.300f * cellSize) + 0);
        z2 = ((0.5000f * cellSize) + 0);

        gl.glColor3fv(color);
        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0.0F, 0.0F, -1.0f);
        gl.glVertex3f(x1, y1, z1);
        gl.glVertex3f(x2, y1, z1);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x1, y2, z1);

        gl.glNormal3f(1.0f, 0.0F, 1.0f);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x1, y2, z2);

        gl.glNormal3f(0.0F, -1.0f, 0.0F);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y1, z1);
        gl.glVertex3f(x1, y1, z1);

        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x1, y2, z2);
        gl.glVertex3f(x1, y2, z1);

        gl.glNormal3f(-1.0f, 0.0F, 0.0F);
        gl.glVertex3f(x1, y2, z1);
        gl.glVertex3f(x1, y2, z2);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x1, y1, z1);

        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y1, z1);
        gl.glEnd();


        /* Flag L*/
        x1 = -((cellSize / 20.0f) + (cellSize / 40.0f));
        x2 = ((cellSize / 20.0f) + (cellSize / 40.0f));
        y1 = -(cellSize / 2.50f);
        y2 = (cellSize / 2.50f);
        //z1 =  ((0.500f*cellSize) + Zpos);
        //z2 =  ((1.000f*cellSize) + Zpos);

        z1 = ((0.300f * cellSize) + 0);
        z2 = ((0.5000f * cellSize) + 0);


        gl.glColor3fv(color);
        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0.0F, 0.0F, -1.0f);
        gl.glVertex3f(x1, y1, z1);
        gl.glVertex3f(x2, y1, z1);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x1, y2, z1);

        gl.glNormal3f(1.0f, 0.0F, 1.0f);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x1, y2, z2);

        gl.glNormal3f(0.0F, -1.0f, 0.0F);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y1, z1);
        gl.glVertex3f(x1, y1, z1);

        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x1, y2, z2);
        gl.glVertex3f(x1, y2, z1);

        gl.glNormal3f(-1.0f, 0.0F, 0.0F);
        gl.glVertex3f(x1, y2, z1);
        gl.glVertex3f(x1, y2, z2);
        gl.glVertex3f(x1, y1, z2);
        gl.glVertex3f(x1, y1, z1);

        gl.glNormal3f(0.0F, 1.0f, 0.0F);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x2, y1, z1);
        gl.glEnd();

        //gl.glTranslatef(0.1f,0.1f,0);
    }

    public static float getFlagSize() {
        return 0.1f;
    }

}