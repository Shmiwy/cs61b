public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private double usage;
    private int first;
    private int tail;
    private final double LEAST_USAGE = 0.25;
    private final int RESIZE_FACTOR = 2;
    private final int MIN_LEN_TO_SHRINK = 16;
    private final int BEGIN_LEN = 8;
    /**  Creates an empty array deque */
    public ArrayDeque() {
        array = (T[]) new Object[MIN_LEN_TO_SHRINK];
        size = 0;
        updateUsage();
        first = 0;
        tail = 0;
    }
    /** update the usage of the array */
    private void updateUsage() {
        usage = 1.0 * size / array.length;
    }
    /** return true if the array is full */
    private boolean isFull() {
        return (size > 0 && tail == first);
    }
    /** return one gain index after the current position */
    private int onePlus(int index) {
        return (index + 1) % array.length;
    }
    /** return one decrease index after the current position */
    private int oneMinus(int index) {
        if (index == 0) {
            return array.length - 1;
        }
        return index - 1;
    }
    /** resize the array */
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int p = first;
        for (int i = 0; i < size; i++) {
            newArray[i] = array[p];
            p = onePlus(p);
        }
        array = newArray;
        first = 0;
        tail = size;
        updateUsage();
    }

    /**  Adds an item to the front of the deque. */
    public void addFirst(T item) {
        if (isFull()) {
            resize(array.length * RESIZE_FACTOR);
        }
        first = oneMinus(first);
        array[first] = item;
        size += 1;
        updateUsage();
    }
    /**  Adds an item to the back of the deque */
    public void addLast(T item) {
        if (isFull()) {
            resize(array.length * RESIZE_FACTOR);
        }
        array[tail] = item;
        tail = onePlus(tail);
        size += 1;
        updateUsage();
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to las */
    public void printDeque() {
        int p = first;
        while (p != tail) {
            System.out.print(array[p] + " ");
            p = onePlus(p);
        }
    }
    /** try to shrink the size of the array */
    private void tryShrinkSize() {
        while (size() >= MIN_LEN_TO_SHRINK && usage < LEAST_USAGE) {
            resize((int) (array.length / 2));
        }

    }
    /** Removes and returns the item at the front of the deque. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removeItem = array[first];
        array[first] = null;
        first = onePlus(first);
        updateUsage();
        tryShrinkSize();
        return  removeItem;

    }
    /** Removes and returns the item at the back of the deque */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        tail = oneMinus(tail);
        T removeItem = array[tail];
        array[tail] = null;
        updateUsage();
        tryShrinkSize();
        return  removeItem;
    }
    /** Gets the item at the given index */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else if (index + first < array.length) {
            return array[index + first];
        } else {
            return array[index - (array.length - first)];
        }
    }
}
