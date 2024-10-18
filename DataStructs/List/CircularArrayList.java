package DataStructs.List;

import java.util.Iterator;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * 
 */
public abstract class CircularArrayList<T> implements ListADT<T> {

    private static final int DEFAULT = 10;
    private T[] list;
    private int head;
    private int tail;
    private int count;

    /**
     * 
     */
    public CircularArrayList() {
        this.list = (T[]) (new Object[DEFAULT]);
        this.head = this.tail = this.count = 0;
    }

    /**
     * 
     * @param size
     */
    public CircularArrayList(int size) {
        this.list = (T[]) (new Object[size]);
        this.head = this.tail = this.count = 0;
    }

    @Override
    public boolean contains(T target) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        return list[head];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        // TODO Auto-generated method stub
        return null;
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
}
