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
 * @param <T> the type of elements stored in the graph
 */
public class ArrayGraph<T> implements GraphADT<T> {
    /** Default capacity for the graph */
    private final int DEFAULT_CAPACITY = 10;

    /** Number of vertices in the graph, and the next available position. */
    protected int numVertices;

    /** Adjacency matrix. */
    protected int[][] adjMatrix;

    /** Values of vertices. */
    protected T[] vertices;

    /** Creates an empty graph with the default capacity */
    public ArrayGraph() {
        numVertices = 0;
        this.adjMatrix = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty graph with a specified initial capacity.
     * 
     * @param size rhe initial capacity of the graph
     */
    public ArrayGraph(int size) {
        numVertices = 0;
        this.adjMatrix = new int[size][size];
        this.vertices = (T[]) (new Object[size]);
    }

    /**
     * Adds an edge between two vertices using their references.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Adds an edge between two vertices using their indices.
     *
     * @param index1 the index of the first vertex
     * @param index2 the index of the secund vertex
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = 1;
            adjMatrix[index2][index1] = 1;
        }
    }

    /**
     * Adds a vertex to the graph, expanding capacity if necessary.
     *
     * @param vertex the vertex to be added
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

    /**
     * Removes an edge between two vertices using their references.
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Removes an edge between two vertices using their indices.
     * 
     * @param index1 the index of the first vertex
     * @param index2 the index of the secund vertex
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = 0;
            adjMatrix[index2][index1] = 0;
        }
    }

    /**
     * Removes a vertex from the graph.
     * 
     * @param vertex the vertex to remove
     * @throws EmptyCollectionException if the graph is empty
     */
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

    /** 
     * Checks if the graph is connected.
     * 
     * @return true if the graph is connected, false otherwise 
     */
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

    /** @return true if the graph is empty, false otherwise */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /** 
     * Check if the graph is empty.
     * 
     * @return the number of vertices in the graph
     */
    @Override
    public int size() {
        return numVertices;
    }

    /**
     * Gets the index of a vertex.
     * 
     * @param vertex the vertex to find
     * @return the index of the vertex, or -1 if not found
     */
    private int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++)
            if (vertices[i].equals(vertex))
                return i;
        return -1;
    }

    /**
     * Validates if an index is within bounds.
     * 
     * @param index the index to validate
     * @return true if valid, false otherwise
     */
    private boolean indexIsValid(int index) {
        return index >= 0;
    }

    /** Expands the graph capacity by doubling its size. */
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

    /**
     * Returns an iterator for breadth-first search (BFS) starting from a given vertex.
     *
     * @param startVertex the vertex to start the BFS from
     * @return an iterator over the vertices visited during the BFS
     */
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

    /*
     * @Override
     * public Iterator<T> iteratorDFS(T startVertex) {
     * StackADT<T> stack = new LinkedStack<T>();
     * UnorderedListADT<T> list = new ArrayUnorderedList<T>();
     * boolean[] visited = new boolean[numVertices];
     * T vertex;
     * boolean found;
     * 
     * if (!indexIsValid(getIndex(startVertex)))
     * return list.iterator();
     * 
     * stack.push(startVertex);
     * list.addToRear(vertices[getIndex(startVertex)]);
     * visited[getIndex(startVertex)] = true;
     * 
     * while (!stack.isEmpty()) {
     * try {
     * vertex = stack.peek();
     * found = false;
     * // Find a vertex adjacent to x that has not been visited
     * // Push it on the stack
     * int i = 0;
     * while (i < numVertices && !found) {
     * if (adjMatrix[getIndex(vertex)][i] == 1 && !visited[i]) {
     * stack.push(vertices[i]);
     * list.addToRear(vertices[i]);
     * visited[i] = true;
     * found = true;
     * }
     * i++;
     * }
     * if (!found && !stack.isEmpty())
     * stack.pop();
     * } catch (EmptyCollectionException e) {
     * // Auto-generated catch block
     * e.printStackTrace();
     * }
     * }
     * return list.iterator();
     * }
     */

    /**
     * Returns an iterator for depth-first search (DFS) starting from a given vertex.
     *
     * @param startVertex the vertex to start the DFS from
     * @return an iterator over the vertices visited during the DFS
     */
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

    /**
     * Returns an iterator over the shortest path between two vertices using BFS.
     *
     * @param startVertex  the starting vertex in the graph
     * @param targetVertex the target vertex in the graph
     * @return an iterator over the shortest path from startVertex to targetVertex,
     *         or an empty iterator if no path exists or vertices are invalid
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        // Verificar se os vertices existem no grafo
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return new ArrayUnorderedList<T>().iterator();
        }

        // Fila para BFS
        QueueADT<Integer> queue = new LinkedQueue<>();
        boolean[] visited = new boolean[numVertices];
        int[] predecessors = new int[numVertices];

        // Inicializar predecessores como -1 (não visitados)
        for (int i = 0; i < numVertices; i++) {
            predecessors[i] = -1;
        }

        queue.enqueue(startIndex);
        visited[startIndex] = true;

        boolean found = false;

        // BFS para Encontrar o caminho mais curto
        while (!queue.isEmpty() && !found) {
            try {
                int currentIndex = queue.dequeue();
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[currentIndex][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        predecessors[i] = currentIndex;
                        queue.enqueue(i);

                        // Se encontrar o destino, parar a busca
                        if (i == targetIndex) {
                            found = true;
                            break;
                        }
                    }
                }
            } catch (EmptyCollectionException e) {
                e.printStackTrace(); // Captura de exceção por segurança
            }
        }

        // Se o destino não for encontrado, retorna o iterador vazio
        if (!found) {
            return new ArrayUnorderedList<T>().iterator();
        }

        // Reconstruir o caminho a partir dos predecessores
        UnorderedListADT<T> path = new ArrayUnorderedList<>();
        int current = targetIndex;
        while (current != -1) {
            path.addToFront(vertices[current]);
            current = predecessors[current];
        }

        return path.iterator();
    }
}
