package Filosofos;

import Filosofos.Candados.Filtro;
import Filosofos.Candados.Semaforo;

/**
 * Clase que simula el problema de los filosofos resolviendolo con el Algoritmo 
 * del Filtro
 */
public class FilosofosFiltro extends Filosofo{
    private Semaforo filtro;

    public FilosofosFiltro(Semaforo filtro){
        super(-1);
        this.filtro = filtro;
    }

    @Override
    public void entrarALaMesa() throws InterruptedException{

    }

    @Override
    public void tomaTenedores() {

    }

    @Override
    public void soltarTenedores() {

    }
    
}