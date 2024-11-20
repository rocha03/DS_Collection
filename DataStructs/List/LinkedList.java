package DataStructs.List;

import java.util.Iterator;

import DataStructs.List.Iterators.LinkedIterator;
import DataStructs.List.Iterators.ModCount;
import DataStructs.Nodes.LinearNode;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * 
 */
public abstract class LinkedList<T> implements ListADT<T> {
    /**
     * Tracks the number of elements in the list.
     */
    protected int count;
    /**
     * 
     */
    protected LinearNode<T> head;
    /**
     * 
     */
    protected LinearNode<T> tail;

    /**
     * ModCount instance for tracking structural modifications.
     */
    protected final ModCount modCount = new ModCount();

    /**
     * 
     */
    public LinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean contains(T target) {
        LinearNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getElement().equals(target))
                return true;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        return head.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        return tail.getElement();
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        LinearNode<T> current = head;
        LinearNode<T> previous = null;

        while (current != null) {
            if (current.getElement().equals(element)) {
                if (previous == null) {
                    // Element is at the head
                    head = current.getNext();
                    if (head == null)
                        tail = null;
                } else {
                    // Element is somewhere else in the list
                    // Skip the current
                    previous.setNext(current.getNext());
                    // If the element is at the tail
                    if (current.getNext() == null) {
                        tail = previous;
                    }
                }
                count--;
                modCount.increment();
                return current.getElement();
            }

            previous = current;
            current = current.getNext();
        }

        throw new ElementNotFoundException("Element not found in the list. ");
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        T removed = head.getElement();
        head = head.getNext();
        count--;
        modCount.increment();
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        LinearNode<T> current = head;
        while (current.getNext().getNext() != null)
            current = current.getNext();
        T removed = current.getNext().getElement();
        current.setNext(null);
        count--;
        modCount.increment();
        return removed;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<>(head, modCount);
    }
}
