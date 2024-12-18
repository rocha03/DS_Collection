package DataStructs.Nodes.ListNodes;

/**
 * LinearNode represents a node in a linear list.
 */
public class LinearNode<T> extends BasicNode<T, LinearNode<T>> {
    /**
     * Creates an empty node.
     */
    public LinearNode() {
        this(null);
    }

    /**
     * Creates a node storing the specified element.
     * 
     * @param elem element to be stored
     */
    public LinearNode(T elem) {
        this.next = null;
        this.element = elem;
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

        return (element != null ? element.equals(other.element) : other.element == null)
                && (next == null ? other.next == null : next.equals(other.next));

    }
}
