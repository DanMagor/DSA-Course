import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Anton Skudarnov on 21.03.2017.
 */
public class BinaryHeap<K extends Comparable<K>, V> {
    public ArrayList<HeapEntry<K, V>> heap;

    BinaryHeap() {
        heap = new ArrayList<HeapEntry<K, V>>();
        comp = Comparator.reverseOrder();
    }

    private Comparator<K> comp;

    public HeapEntry insert(K key, V value) {
        HeapEntry entry = new HeapEntry<K,V>(key, value);
        heap.add(entry);
        upheap(heap.size() - 1);
        return entry;
    }

    protected void upheap(int j) {
        while (j > 0) {
            int p = (j - 1) / 2;
            if (comp.compare(heap.get(j).getKey(), heap.get(p).getKey()) <= 0) break;
            swap(j, p);

            j = p;
        }
    }

    protected void swap(int i, int j) {
        HeapEntry temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() {
        if (heap.size() == 0)
            return true;
        return false;
    }

    public HeapEntry<K, V> min() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    public HeapEntry<K, V> removeMin() {
        if (isEmpty()) return null;
        HeapEntry<K, V> entry = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapify(0);
        return entry;
    }

    protected boolean hasLeft(int j) {
        if (j * 2 + 1 < heap.size()) return true;
        return false;
    }

    protected boolean hasRight(int j) {
        if (j * 2 + 2 < heap.size()) return true;
        return false;
    }

    protected void heapify(int i) {

        while (hasLeft(i)) {
            int left = i * 2 + 1;
            int max = left;
            if (hasRight(i)) {
                int right = i * 2 + 2;
                if (comp.compare(heap.get(left).getKey(), heap.get(right).getKey()) > 0)
                    max = left;
            }
            if (comp.compare(heap.get(max).getKey(), heap.get(i).getKey()) <= 0) break;
            swap(i, max);
            i = max;
        }
    }

}
