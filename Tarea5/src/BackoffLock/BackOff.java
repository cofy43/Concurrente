package src.BackoffLock;

import java.util.Random;

public class BackOff {

    int minDelay;
    int maxDelay;
    int limit;
    Random random;

    public BackOff(int min, int max) {
        minDelay = min;
        maxDelay = max;
        this.limit = minDelay;
        random = new Random();
    }

    public void backoff() throws InterruptedException {
        int delay = random.nextInt(limit);
        limit = Math.min(maxDelay, 2 * limit);
        Thread.sleep(delay);
    }

}
