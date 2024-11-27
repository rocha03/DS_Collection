package DataStructs.Graph;

import java.util.Iterator;

import DataStructs.List.UnorderedList.ArrayUnorderedList;
import DataStructs.Queue.LinkedQueue;
import DataStructs.Stack.LinkedStack;
import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;
import Interfaces.StackADT;
import Interfaces.Graph.GraphADT;
import Interfaces.List.UnorderedListADT;

/**
 * Graph represents an adjacency matrix implementation of a graph.
 *
 */
public class ArrayGraph<T> implements GraphADT<T> {
    /**
     * 
     */
    private final int DEFAULT_CAPACITY = 10;
    /**
     * Number of vertices in the graph, and the next available position.
     */
    protected int numVertices;
    /**
     * Adjacency matrix.
     */
    protected int[][] adjMatrix;
    /**
     * Values of vertices.
     */
    protected T[] vertices;

    /**
     * Creates an empty graph.
     */
    public ArrayGraph() {
        numVertices = 0;
        this.adjMatrix = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty graph.
     * 
     * @param size
     */
    public ArrayGraph(int size) {
        numVertices = 0;
        this.adjMatrix = new int[size][size];
        this.vertices = (T[]) (new Object[size]);
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = 1;
            adjMatrix[index2][index1] = 1;
        }
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph
     * if necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    public void addVertex(T vertex) {
        if (numVertices == vertices.length)
            expandCapacity();
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = 0;
            adjMatrix[i][numVertices] = 0;
        }
        numVertices++;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));

    }

    /**
     * 
     * @param index1
     * @param index2
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = 0;
            adjMatrix[index2][index1] = 0;
        }
    }

    @Override
    public void removeVertex(T vertex) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Graph is empty.");

        int index = getIndex(vertex);

        if (index == -1)
            throw new IllegalArgumentException("Vertex not found in the graph.");

        // Shift rows in the adjacency matrix upwards
        for (int i = index; i < numVertices - 1; i++)
            adjMatrix[i] = adjMatrix[i + 1];

        // Nullify the last row to avoid leftover data
        adjMatrix[numVertices - 1] = new int[numVertices];

        // Shift columns in the adjacency matrix to the left
        for (int i = 0; i < numVertices - 1; i++)
            for (int j = index; j < numVertices - 1; j++)
                adjMatrix[i][j] = adjMatrix[i][j + 1];

        // Nullify the last column in all rows to avoid leftover data
        for (int i = 0; i < numVertices; i++)
            adjMatrix[i][numVertices - 1] = 0;

        // Remove the vertex from the vertices array
        for (int i = index; i < numVertices - 1; i++)
            vertices[i] = vertices[i + 1];

        // Nullify the last vertex in the array
        vertices[numVertices - 1] = null;

        // Decrease the number of vertices
        numVertices--;
    }

    @Override
    public boolean isConnected() {
        if (isEmpty())
            // An empty graph can be considered connected.
            return true;

        // Start DFS from the first vertex
        boolean[] visited = new boolean[numVertices];
        int visitedCount = performDFS(0, visited);

        // If all vertices are visited, the graph is connected
        return visitedCount == numVertices;
    }

    /**
     * Performs a Depth-First Search (DFS) from the given vertex index.
     *
     * @param vertexIndex The starting vertex index.
     * @param visited     The array to track visited vertices.
     * @return The count of vertices visited during the DFS.
     */
    private int performDFS(int vertexIndex, boolean[] visited) {
        visited[vertexIndex] = true;
        int count = 1;

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertexIndex][i] == 1 && !visited[i]) {
                count += performDFS(i, visited);
            }
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return numVertices;
    }

    /**
     * 
     * @param index
     */
    private int getIndex(T vertex) {
        int i = 0;
        while (i < vertices.length) {
            if (vertices[i].equals(vertex))
                return i;
            i++;
        }
        return -1;
    }

    /**
     * 
     * @param index
     * @return
     */
    private boolean indexIsValid(int index) {
        return index >= 0;
    }

    /**
     * 
     */
    private void expandCapacity() {
        int newSize = vertices.length * 2;
        T[] newArr = (T[]) (new Object[newSize]);
        int[][] newMat = new int[newSize][newSize];

        for (int i = 0; i < vertices.length; i++) {
            newArr[i] = vertices[i];
        }

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                newMat[i][j] = adjMatrix[i][j];
            }
        }

        this.vertices = newArr;
        this.adjMatrix = newMat;

    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        QueueADT<T> queue = new LinkedQueue<T>();
        UnorderedListADT<T> list = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        T vertex;
        if (!indexIsValid(getIndex(startVertex)))
            return list.iterator();

        queue.enqueue(startVertex);
        visited[getIndex(startVertex)] = true;

        while (!queue.isEmpty()) {
            try {
                vertex = queue.dequeue();
                list.addToRear(vertices[getIndex(vertex)]);
                /**
                 * Find all vertices adjacent to x that have
                 * not been visited and queue them up
                 */
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[getIndex(vertex)][i] == 1 && !visited[i]) {
                        queue.enqueue(vertices[i]);
                        visited[i] = true;
                    }
                }
            } catch (EmptyCollectionException e) {
                // Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list.iterator();
    }

    /* @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        StackADT<T> stack = new LinkedStack<T>();
        UnorderedListADT<T> list = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        T vertex;
        boolean found;

        if (!indexIsValid(getIndex(startVertex)))
            return list.iterator();

        stack.push(startVertex);
        list.addToRear(vertices[getIndex(startVertex)]);
        visited[getIndex(startVertex)] = true;

        while (!stack.isEmpty()) {
            try {
                vertex = stack.peek();
                found = false;
                // Find a vertex adjacent to x that has not been visited
                // Push it on the stack
                int i = 0;
                while (i < numVertices && !found) {
                    if (adjMatrix[getIndex(vertex)][i] == 1 && !visited[i]) {
                        stack.push(vertices[i]);
                        list.addToRear(vertices[i]);
                        visited[i] = true;
                        found = true;
                    }
                    i++;
                }
                if (!found && !stack.isEmpty())
                    stack.pop();
            } catch (EmptyCollectionException e) {
                // Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list.iterator();
    } */

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        UnorderedListADT<T> list = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(getIndex(startVertex))) {
            return list.iterator();
        }

        // Start the recursive DFS
        dfsRecursiveHelper(startVertex, visited, list);
        return list.iterator();
    }

    /**
     * Recursive helper method for depth-first search.
     *
     * @param vertex  the current vertex being visited
     * @param visited array to track visited vertices
     * @param list    the list to store traversal results
     */
    private void dfsRecursiveHelper(T vertex, boolean[] visited, UnorderedListADT<T> list) {
        // Mark the current vertex as visited and add it to the result list
        int vertexIndex = getIndex(vertex);
        visited[vertexIndex] = true;
        list.addToRear(vertex);

        // Recursively visit all adjacent unvisited vertices
        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertexIndex][i] == 1 && !visited[i]) {
                dfsRecursiveHelper(vertices[i], visited, list);
            }
        }
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        // TODO Auto-generated method stub
        return null;
    }
}
