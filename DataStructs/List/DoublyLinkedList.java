package DataStructs.List;

import java.util.Iterator;

import DataStructs.List.Iterators.LinkedIterator;
import DataStructs.List.Iterators.ModCount;
import DataStructs.Nodes.DoubleNode;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * 
 */
public abstract class DoublyLinkedList<T> implements ListADT<T> {
    /**
     * Tracks the number of elements in the list.
     */
    protected int count;
    /**
     * 
     */
    protected DoubleNode<T> head;
    /**
     * 
     */
    protected DoubleNode<T> tail;

    /**
     * ModCount instance for tracking structural modifications.
     */
    protected final ModCount modCount = new ModCount();

    /**
     * 
     */
    public DoublyLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean contains(T target) {
        DoubleNode<T> current = head;
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
        DoubleNode<T> current = head;
        while (current != null) {
            if (current.getElement().equals(element)) {
                if (current == head) {
                    // Element is at the head
                    head = current.getNext();
                    if (head == null)
                        tail = null;
                } else {
                    // Element is somewhere else in the list
                    // Skip the current
                    current.getPrevious().setNext(current.getNext());
                    // If the element is at the tail
                    if (current.getNext() == null) {
                        tail = current.getPrevious();
                    } else {
                        current.getNext().setPrevious(current.getPrevious());
                    }
                }
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        T removed = head.getElement();
        head = head.getNext();
        head.setPrevious(null);
        count--;
        modCount.increment();
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        T removed = tail.getElement();
        tail = tail.getPrevious();
        tail.setNext(null);
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
