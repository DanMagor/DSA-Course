/**
 * Created by Anton Skudarnov on 01.02.2017.
 */
public class LinkedStack<T> extends LinkedList<T> {

    /**
     * Add new object in the top of Stack
     * @param obj new object for LinkedStack
     */
    @Override
    public void add(T obj){
        LinkedBag o = new LinkedBag(obj);
        LinkedBag temp;
        temp = header;
        o.setNext(temp);
        header = o;
        if (count == 0) {tail = o;}
        count++;
    }
    /**
     Add new object in the top of Stack.
     @param obj new object for LinkedStack
     */
    public void push(T obj){
        add(obj);
    }

    /**
     * Delete one element from Stack
     */
    public void pop(){
        header = header.next;
    }


}

