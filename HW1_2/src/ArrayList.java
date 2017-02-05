import java.util.Objects;

/**
 * Created by Anton Skudarnov on 04.02.2017.
 * Implementation of ArrayList based on default Array.
 */
public class ArrayList<T> {

    private T arr[];
    private int size; // real size of ArrayList
    public int count; // amount of elements

    /**
     * Default constructor of class ArrayList
     */
    ArrayList() {
        count = 0;
        size = 8;
        arr = (T[]) new Object[size];
    }

    /**
     * Constructor with initial size
     * @param s initial size of ArrayList
     */
    ArrayList(int s){
        count = 0;
        size = s;
        arr = (T[]) new Object[size];
    }
    /**
     * resize array if not enough space
     */
    private void resize() {
        size = size * 2;
        T temp[] = (T[]) new Object[size];
        for (int i = 0; i < count; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    /**
     * Add new element in ArrayList. Element add in the end of array.
     * @param obj new object for ArrayList
     */
    public void add(T obj) {
        if (count >= size) {
            resize();
        }
        arr[count] = obj;
        count++;
    }

    /**
     * @param i the index of element
     * @return element on i-th position
     */
    public T get(int i){
        return arr[i];
    }

}
