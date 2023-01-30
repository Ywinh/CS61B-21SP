package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B <K, V>{
    private BSTNode root;
    private int size;
    public BSTMap(){
        root=null;
        size=0;
    }

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key,V value){
            this.key=key;
            this.value=value;
        }
    }


    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
        size=0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        if(containsKey(key,root)){
            return true;
        }
        return false;
    }

    private boolean containsKey(K key,BSTNode T){
        if(T==null){
            return false;
        }
        if(key.equals(T.key)){
            return true;
        } else if(key.compareTo((K) T.key)<0){
            return containsKey(key,T.left);
        }else if(key.compareTo((K) T.key)>0){
            return containsKey(key,T.right);
        }
        return false;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return get(key,root);
    }

    private V get(K key,BSTNode T){
        if(T==null){
            return null;
        }
        if(key.equals(T.key)){
            return  T.value;
        }else if(key.compareTo((K) T.key)<0){
            return get(key,T.left);
        }else if(key.compareTo((K) T.key)>0){
            return get(key,T.right);
        }
        return null;
    }
    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        root = put(key,value,root);
        size+=1;
    }

    private BSTNode put(K key,V value,BSTNode T){
        if(T==null){
            return new BSTNode(key,value);
        }
        if(key.compareTo((K) T.key)<0){
            T.left = put(key,value,T.left);
        }else{
            T.right = put(key,value,T.right);
        }
        return T;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
