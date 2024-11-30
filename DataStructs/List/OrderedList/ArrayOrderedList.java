package DataStructs.List.OrderedList;

import DataStructs.List.ArrayList;
import Interfaces.List.OrderedListADT;

/**
 * An ordered list implementation using an array, where elements are kept in a 
 * sorted order according to their natural ordering (or a custom comparator).
 * This class extends ArrayList and implements the OrderedListADT interface.
 *
 * @param <T> the type of elements in the list, must be comparable
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Constructs an empty ArrayOrderedList with the default capacity.
     */
    public ArrayOrderedList() {
        super();
    }

    /**
     * Constructs an empty ArrayOrderedList with the specified initial capacity.
     *
     * @param size the initial capacity of the list
     */
    public ArrayOrderedList(int size) {
        super(size);
    }

    /**
     * Adds an element to the list while maintaining the list in sorted order.
     * The element is inserted into the correct position based on its natural
     * ordering (via {@code Comparable}).
     * If the list is full, it is expanded to accommodate more elements.
     *
     * @param element the element to add
     * @throws IllegalArgumentException if the element is not comparable
     */
    @Override
    public void add(T element) throws IllegalArgumentException {
        // Ensure there is enough space in the list
        if (size() == list.length)
            expand();

        // Check if the element is comparable
        if (!(element instanceof Comparable<?>))
            throw new IllegalArgumentException("Not comparable");

        int position = 0;

        @SuppressWarnings("unchecked")
        Comparable<T> comparable = (Comparable<T>) element;

        // Find the appropriate position to insert the element to maintain sorted order
        while (position < count && comparable.compareTo(list[position]) > 0)
            position++;

        // Shift elements to the right to make space for the new element
        for (int i = count; i > position; --i)
            list[i] = list[i - 1];

        // Insert the element into the found position
        list[position] = element;
        count++;

        // Increment the modification count to reflect the structural change
        modCount.increment();
    }
}
