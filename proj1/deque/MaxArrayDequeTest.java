package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {

    public class MaxArrayDequeComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }

    /*public Comparator<Integer> getMaxArrayDequeComparator(){
        return new MaxArrayDequeComparator();
    }*/

    @Test
    public void test1(){
        Comparator<Integer> c = new MaxArrayDequeComparator();
        MaxArrayDeque<Integer> m1 = new MaxArrayDeque<>(c);
        m1.addFirst(3);
        m1.addFirst(1);
        m1.addFirst(10);
        m1.addFirst(7);

        assertEquals(10,(int)m1.max());
    }
}
