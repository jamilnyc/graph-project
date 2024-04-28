public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        Vertex vA = new Vertex("A", 0, 0);
        Vertex vB = new Vertex("B", 1, 1);
        Vertex vC = new Vertex("C", 2, 2);
        Vertex vD = new Vertex("D", 3, 3);
        Vertex vE = new Vertex("E", 4, 4);
        Vertex vF = new Vertex("F", 5, 5);

        g.addVertex(vA);
        g.addVertex(vB);
        g.addVertex(vC);
        g.addVertex(vD);
        g.addVertex(vE);
        g.addVertex(vF);

        g.addEdge(vA, vF);
        g.addEdge(vA, vE);
        g.addEdge(vA, vB);
        g.addEdge(vB, vD);
        g.addEdge(vD, vC);

        // Creates cycles
        g.addEdge(vE, vF);
        g.addEdge(vA, vC);

        System.out.println(g.getDepthFirstSearch(vA));
        System.out.println(g.getBreadthFirstSearch(vA));
        System.out.println("Graph has cycles: " + g.hasCycles());
    }
}
