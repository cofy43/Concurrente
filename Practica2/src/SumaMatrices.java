import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SumaMatrices{
    double[][] matrizA;
    double[][] matrizB;
    static double[][] resultado;

    public SumaMatrices(double[][] matrizA, double[][] matrizB){
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        resultado = new double[matrizA.length][matrizA.length];
    }
   
    public void sumaSecuencial(){
        for(int i = 0; i < matrizA.length; i++){
            for(int j = 0; j < matrizB.length; j++){
                resultado[i][j] = matrizA[i][j] + matrizB[i][j];
            }
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

        double[][] A = readMatrix("Matriz.txt");
        double[][] B = readMatrix("Matriz1.txt");
        SumaMatrices m = new SumaMatrices(A,B);
        m.sumaSecuencial();
        System.out.println("Matriz 1:");
        m.muestra(A);
        System.out.println("Matriz 2:");
        m.muestra(B);
        System.out.println("Matriz 1 + Matriz 2:");
        m.muestra(resultado);
        tFin = System.nanoTime()-tInicio;
        System.out.println("Tiempo:");
        System.out.println(tFin);//Me dio 162375 //41 lineas - 3 saltos de linea - 8 llaves que cierran = 30
    }
}