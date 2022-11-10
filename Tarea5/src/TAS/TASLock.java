package src.TAS;
import java.util.concurrent.atomic.AtomicBoolean;
import src.Lock;

/*
* Clase TASLock que implementa Lock
* @author Concurreteam
*/
public class TASLock implements Lock{
    AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        // Intenta hasta que se adquiera el bloqueo
        while(state.getAndSet(true));
    }

    @Override
    public void unlock() {
        // Libera el bloqueo reiniciando el estado
        // a falso
        state.set(false);
    }
}
