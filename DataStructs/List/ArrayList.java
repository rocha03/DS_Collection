package DataStructs.List;

import java.util.Iterator;

import DataStructs.List.Iterators.ArrayIterator;
import DataStructs.List.Iterators.ModCount;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * Abstract class representing a dynamic array-based list structure.
 *
 * @param <T> the type of elements stored in this list.
 */
public abstract class ArrayList<T> implements ListADT<T> {

    /** Default initial capacity of the internal array. */
    private static final int DEFAULT = 10;

    /** Constant representing the index of the first element. */
    private static final int HEAD = 0;

    /** Array that stores the elements of the list. */
    protected T[] list;

    /** The number of elements currently in the list. */
    protected int count;

    /** Tracks structural modifications to the list to ensure iterator integrity. */
    protected final ModCount modCount = new ModCount();

    /**
     * Constructs an empty list with the default initial capacity.
     */
    public ArrayList() {
        this.list = (T[]) (new Object[DEFAULT]);
        this.count = 0;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param size the initial capacity of the list.
     */
    public ArrayList(int size) {
        this.list = (T[]) (new Object[size]);
        this.count = 0;
    }

    /**
     * Checks if the list contains a specific element.
     *
     * @param target the element to search for.
     * @return {@code true} if the element is found, {@code false} otherwise.
     */
    @Override
    public boolean contains(T target) {
        int i = 0;
        while (i != size())
            if (list[i++].equals(target))
                return true;
        return false;
    }

    /**
     * Returns the first element in the list.
     *
     * @return the first element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        return list[HEAD];
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list contains no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the last element in the list.
     *
     * @return the last element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        return list[count - 1];
    }

    /**
     * Removes the specified element from the list.
     *
     * @param element the element to remove.
     * @return the removed element.
     * @throws EmptyCollectionException if the list is empty.
     * @throws ElementNotFoundException if the element is not found in the list.
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        int index = find(element);
        T removed = list[index];
        for (int i = index; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        modCount.increment();
        return removed;
    }

    /**
     * Finds the index of the specified element.
     *
     * @param target the element to locate.
     * @return the index of the element in the list.
     * @throws ElementNotFoundException if the element is not found.
     */
    protected int find(T target) throws ElementNotFoundException {
        int i = 0;
        while (i != size()) {
            if (list[i].equals(target))
                return i;
            i++;
        }
        throw new ElementNotFoundException("No such element in the list");
    }

    /**
     * Removes and returns the first element in the list.
     *
     * @return the removed first element.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        T removed = list[HEAD];
        for (int i = 0; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        count--;
        modCount.increment();
        return removed;
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return the removed last element.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty. ");
        count--;
        modCount.increment();
        return list[count];
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator over the elements in this list.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(list, count, modCount);
    }

    /**
     * Expands the capacity of the internal array by doubling its current size.
     */
    protected void expand() {
        T[] newList = (T[]) (new Object[list.length * 2]);
        for (int i = HEAD; i < list.length; i++) {
            newList[i] = list[i];
        }
        this.list = newList;
    }
}
