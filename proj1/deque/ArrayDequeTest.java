package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void getTest(){
        ArrayDeque<Integer> a1=new ArrayDeque<>();
        ArrayDeque<Integer> a2=new ArrayDeque<>();

        a1.addFirst(0);
        a1.addFirst(3);
        a2.addFirst(0);
        a2.addFirst(3);
        a2.addFirst(3);

        assertEquals("wrong",3,(int)a1.get(0));
        assertEquals(null,a1.get(5));
        System.out.println(a1.equals(a2));
    }
}