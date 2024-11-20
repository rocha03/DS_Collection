package DataStructs.List.OrderedList;

import DataStructs.List.CircularArrayList;
import Interfaces.List.OrderedListADT;

/**
 * 
 */
public class CircularArrayOrderedList<T> extends CircularArrayList<T> implements OrderedListADT<T> {

    /**
     * 
     */
    public CircularArrayOrderedList(){
        super();
    }

    /**
     * 
     * @param size
     */
    public CircularArrayOrderedList(int size){
        super(size);
    }

    @Override
    public void add(T element) throws IllegalArgumentException {
        // Check if element is comparable
        if (!(element instanceof Comparable<?>))
            throw new IllegalArgumentException("Not comparable");

        // Expand the array if needed
        if (count == list.length)
            expand();

        // Cast element to Comparable
        Comparable<T> comparable = (Comparable<T>) element;

        // Find the target position for insertion
        int current = head;
        int insertPosition = 0;
        while (insertPosition < count && comparable.compareTo(list[current]) > 0) {
            current = loop(current);
            insertPosition++;
        }

        // Shift elements to create space for the new element
        int targetIndex = (head + count) % list.length; // End of the current elements
        for (int i = count; i > insertPosition; i--) {
            list[targetIndex] = list[(targetIndex - 1 + list.length) % list.length];
            targetIndex = (targetIndex - 1 + list.length) % list.length;
        }

        // Insert the new element
        list[(head + insertPosition) % list.length] = element;

        // Adjust tail and metadata
        tail = (head + count + 1) % list.length;
        count++;
        modCount.increment();
    }

}
