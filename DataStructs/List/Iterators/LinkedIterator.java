package DataStructs.List.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import DataStructs.Nodes.LinearNode;

/**
 * 
 */
public class LinkedIterator<T> implements Iterator<T> {
    /**
     * 
     */
    private LinearNode<T> node;
    /**
     * 
     */
    private int expectedModCount;

    /**
     * 
     * @param node
     * @param expectedModCount
     */
    public LinkedIterator(LinearNode<T> node, int expectedModCount) {
        this.node = node;
        this.expectedModCount = expectedModCount;
    }

    @Override
    public boolean hasNext() {
        return node.getNext() != null;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T prev = node.getElement();
        node = node.getNext();
        return prev;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operação não implementada.\n");
    }
}
