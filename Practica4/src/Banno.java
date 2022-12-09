import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banno {
    private volatile long timesMalesEntered;
    private volatile long timesFemalesEntered;

    private volatile long males;
    private volatile long females;
    
    private boolean used;

    private Lock lock = new ReentrantLock();

    private Condition hombres = lock.newCondition();
    private Condition mujeres = lock.newCondition();

    public Banno(){
        this.timesMalesEntered = 0;
        this.timesFemalesEntered = 0;
        males = females = 0;
        used = false;
    }

    public long getTimesMalesEntered() {
        return timesMalesEntered;
    }

    public long getTimesFemalesEntered() {
        return timesFemalesEntered;
    }

    public long getMales() {
        return males;
    }

    public long getFemales() {
        return females;
    }

    public void entraHombre() throws InterruptedException{
        lock.lock();
        try {
            while (used) {
                this.hombres.await();
            }
            this.timesMalesEntered += 1;
            this.males += 1;
        } finally {
            lock.unlock();
        }
    }

    public void salHombre(){
        this.used = false;
        this.lock.unlock();
        this.hombres.signalAll();
    }

    public void entraMujer() throws InterruptedException{
        lock.lock();
        try {
            while (used) {
                this.mujeres.await();
            }
            this.timesMalesEntered += 1;
            this.males += 1;
        } finally {
            lock.unlock();
        }
    }

    public void salMujer(){
        this.used = false;
        this.lock.unlock();
        this.mujeres.signalAll();
    }
}