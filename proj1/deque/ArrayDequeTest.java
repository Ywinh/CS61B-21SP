package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void getTest(){
        ArrayDeque<Integer> a1=new ArrayDeque<>();
        a1.addFirst(1);
        a1.get(0);
        assertEquals(1,a1.size());
    }
}