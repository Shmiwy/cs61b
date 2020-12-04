package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;


    @Override
    public int capacity() {
        return capacity;
    }
    @Override
    public int fillCount() {
        return fillCount;
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * return the position index after the current position
     */
    private int onePlus(int x) {
        return (x + 1) % this.capacity;
    }

    /**
     * return the position index before the current position
     */
    private int oneMinus(int x) {
        if (0 == x) {
            return this.capacity - 1;
        } else {
            return x - 1;
        }
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = onePlus(last);
        this.fillCount += 1;
    }
    @Override
    public boolean isEmpty()
    {
        return fillCount() == 0;
    }
    @Override
    public boolean isFull() {
        return fillCount() == capacity();
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T dequeueItem = rb[first];
        rb[first] = null;
        first = onePlus(first);
        this.fillCount -= 1;
        return dequeueItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < fillCount();
        }

        public T next() {
            T returnItem = rb[wizPos];
            wizPos =onePlus(wizPos);
            return returnItem;
        }
    }

}
