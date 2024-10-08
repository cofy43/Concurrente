package Filtros;

import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Clase que representa los Filtros Secuenciales
 * @author Sunny Mirael
 */
public class Filtros {

    /**
     * Metodo que carga una imagen
     * @param ruta La ruta de la imagen
     * @return La Imagen en un BufferedImage
     * @throws IOException 
     */
    public BufferedImage leeImagen(String ruta) throws IOException{
        InputStream input = new FileInputStream(ruta);
        ImageInputStream imageInput = ImageIO.createImageInputStream(input);
        return ImageIO.read(imageInput);
    }

    /**
     * Metodo que guarda una imagen en archivo png
     * @param imagen La imagen
     * @param nombre El nombre del archivo a guardar
     * @throws IOException 
     */
    public void guardaImagen(BufferedImage imagen, String nombre) throws IOException{
        ImageIO.write(imagen,"png",new File(nombre));
    }
    /**
     * Returns a {@link BufferedImage} with the specified image type, where the
     * graphical content is a copy of the specified image.
     * 
     * @param img    The image to copy.
     * @param imageType  The image type for the image to return.
     * @return      A copy of the specified image.
     */
    public static BufferedImage copia(BufferedImage img, int imageType){
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, imageType);
        Graphics g = newImage.createGraphics();

        g.drawImage(img, 0, 0, null);

        g.dispose();

