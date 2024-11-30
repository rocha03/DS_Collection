package DataStructs.List.UnorderedList;

import DataStructs.List.ArrayList;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;

/**
 * Represents an unordered list implemented using an array. 
 * Elements can be added to the front, rear, or after a specific target element.
 * 
 * @param <T> the type of elements stored in the list.
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Constructs an empty unordered list with the default capacity.
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * Constructs an empty unordered list with a specified capacity.
     *
     * @param size the initial capacity of the list.
     */
    public ArrayUnorderedList(int size) {
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

        int position = find(target); // Find the index of the target element
        if (position == -1) 
            throw new ElementNotFoundException("Target element not found in the list.");

        // Shift elements to the right to make space for the new element
        for (int i = size(); i > position; i--) {
            list[i] = list[i - 1];
        }
        list[position + 1] = element; // Add the new element after the target
        count++; // Increase the size of the list
        modCount.increment(); // Track structural modification
    }

    /**
     * Adds an element to the front of the list. The list will expand if it is full.
     *
     * @param element the element to be added to the front of the list.
     */
    @Override
    public void addToFront(T element) {
        if (size() == list.length)
            expand(); // Expand the list if it is full

        // Shift all elements to the right to make space at the front
        for (int i = count; i > 0; i--) {
            list[i] = list[i - 1];
        }
        list[0] = element; // Add the new element at the front
        count++; // Increase the size of the list
        modCount.increment(); // Track structural modification
    }

    /**
     * Adds an element to the rear of the list. The list will expand if it is full.
     *
     * @param element the element to be added to the rear of the list.
     */
    @Override
    public void addToRear(T element) {
        if (size() == list.length)
            expand(); // Expand the list if it is full

        list[count++] = element; // Add the new element to the end of the list
        modCount.increment(); // Track structural modification
    }
}
