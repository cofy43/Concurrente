public class Peterson implements Lock {

    public Peterson() {}

    boolean flag[] = {false, false};
    volatile int turn;

    @Override
    public void lock() {
        int i = Integer.parseInt(Thread.currentThread().getName()); // ThreadID is a ThreadLocal field
        int j = 1 - i; // Another thread
        flag[i] = true; // change flag of current Thread
        turn = i; // assing turn
        while(flag[j] && turn == i) {} // wait
    }

    @Override
    public void unlock() {
        int i = Integer.parseInt(Thread.currentThread().getName()); // ThreadID is a ThreadLocal field
        flag[i] = false; // Update boolean
    }
    
}
