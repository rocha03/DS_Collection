package DataStructs.List.Iterators;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import DataStructs.Nodes.BasicNode;

/**
 * 
 */
public class LinkedIterator<T, N extends BasicNode<T, N>> implements Iterator<T> {
    /**
     * The current node.
     */
    private N node;
    /**
     * Reference to the ModCount wrapper.
     */
    private final ModCount modCount;
    /**
     * Expected modCount for detecting concurrent modifications.
     */
    private final int expectedModCount;

    /**
     * Constructs an iterator for the linked list.
     *
     * @param node      The starting node of the iteration.
     * @param modCount  The ModCount instance from the list.
     */
    public LinkedIterator(N node, ModCount modCount) {
        this.node = node;
        this.modCount = modCount;
        this.expectedModCount = modCount.value;  // Take initial snapshot of modCount
    }

    @Override
    public boolean hasNext() {
        checkForComodification();
        return node.getNext() != null;
    }

    @Override
    public T next() {
        checkForComodification();
        if (!hasNext()) throw new NoSuchElementException();
        T prev = node.getElement();
        node = node.getNext();
        return prev;
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
