package DataStructs.List.Iterators;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * 
 */
public class ArrayIterator<T> implements Iterator<T> {
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
    private int current;

    /**
     * 
     * @param list
     * @param count
     */
    public ArrayIterator(T[] list, int count) {
        this.list = list;
        this.count = count;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < count;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        return list[current++];
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
    }
}