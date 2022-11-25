package com.example.extrahilos.Hilos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SumaMatrices {

    int[][] matrizA;
    int[][] matrizB;
    static int[][] resultado;

    public SumaMatrices(int[][] matrizA, int[][] matrizB){
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        resultado = new int[matrizA.length][matrizA.length];
    }

    public void sumaSecuencial(){
        for(int i = 0; i < matrizA.length; i++){
            for(int j = 0; j < matrizB.length; j++){
                resultado[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
    }

}
