package DataStructs.List;

import java.util.Iterator;

import DataStructs.List.Iterators.ArrayIterator;
import DataStructs.List.Iterators.ModCount;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * Abstract class representing a circular array-based list structure.
 * Elements are stored in a circular manner, with the head and tail pointers indicating the boundaries of the list.
 *
 * @param <T> the type of elements stored in this list.
 */
public abstract class CircularArrayList<T> implements ListADT<T> {

    /** Default initial capacity of the internal array. */
    private static final int DEFAULT = 10;

    /** Array that stores the elements of the list. */
    protected T[] list;
    
    /** Index of the first element (head) of the list. */
    protected int head;
    
    /** Index of the last element (tail) of the list. */
    protected int tail;// add here
    
    /** The number of elements currently in the list. */
    protected int count;

    /** Instance of ModCount to track structural modifications to the list. */
    protected final ModCount modCount = new ModCount();

    /**
     * Constructs an empty circular list with the default initial capacity.
     */
    public CircularArrayList() {
        this.list = (T[]) (new Object[DEFAULT]);
        this.head = this.tail = this.count = 0;
    }

    /**
     * Constructs an empty circular list with the specified initial capacity.
     *
     * @param size the initial capacity of the list.
     */
    public CircularArrayList(int size) {
        this.list = (T[]) (new Object[size]);
        this.head = this.tail = this.count = 0;
    }

    /**
     * Returns the next index in a circular manner.
     *
     * @param index the current index.
     * @return the next index, wrapping around if necessary.
     */
    protected int loop(int index) {
        return (index + 1) % list.length;
    }

    /**
     * Returns the next or previous index in a circular manner, depending on the direction specified.
     *
     * @param index the current index.
     * @param forward if {@code true}, returns the next index; otherwise, returns the previous index.
     * @return the next or previous index, wrapping around if necessary.
     */
    protected int loop(int index, boolean forward) {
        if (forward)
            return (index + 1) % list.length;
        return (index - 1 + list.length) % list.length;
    }

    /**
     * Checks if the list contains a specific element.
     *
     * @param target the element to search for.
     * @return {@code true} if the element is found, {@code false} otherwise.
     */
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
        return list[head];
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list contains no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
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
        return list[tail];
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
        T removed = list[head];
        head = loop(head);
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
        tail = loop(tail, false);
        count--;
        modCount.increment();
        return list[tail];
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
        T[] tempList = (T[]) (new Object[count]);
        int j = head;
        for (int i = 0; i < count; i++) {
            tempList[i] = list[j];
            j = loop(j);
        }
        return new ArrayIterator<T>(tempList, count, modCount);
    }

    /**
     * Expands the internal array when it is full, preserving the order of elements.
     * The elements are reallocated starting from the head of the list.
     */
    protected void expand() {
        T[] newList = (T[]) new Object[list.length * 2];
        int currentIndex = head;
        for (int i = 0; i < count; i++) {
            newList[i] = list[currentIndex];
            currentIndex = loop(currentIndex);
        }
        list = newList;
        head = 0;
        tail = count;
    }
}
