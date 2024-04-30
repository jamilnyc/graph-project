import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static GraphPane pane;

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
        // g.addEdge(vE, vF);
        // g.addEdge(vA, vC);

        // Disconnect graph
        // g.removeEdge(vA, vF);

        System.out.println(g.getDepthFirstSearch(vA));
        System.out.println(g.getBreadthFirstSearch(vA));
        System.out.println("Graph has cycles: " + g.hasCycles());
        System.out.println("Is Connected: " + g.isConnected(vA));

        pane = new GraphPane(g);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();

        root.getChildren().add(getEdgeInput());
        root.getChildren().add(pane);
        root.getChildren().add(getInfoInput());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Node getEdgeInput()
    {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setSpacing(10);
        Button addEdge = new Button("Add Edge");
        hbox.getChildren().add(addEdge);

        Label vertex1Label = new Label("Vertex 1");
        TextField vertex1Text = new TextField();
        vertex1Text.setPrefColumnCount(2);

        Label vertex2Label = new Label("Vertex 2");
        TextField vertex2Text = new TextField();
        vertex2Text.setPrefColumnCount(2);

        hbox.getChildren().add(vertex1Label);
        hbox.getChildren().add(vertex1Text);
        hbox.getChildren().add(vertex2Label);
        hbox.getChildren().add(vertex2Text);

        return hbox;
    }

    private Node getInfoInput()
    {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.setSpacing(10);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setSpacing(10);
        Button isConnectedBtn = new Button("Is Connected?");
        Button hasCyclesBtn = new Button("Has Cycles?");
        Button depthFirstSearchBtn = new Button("Depth First Search");
        Button breadthFirstSearchBtn = new Button("Breadth First Search");
        hbox.getChildren().add(isConnectedBtn);
        hbox.getChildren().add(hasCyclesBtn);
        hbox.getChildren().add(depthFirstSearchBtn);
        hbox.getChildren().add(breadthFirstSearchBtn);

        vbox.getChildren().add(hbox);

        TextField message = new TextField();
        message.setMaxWidth(400);
        vbox.getChildren().add(message);
        return vbox;
    }
}
