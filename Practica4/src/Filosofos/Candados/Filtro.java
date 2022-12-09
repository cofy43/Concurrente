package Filosofos.Candados;

public class Filtro implements Semaforo {

    int threads;
    int maxConcurrentTheads;
    volatile int level[];
    volatile int lastToEnter[];

    public Filtro(int threads, int maxConcurrentTheads) {
        this.threads = threads;
        this.maxConcurrentTheads = threads;
        this.level = new int[threads];
        this.lastToEnter = new int[(threads-1)];
    }

    @Override
    public int getPertitsOnCriticalSections() {
        return this.maxConcurrentTheads;
    }

    @Override
    public void acquire() {
        int i = Integer.parseInt(Thread.currentThread().getName()); // ThreadID is a ThreadLocal field
        for (int l = 1; l < (threads-1); l++) {
            level[i] = l;
            lastToEnter[l] = i;
            while(this.sameOrHigher(i, l) && lastToEnter[l] == i) {}
        }
    }

    boolean sameOrHigher(int i, int j) {
        for (int k = 0; k < this.threads; k++) {
            if (k != i && level[k] >= j) return true;
        }
        return false;
    }

    @Override
    public void release() {
        int i = Integer.parseInt(Thread.currentThread().getName()); // ThreadID is a ThreadLocal field
        level[i] = 0;
    }
    
}
