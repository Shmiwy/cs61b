public class LinkedListDeque<T> implements Deque<T>{
    private class ItemNode {
        private T item;
        private ItemNode rest;
        private ItemNode last;
        private ItemNode(T i, ItemNode r, ItemNode l) {
            this.item = i;
            this.rest = r;
            this.last = l;
        }
    }
    private ItemNode sentinel;
    private int size;
    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new ItemNode((T) null, null, null);
        size = 0;
        sentinel.last = sentinel;
        sentinel.rest = sentinel;
    }
    /** Adds an item to the front of the deque */
    @Override
    public void addFirst(T item) {
        sentinel.rest = new ItemNode(item, sentinel.rest, sentinel);
        sentinel.rest.rest.last = sentinel.rest;
        size += 1;
    }
    /** Adds an item to the back of the deque */
    @Override
    public void addLast(T item) {
        sentinel.last = new ItemNode(item, sentinel, sentinel.last);
        sentinel.last.last.rest = sentinel.last;
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return  (size == 0);
    }
    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last */
    @Override
    public void printDeque() {
        ItemNode p = sentinel.rest;
        while (sentinel != p) {
            System.out.print(p.item + " ");
            p = p.rest;
        }
    }
    /** Removes and returns the item at the front of the deque */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        ItemNode firstNode = sentinel.rest;
        sentinel.rest = firstNode.rest;
        firstNode.rest.last = sentinel;
        size -= 1;
        return firstNode.item;
    }
    /** Removes and returns the item at the back of the deque. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        ItemNode lastNode = sentinel.last;
        sentinel.last = lastNode.last;
        lastNode.last.rest = sentinel;
        size -= 1;
        return  lastNode.item;
    }
    /** helper of the getRecursive function */
    private T getRecursiveHelper(int index, int count, ItemNode p) {
        if (index == count) {
            return p.item;
        }
        return getRecursiveHelper(index, count + 1, p.rest);
    }
    /** Gets the item at the given index using recursion*/
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        ItemNode p = sentinel.rest;
        return getRecursiveHelper(index, 0, p);
    }

    /** Gets the item at the given index */
    @Override
    public T get(int index) {
        if (index < 0  || index >= size) {
            return null;
        }
        ItemNode p = sentinel.rest;
        for (int i = 0; i < index; i++) {
            p =  p.rest;
        }
        return  p.item;
    }
}
