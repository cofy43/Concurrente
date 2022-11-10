package src.MCSLock;
import java.util.concurrent.atomic.AtomicReference;

import src.Lock;

/*
* Clase QNode
*/

class QNode{
    /*Atributos*/
    volatile boolean locked = false;
    QNode next = null;
}

/*
* Clase MCSLock que implementa Lock
* @author Concurreteam
*/
public class MCSLock implements Lock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;
    
    /*Método constructor*/
    public MCSLock(){
        tail = new AtomicReference<QNode>(null);
        myNode = new ThreadLocal<QNode>(){
            protected QNode initialValue(){
                return new QNode();
            }
        };
    }

    @Override
    /*Método void que bloquea*/
    public void lock() {
        QNode qnode = myNode.get();
        QNode pred = tail.getAndSet(qnode);
        if(pred != null){
            qnode.locked = true;
            pred.next = qnode;
            while(qnode.locked) Thread.yield();
        }
    }

    @Override
    /*Método void que desbloquea*/
    public void unlock() {
        QNode qnode = myNode.get();
        if(qnode.next == null){
            if(tail.compareAndSet(qnode,null)) return;
            while(qnode.next == null) Thread.yield();
        }
        qnode.next.locked = false;
        qnode.next = null;
    }
}
