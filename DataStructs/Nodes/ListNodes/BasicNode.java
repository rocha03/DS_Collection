package DataStructs.Nodes.ListNodes;

/**
 * Abstract base class for linked list nodes.
 * Holds an element of type T and a reference to the next node of type N.
 * Used as a base class for other types of nodes (e.g., linear, double).
 */
public abstract class BasicNode<T, N extends BasicNode<T, N>> {
    
    /**
     * Element held in the node.
     * This stores the value or data associated with the node.
     */
    protected T element;

    /**
     * The next node, specific to each node type (e.g., singly linked or doubly linked).
     * This is the reference to the next node in the list.
     */
    protected N next;

    /**
     * Returns the element held in this node.
     * 
     * @return T element held in this node.
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element held in this node.
     * 
     * @param element element to be held in this node.
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Returns the node that follows this one.
     * 
     * @return N reference to the next node.
     */
    public N getNext() {
        return next;
    }

    /**
     * Sets the node that follows this one.
     * 
     * @param next node to follow this one.
     */
    public void setNext(N next) {
        this.next = next;
    }

    /**
     * Checks if two BasicNode instances are equal based on their elements.
     * Two nodes are considered equal if their elements are equal (or both null).
     *
     * @param obj The object to compare with.
     * @return true if both nodes are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Same reference, so they are equal
        if (obj == null || getClass() != obj.getClass()) return false;  // Different class or null
        
        BasicNode<?, ?> other = (BasicNode<?, ?>) obj;
        
        // Compare the elements (handling nulls)
        return (element != null ? element.equals(other.element) : other.element == null);
    }
}
