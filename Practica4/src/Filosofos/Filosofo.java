package Filosofos;

public class Filosofo implements Runnable {

    private int id;
    private int threadsNumber;

    public Filosofo(int threads) {
        this.threadsNumber = threads;
    }

    @Override
    public void run() {
        System.out.println("Filosofo:" + id + " comiendo");
    }

    public void setId(int i) {
        this.id = i;
    }

}
