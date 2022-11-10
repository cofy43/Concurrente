package src.BackoffLock;
import java.util.concurrent.atomic.AtomicBoolean;
import src.Lock;

/*
* Clase BackoffLock que implementa Lock
* @author Concurreteam
*/
public class BackoffLock implements Lock{
    private AtomicBoolean state = new AtomicBoolean(false);
    private static final int MIN_DELAY = 1;
    private static final int MAX_DELAY = 3;

    @Override
    /*Método void que bloquea*/
    public void lock() {
        BackOff backoff = new BackOff(MIN_DELAY, MAX_DELAY);
        while(true) {
            while(state.get()) {};
            if (!state.getAndSet(true)) {
                return;
            } else {
                try {
                    backoff.backoff();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    /*Método void que desbloquea*/
    public void unlock() {
        state.set(false);
    }
    
}
