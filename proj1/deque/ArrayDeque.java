package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> ,Iterable<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items=(T[]) new Object[8];
        size=0;
        nextFirst=4;
        nextLast=5;
    }

    private void resizeUp(int capacity){
        T[] a=(T[]) new Object[capacity];
        System.arraycopy(items,nextLast,a,0,items.length-nextLast);
        System.arraycopy(items,0,a,items.length-nextLast,nextFirst+1);
        items=a;
        nextFirst=capacity-1;
        nextLast=size;
    }

    private void resizeDown(int capacity){
        T[] a=(T[]) new Object[capacity];
        if(nextFirst<nextLast){
            System.arraycopy(items,(nextFirst+1)%items.length,a,0,nextLast-nextFirst-1);
            items=a;
            nextFirst=capacity-1;
            nextLast=size;
        }else{
            System.arraycopy(items,(nextFirst+1)%items.length,a,0,items.length-nextFirst-1);
            System.arraycopy(items,0,a,items.length-nextFirst-1,nextLast);
            items=a;
            nextFirst=capacity-1;
            nextLast=size;
        }
    }

    @Override
    /*Adds an item of type T to the front of the deque. You can assume that item is never null.*/
    public void addFirst(T item){
        if(size == items.length){
            resizeUp(size*2);
        }
        items[nextFirst]=item;
        nextFirst=(nextFirst-1+items.length)% items.length;
        size+=1;
    }

    @Override
    /*Adds an item of type T to the back of the deque. You can assume that item is never null.*/
    public void addLast(T item){
        if(size== items.length){
            resizeUp(size*2);
        }
        items[nextLast]=item;
        nextLast=(nextLast+1)% items.length;
        size+=1;
    }

    @Override
    /* Returns the number of items in the deque.*/
    public int size(){
        return size;
    }

    @Override
    /*Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.*/
    public void printDeque(){
        for(int i=(nextFirst+1)% items.length;i!=nextLast;i=(i+1)% items.length){
            System.out.print(items[i]+" ");
        }
        System.out.println();
    }

    private void proptional(){
        if(items.length<16){
            return;
        }
        double ratio= (double)size/items.length;
        if(ratio<0.25){
            resizeDown(items.length/2);
        }
    }

    @Override
    /*Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst(){
        if(size==0){
            return null;
        }
        int firstIndex=(nextFirst+1)% items.length;
        T value=items[firstIndex];
        items[firstIndex]=null;
        nextFirst=firstIndex;
        size-=1;
        proptional();
        return value;
    }

    @Override
    /*Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast(){
        if(size==0){
            return null;
        }
        int lastIndex=(nextLast-1+ items.length)% items.length;
        T value=items[lastIndex];
        items[lastIndex]=null;
        nextLast=lastIndex;
        size-=1;
        proptional();
        return value;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index){
        return items[(nextFirst+1+index)%items.length];
    }

    /*The Deque objects we???ll make are iterable (i.e. Iterable<T>) so we must provide this method to return an iterator.*/
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T>{
        private int pos;
        public ArrayDequeIterator(){
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos<size;
        }

        @Override
        public T next() {
            T returnValue = get(pos);
            pos++;
            return returnValue;
        }
    }

    /*Returns whether or not the parameter o is equal to the Deque. o is considered equal if it is a Deque
    and if it contains the same contents (as goverened by the generic T???s equals method) in the same order
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }
        /*if(this.getClass()!=o.getClass()){
            return false;
        }*/
        if(!(o instanceof Deque)){
            return false;
        }
        int index=0;
        ArrayDeque<T> o1 = (ArrayDeque<T>) o;
        if(this.size!=o1.size()){
            return false;
        }
        for(T item:this){
            if(item.equals(o1.get(index))){
                index++;
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
}