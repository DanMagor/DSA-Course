/**
 * Created by Anton Skudarnov on 05.02.2017.
 */
public class BitSet<T> {

    private int count;
    private T arr[];
    private int size;

    /**
     * Default Constructor of the Class BitSet
     * Default Size = 8
     * Default count of element = 0
     */
    BitSet() {
        count = 0;
        size = 8;
        arr = (T[]) new Object[size];
    }

    /**
     * resize this Set
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
     * Add new element in Set
     * @param obj new object for Set
     */
    public void add(T obj) {
        if (!isConsist(obj)) {
            if (count >= size) {
                resize();
            }
            arr[count] = obj;
            count++;
        }
    }

    /**
     * Does the set have object "obj"?
     * @param obj object for checking.
     * @return true if there is object "obj". return false if there is no object "obj".
     */
    public boolean isConsist(T obj) {
        boolean flag = false;
        for(int i=0; i<count; i++){
            if (arr[i].equals(obj)) {flag = true; i=count;}
        }
        return flag;
    }
}
