package Filtros;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void sharpenConcurrente(BufferedImage imagen, BufferedImage copia, int i) throws IOException {
        int alto = copia.getHeight();
        int ancho = copia.getWidth();

        int sharpenMatriz[][] = {
            { -1, -1, -1 },
            { -1, 9, -1 },
            { -1, -1, -1 },
        };

        for (int j = 0; j < ancho; j++) {
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
            pixel = imagen.getRGB(((j-1)%ancho<0)?ancho-1:j-1,((i-1)%alto<0)?alto-1:i-1);
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
    
    public void componentesRGBConcurrente(BufferedImage imagen, BufferedImage copia, int i) throws IOException {
        int ancho = copia.getWidth();

        for(int j = 0; j < ancho; j++) {
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

    public void blur2Concurrente(BufferedImage imagen, BufferedImage copia, int i) throws IOException {
        int ancho = copia.getWidth();
        int alto = copia.getHeight();

        double blur[][] = {
            {0, 0, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 0},
            {0, 0, 1, 0, 0},
        };

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

    public void grisesConcurrente(BufferedImage imagen, BufferedImage copia, int y) throws IOException {
        int ancho = copia.getWidth();

        for(int x = 0; x<ancho; ++x){
            double rojo = 0;
            double verde = 0;
            double azul = 0;
            double gray=0;
            
            int pixel=imagen.getRGB(x, y);
            Color c= new Color(pixel);

            gray= ((c.getRed() + c.getGreen() + c.getBlue()) / 3);

            rojo = (rojo>255)?255:(rojo<0)?0:rojo;
            verde = (verde>255)?255:(verde<0)?0:verde;
            azul = (azul>255)?255:(azul<0)?0:azul;

            copia.setRGB(x, y, new Color((int)gray,(int)gray,(int)gray).getRGB());
        }
    }

    public void correctudConcurrente(BufferedImage imagen, BufferedImage copia, int y) throws IOException {
        int ancho = copia.getWidth();

        for(int x = 0; x<ancho; ++x){
            double rojo = 0;
            double verde = 0;
            double azul = 0;
            double gray=0;

            int pixel=imagen.getRGB(x, y);
            Color c= new Color(pixel);

            gray= (c.getRed()*.03 + c.getGreen()*.059 + c.getBlue()*.11);

            rojo = (rojo>255)?255:(rojo<0)?0:rojo;
            verde = (verde>255)?255:(verde<0)?0:verde;
            azul = (azul>255)?255:(azul<0)?0:azul;

            copia.setRGB(x, y, new Color((int)gray,(int)gray,(int)gray).getRGB());
        }
    }

    public void correctud2Concurrente(BufferedImage imagen, BufferedImage copia, int y) throws IOException {
        int ancho = copia.getWidth();

        for(int x = 0; x<ancho; ++x){
            double rojo = 0;
            double verde = 0;
            double azul = 0;
            double gray=0;

            int pixel=imagen.getRGB(x, y);
                Color c= new Color(pixel);

                gray= (c.getRed()*.216 + c.getGreen()*.7152 + c.getBlue()*.0722);

                rojo = (rojo>255)?255:(rojo<0)?0:rojo;
                verde = (verde>255)?255:(verde<0)?0:verde;
                azul = (azul>255)?255:(azul<0)?0:azul;

                copia.setRGB(x, y, new Color((int)gray,(int)gray,(int)gray).getRGB());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        FiltrosConcurrente fc = new FiltrosConcurrente();

        BufferedImage imagen = fc.leeImagen("src/Filtros/test1.jpeg");

        // GRISES (FACILES)
        BufferedImage copiaGrises = copia(imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage resGrises = fc.grises(imagen);//Secuencial
        fc.guardaImagen(resGrises,"src/Filtros/Prueba_grises_secuencial.png");

        BufferedImage copiaCorrectud = copia(imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage resCorrectud = fc.correctud(imagen);//Secuencial
        fc.guardaImagen(resCorrectud,"src/Filtros/Prueba_correctud_secuencial.png");
        
        BufferedImage copiaCorrectud2 = copia(imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage resCorrectud2 = fc.correctud2(imagen);//Secuencial
        fc.guardaImagen(resCorrectud2,"src/Filtros/Prueba_correctud2_secuencial.png");

        // Blurs (Dificiles)      
        BufferedImage copiaMotionBlur = copia(imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage resMotionBlur = fc.motionBlur(imagen);//Secuencial
        fc.guardaImagen(resMotionBlur,"src/Filtros/Prueba_motion_blur_secuencial.png");

        BufferedImage copiaBlur2 = copia(imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage resBlur2 = fc.blur2(imagen);//Secuencial
        fc.guardaImagen(resBlur2,"src/Filtros/Prueba_blur2_secuencial.png");

        // Varios (Medios)
        BufferedImage copiaSharpen = copia(imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage resSharpen = fc.sharpen(imagen);//Secuencial
        fc.guardaImagen(resSharpen,"src/Filtros/Prueba_sharpen_secuencial.png");

        BufferedImage copiaComponentesRGB = copia(imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage resComponentesRGB = fc.componentesRGB(imagen);//Secuencial
        fc.guardaImagen(resComponentesRGB,"src/Filtros/Prueba_componentesRGB_secuencial.png");


        List<Thread> hilosL = new ArrayList<>();
        int hilos = 15;
        
        for(int i = 0; i < imagen.getHeight(); ++i){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        // GRISES (FACILES)
                        fc.grisesConcurrente(imagen, copiaGrises, Integer.parseInt(Thread.currentThread().getName()));
                        fc.correctudConcurrente(imagen, copiaCorrectud, Integer.parseInt(Thread.currentThread().getName()));
                        fc.correctud2Concurrente(imagen, copiaCorrectud2, Integer.parseInt(Thread.currentThread().getName()));

                        // Blurs (Dificiles)
                        fc.motionBlurConcurrente(imagen, copiaMotionBlur, Integer.parseInt(Thread.currentThread().getName()));
                        fc.blur2Concurrente(imagen, copiaBlur2, Integer.parseInt(Thread.currentThread().getName()));
                        
                        // Varios (Medios)
                        fc.sharpenConcurrente(imagen, copiaSharpen, Integer.parseInt(Thread.currentThread().getName()));
                        fc.componentesRGBConcurrente(imagen, copiaComponentesRGB, Integer.parseInt(Thread.currentThread().getName()));
                        
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
        // GRISES (FACILES)
        fc.guardaImagen(copiaGrises,"src/Filtros/Prueba_grises_concurrente.png");
        fc.guardaImagen(copiaCorrectud,"src/Filtros/Prueba_correctud_concurrente.png");
        fc.guardaImagen(copiaCorrectud2,"src/Filtros/Prueba_correctud2_concurrente.png");

        // Blurs (Dificiles)
        fc.guardaImagen(copiaMotionBlur,"src/Filtros/Prueba_motion_blur_concurrente.png");
        fc.guardaImagen(copiaBlur2,"src/Filtros/Prueba_blur2_concurrente.png");

        // Varios (Medios)
        fc.guardaImagen(copiaSharpen,"src/Filtros/Prueba_sharpen_concurrente.png");
        fc.guardaImagen(copiaComponentesRGB,"src/Filtros/Prueba_ComponentesRGB_concurrente.png");
    }
}