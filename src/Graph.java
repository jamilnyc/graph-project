import java.util.*;

public class Graph {
    private Map<Vertex, List<Vertex>> adjacencyList;
    private Vertex root;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        if (adjacencyList.isEmpty()) {
            root = vertex;
        }

        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(Vertex vertex) {
        // Remove any edges connected to this vertex
        adjacencyList.values().forEach(list -> list.remove(vertex));

        // Remove the vertex itself
        adjacencyList.remove(vertex);
    }

    private List<Vertex> getAdjacentVertices(Vertex vertex) {
        List<Vertex> adjacentVertices = adjacencyList.get(vertex);
        if (adjacentVertices == null) {
            throw new NonExistentVertexException("Vertex " + vertex + " does not exist in graph");
        }
        return adjacentVertices;
    }

    public void addEdge(Vertex v1, Vertex v2) {
        List<Vertex> v1List = getAdjacentVertices(v1);
        List<Vertex> v2List = getAdjacentVertices(v2);
        v1List.add(v2);
        v2List.add(v1);
    }

    public void removeEdge(Vertex v1, Vertex v2) {
        List<Vertex> v1List = getAdjacentVertices(v1);
        List<Vertex> v2List = getAdjacentVertices(v2);
        v1List.remove(v2);
        v2List.remove(v1);
    }

    private List<Vertex> getNeighbors(Vertex v1) {
        return adjacencyList.get(v1);
    }

    // Adapted from https://www.baeldung.com/java-graphs
    private LinkedHashSet<Vertex> depthFirstSearch(Vertex root) {
        LinkedHashSet<Vertex> visited = new LinkedHashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (Vertex v : getNeighbors(current)) {
                    stack.push(v);
                }
            }
        }

        return visited;
    }

    public String getDepthFirstSearch(Vertex root) {
        LinkedHashSet<Vertex> visited = depthFirstSearch(root);
        StringBuilder sb = new StringBuilder();
        sb.append("Depth First Search: ");
        for (Vertex v : visited) {
            sb.append(v.getName()).append(" ");
        }

        return sb.toString();
    }

    // Adapted from https://www.baeldung.com/java-graphs
    private LinkedHashSet<Vertex> breadthFirstSearch(Vertex root) {
        LinkedHashSet<Vertex> visited = new LinkedHashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);

        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            for (Vertex v : getNeighbors(current)) {
                if (!visited.contains(v)) {
                    queue.add(v);
                    visited.add(v);
                }
            }
        }

        return visited;
    }

    public String getBreadthFirstSearch(Vertex root) {
        LinkedHashSet<Vertex> visited = breadthFirstSearch(root);
        StringBuilder sb = new StringBuilder();
        sb.append("Breadth First Search: ");
        for (Vertex v : visited) {
            sb.append(v.getName()).append(" ");
        }

        return sb.toString();
    }

    // Adapted from https://github.com/ClaireLee22/Detect-cycle-in-a-graph/blob/main/detect%20cycle%20in%20undirected%20graph/has_cycle.py
    public boolean hasCycles() {
        LinkedHashSet<Vertex> visited = new LinkedHashSet<>();

        for(Vertex v : adjacencyList.keySet()) {
            if (!visited.contains(v) && cycleExists(v, visited, null)) {
                return true;
            }
        }
        return false;
    }

    private boolean cycleExists(Vertex current, LinkedHashSet<Vertex> visited, Vertex parent) {
        if (visited.contains(current)) {
            return true;
        }

        visited.add(current);
        List<Vertex> neighbors = getNeighbors(current);
        for (Vertex neighbor : neighbors) {
            if (neighbor.notEquals(parent) && cycleExists(neighbor, visited, current)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected(Vertex root) {
        LinkedHashSet<Vertex> visited = depthFirstSearch(root);

        // If connected, you should have visited all the nodes
        return visited.size() == adjacencyList.size();
    }

    public Set<Vertex> getVertices() {
        return adjacencyList.keySet();
    }

    public Map<Vertex, List<Vertex>> getAdjacencyList() {
        return adjacencyList;
    }

    public Vertex getVertexByName(String name) {
        for (Vertex v : getVertices()) {
            if (v.getName().equals(name)) {
                return v;
            }
        }

        throw new NonExistentVertexException("Vertex with name " + name + " does not exist in graph");
    }
}
