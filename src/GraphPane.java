import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import java.util.*;

public class GraphPane extends Pane {
    public static final double DESIRED_HEIGHT = 500;
    public static final double DESIRED_WIDTH = 500;

    public static final int LINE_STROKE_WIDTH = 2;
    public static final int CIRCLE_RADIUS = 6;

    private static final String[] labelsList = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private Graph graph;
    private int labelIndex = 0;

    public GraphPane() {
        this.setPrefHeight(DESIRED_HEIGHT);
        this.setPrefWidth(DESIRED_WIDTH);
        graph = new Graph();
        this.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    addVertex(e.getX(), e.getY());
                    redraw();
                }
            }
        };

        this.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void redraw() {
        this.getChildren().clear();
        drawGraph();
    }

    public void addVertex(double x, double y) {
        String label = getNextLabel();
        Vertex vertex = new Vertex(label, x, y);
        System.out.println("Adding vertex " + vertex);
        graph.addVertex(vertex);
    }

    private String getNextLabel() {
        if (labelIndex == labelsList.length - 1) {
            labelIndex = 0;
        }
        return labelsList[labelIndex++];
    }

    private void drawGraph() {
        Map<Vertex, List<Vertex>> adjacencyList = graph.getAdjacencyList();
        for (Map.Entry<Vertex, List<Vertex>> entry : adjacencyList.entrySet()) {
            Vertex source = entry.getKey();
            drawVertex(source);

            for (Vertex neighbor : entry.getValue()) {
                Line line = new Line(source.getX(), source.getY(), neighbor.getX(), neighbor.getY());
                line.setFill(Color.RED);
                line.setStrokeWidth(LINE_STROKE_WIDTH);
                this.getChildren().add(line);
            }
        }
    }

    private void drawVertex(Vertex v) {
        System.out.println("Drawing vertex " + v);
        Circle c = new Circle(v.getX(), v.getY(), CIRCLE_RADIUS);
        c.setFill(Color.BLACK);
        Label label = new Label(v.getName());
        label.setTextFill(Color.LIMEGREEN);
        label.setLayoutX(v.getX()+5);
        label.setLayoutY(v.getY()-20);

        this.getChildren().add(c);
        this.getChildren().add(label);
    }

    public void addEdge(String name1, String name2) {
        if (name1.equals(name2)) {
            System.err.println("Cannot add edge from a node to itself: " + name1);
        }

        if (name1.isEmpty() || name2.isEmpty()) {
            System.err.println("One or more vertices is missing to create an edge");
            return;
        }

        Vertex v1 = graph.getVertexByName(name1);
        Vertex v2 = graph.getVertexByName(name2);

        graph.addEdge(v1, v2);
        redraw();
    }


}
