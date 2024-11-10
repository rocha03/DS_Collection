package DataStructs.Nodes;

/**
 * DoubleNode represents a node in a doubly linked list.
 *
 */
public class DoubleNode<E> extends BasicNode<E, DoubleNode<E>> {
    /**
     * Previous node in the doubly linked list.
     */
    private DoubleNode<E> previous;

    /**
     * Creates an empty node.
     */
    public DoubleNode() {
        this.next = null;
        this.element = null;
        this.previous = null;
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param elem the element to be stored into the new node.
     */
    public DoubleNode(E elem) {
        this.next = null;
        this.element = elem;
        this.previous = null;
    }

    /**
     * Returns the node that precedes this one.
     *
     * @return the node that precedes the current one.
     */
    public DoubleNode<E> getPrevious() {
        return previous;
    }

    /**
     * Sets the node that precedes this one.
     * 
     * @param dnode the node to be set as the one to precede
     *              the current one.
     */
    public void setPrevious(DoubleNode<E> dnode) {
        previous = dnode;
    }

    @Override
    public String toString() {
        return "DoubleNode [element=" + element + "]";
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
}