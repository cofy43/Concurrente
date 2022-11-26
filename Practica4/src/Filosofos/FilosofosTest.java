package Filosofos;

public class FilosofosTest {
    static int TABLE_SIZE = 5;
    static int TEN_SECONDS = 10000;
    static int EXPECTED_EATING_TIMES = 500;

    Filosofo[] philosophers;
    Thread[] threads;
    Tenedor[] chopsticks;

    Semaforo semaphore;

    void setUp() {
        initChopsticks();
    }

    void initChopsticks() {
        chopsticks = new Tenedor[TABLE_SIZE];
        for(int i = 0; i < TABLE_SIZE; i++) {
            chopsticks[i] = new TenedorImp(i);
        }
    }

    void initThreads() {
        threads = new Thread[5];
        for(int i = 0; i < TABLE_SIZE; i++) {
            threads[i] = new Thread(philosophers[i], String.format("%d", i));
        }
    }

    void initPhilosophers(){
        semaphore = new FilterSemaphoreImpl(TABLE_SIZE,4);
        philosophers = new Filosofo[TABLE_SIZE];

        try{
            for(int i=0;i<TABLE_SIZE; i++){
                philosophers[i].setId(i);
                //philosophers[i].set
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void runThreads() throws InterruptedException {
        for(Thread thread: threads) {
            thread.start();
        }

        Thread.sleep(TEN_SECONDS);

        for(Thread t : threads) {
            t.interrupt();
        }

        for(Thread t : threads) {
            t.join();
        }
    }

}