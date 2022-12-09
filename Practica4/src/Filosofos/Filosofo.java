package Filosofos;

/**
 * Clase que simula el problema de los 5 filosofos comiendo
 */
public abstract class Filosofo implements Runnable {
    public static int DEFAULT_MEZA_TAM = 5;
    private int ID;
    private int comido;
    private Tenedor tenedorL;
    private Tenedor tenedorD;

    public Filosofo(int ID){
        this.ID = ID;

        comido = 0;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 500; ++i){
                pensar();
                entrarALaMesa();
            }
        }catch(Exception e){}
        
    }

    /**
     * Metodo donde un filosofo entra a la mesa para comer, una vez que haya terminado de pensar
     * Debemos tomar el tenedor, comer, FINALMENTE, dejar los tenedores libres.
     * @throws InterruptedException
     */
    public void entrarALaMesa() throws InterruptedException{
        this.comido = this.ID;
    }

    /**
     * El filosofo se detiene a pensar unos milisegundos para decidir que tenedor tomar.
     * @throws InterruptedException
     */
    public void pensar() throws InterruptedException{
        try{
            Thread.currentThread();
            Thread.sleep(getRandomTime());
        }catch(InterruptedException e){
        }
    }

    /**
     * Una vez que el filosofo tenga los 2 tenedores podra comensar a comer.
     * PELIGRO: ESTA ES LA SECCION CRITICA
     * @throws InterruptedException
     */
    public void eat() throws InterruptedException{
        int id = Integer.valueOf(Thread.currentThread().getName());
        if (id == this.ID) {
            this.tenedorD = new TenedorImp(this.ID);
            this.tenedorL = new TenedorImp(this.ID);
            this.tenedorD.tomar();
            this.tenedorL.tomar();
        }
    }

    /**
     * Metodo que nos regresa un valor entre 0 y 10
     * @return El valor pseudoaleatorio
     */
    private long getRandomTime() {
        double i=Math.random()*10.0;
        return (long)i ;
    }

    /**
     * Metodo que nos permite tomar los tenedores
     */
    public abstract void tomaTenedores();
    
    /**
     * Metodo que nos permite soltar los tenedores
     */
    public abstract void soltarTenedores();

    public void setId(int i) {
        this.ID = i;
    }
}