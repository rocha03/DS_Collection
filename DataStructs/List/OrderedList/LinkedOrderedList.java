package DataStructs.List.OrderedList;

import DataStructs.List.LinkedList;
import DataStructs.Nodes.ListNodes.LinearNode;
import Interfaces.List.OrderedListADT;

/**
 * 
 */
public class LinkedOrderedList<T> extends LinkedList<T> implements OrderedListADT<T> {

    @Override
    public void add(T element) throws IllegalArgumentException {
        // Check if element is comparable
        if (!(element instanceof Comparable<?>))
            throw new IllegalArgumentException("Not comparable");

        // Cast element to Comparable
        Comparable<T> comparable = (Comparable<T>) element;
        LinearNode<T> newNode = new LinearNode<>(element);

        // Insert at the head if the list is empty or element is smallest
        if (head == null || comparable.compareTo(head.getElement()) <= 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            // Traverse to find the insertion point
            LinearNode<T> current = head;
            while (current.getNext() != null && comparable.compareTo(current.getNext().getElement()) > 0)
                current = current.getNext();

            // Insert new node at the found position
            newNode.setNext(current.getNext());
            current.setNext(newNode);

            // Update tail if inserted at the end
            if (newNode.getNext() == null)
                tail = newNode;
        }
        count++;
        modCount.increment();
    }
}
