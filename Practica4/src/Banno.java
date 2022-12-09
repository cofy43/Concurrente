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
        //Aqui va el codigo
    }

    public void salHombre(){
        //Aqui va el codigo
    }

    public void entraMujer() throws InterruptedException{
        //Aqui va el codigo
    }

    public void salMujer(){
        //Aqui va el codigo
    }
}