package src.TTAS;
import java.util.concurrent.atomic.AtomicBoolean;

import src.Lock;

/*
*Clase TTASLock que implementa Lock
*/
public class TTASLock implements Lock{
    private AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        while(true){
            // Espere hasta que el candado se libere
            while(state.get());
            // Tratamos de adquirir el candado
            if(!state.getAndSet(true)){
                return;
            }
        }
    }

    @Override
    public void unlock() {
        // Libera el bloqueo reiniciando el estado
        // a falso
        state.set(false);
    }
}
