public class LinkedListDeque<T> {
    private class ItemNode {
        public T item;
        public ItemNode rest;
        public ItemNode last;
        public ItemNode(T i, ItemNode r, ItemNode l) {
            this.item = i;
            this.rest = r;
            this.last = l;
        }
    }
    private ItemNode sentinel;
    private int size;
    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new ItemNode((T)null,null,null);
        size = 0;
        sentinel.last = sentinel;
        sentinel.rest = sentinel;
    }
    /** Adds an item to the front of the deque */
    public void addFirst(T item) {
        sentinel.rest = new ItemNode(item, sentinel.rest, sentinel);
        sentinel.rest.rest.last = sentinel.rest;
        size += 1;
    }
    /** Adds an item to the back of the deque */
    public void addLast(T item) {
        sentinel.last = new ItemNode(item, sentinel, sentinel.last);
        sentinel.last.last.rest = sentinel.last;
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last */
    public void printDeque() {
        ItemNode p = sentinel.rest;
        while (sentinel != p) {
            System.out.print(p.item + " ");
            p = p.rest;
        }
    }
    /** Removes and returns the item at the front of the deque */
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
    /** Gets the item at the given index */
    public T get(int index) {
        if (index < 0  || index >= size) {
            return null;
        }
        ItemNode p = sentinel.rest;
        for (int i=0; i<index; i++) {
            p =  p.rest;
        }
        return  p.item;
    }
}
