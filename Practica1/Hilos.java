import java.util.ArrayList;
import java.util.List;

/**
 * Clase que ejemplifica los Hilos implementando Runnable
 * @author Sunny Mirael
 * @version 1.1
 */
public class Hilos implements Runnable {
    @Override
    //Sobrescribimos el metodo run
    public void run() { 
        //Pedimos el nombre del hilo pidiendo primero que se seleccione el Hilo
        System.out.println("Hola soy el: "+ Thread.currentThread().getName());
    }
    public static void main(String[] args) throws InterruptedException {
        //Se crea una instancia de la clase
        Hilos h = new Hilos();
        List<Thread> lista = new ArrayList<Thread>();
        for (int i=0; i < 10; i++) {
            // Creamos un hilo
            Thread t = new Thread(h,"Hilo " + i);
            // Lo inicamos
            t.start();
            // lo agregamos a la lista
            lista.add(t);
        }
        // Ejecutamos el método join para cada hilo de la lista
        for (Thread t : lista) {
            t.join();
        }
    }
}