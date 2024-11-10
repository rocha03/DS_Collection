package DataStructs.List;

import java.util.Iterator;

import DataStructs.List.Iterators.LinkedIterator;
import DataStructs.Nodes.LinearNode;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * 
 */
public abstract class LinkedList<T> implements ListADT<T> {
    /**
     * 
     */
    private int count;
    /**
     * 
     */
    private LinearNode<T> head;
    /**
     * 
     */
    private LinearNode<T> tail;

    /**
     * 
     */
    public LinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean contains(T target) { // test this thing
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        head = head.getNext();
        count--;
        return head.getElement();
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
        return removed;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<>(head, 0);
    }
}
