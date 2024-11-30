package DataStructs.List.OrderedList;

import DataStructs.List.CircularArrayList;
import Interfaces.List.OrderedListADT;

/**
 * An ordered list implementation using a circular array, where elements are 
 * kept in a sorted order according to their natural ordering (or a custom comparator).
 * This class extends CircularArrayList and implements the OrderedListADT interface.
 *
 * @param <T> the type of elements in the list, must be comparable
 */
public class CircularArrayOrderedList<T> extends CircularArrayList<T> implements OrderedListADT<T> {

    /**
     * Constructs an empty CircularArrayOrderedList with the default capacity.
     */
    public CircularArrayOrderedList() {
        super();
    }

    /**
     * Constructs an empty CircularArrayOrderedList with the specified initial capacity.
     *
     * @param size the initial capacity of the list
     */
    public CircularArrayOrderedList(int size) {
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
        // Ensure the element is comparable
        if (!(element instanceof Comparable<?>))
            throw new IllegalArgumentException("Not comparable");

        // Expand the array if it is full
        if (count == list.length)
            expand();

        // Cast element to Comparable
        Comparable<T> comparable = (Comparable<T>) element;

        // Find the correct position to insert the element while maintaining order
        int current = head;
        int insertPosition = 0;
        while (insertPosition < count && comparable.compareTo(list[current]) > 0) {
            current = loop(current);  // Move to the next index in a circular manner
            insertPosition++;
        }

        // Shift elements to make space for the new element
        int targetIndex = (head + count) % list.length;  // The index of the last element
        for (int i = count; i > insertPosition; i--) {
            // Shift elements one position to the right to make space for the new element
            list[targetIndex] = list[(targetIndex - 1 + list.length) % list.length];
            targetIndex = (targetIndex - 1 + list.length) % list.length;
        }

        // Insert the element at the calculated position
        list[(head + insertPosition) % list.length] = element;

        // Update the tail and other metadata
        tail = (head + count + 1) % list.length;  // Update the tail to the next position
        count++;  // Increase the count of elements
        modCount.increment();  // Increment the modification count to track changes
    }
}
