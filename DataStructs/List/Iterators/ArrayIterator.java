package DataStructs.List.Iterators;

import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * An iterator for traversing an array-based list. This iterator supports 
 * the basic operations of checking for the next element, retrieving the next element, 
 * and detecting concurrent modifications during iteration.
 * 
 * @param <T> the type of elements returned by the iterator.
 */
public class ArrayIterator<T> implements Iterator<T> {

    /**
     * The array that holds the list elements.
     */
    private T[] list;

    /**
     * The number of elements in the list.
     */
    private int count;

    /**
     * The current position of the iterator.
     */
    private int current;

    /**
     * A reference to the ModCount object that tracks modifications to the list.
     */
    private final ModCount modCount;

    /**
     * The expected modCount value to detect concurrent modifications.
     */
    private final int expectedModCount;

    /**
     * Constructs an iterator for the specified array-based list.
     *
     * @param list the array holding the list elements.
     * @param count the number of elements in the list.
     * @param modCount the ModCount object tracking structural modifications to the list.
     */
    public ArrayIterator(T[] list, int count, ModCount modCount) {
        this.list = list;
        this.count = count;
        this.current = 0;
        this.modCount = modCount;
        this.expectedModCount = modCount.value;  // Take initial snapshot of modCount
    }

    /**
     * Checks if there are more elements to iterate over.
     *
     * @return true if there are more elements, false otherwise.
     * @throws ConcurrentModificationException if the list has been modified during iteration.
     */
    @Override
    public boolean hasNext() {
        checkForComodification();
        return current < count;
    }

    /**
     * Returns the next element in the iteration and advances the iterator.
     *
     * @return the next element in the iteration.
     * @throws NoSuchElementException if there are no more elements to iterate over.
     * @throws ConcurrentModificationException if the list has been modified during iteration.
     */
    @Override
    public T next() {
        checkForComodification();
        if (!hasNext()) throw new NoSuchElementException("No more elements in the list.");
        return list[current++];
    }

    /**
     * Removes the current element from the list.
     * This operation is not supported by this iterator.
     *
     * @throws UnsupportedOperationException if called.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not implemented.");
    }

    /**
     * Checks for concurrent modifications to the list during iteration.
     * If the list's modification count has changed since the iterator was created,
     * a {@link ConcurrentModificationException} is thrown.
     *
     * @throws ConcurrentModificationException if the list has been modified since the iterator was created.
     */
    private void checkForComodification() {
        if (expectedModCount != modCount.value) {
            throw new ConcurrentModificationException("List was modified during iteration.");
        }
    }
}
