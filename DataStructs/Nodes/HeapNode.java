package DataStructs.Nodes;

/**
 * Represents a node in a heap, extending BinaryTreeNode to include
 * a reference to the parent node for easier heap operations.
 * 
 * @param <T> the type of element stored in the node.
 */
public class HeapNode<T> extends BinaryTreeNode<T> {
    protected HeapNode<T> parent;

    /**
     * Creates a new heap node with the specified data.
     *
     * @param obj the data to be contained within
     *            the new heap nodes.
     */
    public HeapNode(T obj) {
        super(obj);
        parent = null;
    }

    /**
     * Returns the parent of this node.
     *
     * @return the parent node, or null if this node is the root.
     */
    public HeapNode<T> getParent() {
        return parent;
    }

    /**
     * Sets the parent of this node.
     *
     * @param parent the parent node to set.
     */
    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        HeapNode<?> other = (HeapNode<?>) obj;

        return (getElement() != null ? getElement().equals(other.getElement()) : other.getElement() == null)
                && (parent == null ? other.parent == null : parent.equals(other.parent))
                && (getLeft() == null ? other.getLeft() == null : getLeft().equals(other.getLeft()))
                && (getRight() == null ? other.getRight() == null : getRight().equals(other.getRight()));
    }
}
