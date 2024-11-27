package DataStructs.Tree.Heap;

import DataStructs.Tree.ArrayBinaryTree;
import Exceptions.EmptyCollectionException;
import Interfaces.Tree.HeapADT;

/**
 * ArrayHeap provides an array implementation of a minheap.
 *
 */
public class ArrayHeap<T extends Comparable<T>> extends ArrayBinaryTree<T> implements HeapADT<T> {
    public ArrayHeap() {
        super();
    }

    @Override
    public void addElement(T element){
        // Implementação do método para inserir elementos no heap
        if (count == size())
            //extends();
        tree[count] = element;
        count++;

        if (count > 1)
            heapifyAdd();
    }

    /**
     * Remove the element with the lowest value in this heap and
     * returns a reference to it, Throws an EmptyCollectionException if
     * the heap is empty.
     * 
     * @return a referencee to the element with the lowest value in this heap.
     * @throws EmptyCollectionException if an empty collection exception occurs.
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        // Implementação do método para remover o menor elemento
        if (isEmpty())
            throw new EmptyCollectionException("Heap is empty");

        T minElement = tree[0];

        tree[0] = tree[count - 1];
        heapifyRemove();
        count--;

        return minElement;
    }

    private int getNextNode(int node) {
        int left = 2 * node + 1;
        int right = 2 * node + 2;

        if (left >= count && right >= count)
            return count; // Sem filhos
        if (left >= count)
            return right; // Apenas o filho direito
        if (right >= count)
            return left; // Apenas o filho esquerdo
        return (tree[left].compareTo(tree[right]) < 0) ? left : right; // O menor dos dois
    }

    /**
     * Reorders this heap to maintain the ordering property after
     * dding a node.
     */
    private void heapifyAdd() {
        int next = count - 1;
        T temp = tree[next];
        while (next != 0 && tree[next].compareTo(tree[(next - 1) / 2]) < 0) {
            tree[next] = tree[(next - 1) / 2];
            tree[(next - 1) / 2] = temp;
            next = (next - 1) / 2;
        }
    }

    /**
     * Reorders this heap to maintain the ordering property.
     */
    private void heapifyRemove() {
        int node = 0;
        int next = getNextNode(node);

        while (next < count && tree[next].compareTo(tree[node]) < 0) {
            T temp = tree[node];
            tree[node] = tree[next];
            tree[next] = temp;

            node = next;
            next = getNextNode(node);
        }
    }

    /**
     * Returns the element with the lowest value in this heap.
     * Throws an EmptyCollectionException if the heap is empty.
     *
     * @return the element with the lowest value in this heap
     * @throws EmptyCollectionException if an empty heap exception occurs
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        // Implementação do método para remover o menor elemento
        if (isEmpty())
            throw new EmptyCollectionException("Heap is empty");

        return tree[0];
    }
}
