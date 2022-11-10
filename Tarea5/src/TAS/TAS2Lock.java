package src.TAS;

import src.Lock;

/*
* Clase TAS2Lock que implementa Lock
* @author Concurreteam
*/

public class TAS2Lock implements Lock{
    volatile boolean state = false;

    @Override
    /*Método void que bloquea*/
    public void lock() {
        while(getAndSet(true));    
    }

    @Override
    /*Método void que desbloquea*/
    public void unlock() {
        set(false); 
    }
    
    /*
    *Método boolean que obtiene y asigna el estado
    *@param state
    @return result
    */
    public boolean getAndSet(Boolean state) {
        boolean result = this.state;
        this.state = state;
        return result;
    }

    /*
    *Método void que asigna el estado
    *@param state
    */
    public void set(boolean state) {
        this.state = state;
    }
}
