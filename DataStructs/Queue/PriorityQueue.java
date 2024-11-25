package DataStructs.Queue;

import DataStructs.Nodes.PriorityQueueNode;
import DataStructs.Tree.Heap.ArrayHeap;

/**
 * PriorityQueue demonstrates a priority queue using a min-heap.
 * Elements are dequeued based on their priority, where the smallest
 * priority value is served first.
 *
 * @param <T> the type of elements in the priority queue
 */
public class PriorityQueue<T> extends ArrayHeap<PriorityQueueNode<T>> {
    /**
     * Creates an empty priority queue.
     */
    public PriorityQueue() {
        super();
    }

    /**
     * Adds the given element to this PriorityQueue.
     *
     * @param object   the element to be added to the priority queue
     * @param priority the integer priority of the element to be added
     *                 (lower values indicate higher priority).
     */
    public void addElement(T object, int priority) {
        if (priority < 0) {
            throw new IllegalArgumentException("Priority must be non-negative.");
        }
        PriorityQueueNode<T> node = new PriorityQueueNode<T>(object, priority);
        super.addElement(node); // Delegate to ArrayHeap's addElement
    }

    /**
     * Removes the next highest priority element from this priority
     * queue and returns a reference to it.
     *
     * @return a reference to the next highest priority element
     *         in this queue
     */
    public T removeNext() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty.");
        }
        PriorityQueueNode<T> temp = super.removeMin(); // Remove the node with the smallest priority
        return temp.getElement(); // Return only the element, not the priority
    }
}
