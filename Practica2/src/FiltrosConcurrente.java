import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class FiltrosConcurrente extends Filtros {
    
    /**
     * Metodo que aplica el Metodo motion blur usando una matriz 9x9 a una imagen
     * @param imagen La imagen con la que se trabajara
     * @throws IOException
     */
    public void motionBlurConcurrente(BufferedImage imagen, BufferedImage copia, int y) throws IOException {

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

        for(int x = 0; x<ancho; ++x){
            double rojo = 0;
            double verde = 0;
            double azul = 0;

            //Pixel1 (-1,-1)

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

    public static void main(String[] args) throws IOException, InterruptedException{
        FiltrosConcurrente fc = new FiltrosConcurrente();

        BufferedImage imagen = fc.leeImagen("src/assets/img/test1.jpeg");
        BufferedImage copia = copia(imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage res = fc.motionBlur(imagen);//Secuencial

        List<Thread> hilosL = new ArrayList<>();
        int hilos = 15;
        
        for(int i = 0; i < imagen.getHeight(); ++i){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        fc.motionBlurConcurrente(imagen, copia, Integer.parseInt(Thread.currentThread().getName()));
                    }catch(IOException e){
                        e.printStackTrace();
                    } 
                }
            },""+i);
            t.start();
            hilosL.add(t);

            if(hilos == hilosL.size()){
                for(Thread threads : hilosL){
                    threads.join();
                }
                hilosL.clear();
            }
        }

        for(Thread threads : hilosL){
            threads.join();
        }

        fc.guardaImagen(copia,"Prueba.png");
    }
}