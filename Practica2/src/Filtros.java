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
}