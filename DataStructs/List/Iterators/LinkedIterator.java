package DataStructs.List.Iterators;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import DataStructs.Nodes.ListNodes.BasicNode;

/**
 * An iterator for traversing a linked list. This iterator supports basic 
 * operations such as checking for the next element, retrieving the next element, 
 * and detecting concurrent modifications during iteration.
 * 
 * @param <T> the type of elements returned by the iterator.
 * @param <N> the type of the nodes in the linked list, extending {@link BasicNode}.
 */
public class LinkedIterator<T, N extends BasicNode<T, N>> implements Iterator<T> {
    
    /** The current node in the linked list. */
    private N current;

    /** A reference to the ModCount object that tracks modifications to the list. */
    private final ModCount modCount;

    /** The expected modCount value to detect concurrent modifications. */
    private final int expectedModCount;

    /** */
    private boolean canRemove;
    /** */
    private N lastReturned;

    /**
     * Constructs an iterator for the linked list.
     * 
     * @param node the starting node for the iteration.
     * @param modCount the ModCount instance that tracks structural modifications to the list.
     */
    public LinkedIterator(N node, ModCount modCount) {
        this.current = node;
        this.modCount = modCount;
        this.expectedModCount = modCount.value;  // Capture initial modCount value

        this.canRemove = false;
        this.lastReturned = null;
    }

    /**
     * Checks if there are more elements to iterate over in the linked list.
     *
     * @return true if there are more elements, false otherwise.
     * @throws ConcurrentModificationException if the list has been modified during iteration.
     */
    @Override
    public boolean hasNext() {
        checkForCoModification();
        return current.getNext() != null;
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
        checkForCoModification();
        if (!hasNext())
            throw new NoSuchElementException("No more elements in the list.");
        T prev = current.getElement();
        current = current.getNext();
        return prev;
    }

    /**
     * Removes the current element from the list.
     * This operation is not supported by this iterator.
     *
     * @throws UnsupportedOperationException if called.
     */
    @Override
    public void remove() {
        checkForCoModification();
        if (!canRemove) {
            throw new IllegalStateException("");
        }
        if (lastReturned == current) {
            throw new IllegalStateException("");
        }

        if (lastReturned != null) {
            N previous = findPrevious(lastReturned);
            if (previous != null)
                previous.setNext(lastReturned.getNext());
        }

        lastReturned = null;
        canRemove = false;
        modCount.increment();

        // throw new UnsupportedOperationException("Remove operation is not implemented.");
    }

    /**
     * Checks for concurrent modifications to the list during iteration.
     * If the list's modification count has changed since the iterator was created,
     * a {@link ConcurrentModificationException} is thrown.
     *
     * @throws ConcurrentModificationException if the list has been modified since the iterator was created.
     */
    private void checkForCoModification() {
        if (expectedModCount != modCount.value) {
            throw new ConcurrentModificationException("List was modified during iteration.");
        }
    }

    private N findPrevious(N node) {
        N temp = current;
        while (temp != null && temp.getNext() != node) {
            temp = temp.getNext();
        }
        return temp;
    }
}
