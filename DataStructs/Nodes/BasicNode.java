package DataStructs.Nodes;

/**
 * 
 */
public abstract class BasicNode<T, N extends BasicNode<T, N>> {
    /**
     * Element held in the node.
     */
    protected T element;
    /**
     * The next node, specific to each node type.
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
     * @return LinearNode<T> reference to next node.
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
}
