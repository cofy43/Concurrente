package Suma_N;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumaNConcurrente implements Runnable {

    static int result;
    static int n;

    public static int getResult() {
        return result;
    }

    public static void setResult(int r) {
        result = r;
    }

    @Override
    public void run() {
        try {
            System.out.println("entra");
            int i = Integer.parseInt(Thread.currentThread().getName()) + 1;
            suma(i);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static void suma(int i) throws InterruptedException {
        int temp = getResult();
        temp += i;
        setResult(temp);

    }

    public static void main(String[] args) throws InterruptedException {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Introduce un valor entero positivo");
            n = sc.nextInt();
            int hilos = 10;
            List<Thread> hilosh = new ArrayList<Thread>();
            for (int i = 0; i < n; i++) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = Integer.parseInt(Thread.currentThread().getName()) + 1;
                        try {
                            suma(i);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                },""+i);
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
            System.out.println("El resultado de la suma es:");
            System.out.println(result);
        }
    }
}
