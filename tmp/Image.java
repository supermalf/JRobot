/**
 * @(#)Image.java 1.00 27/05/05
 *
 */


package jrobot.ogl;


/********************************************
 * This class represents an Images treatment.
 * Open and manipulate PNG images.
 * Manipulates and creates OpenGL textures.
 *
 * @since JBobot v0.1, JDK v1.4
 * @version 2.00, 27/05/05
 * @author MALF
 * @author TUCANO
 *
 *******************************************/

import net.java.games.jogl.GL;
import net.java.games.jogl.GLU;
import net.java.games.jogl.util.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;


public class Image
{

    /* OpenGL Context */
    private static GL  gl;
    private static GLU glu;


    /*****************************************************
     *
     * Creates the texture 2D
     *
     * @param gl OpenGL context
     * @param glu GLU context
     * @param texture Texture id
     * @param color Color to be mixed with the texture
     * @param Filename Texture filename
     *
     *****************************************************/

    public static void makeTexture2D(GL gl, GLU glu, int texture, float[]color, String Filename )
    {
         /* Getting the context */
         Image.gl = gl;
         Image.glu = glu;


         gl.glEnable(GL.GL_TEXTURE_2D);

         texture = genTexture(gl);

         gl.glBindTexture(GL.GL_TEXTURE_2D, texture);

         BufferedImage img = readPNGImage(Filename);
         makeRGBTexture(img, GL.GL_TEXTURE_2D, false);

         gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MIN_FILTER,GL.GL_LINEAR);
         gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MAG_FILTER,GL.GL_LINEAR);
         gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_WRAP_S,GL.GL_REPEAT);
         gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_WRAP_T,GL.GL_REPEAT);
         gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_MODULATE);
         gl.glTexEnvfv(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_COLOR, color);

         gl.glDisable(GL.GL_TEXTURE_2D);
    }


   /*****************************************************
    *
    * Open the PNG image and make a buffer with it
    *
    * @param resourceName File name
    * @return BufferedImage Buffer with the PNG image
    *
    *****************************************************/

    private static BufferedImage readPNGImage(String resourceName)
    {
       try
       {
           URL url = getResource(resourceName);

           if (url == null)
           {
                throw new RuntimeException("Error reading resource " + resourceName);
           }

           BufferedImage img = ImageIO.read(url);
           AffineTransform tx = AffineTransform.getScaleInstance(1.0, (double) -1);
           tx.translate(0.0, -img.getHeight(null));
           AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
           img = op.filter(img, null);

           return img;
       }

       catch (IOException e)
       {
           System.err.print("IOException - Unavaliable Image: Can\'t open " + resourceName);
           return null;
       }
    }

   /*****************************************************
    *
    * Creates the texture with the image buffer
    *
    * @param img Image to create the texture
    * @param target Texture location
    * @param mipmapped Activates the Mipmapped Image
    *
    *****************************************************/

    private static void makeRGBTexture(BufferedImage img, int target, boolean mipmapped)
    {
       ByteBuffer dest = null;

       /* Checking if the image could be opened */
       if (img==null)
         return;

       switch (img.getType())
       {
         case BufferedImage.TYPE_3BYTE_BGR:

         case BufferedImage.TYPE_CUSTOM:
         {
             byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
             dest = ByteBuffer.allocateDirect(data.length);
             dest.order(ByteOrder.nativeOrder());
             dest.put(data, 0, data.length);
             break;
         }

         case BufferedImage.TYPE_INT_RGB:
         {
             WritableRaster raster = img.getRaster();
             int[] data = ((DataBufferInt) raster.getDataBuffer()).getData();
             dest = ByteBuffer.allocateDirect(data.length * BufferUtils.SIZEOF_INT);
             dest.order(ByteOrder.nativeOrder());
             IntBuffer intBuffer = dest.asIntBuffer();
             intBuffer.put(data, 0, data.length);
             break;
        }

        default:
             throw new RuntimeException("Unsupported image type " + img.getType());
       }

      if (mipmapped)
      {
        glu.gluBuild2DMipmaps(target, GL.GL_RGB8, img.getWidth(), img.getHeight(),
                              GL.GL_RGB, GL.GL_UNSIGNED_BYTE, dest);
      }

      else
      {
        gl.glTexImage2D(target, 0, GL.GL_RGB, img.getWidth(), img.getHeight(), 0,
                        GL.GL_RGB, GL.GL_UNSIGNED_BYTE, dest);
      }
    }

    /*****************************************************
    *
    * Generates texture
    *
    * @param gl OpenGL context
    * @return Texture id
    *
    *****************************************************/

    public static int genTexture(GL gl)
    {
        final int[] tmp = new int[1];
        gl.glGenTextures(1, tmp);
        return tmp[0];
    }


   /*******************************************************************************
    *
    * Retrieve a URL resource from the jar.  If the resource is not found, then
    * the local disk is also checked.
    *
    * @param filename Complete filename, including parent path
    * @return a URL object if resource is found, otherwise null.
    *
    *******************************************************************************/

     public static URL getResource(String filename)
     {
       /* Try to load resource from jar */
       URL url = ClassLoader.getSystemResource(filename);

       /* If not found in jar, then load from disk */
       if (url == null)
       {
         try
         {
           url = new URL("file", "localhost", filename);
         }
         catch (Exception urlException){} // ignore
       }
       return url;
     }


    /*************************************************
     *
     * Gauss Filter reduce the noise from images.
     *
     * @param image Image Matrix with the values
     * @param width  Image width
     * @param height Image height
     *
     *
     **************************************************/

    public static void GaussFilter(float[][]image, int width, int height)
    {
        int[][] Gauss;
        Gauss = new int[3][3];

         int x, y;
         float v0,v1,v2,v3,v4,v5,v6,v7,v8;

         Gauss[0][0] = 1; Gauss[0][1] = 2; Gauss[0][2] = 1;
         Gauss[1][0] = 2; Gauss[1][1] = 4; Gauss[1][2] = 2;
         Gauss[2][0] = 1; Gauss[2][1] = 2; Gauss[2][2] = 1;

         for (y=0;y<height;y++){
             for (x=0;x<width;x++) {

                 /* Edges pixels case */
                 if(y == 0 && x == 0)
                 {
                     v0 = image[x+1][y];
                     v1 = image[x][y+1];
                     v2 = image[x+1][y+1];
                     v3 = image[x][y];

                     image[x][y] = (v3*(float) Gauss[1][1] + v0*(float) Gauss[1][2] +
                                    v1*(float) Gauss[2][1] + v2*(float) Gauss[2][2])/16.0F;
                 }

                 if(x == width - 1 && y == 0)
                 {
                     v0 = image[x-1][y];
                     v1 = image[x-1][y+1];
                     v2 = image[x][y+1];
                     v3 = image[x][y];

                     image[x][y] = (v3*(float) Gauss[1][1] + v0*(float) Gauss[1][2] +
                                    v1*(float) Gauss[2][1] + v2*(float) Gauss[2][2])/16.0F;
                 }

                 if(x == width - 1 && y == height - 1)
                 {
                     v0 = image[x-1][y-1];
                     v1 = image[x][y-1];
                     v2 = image[x-1][y];
                     v3 = image[x][y];

                     image[x][y] = (v3*(float) Gauss[1][1] + v0*(float) Gauss[1][2] +
                                    v1*(float) Gauss[2][1] + v2*(float) Gauss[2][2])/16.0F;
                 }

                 if(y == height - 1 && x == 0)
                 {
                     v0 = image[x][y-1];
                     v1 = image[x+1][y-1];
                     v2 = image[x+1][y];
                     v3 = image[x][y];

                     image[x][y] = (v3*(float) Gauss[1][1] + v0*(float) Gauss[1][2] +
                                    v1*(float) Gauss[2][1] + v2*(float) Gauss[2][2])/16.0F;
                 }

                 if(x == width - 1 && y > 0 && y < height - 1)
                 {
                     v0 = image[x-1][y-1];
                     v1 = image[x][y-1];
                     v2 = image[x-1][y];
                     v3 = image[x-1][y+1];
                     v4 = image[x][y+1];
                     v5 = image[x][y];

                     image[x][y] = (v5*(float) Gauss[1][1] + v0*(float) Gauss[0][0] + v1*(float) Gauss[0][1] +
                                    v2*(float) Gauss[1][0] + v4*(float) Gauss[2][1] + v3*(float) Gauss[2][0])/16.0F;
                 }

                 if(x == 0 && y > 0 && y < height - 1)
                 {
                     v0 = image[x][y-1];
                     v1 = image[x+1][y-1];
                     v2 = image[x+1][y];
                     v3 = image[x][y+1];
                     v4 = image[x+1][y+1];
                     v5 = image[x][y];

                     image[x][y] = (v5*(float) Gauss[1][1] + v0*(float) Gauss[0][1] + v1*(float) Gauss[0][2] +
                                    v2*(float) Gauss[1][2] + v3*(float) Gauss[2][1] + v4*(float) Gauss[2][2])/16.0F;
                 }

                 if(y == 0 && x > 0 && x < width - 1)
                 {
                     v0 = image[x-1][y];
                     v1 = image[x+1][y];
                     v2 = image[x-1][y+1];
                     v3 = image[x][y+1];
                     v4 = image[x+1][y+1];
                     v5 = image[x][y];

                     image[x][y] = (v5*(float) Gauss[1][1] + v0*(float) Gauss[1][0] + v1*(float) Gauss[1][2] +
                                    v2*(float) Gauss[2][0] + v3*(float) Gauss[2][1] + v4*(float) Gauss[2][2])/16.0F;
                 }

                 if(y == height - 1 && x > 0 && x < width - 1)
                 {
                     v0 = image[x-1][y-1];
                     v1 = image[x][y-1];
                     v2 = image[x+1][y-1];
                     v3 = image[x-1][y];
                     v4 = image[x][y];
                     v5 = image[x+1][y];

                     image[x][y] = (v4*(float) Gauss[1][1] + v0*(float) Gauss[0][0] + v1*(float) Gauss[0][1] +
                                    v2*(float) Gauss[0][2] + v3*(float) Gauss[1][0] + v5*(float) Gauss[1][2])/16.0F;
                 }

                 /* Central Pixels */
                 if( x != 0 && y != 0 && y != height - 1 && x != width - 1)
                 {
                     v0 = image[x-1][y-1];
                     v1 = image[x][y-1];
                     v2 = image[x+1][y-1];
                     v3 = image[x-1][y];
                     v4 = image[x][y];
                     v5 = image[x+1][y];
                     v6 = image[x-1][y+1];
                     v7 = image[x][y+1];
                     v8 = image[x+1][y+1];

                     image[x][y] = (v0*(float) Gauss[0][0] + v1*(float) Gauss[0][1] + v2*(float) Gauss[0][2] +
                                    v3*(float) Gauss[1][0] + v4*(float) Gauss[1][1] + v5*(float) Gauss[1][2] +
                                    v6*(float) Gauss[2][0] + v7*(float) Gauss[2][1] + v8*(float) Gauss[2][2] )/16.0F;
                 }
             }
         }
    }

}
