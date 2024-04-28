import java.util.*;

public class Graph {
    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    void addVertex(Vertex vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    void removeVertex(Vertex vertex) {
        // Remove any edges connected to this vertex
        adjVertices.values().forEach(list -> list.remove(vertex));

        // Remove the vertex itself
        adjVertices.remove(vertex);
    }

    private List<Vertex> getAdjacentVertices(Vertex vertex) {
        List<Vertex> adjacentVertices = adjVertices.get(vertex);
        if (adjacentVertices == null) {
            throw new NonExistentVertexException("Vertex " + vertex + " does not exist in graph");
        }
        return adjacentVertices;
    }

    void addEdge(Vertex v1, Vertex v2) {
       List<Vertex> v1List = getAdjacentVertices(v1);
       List<Vertex> v2List = getAdjacentVertices(v2);
       v1List.add(v2);
       v2List.add(v1);
    }

    void removeEdge(Vertex v1, Vertex v2) {
        List<Vertex> v1List = getAdjacentVertices(v1);
        List<Vertex> v2List = getAdjacentVertices(v2);
        v1List.remove(v2);
        v2List.remove(v1);
    }

    List<Vertex> getNeighbors(Vertex v1) {
        return adjVertices.get(v1);
    }

    LinkedHashSet<Vertex> depthFirstSearch(Vertex root) {
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
        for (Vertex v : visited) {
            sb.append(v.getName()).append(" ");
        }

        return sb.toString();
    }


}
