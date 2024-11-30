package DataStructs.List.UnorderedList;

import DataStructs.List.CircularArrayList;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;

/**
 * Represents an unordered list implemented using a circular array. 
 * Elements can be added to the front, rear, or after a specified target element.
 * 
 * @param <T> the type of elements stored in the list.
 */
public class CircularArrayUnorderedList<T> extends CircularArrayList<T> implements UnorderedListADT<T> {

    /**
     * Constructs an empty unordered list with the default capacity.
     */
    public CircularArrayUnorderedList() {
        super();
    }

    /**
     * Constructs an empty unordered list with a specified capacity.
     *
     * @param size the initial capacity of the list.
     */
    public CircularArrayUnorderedList(int size) {
        super(size);
    }

    /**
     * Adds an element immediately after a specified target element in the list.
     * If the target element is not found, an {@link ElementNotFoundException} is thrown.
     * The list will expand if it is full.
     *
     * @param element the element to be added to the list.
     * @param target the element after which the new element will be added.
     * @throws ElementNotFoundException if the target element is not found in the list.
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        if (size() == list.length)
            expand(); // Expand the list if it is full

        // Find the position of the target element in the circular list
        int position = head;
        boolean found = false;
        int index = 0;
        while (index < count && !found) {
            if (list[position].equals(target)) {
                found = true; // Target element found
            } else {
                position = loop(position); // Move to the next element in the circular list
                index++;
            }
        }

        if (!found)
            throw new ElementNotFoundException("Target element not found.");

        // Shift elements to the right to make space for the new element
        for (int i = count; i > index; i--) {
            list[loop(head + i)] = list[loop(head + i - 1)];
        }

        // Insert the new element after the target element
        list[loop(head + index)] = element;

        // Update the list size and structural modification count
        count++;
        modCount.increment();
    }

    /**
     * Adds an element to the front of the list. 
     * The list will expand if it is full.
     *
     * @param element the element to be added to the front of the list.
     */
    @Override
    public void addToFront(T element) {
        if (size() == list.length)
            expand(); // Expand the list if it is full

        // Shift elements to the right to make space at the front
        int position = loop(head - 1);
        for (int i = count; i > 0; i--) {
            list[loop(head + i)] = list[loop(head + i - 1)];
        }

        // Insert the new element at the front of the list
        list[head] = element;
        head = position; // Update the head to point to the new front

        // Update the list size and structural modification count
        count++;
        modCount.increment();
    }

    /**
     * Adds an element to the rear of the list. 
     * The list will expand if it is full.
     *
     * @param element the element to be added to the rear of the list.
     */
    @Override
    public void addToRear(T element) {
        if (size() == list.length)
            expand(); // Expand the list if it is full

        // Insert the element at the rear of the list
        list[loop(head + count)] = element;

        // Update the list size and structural modification count
        count++;
        modCount.increment();
    }
}
