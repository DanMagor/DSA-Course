import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by Anton Skudarnov on 23.03.2017.
 */
public class RBTree<K extends Comparable<K>, V> {


    protected class RBNote<K, V> {
        K key;
        V value;
        int color;
        RBNote<K, V> left;
        RBNote<K, V> right;
        RBNote<K, V> parent;

        RBNote(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;

        }

    }

    private Comparator<K> comp;

    private RBNote<K, V> root;

    RBTree() {
        root = null;
        comp = Comparator.naturalOrder();
    }

    public V put(K key, V value) {
        if (root == null) {
            root = new RBNote<>(key, value);
            root.color = 0;
            root.parent = null;
            return null;
        }
        RBNote<K, V> p = treeSearch(key);
        if (comp.compare(p.key, key) == 0) {
            V old = p.value;
            p.value = value;
            rebalanceInsert(p);
            return old;
        } else {
            RBNote<K, V> note = new RBNote<>(key, value);
            expandExternal(p, note);

            return null;
        }
    }

    protected void rebalanceInsert(RBNote<K, V> p) {
        if (p != root) {
            makeRed(p);
            resolveRed(p);
        } else
            p.color = 0;
    }

    private void resolveRed(RBNote<K, V> p) {
        RBNote<K, V> parent, uncle, grand;
        parent = p.parent;
        grand = parent.parent;
        if (isRed(parent)) { // double-red problem exists
            uncle = sibling(parent);
            if (uncle != null && isRed(uncle)) {
                parent.color = 0;
                uncle.color = 0;
                grand.color = 1;
                rebalanceInsert(grand);
            } else {
                if (p == parent.right && parent == grand.left) {
                    rotateLeft(parent);
                    p = p.left;
                } else if (p == parent.left && parent == grand.right) {
                    rotateRight(parent);
                    p = p.right;
                }
                parent = p.parent;
                grand = parent.parent;
                parent.color = 0;
                grand.color = 1;
                if (p == parent.left && parent == grand.left) {
                    rotateRight(grand);
                } else {
                    rotateLeft(grand);
                }
            }

        }
    }


    protected void makeRed(RBNote<K, V> p) {
        p.color = 1;
    }

    private void rotateLeft(RBNote<K, V> p) {

        RBNote<K, V> pivot = p.right;
        pivot.parent = p.parent;
        if (p.parent == null) root = pivot;
        if (p.parent != null) {
            if (p.parent.left == p)
                p.parent.left = pivot;
            else p.parent.right = pivot;
        }
        p.right = pivot.left;
        if (pivot.left != null)
            pivot.left.parent = p;
        p.parent = pivot;
        pivot.left = p;
    }

    private void rotateRight(RBNote<K, V> p) {

        RBNote<K, V> pivot = p.left;

        pivot.parent = p.parent;
        if (p.parent == null) root = pivot;
        if (p.parent != null) {
            if (p.parent.left == p)
                p.parent.left = pivot;
            else p.parent.right = pivot;
        }
        p.left = pivot.right;
        if (pivot.right != null)
            pivot.right.parent = p;
        p.parent = pivot;
        pivot.right = p;
    }


    private RBNote<K, V> sibling(RBNote<K, V> p) {
        RBNote<K, V> parent = p.parent;
        if (parent != null) {
            if (parent.left == p) {
                return parent.right;
            }
            return parent.left;
        }
        return null;
    }

    private boolean isRed(RBNote p) {
        return p.color == 1;
    }

    protected void expandExternal(RBNote<K, V> p, RBNote<K, V> note) {
        note.parent = p;
        if (comp.compare(p.key, note.key) < 0) {
            p.right = note;
            rebalanceInsert(note);
        } else {
            p.left = note;
            rebalanceInsert(note);
        }
    }

    protected boolean isExternal(RBNote p) {
        if (p.left == null && p.right == null)
            return true;
        return false;
    }

    public V getValue(K key) {
        RBNote<K, V> p = root;
        while (p != null && comp.compare(p.key, key) != 0) {
            if (comp.compare(p.key, key) < 0) {
                p = p.right;
            } else p = p.left;
        }
        if (p != null)
            return p.value;
        else return null;
    }

    public boolean containsKey(K key) {
        RBNote<K, V> p = root;
        while (p != null && comp.compare(p.key, key) != 0) {
            if (comp.compare(p.key, key) < 0) {
                p = p.right;
            } else p = p.left;
        }
        if (p != null)
            return true;
        else return false;
    }

    protected RBNote<K, V> treeSearch(K key) {
        RBNote<K, V> p = root;
        while (!isExternal(p) && comp.compare(p.key, key) != 0) {
            if (comp.compare(p.key, key) < 0) {
                if (p.right == null) break;
                p = p.right;
            } else {
                if (p.left == null) break;
                p = p.left;
            }
        }
        return p;
    }
}
