package DataStructs.List.UnorderedList;

import DataStructs.List.LinkedList;
import DataStructs.Nodes.ListNodes.LinearNode;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;

/**
 * A singly linked unordered list implementation.
 * Supports operations like adding elements to the front, rear, and after a
 * specific target.
 */
public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedListADT<T> {

    /**
     * Adds an element to the front of the list.
     * Creates a new node and sets it as the new head of the list.
     * 
     * @param element the element to add to the front
     */
    @Override
    public void addToFront(T element) {
        LinearNode<T> node = new LinearNode<T>(element);
        if (head == null)
            head = tail = node;
        else {
            node.setNext(head); // Set the new node's next to the current head
            head = node; // Move the head to the new node
        }
        count++; // Increment element count
        modCount.increment(); // Increment modification count
    }

    /**
     * Adds an element to the rear (tail) of the list.
     * Sets the new node as the next of the current tail and updates the tail.
     * 
     * @param element the element to add to the rear
     */
    @Override
    public void addToRear(T element) {
        LinearNode<T> node = new LinearNode<T>(element);
        if (head == null)
            head = tail = node;
        else {
            tail.setNext(node); // Set the current tail's next to the new node
            tail = node; // Move the tail to the new node
        }
        count++; // Increment element count
        modCount.increment(); // Increment modification count
    }

    /**
     * Adds an element after a specific target element in the list.
     * If the target element is found, the new element is inserted after it.
     * 
     * @param element the element to add after the target
     * @param target  the target element after which the new element is added
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        LinearNode<T> current = head;
        boolean found = false;

        // Traverse the list to find the target element
        while (current != null && !found) {
            if (current.getElement().equals(target)) {
                if (current == head) {
                    addToFront(element); // If target is the head, add the element to the front
                } else if (current == tail) {
                    addToRear(element); // If target is the tail, add the element to the rear
                } else {
                    // Insert the new element after the target
                    LinearNode<T> node = new LinearNode<T>(element);
                    node.setNext(current.getNext());
                    current.setNext(node);
                    count++; // Increment element count
                    modCount.increment(); // Increment modification count
                }
                found = true; // Mark target as found
            }
            current = current.getNext(); // Move to the next node
        }

        // If target was not found, throw ElementNotFoundException
        if (!found) {
            throw new ElementNotFoundException("Target element not found");
        }
    }
}
