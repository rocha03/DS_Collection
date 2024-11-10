package DataStructs.List.Iterators;

import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * 
 */
public class ArrayIterator<T> implements Iterator<T> {
    /**
     * 
     */
    private T[] list;
    /**
     * 
     */
    private int count;
    /**
     * 
     */
    private int current;
    /**
     * Reference to the ModCount wrapper.
     */
    private final ModCount modCount;
    /**
     * Expected modCount for detecting concurrent modifications.
     */
    private final int expectedModCount;

    /**
     * 
     * @param list
     * @param count
     * @param modCount
     */
    public ArrayIterator(T[] list, int count, ModCount modCount) {
        this.list = list;
        this.count = count;
        this.current = 0;
        this.modCount = modCount;
        this.expectedModCount = modCount.value;  // Take initial snapshot of modCount
    }

    @Override
    public boolean hasNext() {
        checkForComodification();
        return current < count;
    }

    @Override
    public T next() {
        checkForComodification();
        if (!hasNext()) throw new NoSuchElementException();
        return list[current++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operação não implementada.\n");
    }

    /**
     * Checks for concurrent modification by comparing the expected modCount
     * with the actual modCount of the list.
     * 
     * @throws ConcurrentModificationException if the modCounts differ.
     */
    private void checkForComodification() {
        if (expectedModCount != modCount.value) {
            throw new ConcurrentModificationException();
        }
    }
}








































































































































































































































































































































































































































































































































































































































// I wanna die, for now...