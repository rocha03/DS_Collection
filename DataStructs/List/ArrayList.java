package DataStructs.List;

import java.util.Iterator;

import DataStructs.List.Iterators.ArrayIterator;
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
    protected T[] list;

    /**
     * 
     */
    protected int count;

    /**
     * 
     */
    protected int modcount;

    /**
     * 
     */
    public ArrayList() {
        this.list = (T[]) (new Object[DEFAULT]);
        this.count = 0;
        this.modcount = 0;
    }

    /**
     * 
     * @param size
     */
    public ArrayList(int size) {
        this.list = (T[]) (new Object[size]);
        this.count = 0;
        this.modcount = 0;
    }

    @Override
    public boolean contains(T target) {
        int i = 0;
        while (i != size())
            if (list[i++].equals(target))
                return true;
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
        int index = find(element);
        T removed = list[index]; // do i need this or can i just use element??
        for (int i = index; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        return removed;
    }

    /**
     * 
     * @param target
     * @return
     * @throws ElementNotFoundException
     */
    protected int find(T target) throws ElementNotFoundException {
        int i = 0;
        while (i != size()){
            if (list[i].equals(target))
                return i;
            i++;
        }
        throw new ElementNotFoundException("No such element in the list");
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
        return new ArrayIterator<T>(list, count);
    }

    /**
     * 
     */
    protected void expand(){
        T[] newList = (T[]) (new Object[list.length * 2]);
        for (int i = HEAD; i < list.length; i++) {
            newList[i] = list[i];
        }
        this.list = newList;
    }
}
