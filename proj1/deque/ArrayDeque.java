package deque;
public class ArrayDeque<T>{
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

    public void resizeUp(int capacity){
        T[] a=(T[]) new Object[capacity];
        System.arraycopy(items,nextLast,a,0,items.length-nextLast);
        System.arraycopy(items,0,a,items.length-nextLast,nextFirst+1);
        items=a;
        nextFirst=capacity-1;
        nextLast=size;
    }

    public void resizeDown(int capacity){

    }
    /*Adds an item of type T to the front of the deque. You can assume that item is never null.*/
    public void addFirst(T item){
        if(size== items.length){
            resizeUp(size*2);
        }
        items[nextFirst]=item;
        nextFirst=(nextFirst-1+items.length)% items.length;
        size+=1;
    }

    /*Adds an item of type T to the back of the deque. You can assume that item is never null.*/
    public void addLast(T item){
        if(size== items.length){
            resizeUp(size*2);
        }
        items[nextLast]=item;
        nextLast=(nextLast+1)% items.length;
        size+=1;
    }

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty(){
        return size==0;
    }

    /* Returns the number of items in the deque.*/
    public int size(){
        return size;
    }

    /*Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.*/
    public void printDeque(){
        for(int i=(nextFirst+1)% items.length;i!=nextLast;i=(i+1)% items.length){
            System.out.print(items[i]+" ");
        }
        System.out.println();
    }

    public void proptional(){
        if(size<16){
            return;
        }
        double ratio= (double)size/items.length;
        if(ratio<0.25){
            resizeDown(size/2);
        }
        return;
    }
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

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index){
        int cnt = 0;
        for(int i=(nextFirst+1)% items.length;i!=nextLast;i=(i+1)% items.length){
            if(cnt==index){
                return items[i];
            }
            cnt++;
        }
        return null;
    }

}