        return newImage;
    }

    public BufferedImage motionBlur(BufferedImage imagen) throws IOException{
        BufferedImage copia = copia(imagen,BufferedImage.TYPE_INT_RGB);
        
        int alto = copia.getHeight();
        int ancho = copia.getWidth();
        
        double blur[][] ={
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0},//centro
            {0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1}
        };
        //System.out.println(blur[7][7]);
        for(int y = 0; y< alto; ++y){
            double rojo = 0;
            double verde = 0;
            double azul = 0;
            
            for(int x = 0; x<ancho; ++x){

                //Pixel1 (-4,-4)
                int pixel = imagen.getRGB(((x-4)%ancho<0)?ancho-4:x-4,((y-4)%alto<0)?alto-4:y-4);
                Color c = new Color(pixel);
                rojo += c.getRed()* blur[0][0];
                verde += c.getGreen()* blur[0][0];
                azul += c.getBlue()* blur[0][0];
                
                //Pixel2 (-3,-3)
                pixel = imagen.getRGB(((x-3)%ancho<0)?ancho-3:x-3,((y-3)%alto<0)?alto-3:y-3);
                c = new Color(pixel);
                rojo += c.getRed()* blur[1][1];
                verde += c.getGreen()* blur[1][1];
                azul += c.getBlue()* blur[1][1];
                
                //Pixel3 (-2,-2)
                pixel = imagen.getRGB(((x-2)%ancho<0)?ancho-2:x-2,((y-2)%alto<0)?alto-2:y-2);
                c = new Color(pixel);
                rojo += c.getRed()* blur[2][2];
                verde += c.getGreen()* blur[2][2];
                azul += c.getBlue()* blur[2][2];
                
                //Pixel4 (-1,-1)
                pixel = imagen.getRGB(((x-1)%ancho<0)?ancho-1:x-1,((y-1)%alto<0)?alto-1:y-1);
                c = new Color(pixel);
                rojo += c.getRed()* blur[3][3];
                verde += c.getGreen()* blur[3][3];
                azul += c.getBlue()* blur[3][3];
                
                //Pixel5 (0,0)
                pixel = imagen.getRGB((x)%ancho,(y)%alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[4][4];
                verde += c.getGreen()* blur[4][4];
                azul += c.getBlue()* blur[4][4];
                
                //Pixel6 (1,1)
                pixel = imagen.getRGB((x+1)%ancho,(y+1)%alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[5][5];
                verde += c.getGreen()* blur[5][5];
                azul += c.getBlue()* blur[5][5];
                
                //Pixel7 (2,2)
                pixel = imagen.getRGB((x+2)%ancho,(y+2)%alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[6][6];
                verde += c.getGreen()* blur[6][6];
                azul += c.getBlue()* blur[6][6];
                
                //Pixel8 (3,3)
                pixel = imagen.getRGB((x+3)%ancho,(y+3)%alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[7][7];
                verde += c.getGreen()* blur[7][7];
                azul += c.getBlue()* blur[7][7];
                
                //Pixel9 (4,4)
                pixel = imagen.getRGB((x+4)%ancho,(y+4)%alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[8][8];
                verde += c.getGreen()* blur[8][8];
                azul += c.getBlue()* blur[8][8];
                
                rojo /= 9;
                verde /= 9;
                azul /= 9;
                
                rojo = (rojo>255)?255:(rojo<0)?0:rojo;
                verde = (verde>255)?255:(verde<0)?0:verde;
                azul = (azul>255)?255:(azul<0)?0:azul;
                
                copia.setRGB(x, y, new Color((int)rojo,(int)verde,(int)azul).getRGB());
            }
        }
        return copia;
    }

    public BufferedImage sharpen(BufferedImage imagen) throws IOException {
        BufferedImage copia = copia(imagen, BufferedImage.TYPE_INT_RGB);

        int alto = copia.getHeight();
        int ancho = copia.getWidth();

        int sharpenMatriz[][] = {
                { -1, -1, -1 },
                { -1, 9, -1 },
                { -1, -1, -1 },
        };

        for (int i = 0; i < alto; ++i) {
            for (int j = 0; j < ancho; ++j) {
                double rojo = 0;
                double verde = 0;
                double azul = 0;

                // Pixel 1 (-1, 1) = -1
                int pixel = imagen.getRGB(((j - 1) % ancho < 0) ? ancho - 1 : j - 1, (i + 1) % alto);
                Color c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[0][0];
                verde += c.getGreen() * sharpenMatriz[0][0];
                azul += c.getBlue() * sharpenMatriz[0][0];

                // Pixel 2 (0, 1) = -1
                pixel = imagen.getRGB((j % ancho), (i + 1) % alto);
                c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[0][1];
                verde += c.getGreen() * sharpenMatriz[0][1];
                azul += c.getBlue() * sharpenMatriz[0][1];

                // Pixel 3 (1, 1) = -1
                pixel = imagen.getRGB((j + 1) % ancho, (i + 1) % alto);
                c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[0][2];
                verde += c.getGreen() * sharpenMatriz[0][2];
                azul += c.getBlue() * sharpenMatriz[0][2];

                // Pixel 4 (-1, 0) = -1
                pixel = imagen.getRGB(((j - 1) % ancho < 0) ? ancho - 1 : j - 1, (i) % alto);
                c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[1][0];
                verde += c.getGreen() * sharpenMatriz[1][0];
                azul += c.getBlue() * sharpenMatriz[1][0];

                // Pixel 5 (0, 0) = 9
                pixel = imagen.getRGB((j) % ancho, (i) % alto);
                c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[1][1];
                verde += c.getGreen() * sharpenMatriz[1][1];
                azul += c.getBlue() * sharpenMatriz[1][1];

                // Pixel 6 (1, 0) = -1
                pixel = imagen.getRGB((j + 1) % ancho, (i) % alto);
                c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[1][2];
                verde += c.getGreen() * sharpenMatriz[1][2];
                azul += c.getBlue() * sharpenMatriz[1][2];

                // Pixel 7 (-1, -1) = -1
                pixel = imagen.getRGB(((j - 1) % ancho < 0) ? ancho - 1 : j - 1,
                        ((i - 1) % alto < 0) ? alto - 1 : i - 1);
                c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[2][0];
                verde += c.getGreen() * sharpenMatriz[2][0];
                azul += c.getBlue() * sharpenMatriz[2][0];

                // Pixel 8 (0, -1) = -1
                pixel = imagen.getRGB((j) % ancho, ((i - 1) % alto < 0) ? alto - 1 : i - 1);
                c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[2][1];
                verde += c.getGreen() * sharpenMatriz[2][1];
                azul += c.getBlue() * sharpenMatriz[2][1];

                // Pixel 9 (1, -1) = -1
                pixel = imagen.getRGB((j + 1) % ancho, ((i - 1) % alto < 0) ? alto - 1 : i - 1);
                c = new Color(pixel);
                rojo += c.getRed() * sharpenMatriz[2][1];
                verde += c.getGreen() * sharpenMatriz[2][1];
                azul += c.getBlue() * sharpenMatriz[2][1];

                rojo = (rojo>255)?255:(rojo<0)?0:rojo;
                verde = (verde>255)?255:(verde<0)?0:verde;
                azul = (azul>255)?255:(azul<0)?0:azul;

                copia.setRGB(j, i, new Color((int)rojo,(int)verde,(int)azul).getRGB());
            }
        }

        return copia;
    }

    public BufferedImage componentesRGB(BufferedImage imagen) throws IOException {
        BufferedImage copia = copia(imagen, BufferedImage.TYPE_INT_RGB);

        int alto = copia.getHeight();
        int ancho = copia.getWidth();

        for(int i = 0; i < alto; ++i) {
            for(int j = 0; j < ancho; ++j) {
                double rojo = 0;
                double verde = 0;
                double azul = 0;
                int pixel = imagen.getRGB(j, i);
                Color c = new Color(pixel);

                rojo += c.getRed() + 50;
                verde += c.getGreen();
                azul += c.getBlue() + 25;

                rojo = (rojo>255)?255:(rojo<0)?0:rojo;
                verde = (verde>255)?255:(verde<0)?0:verde;
                azul = (azul>255)?255:(azul<0)?0:azul;

                copia.setRGB(j, i, new Color((int)rojo,(int)verde,(int)azul).getRGB());
            }
        }

        return copia;
    }

    public BufferedImage blur2(BufferedImage imagen) throws IOException {
        BufferedImage copia = copia(imagen, BufferedImage.TYPE_INT_RGB);

        int alto = copia.getHeight();
        int ancho = copia.getWidth();

        double blur[][] = {
            {0, 0, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 0},
            {0, 0, 1, 0, 0},
        };

        for(int i = 0; i < alto; ++i) {
            for(int j = 0; j < ancho; ++j) {
                double rojo = 0;
                double verde = 0;
                double azul = 0;

                // Pixel 1 (0, 2)
                int pixel = imagen.getRGB((j) % ancho, (i + 2) % alto);
                Color c = new Color(pixel);
                rojo += c.getRed()* blur[0][2];
                verde += c.getGreen()* blur[0][2];
                azul += c.getBlue()* blur[0][2];

                // Pixel 2 (-1, 1)
                pixel = imagen.getRGB(((j - 1) % ancho < 0) ? ancho - 1 : j - 1, (i + 1) % alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[1][1];
                verde += c.getGreen()* blur[1][1];
                azul += c.getBlue()* blur[1][1];

                // Pixel 3 (0, 1)
                pixel = imagen.getRGB(j % ancho, (i + 1) % alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[1][2];
                verde += c.getGreen()* blur[1][2];
                azul += c.getBlue()* blur[1][2];

                // Pixel 4 (1, 1)
                pixel = imagen.getRGB((j + 1) % ancho, (i + 1) % alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[1][2];
                verde += c.getGreen()* blur[1][2];
                azul += c.getBlue()* blur[1][2];

                // Pixel 5 (-2, 0)
                pixel = imagen.getRGB(((j - 2) % ancho < 0) ? ancho - 2 : j - 2, i % alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[2][0];
                verde += c.getGreen()* blur[2][0];
                azul += c.getBlue()* blur[2][0];

                // Pixel 6 (-1, 0)
                pixel = imagen.getRGB(((j - 1) % ancho < 0) ? ancho - 1 : j - 1, i % alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[2][1];
                verde += c.getGreen()* blur[2][1];
                azul += c.getBlue()* blur[2][1];

                // Pixel 7 (0, 0)
                pixel = imagen.getRGB((j + 1) % ancho, i % alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[2][2];
                verde += c.getGreen()* blur[2][2];
                azul += c.getBlue()* blur[2][2];

                // Pixel 8 (1, 0)
                pixel = imagen.getRGB((j + 1) % ancho, i % alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[2][3];
                verde += c.getGreen()* blur[2][3];
                azul += c.getBlue()* blur[2][3];

                // Pixel 9 (2, 0)
                pixel = imagen.getRGB((j + 2) % ancho, i % alto);
                c = new Color(pixel);
                rojo += c.getRed()* blur[2][4];
                verde += c.getGreen()* blur[2][4];
                azul += c.getBlue()* blur[2][4];

                // Pixel 10 (-1, -1)
                pixel = imagen.getRGB(((j - 1) % ancho < 0) ? ancho - 1 : j - 1,
                        ((i - 1) % alto < 0) ? alto - 1 : i - 1);
                c = new Color(pixel);
                rojo += c.getRed()* blur[3][1];
                verde += c.getGreen()* blur[3][1];
                azul += c.getBlue()* blur[3][1];

                // Pixel 11 (0, -1)
                pixel = imagen.getRGB(j % ancho, ((i - 1) % alto < 0) ? alto - 1 : i - 1);
                c = new Color(pixel);
                rojo += c.getRed()* blur[3][2];
                verde += c.getGreen()* blur[3][2];
                azul += c.getBlue()* blur[3][2];

                // Pixel 12 (1, -1)
                pixel = imagen.getRGB((j + 1) % ancho, ((i - 1) % alto < 0) ? alto - 1 : i - 1);
                c = new Color(pixel);
                rojo += c.getRed()* blur[3][3];
                verde += c.getGreen()* blur[3][3];
                azul += c.getBlue()* blur[3][3];

                // Pixel 13 (0, -2)
                pixel = imagen.getRGB(j % ancho, ((i - 2) % alto < 0) ? alto - 2 : i - 2);
                c = new Color(pixel);
                rojo += c.getRed()* blur[4][2];
                verde += c.getGreen()* blur[4][2];
                azul += c.getBlue()* blur[4][2];

                rojo /= 13;
                verde /= 13;
                azul /= 13;

                rojo = (rojo>255)?255:(rojo<0)?0:rojo;
                verde = (verde>255)?255:(verde<0)?0:verde;
                azul = (azul>255)?255:(azul<0)?0:azul;

                copia.setRGB(j, i, new Color((int)rojo,(int)verde,(int)azul).getRGB());
            }
        }

        return copia;
    }

    public BufferedImage correctud(BufferedImage imagen) throws IOException{
        BufferedImage copia = copia(imagen,BufferedImage.TYPE_INT_RGB);
        
        int alto = copia.getHeight();
        int ancho = copia.getWidth();


        for(int y = 0; y< alto; ++y){
            double rojo = 0;
            double verde = 0;
            double azul = 0;
            double gray=0;
            
            for(int x = 0; x<ancho; ++x){
                int pixel=imagen.getRGB(x, y);
                Color c= new Color(pixel);

                gray= (c.getRed()*.03 + c.getGreen()*.059 + c.getBlue()*.11);

                rojo = (rojo>255)?255:(rojo<0)?0:rojo;
                verde = (verde>255)?255:(verde<0)?0:verde;
                azul = (azul>255)?255:(azul<0)?0:azul;

                copia.setRGB(x, y, new Color((int)gray,(int)gray,(int)gray).getRGB());
            }
        }
        return copia;
    }

    public BufferedImage grises(BufferedImage imagen) throws IOException{
        BufferedImage copia = copia(imagen,BufferedImage.TYPE_INT_RGB);
        
        int alto = copia.getHeight();
        int ancho = copia.getWidth();


        for(int y = 0; y< alto; ++y){
            double rojo = 0;
            double verde = 0;
            double azul = 0;
            double gray=0;
            
            for(int x = 0; x<ancho; ++x){
                int pixel=imagen.getRGB(x, y);
                Color c= new Color(pixel);

                gray= ((c.getRed() + c.getGreen() + c.getBlue()) / 3);

                rojo = (rojo>255)?255:(rojo<0)?0:rojo;
                verde = (verde>255)?255:(verde<0)?0:verde;
                azul = (azul>255)?255:(azul<0)?0:azul;

                copia.setRGB(x, y, new Color((int)gray,(int)gray,(int)gray).getRGB());


      
            }
        }
        return copia;
    }

    public BufferedImage correctud2(BufferedImage imagen) throws IOException{
        BufferedImage copia = copia(imagen,BufferedImage.TYPE_INT_RGB);
        
        int alto = copia.getHeight();
        int ancho = copia.getWidth();


        for(int y = 0; y< alto; ++y){
            double rojo = 0;
            double verde = 0;
            double azul = 0;
            double gray=0;
            
            for(int x = 0; x<ancho; ++x){
                int pixel=imagen.getRGB(x, y);
                Color c= new Color(pixel);

                gray= (c.getRed()*.216 + c.getGreen()*.7152 + c.getBlue()*.0722);

                rojo = (rojo>255)?255:(rojo<0)?0:rojo;
                verde = (verde>255)?255:(verde<0)?0:verde;
                azul = (azul>255)?255:(azul<0)?0:azul;

                copia.setRGB(x, y, new Color((int)gray,(int)gray,(int)gray).getRGB());
            }
        }
        return copia;
    }
}