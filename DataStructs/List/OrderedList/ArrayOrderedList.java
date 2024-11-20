package DataStructs.List.OrderedList;

import DataStructs.List.ArrayList;
import Interfaces.List.OrderedListADT;

/**
 *
 * @author
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * 
     */
    public ArrayOrderedList(){
        super();
    }

    /**
     * 
     * @param size
     */
    public ArrayOrderedList(int size){
        super(size);
    }

    @Override
    public void add(T element) throws IllegalArgumentException {
        if (size() == list.length)
            expand();
        if (!(element instanceof Comparable<?>))
            throw new IllegalArgumentException("Not comparable");
        
        int position = 0;
        Comparable<T> comparable = (Comparable<T>) element;

        // find the target position
        while (position < count && comparable.compareTo(list[position]) > 0)
            position++;

        // shift elements to make space
        for (int i = count; i > position; --i)
            list[i] = list[i - 1];
        
        list[position] = element;
        count++;
        modCount.increment();
    }
}
