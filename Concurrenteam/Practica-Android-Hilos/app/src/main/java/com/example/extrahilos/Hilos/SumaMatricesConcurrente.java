package com.example.extrahilos.Hilos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SumaMatricesConcurrente implements Runnable{
    int[][] matrizA;
    int[][] matrizB;
    static int[][] resultado;

    public SumaMatricesConcurrente(int[][] matrizA, int[][] matrizB){
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        resultado = new int[matrizA.length][matrizA.length];
    }

    @Override
    public void run() {
        sumaConcurrente(matrizA, matrizB,Integer.parseInt(Thread.currentThread().getName()));
    }

    public void sumaConcurrente(int[][] matrizA, int[][] matrizB, int fila){
        for(int j = 0; j < matrizB.length; j++){
            resultado[fila][j] = matrizA[fila][j] + matrizB[fila][j];
        }
    }

}