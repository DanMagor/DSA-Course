import java.util.ArrayList;

/**
 * Created by Anton Skudarnov on 21.04.2017.
 */
public class BinaryMinHeap<K extends Comparable, V> {

    public BinaryMinHeap() {

    }

    /**
     * @param arrayList list of elements to be placed into heap
     */
    public BinaryMinHeap(ArrayList<Entry<K, V>> arrayList) {
        heapify(arrayList);
    }

    //heap entry
    public class Entry<K, V> {

        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        } //only value can be changed
    }

    ArrayList<Entry<K, V>> heap = new ArrayList<>(); //heap initialization

    public int getSize() {
        return heap.size();
    }

    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> entry = new Entry(key, value);
        heap.add(entry); //adds to the end, which preserves complete tree property
        upHeap(getSize() - 1); //corrects the structure of the heap
        return entry;
    }

    //parent and child indexes according to array based tree representation
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    private boolean hasLeftChild(int index) {
        return (getLeftChildIndex(index) < getSize());
    }

    private boolean hasRightChild(int index) {
        return (getRightChildIndex(index) < getSize());
    }

    private boolean hasChildren(int index) {
        return (hasLeftChild(index) && hasRightChild(index));
    }

    /**
     * sift-up, heapifyUp, upHeap corrects the structure of the heap
     * via going up and swapping starting from the node at the index
     * @param index
     */
    private void upHeap(int index) {
        if (!heap.isEmpty() && index != 0) {                            //if current element is not root and not heap is not empty
            Entry<K, V> current = heap.get(index);
            Entry<K, V> parent = heap.get(getParentIndex(index));
            if (current.getKey().compareTo(parent.getKey()) < 0) {      //if current element's key is more than parent's key
                heap.set(index, parent);                                //swap elements
                heap.set(getParentIndex(index), current);
                upHeap(getParentIndex(index));                          //go up heap to the parent
            }
        }
    }

    /**
     * @param arrayList list of elements to be placed into heap
     */
    private void heapify(ArrayList<Entry<K, V>> arrayList) {
        for (Entry<K, V> e :
                arrayList) {
            insert(e.getKey(), e.getValue());
        }
    }

    /**
     * removes and returns max entry
     * @return max entry which is on the top of the heap
     */
    public Entry<K, V> removeMin() {
        if (!heap.isEmpty()) {
            Entry<K, V> maxEntry = heap.get(0);     //according to array based tree representation root is at the beginning of array
            heap.set(0, heap.get(getSize() - 1));   //to preserve complete tree property replace the first with the last
            heap.remove(getSize() - 1);        //then remove the last
            downHeap(0);                       //correct the structure starting from the new root
            return maxEntry;
        }
        return null;
    }


    private void downHeap(int index) {
        if (hasLeftChild(index)) {                                          //downHeap is possible only if node has children
            Entry<K, V> current = heap.get(index);
            Entry<K, V> left = heap.get(getLeftChildIndex(index));
            Entry<K, V> maxChild = left;                                    //let's suppose left child is max
            int maxChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index)) {                                     //if node has right child, let's check if our max can be right
                Entry<K, V> right = heap.get(getRightChildIndex(index));    //if right child is bigger than max, let it be max
                if (right.getKey().compareTo(left.getKey()) < 0) {
                    maxChild = right;
                    maxChildIndex = getRightChildIndex(index);
                }
            }
            if (maxChild.getKey().compareTo(current.getKey()) < 0) {        //if parent is less then max child, than swap and downHeap
                heap.set(index, maxChild);
                heap.set(maxChildIndex, current);
                downHeap(maxChildIndex);
            }                                                               //else our heap is correct
        }
    }

    /**
     * @return max entry (doesn't remove it)
     */
    public Entry<K, V> min() {
        if (!heap.isEmpty())
            return heap.get(0);
        return null;
    }

    public boolean isEmpty(){
        if (heap.isEmpty())
            return true;
        return false;
    }
}
