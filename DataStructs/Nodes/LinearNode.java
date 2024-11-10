package DataStructs.Nodes;

/**
 * LinearNode represents a node in a linear list.
 */
public class LinearNode<T> {
    /**
     * Reference to next node in list.
     */
    private LinearNode<T> next;
    /**
     * Element stored at this node.
     */
    private T element;
    /**
     * Creates an empty node.
     */
    public LinearNode() {
        next = null;
        element = null;
    }

    /**
     * Creates a node storing the specified element.
     * 
     * @param elem element to be stored
     */
    public LinearNode(T elem) {
        next = null;
        element = elem;
    }

    /**
     * Returns the node that follows this one.
     * 
     * @return LinearNode<T> reference to next node
     */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     * Sets the node that follows this one.
     * 
     * @param node node to follow this one
     */
    public void setNext(LinearNode<T> node) {
        next = node;
    }

    /**
     * Returns the element stored in this node.
     * 
     * @return T element stored at this node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     * 
     * @param elem element to be stored at this node
     */
    public void setElement(T elem) {
        element = elem;
    }

    @Override
    public String toString() {
        return "LinearNode [element=" + element + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null || getClass() != obj.getClass())
            return false;

        LinearNode<?> other = (LinearNode<?>) obj;

        if (element != null ? !element.equals(other.element) : other.element != null)
            return false;
        
        return (next == null && other.next == null) || (next != null && next.equals(other.next));
    }
}
