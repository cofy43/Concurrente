package Filosofos;

public class TenedorImp implements Tenedor {

    private int id;
    private int times;

    boolean flag[] = {false, false};
    volatile int turn;

    public TenedorImp(int id) {
        this.id = id;
        this.times = 0;
    }

    @Override
    public void tomar() {
        int i = Integer.parseInt(Thread.currentThread().getName()); // ThreadID is a ThreadLocal field
        int j = 1 - i; // Another thread
        flag[i] = true; // change flag of current Thread
        this.times += 1; // Increment number of times that we taked
        turn = i; // assing turn
        while(flag[j] && turn == i) {} // wait
    }

    @Override
    public void soltar() {
        int i = Integer.parseInt(Thread.currentThread().getName()); // ThreadID is a ThreadLocal field
        flag[i] = false; // Update boolean
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getVecesTomado() {
        return this.times;
    }

}
