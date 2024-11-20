package DataStructs.List.OrderedList;

import DataStructs.List.DoublyLinkedList;
import DataStructs.Nodes.ListNodes.DoubleNode;
import Interfaces.List.OrderedListADT;

/**
 * 
 */
public class DoublyLinkedOrderedList<T> extends DoublyLinkedList<T> implements OrderedListADT<T> {

    @Override
    public void add(T element) throws IllegalArgumentException {
        // Check if element is comparable
        if (!(element instanceof Comparable<?>))
            throw new IllegalArgumentException("Not comparable");

        // Cast element to Comparable
        Comparable<T> comparable = (Comparable<T>) element;
        DoubleNode<T> newNode = new DoubleNode<>(element);

        // Insert at the head if the list is empty or element is smallest
        if (head == null || comparable.compareTo(head.getElement()) <= 0) {
            newNode.setNext(head);
            if (head != null)
                head.setPrevious(newNode);
            head = newNode;
            if (tail == null)
                tail = newNode;
        } else {
            // Traverse to find the insertion point
            DoubleNode<T> current = head;
            while (current.getNext() != null && comparable.compareTo(current.getNext().getElement()) > 0)
                current = current.getNext();

            // Insert new node at the found position
            newNode.setNext(current);
            if (current.getNext() != null)
                current.getNext().setPrevious(newNode);

            current.setNext(newNode);
            newNode.setPrevious(current);

            // Update tail if inserted at the end
            if (newNode.getNext() == null)
                tail = newNode;
        }
        count++;
        modCount.increment();
    }

}
