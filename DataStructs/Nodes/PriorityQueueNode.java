package DataStructs.Nodes;

/**
 * PriorityQueueNode represents a node in a priority queue
 * containing a comparable object, order, and a priority value.
 */
public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode<T>> {
    private static int nextorder = 0;
    private int priority;
    private int order;
    private T element;

    /**
     * Creates a new PriorityQueueNode with the specified data.
     *
     * @param obj  the element of the new priority queue node
     * @param prio the integer priority of the new queue node
     */
    public PriorityQueueNode(T obj, int prio) {
        element = obj;
        priority = prio;
        order = nextorder++;
    }

    /**
     * Returns the element in this node.
     *
     * @return the element contained within this node
     */
    public T getElement() {
        return element;
    }

    /**
     * Returns the priority value for this node.
     *
     * @return the integer priority for this node
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the order for this node.
     *
     * @return the integer order for this node
     */
    public int getOrder() {
        return order;
    }

    /**
     * Returns a string representation for this node.
     *
     */
    public String toString() {
        return "Element: " + element.toString() + "\nPriority: " + priority + "\nOrder: " + order;
    }

    /**
     * Compares the current node with the given node.
     * Returns 1 if the current node has higher priority or same priority but later order.
     * Returns -1 if the other node has higher priority or same priority but earlier order.
     * Returns 0 if both have the same priority and order.
     *
     * @param obj the node to compare to this node
     * @return 1, -1, or 0 based on the comparison result
     */
    @Override
    public int compareTo(PriorityQueueNode<T> obj) {
        if (this.priority > obj.getPriority())
            return 1;
        if (this.priority < obj.getPriority())
            return -1;
        return Integer.compare(this.order, obj.getOrder());
    }

    /**
     * Checks if the current node is equal to the given object.
     *
     * @param obj the object to compare to this node
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        PriorityQueueNode<?> other = (PriorityQueueNode<?>) obj;

        return (element != null ? element.equals(other.element) : other.element == null)
                && priority == other.priority
                && order == other.order;
    }
}
