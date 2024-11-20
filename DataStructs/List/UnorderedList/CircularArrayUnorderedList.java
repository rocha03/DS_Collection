package DataStructs.List.UnorderedList;

import DataStructs.List.CircularArrayList;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;

/**
 * 
 */
public class CircularArrayUnorderedList<T> extends CircularArrayList<T> implements UnorderedListADT<T> {

    /**
     * 
     */
    public CircularArrayUnorderedList() {
        super();
    }

    /**
     * 
     * @param size
     */
    public CircularArrayUnorderedList(int size) {
        super(size);
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        if (size() == list.length)
            expand();

        // Find the target position
        int position = head;
        boolean found = false;
        int index = 0;
        while (index < count && !found) {
            if (list[position].equals(target)) {
                found = true;
            } else {
                position = loop(position);
                index++;
            }
        }

        if (!found)
            throw new ElementNotFoundException("Target element not found");

        // Shift elements to make space
        for (int i = count; i > index; i--) {
            list[loop(head + i)] = list[loop(head + i - 1)];
        }

        // Insert the new element
        list[loop(head + index)] = element;

        // Update the count and modCount
        count++;
        modCount.increment();
    }

    @Override
    public void addToFront(T element) {
        if (size() == list.length)
            expand();

        // Shift elements to make space at the front
        int position = loop(head - 1);
        for (int i = count; i > 0; i--) {
            list[loop(head + i)] = list[loop(head + i - 1)];
        }

        // Insert the element at the front
        list[head] = element;
        head = position;

        // Update the count and modCount
        count++;
        modCount.increment();
    }

    @Override
    public void addToRear(T element) {
        if (size() == list.length)
            expand();

        // Insert the element at the rear
        list[loop(head + count)] = element;

        // Update the tail and the count
        count++;
        modCount.increment();
    }
}
