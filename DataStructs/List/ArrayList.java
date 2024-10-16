package DataStructs.List;

import java.util.Iterator;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * 
 */
public abstract class ArrayList<T> implements ListADT<T> {
    /**
     * 
     */
    private static final int DEFAULT = 10;
    /**
     * 
     */
    private static final int HEAD = 0;
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
    public ArrayList() {
        this.list = (T[]) (new Object[DEFAULT]);
        this.count = 0;
    }

    /**
     * 
     * @param size
     */
    public ArrayList(int size) {
        this.list = (T[]) (new Object[size]);
        this.count = 0;
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
        return list[HEAD];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        return list[count - 1];
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
        T removed = list[HEAD];
        for (int i = 0; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        count--;
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        count--;
        return list[count];
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
