/**
 * Created by Anton Skudarnov on 01.02.2017.
 * The class is implementation of LinkedQueue
 */
public class LinkedQueue<T> extends LinkedList<T>{

    /**
     * Add new object in Queue
     * @param obj new object for Queue
     */
    public  void enqueue(T obj){
       add(obj);
    }

    /**
     * Delete one element from Queue
     */
    public void dequeue(){
        header = header.next;
    }

}
