import sun.reflect.generics.tree.Tree;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Anton Skudarnov on 27.02.2017.
 */
public class BinaryTree<K,V> implements Map {
    public int size;
    public TreeNote<K,V> root;
    private
    BinaryTree(){
        size = 0;
        root = new TreeNote<K, V>(null,null);
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
