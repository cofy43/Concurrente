package Suma_N;

import java.util.Scanner;

public class SumaN {

    static int suma(int n) {
        int resultado = 0;
        for (int i = 0; i < n; i++) {
            resultado += i + 1;
        }
        return resultado;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Introduce un valor entero positivo");
            int n = sc.nextInt();
            System.out.println("El resultado de la suma es:");
            System.out.println(suma(n));
        }
    }
}
