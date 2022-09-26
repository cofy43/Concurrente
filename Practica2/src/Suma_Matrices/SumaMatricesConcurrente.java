package Suma_Matrices;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SumaMatricesConcurrente implements Runnable{
    double[][] matrizA;
    double[][] matrizB;
    static double[][] resultado;

    public SumaMatricesConcurrente(double[][] matrizA, double[][] matrizB){
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        resultado = new double[matrizA.length][matrizA.length];
    }

    @Override
    public void run() {
        sumaConcurrente(matrizA, matrizB,Integer.parseInt(Thread.currentThread().getName()));
    }

    public void sumaConcurrente(double[][] matrizA, double[][] matrizB, int fila){
        for(int j = 0; j < matrizB.length; j++){
            resultado[fila][j] = matrizA[fila][j] + matrizB[fila][j];
        }
    }

    public void muestra(double[][] matrix){
        for(int i = 0; i < matrizA.length; ++i){
            for(int j = 0; j < matrizB.length; j++){
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    private static double[][] readMatrix(String pathFile) {
        try {
            // Lectura del archivo con la sopa de letras
            File myObj = new File(pathFile);
            Scanner myReader = new Scanner(myObj);
            List<double[]> rows = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                double[] doubleArray = Arrays.stream(data).mapToDouble(Double::parseDouble).toArray();
                rows.add(doubleArray);
            }
            myReader.close();
            double[][] matrix = new double[rows.size()][rows.get(0).length];
            for (int i = 0; i < rows.size(); i++) {
                for (int j = 0; j < rows.size(); j++) {
                    matrix[i][j] = rows.get(i)[j];
                }
            }
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        long tInicio, tFin;
        tInicio = System.nanoTime();
        double[][] A = readMatrix("src/Suma_Matrices/Matriz.txt");
        double[][] B = readMatrix("src/Suma_Matrices/Matriz1.txt");
        SumaMatricesConcurrente m = new SumaMatricesConcurrente(A,B);
        int hilos = 5;
        List<Thread> hilosh = new ArrayList<Thread>();
        for(int i = 0; i < A.length; i++){
            Thread t = new Thread(m,""+i);
            hilosh.add(t);
            t.start();
            if(hilosh.size() == hilos){
                for(Thread threads: hilosh){
                    threads.join();
                }
                hilosh.clear();
            }
        }
        for(Thread threads: hilosh){
            threads.join();
        }
        System.out.println("Matriz 1:");
        m.muestra(A);
        System.out.println("Matriz 2:");
        m.muestra(B);
        System.out.println("Matriz 1 + Matriz 2:");
        m.muestra(resultado);
        tFin = System.nanoTime()-tInicio;
        System.out.println("Tiempo:");
        System.out.println(tFin); // Me dio 392167 // 59 lineas - 6 saltos - 13 = 40 de las cuales 5 son paralelas :v
    }
}