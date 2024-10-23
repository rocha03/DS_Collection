package DataStructs.List;

import java.util.Iterator;

import DataStructs.Nodes.LinearNode;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

public abstract class LinkedList<T> implements ListADT<T> {

    private int count;
    private LinearNode<T> head;

    public LinkedList() {
        this.count = 0;
        this.head = null;
    }

    @Override
    public boolean contains(T target) {
        LinearNode<T> current = head;
        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            }
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
        return count == 0;
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        LinearNode<T> current = head;
        while (current.getNext() != null)
            current = current.getNext();
        return current.getElement();
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
                    // Removing the head
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                count--;
                return current.getElement();
            }
            previous = current;
            current = current.getNext();
        }
        throw new ElementNotFoundException("Element not found in the list.");
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
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     */
    private class BasicIterator<E> {
        
    }
}
