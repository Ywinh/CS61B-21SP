package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> Ano=new AListNoResizing<>();
        BuggyAList<Integer> a=new BuggyAList<>();

        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                Ano.addLast(3+i);
                a.addLast(3+i);
                for(int k=0;k<a.size();k++){
                    assertEquals(a.get(k),Ano.get(k));
                }
            }
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L1.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(L.size(),L1.size());
            } else if (operationNumber == 2){
                // getlast
                if(L.size()==0){
                    continue;
                }
                int value = L.getLast();
                assertEquals(L.getLast(),L1.getLast());
            } else if (operationNumber == 3){
                // removeLast
                if(L.size()==0){
                    continue;
                }
                assertEquals(L.removeLast(),L1.removeLast());

            }
        }
    }
}
