package deque;

import java.util.Comparator;

public class MaxArrayDeque <T> extends ArrayDeque<T>{

    private Comparator<T> maxComparator;

    /*creates a MaxArrayDeque with the given Comparator.*/
    public MaxArrayDeque(Comparator<T> c){
        maxComparator = c;
    }

    /*returns the maximum element in the deque as governed by the previously given Comparator. If the MaxArrayDeque is empty, simply return null.*/
    public T max(){
        return max(maxComparator);
    }

    /*returns the maximum element in the deque as governed by the parameter Comparator c. If the MaxArrayDeque is empty, simply return null.*/
    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T returnMax = get(0);
        for(int i=0;i<size();i++){
            if(c.compare(get(i),returnMax)>0){
                returnMax = get(i);
            }
        }
        return returnMax;
    }

}
