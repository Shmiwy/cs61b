package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void onePlus() {
        int capacity = 100;
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(capacity);
        for (int i = 0; i < arb.capacity; i++) {
            arb.enqueue(i);
        }
        for (int i = 0; i < arb.capacity; i++ ) {
            assertEquals((int) arb.dequeue(), i);
        }
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals((int) arb.peek(), 1);
        assertEquals((int) arb.peek(), 1);

        arb.dequeue();
        assertEquals((int) arb.peek(), 2);

    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
