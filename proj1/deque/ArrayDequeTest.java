package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void getTest(){
        ArrayDeque<Integer> a1=new ArrayDeque<>();
        for(int i=0;i<30;i++){
            a1.addFirst(i);
        }
        for(int i=0;i<27;i++){
            a1.removeLast();
        }

    }
}