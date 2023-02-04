package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    public class HashMapIterator implements Iterator<K>{
        private int index;
        private Node p;

        HashMapIterator(){
            index = 0;
            p = null;
        }

        @Override
        public boolean hasNext() {
            for(int i=index;i<buckets.length;i++){
                if(buckets[i]==null) continue;
                for(Node item : buckets[i]){
                    if(item!=null){
                        p = item;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public K next() {
            int pth = Math.floorMod(p.key.hashCode(),buckets.length);
            index = pth;
            return p.key;
        }
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double loadFactor;
    private int size;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(16);
        loadFactor = 0.75;
        size = 0;
    }

    public MyHashMap(int initialSize) {
        buckets = createTable(initialSize);
        loadFactor = 0.75;
        size = 0;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        loadFactor = maxLoad;
        size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    /** Removes all of the mappings from this map. */
    public void clear(){
        size = 0;
        for(int i=0;i<buckets.length;i++){
            buckets[i] = null;
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        int index = Math.floorMod(key.hashCode(),buckets.length);
        if(buckets[index]==null) return false;
        for(Node item : buckets[index]){
            if(item.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        int index = Math.floorMod(key.hashCode(),buckets.length);
        if(buckets[index]==null) return null;
        for(Node item:buckets[index]){
            if(item.key.equals(key)){
                return item.value;
            }
        }
        return null;
    }

    private Node getNode(K key){
        int index = Math.floorMod(key.hashCode(),buckets.length);
        if(buckets[index]==null) return null;
        for(Node item:buckets[index]){
            if(item.key.equals(key)){
                return item;
            }
        }
        return null;
    }
    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value){
        if(containsKey(key)){
            getNode(key).value = value;
            return;
        }
        double load = (double) size()/buckets.length;
        if(load >= loadFactor) resizeUp();
        int index = Math.floorMod(key.hashCode(),buckets.length);
        if(buckets[index]==null){
            buckets[index] = createBucket();
            buckets[index].add(createNode(key,value));
        }else{
            buckets[index].add(createNode(key,value));
        }
        size++;
    }

    private void resizeUp(){
        Collection<Node>[] temp = new Collection[buckets.length*2];
        for(int i=0;i<buckets.length;i++){
            if(buckets[i]==null) continue;
            for(Node item : buckets[i]){
                int index = Math.floorMod(item.key.hashCode(),temp.length);
                if(temp[index]==null){
                    temp[index] = createBucket();
                    temp[index].add(item);
                }else{
                    temp[index].add(item);
                }
                temp[index].add(item);
            }
        }
        buckets = temp;
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        Set s = new HashSet<>();
        for(int i=0;i<buckets.length;i++){
            if(buckets[i]==null) continue;
            for(Node item : buckets[i]){
                s.add(item.key);
            }
        }
        return s;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        int index = Math.floorMod(key.hashCode(),buckets.length);
        V returnValue =null;
        if(buckets[index]==null) return null;
        for(Node item:buckets[index]){
            if(item.key.equals(key)){
                buckets[index].remove(item);
                returnValue = item.value;
            }
        }
        return returnValue;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        int index = Math.floorMod(key.hashCode(),buckets.length);
        V returnValue =null;
        if(buckets[index]==null) return null;
        for(Node item:buckets[index]){
            if(item.key.equals(key)&&item.value.equals(value)){
                buckets[index].remove(item);
                returnValue = item.value;
            }
        }
        return returnValue;
    }



}
