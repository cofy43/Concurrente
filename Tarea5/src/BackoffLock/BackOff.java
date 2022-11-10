package src.BackoffLock;
import java.util.Random;

/*
* Clase BackOff
*/
public class BackOff {
    /*Atributos*/
    int minDelay;
    int maxDelay;
    int limit;
    Random random;

    /*
    *Método constructor Backoff
    *@param min
    *@param amx
    */
    public BackOff(int min, int max) {
        minDelay = min;
        maxDelay = max;
        this.limit = minDelay;
        random = new Random();
    }
    /*Exception*/
    public void backoff() throws InterruptedException {
        int delay = random.nextInt(limit);
        limit = Math.min(maxDelay, 2 * limit);
        Thread.sleep(delay);
    }

}
