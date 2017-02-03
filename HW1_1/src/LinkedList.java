/**
 * Created by Anton Skudarnov on 01.02.2017.
 * The class is implementation of LinkedList.
 */
public class LinkedList<T>  {

    LinkedList(){
        count = 0;
        header = null;
        tail = null;
    }

    LinkedBag header; //the first element
    LinkedBag tail;  // the last element
    int count;      // amount of elements in LinkedList

    /**
     * Add new object in LinkedList
     * @param obj new object for LinkedList
     */
    public void add(T obj){
        LinkedBag o = new LinkedBag(obj);
        if (count == 0) {header = o; tail = o;}
        else{
            LinkedBag temp;
            temp = tail;
            tail = o;
            temp.setNext(o);
        }
        count++;
    }

    /**
     * Display all elements of LinkedList
     */
    public void display(){
        for(LinkedBag i = header; i!=null; i=i.next){
            System.out.print(i.value+" ");
        }
        System.out.println();
    }

    /**
     *
     * @return the first element of LinkedList
     */
    public Object peek(){
        return header.value;
    }


}
