package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T>{
    private Node sentinel;
    private int size;

    private class Node{
        public Node pre;
        public T item;
        public Node next;
        public Node(Node pre,T item,Node next){
            this.pre=pre;
            this.item=item;
            this.next=next;
        }
    }

    public LinkedListDeque(){
        sentinel=new Node(null,null,null);
        sentinel.pre=sentinel;
        sentinel.next=sentinel;
        size=0;
    }

    @Override
    /*Adds an item of type T to the front of the deque. You can assume that item is never null.*/
    public void addFirst(T item){
        Node p=new Node(null,item,null);
        p.next = sentinel.next;
        sentinel.next.pre = p;
        p.pre = sentinel;
        sentinel.next=p;
        size+=1;
    }

    @Override
    /*Adds an item of type T to the back of the deque. You can assume that item is never null.*/
    public void addLast(T item){
        Node p=new Node(null,item,null);
        p.pre = sentinel.pre;
        sentinel.pre.next = p;
        p.next = sentinel;
        sentinel.pre=p;
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
        Node p=sentinel.next;
        while(p!=sentinel){
            System.out.print(p.item+" ");
            p=p.next;
        }
        System.out.println();
    }

    @Override
    /*Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        Node p=sentinel.next;
        sentinel.next=p.next;
        p.next.pre=sentinel;
        T value=p.item;
        size-=1;
        return value;
    }

    @Override
    /*Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        Node p=sentinel.pre;
        p.pre.next=sentinel;
        sentinel.pre=p.pre;
        T value=p.item;
        size-=1;
        return value;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index){
        Node p=sentinel.next;
        if(p==sentinel){
            return null;
        }
        int cnt=0;
        while(p!=sentinel){
            if(index==cnt){
                return p.item;
            }
            cnt++;
            p=p.next;
        }
        return null;
    }

    /*Same as get, but uses recursion.*/
    public T getRecursive(int index){
        return getRecursiveHelper(index,sentinel.next);
    }
    private T getRecursiveHelper(int index,Node p){
        if(p==sentinel){
            return null;
        }
        if(index==0){
            return p.item;
        }
        return getRecursiveHelper(index-1,p.next);
    }

    private class LinkerListDequeIterator implements Iterator<T>{

        private Node p;

        public LinkerListDequeIterator(){
            p = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            if(p.equals(sentinel)){
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            if(p.equals(sentinel)){
                return null;
            }
            T returnValue = p.item;
            p = p.next;
            return returnValue;
        }

    }

    /*The Deque objects we’ll make are iterable (i.e. Iterable<T>) so we must provide this method to return an iterator.*/
    public Iterator<T> iterator(){
        return new LinkerListDequeIterator();
    }

    /*Returns whether or not the parameter o is equal to the Deque. o is considered equal if it is a Deque
    and if it contains the same contents (as goverened by the generic T’s equals method) in the same order
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
        int index=0;
        LinkedListDeque<T> o1 = (LinkedListDeque<T>) o;
        if(this.size!=o1.size()){
            return false;
        }
        for(T x:this){
            if(x==null){
                break;
            }
            if(x.equals(o1.get(index))){
                index++;
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
}