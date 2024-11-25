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
    HeapNode(T obj) {
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
}
