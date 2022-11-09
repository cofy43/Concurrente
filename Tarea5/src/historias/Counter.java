package src.historias;
import java.util.ArrayList;
import java.util.List;

public class Counter implements Runnable {
    public static final int ROUNDS = 5;
    private int counter = 0;

    @Override
    public void run() {
        for(int i = 0; i < ROUNDS; i++) {
            counter ++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        List<Thread> hilos = new ArrayList();
        int threadNumber = 26;
        for (int i = 0; i < threadNumber; i ++) {
            hilos.add(new Thread(c));
        }
        for (int i = 0; i < threadNumber; i ++) {
            hilos.get(i).start();
        }
        for (int i = 0; i < threadNumber; i ++) {
            hilos.get(i).join();
        }

        //Thread t1 = new Thread();
        //Thread t1 = new Thread();
        System.out.println(c.counter);
    }
}
