package src.CLHLock;
import java.util.concurrent.atomic.AtomicReference;
import src.Lock;

/*
* Clase CLHLock que implementa Lock
* @author Concurreteam
*/
public class CLHLock implements Lock{
    private final AtomicReference<QNode> tail;
	private final ThreadLocal<QNode> myPred;
	private final ThreadLocal<QNode> myNode;

	/*Método constructor*/
	public CLHLock() {
        tail = new AtomicReference<QNode>(new QNode());
		myNode = new ThreadLocal<QNode>(){
			protected QNode initialValue() {
				return new QNode();
			}
		};
		
		myPred = new ThreadLocal<QNode>();
	}

    @Override
    /*Método void que bloquea*/		
    public void lock() {
        QNode qnode = myNode.get();
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.locked) {};
    }

    @Override
    /*Método voisd que desbloquea*/	
    public void unlock() {
        QNode qNode = myNode.get();
        qNode.locked = false;
        myNode.set(myPred.get());
    }
    
}
