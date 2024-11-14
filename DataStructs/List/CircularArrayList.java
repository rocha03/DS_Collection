package DataStructs.List;

import java.util.Iterator;

import DataStructs.List.Iterators.ArrayIterator;
import DataStructs.List.Iterators.ModCount;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * 
 */
public abstract class CircularArrayList<T> implements ListADT<T> {

    /**
     * 
     */
    private static final int DEFAULT = 10;

    /**
     * 
     */
    private T[] list;

    /**
     * 
     */
    private int head;

    /**
     * 
     */
    private int tail;// add here

    /**
     * 
     */
    private int count;

    /**
     * ModCount instance for tracking structural modifications.
     */
    protected final ModCount modCount = new ModCount();

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

    /**
     * 
     * @param index
     * @return
     */
    protected int loop(int index) {
        return (index + 1) % list.length;
    }

    /**
     * 
     * @param index
     * @param forward
     * @return
     */
    protected int loop(int index, boolean forward) {
        if (forward)
            return (index + 1) % list.length;
        return (index - 1 + list.length) % list.length;
    }

    @Override
    public boolean contains(T target) {
        int i = head;
        while (i != tail) {
            if (list[i].equals(target))
                return true;
            i = loop(i);
        }
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
        return list[tail];
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        int j = head, i = 0;
        boolean found = false;
        while (i < count && !found) {
            if (list[j].equals(element)) {
                found = true;
            } else {
                j = loop(j);
                i++;
            }
        }
        T removed = list[j];
        for (int k = i; k < count - 1; k++) {
            list[j] = list[loop(j)];
            j = loop(j);
        }
        count--;
        modCount.increment();
        return removed;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        T removed = list[head];
        head = loop(head);
        count--;
        modCount.increment();
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        tail = loop(tail, false);
        count--;
        modCount.increment();
        return list[tail];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        T[] tempList = (T[]) (new Object[count]);
        int j = head;
        for (int i = 0; i < count; i++) {
            tempList[i] = list[j];
            j = loop(j);
        }
        return new ArrayIterator<T>(tempList, count, modCount);
    }
}
