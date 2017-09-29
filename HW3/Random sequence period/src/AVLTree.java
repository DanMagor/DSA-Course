import javax.xml.soap.Node;
import java.util.Comparator;

/**
 * Created by Anton Skudarnov on 25.03.2017.
 */
public class AVLTree<K extends Comparable<K>, V> {


    private Comparator<K> comp;

    private Note<K, V> root;

    AVLTree() {
        root = null;
        comp = Comparator.naturalOrder();
    }

    /**
     *
     * @param key key for new note in tree
     * @param value value for new note
     * @return null if there was no element with the same key, old value otherwise
     */
    public V put(K key, V value) {
        if (root == null) {
            root = new Note<>(key, value);
            root.setParent(null);
            return null;
        } else {
            Note<K, V> temp = treeSerach(key);
            if (comp.compare(key, temp.getKey()) == 0) {
                V old = temp.getValue();
                temp.setValue(value);
                rebalance(temp);
                return old;
            }
            if (comp.compare(key, temp.getKey()) < 0) {
                temp.setLeft(new Note<>(key, value));
                temp.getLeft().setParent(temp);
                rebalance(temp.getLeft());
                return null;
            }
            temp.setRight(new Note<>(key, value));
            temp.getRight().setParent(temp);
            rebalance(temp.getRight());
            return null;

        }
    }

    /**
     *
     * @param key for search
     * @return true if there is element with such key, false otherwise
     */
    public boolean containsKey(K key){
        Note<K, V> p = root;
        while (p != null && comp.compare(p.getKey(), key) != 0) {
            if (comp.compare(p.getKey(), key) < 0) {
                p = p.getRight();
            } else p = p.getLeft();
        }
        if (p != null)
            return true;
        else return false;
    }

    /**
     *
     * @param key get value of  note with key
     * @return null if there is no such note, value otherwise
     */
    public V getValue(K key) {
        Note<K, V> p = root;
        while (p != null && comp.compare(p.getKey(), key) != 0) {
            if (comp.compare(p.getKey(), key) < 0) {
                p = p.getRight();
            } else p = p.getLeft();
        }
        if (p != null)
            return p.getValue();
        else return null;
    }

    /**
     *
     * @param note recompute height of the note
     * @return height of current note
     */
    protected int recomputeHeight(Note note) {
        note = root;
        Note left = root.getLeft();
        Note right = root.getRight();
        int height;
        if (left == null && right == null) {
            note.setHeight(1);
            return 1;
        } else if (left == null) {
            height = right.getHeight() + 1;
            note.setHeight(height);
            return height;
        } else if (right == null) {
            height = left.getHeight() + 1;
            note.setHeight(height);
            return height;
        } else
            height = Math.max(left.getHeight(),right.getHeight()) + 1;
        note.setHeight(height);
        return height;

    }

    /**
     * Rebalance subtree from note
     * @param note note that we want to rebalance
     */
    protected void rebalance(Note<K, V> note) {
        int oldHeight, newHeight;

        do {
            oldHeight = note.getHeight();
            if (!isBalanced(note)) {
                note = restructure(tallerChild(tallerChild(note)));
                recomputeHeight(note.getLeft());
                recomputeHeight(note.getRight());
            }
            recomputeHeight(note);
            newHeight = note.getHeight();
            note = note.getParent();
        } while (oldHeight != newHeight && note != null);
    }

    /**
     * tree node restructure
     * @param x note that we want to restructure
     * @return parent note after restructure
     */
    protected Note<K, V> restructure(Note<K, V> x) {
        Note<K, V> y = x.getParent();
        Note<K, V> z = y.getParent();
        if ((x == y.getRight()) == (y == z.getRight())) {
            rotate(y);
            return y;
        } else {
            rotate(x);
            rotate(x);
            return x;
        }
    }

    protected void rotate(Note<K, V> note) {
        Note<K, V> x = note;
        Note<K, V> y = x.getParent();
        Note<K, V> z = y.getParent();
        if (z == null) {
            root = x;
            x.setParent(null);
        } else
            relink(z, x, y == z.getLeft());

        if (x == y.getLeft()) {
            relink(y, x.getRight(), true);
            relink(x,y,false);
        }else{
            relink(y,x.getLeft(),false);
            relink(x,y,true);
        }
    }

    /**
     * need for restructure
     * @param parent future parent
     * @param child future child
     * @param makeLeft left or right child
     */
    protected void relink(Note<K, V> parent, Note<K, V> child, boolean makeLeft) {
        child.setParent(parent);
        if (makeLeft) {
            parent.setLeft(child);
        } else
            parent.setRight(child);
    }

    /**
     * The biggest child of note
     * @param note
     * @return
     */
    protected Note<K, V> tallerChild(Note<K, V> note) {
        Note<K, V> left = note.getLeft();
        Note<K, V> right = note.getRight();
        if (left.getHeight() > right.getHeight()) return left;
        if (left.getHeight() < right.getHeight()) return right;
        if (note == root) return left;
        else return right;

    }


    protected boolean isBalanced(Note<K, V> note) {
        if (isExternal(note)) return true;
        int leftH = note.getLeft().getHeight();
        int rightH = note.getRight().getHeight();
        if (Math.abs(leftH - rightH) <= 1)
            return true;
        return false;
    }

    protected Note<K, V> treeSerach(K key) {
        Note<K, V> temp = root;
        while (!isExternal(temp) && comp.compare(key, temp.getKey()) != 0) {
            if (comp.compare(key, temp.getKey()) < 0) {
                if (temp.getLeft()== null) return temp;
                temp = temp.getLeft();

            } else {
                if (temp.getRight() == null) return temp;
                temp = temp.getRight();
            }
        }
        return temp;
    }

    protected boolean isExternal(Note<K, V> note) {
        if (note.getLeft() == null && note.getRight() == null)
            return true;
        return false;
    }
}
