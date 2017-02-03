/**
 * Created by Anton Skudarnov on 01.02.2017.
 * Special note class for implementation of LinkedList. Object of this class keep value and point on
 * the next object(LinkedBag) in LinkedList
 */
public class LinkedBag<T> {
    /**
     *
     * @param o value for LinkedBag
     */
    LinkedBag(T o){
        value = o;
        next = null;
    }

    T value;  //the value of bag

    LinkedBag next;  // the pointer on the next object(LinkedBag)

    /**
     * Set the next object
     * @param o the next object
     */
    public void setNext(LinkedBag o){
        next = o;
    }
}
