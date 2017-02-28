import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Anton Skudarnov on 23.02.2017.
 */

/**
 * That class contains implementation of Hash Table. It use quadratic open addressing strategy for collisions.
 * @param <K> key of Hash Entry
 * @param <V> value of Hash Entry
 */
public class Hashtable<K, V> {
    private int size;   //amount of elements in the table
    private HashEntry<K, V>[] table;
    private int tableSize;  //size of table (with null and deleted elements)

    Hashtable() {
        size = 0;
        tableSize = 128;
        table = new HashEntry[tableSize];
        for (int i = 0; i < tableSize; i++)
            table[i] = null;
    }

    /**
     *  Add new element in hash table
     * @param key is for hash function
     * @param value is value of entry
     * @return <code>null</code> if there is no element with <code>key</code> and value of old elements otherwise
     */
    public V put(K key, V value) {
        int hash = Math.abs(key.hashCode());
        int index = hash % tableSize;
        int power = 0;
        while ((table[index] != null & table[index] != DeletedHashEntry.uniquieDeletedEntry()) &&
                !table[index].key.equals(key)){
            index = (index + (int) Math.pow(++power, 2)) % tableSize;
        }
        if (table[index] == null || table[index] == DeletedHashEntry.uniquieDeletedEntry()) {
            table[index] = new HashEntry(key, value);
            size++;
            if (size >= tableSize) resize();
            return null;
        } else {
            HashEntry<K, V> hashEntry = table[index];
            V old = hashEntry.value;
            table[index] = new HashEntry(key, value);
            size++;
            if (size >= tableSize) resize();
            return old;
        }
    }

    /**
     * Get value of elements with <code>key</code>
     * @param key key of elements
     * @return the value of elements
     */
    public V get(K key){
        int power = 0;
        int index = Math.abs(key.hashCode()) % tableSize;
        while(table[index] != null && !table[index].key.equals(key)){
            index = (index + (int) Math.pow(++power, 2)) % tableSize;
        }
        if (table[index] == null)
            return null;
        else
            return table[index].value;
    }

    /**
     * Is it contain element with <code>key</code>
     * @param key is key of element
     * @return true if there is such element, false otherwise
     */
    public boolean contains(K key) {
        int index = Math.abs(key.hashCode()) % tableSize;
        int power = 0;
        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + (int) Math.pow(++power, 2)) % tableSize;
        }
        if (table[index] == null)
            return false;
        else
            return true;
    }

    /**
     * Remove element with <code>key</code>
     * @param key is key or removing element
     * @return the value of removed element
     */
    public V remove(K key) {
        int power = 0;
        int index = key.hashCode() % tableSize;
        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + (int) Math.pow(++power, 2)) % tableSize;
        }
        if (table[index] == null) {
            return null;
        } else {
            HashEntry<K, V> temp = table[index];
            table[index] = new DeletedHashEntry();
            size--;
            return temp.value;
        }
    }

    /**
     * Corteges of all not null elements
     * @return <code>ArrayList</code> with <code>HashEntry</code>.
     */
    public ArrayList<HashEntry<K,V>> values(){
        ArrayList<HashEntry<K,V>> values = new ArrayList<>();
        for(int i=0;i<tableSize;i++){
            if(table[i]!=null && table[i]!=DeletedHashEntry.uniquieDeletedEntry()) {
                values.add(table[i]);
            }
        }
        return values;
    }

    /**
     * It is needed for resize if there is no enough space for new elements.
     */
    private void resize() {
        HashEntry<K, V>[] temp = table;
        tableSize = tableSize * 2;
		table = new HashEntry[tableSize];
		size = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null && temp[i]!=DeletedHashEntry.uniquieDeletedEntry())
                put(temp[i].key, temp[i].value);
        }
    }




}
