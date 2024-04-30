import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Collections;

public class GraphPane extends Pane {
    public static final double DESIRED_HEIGHT = 500;
    public static final double DESIRED_WIDTH = 500;

    private static final String[] labelsList = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private Graph graph;
    private int labelIndex = 0;

    public GraphPane() {
        this.setMinHeight(DESIRED_HEIGHT);
        this.setMinWidth(DESIRED_WIDTH);
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

    }

    public void addVertex(double x, double y) {
        String label = getNextLabel();
        Vertex vertex = new Vertex(label, x, y);
        System.out.println("Adding vertex " + vertex);
        graph.addVertex(vertex);
//        drawVertex(vertex);
    }

    private String getNextLabel() {
        if (labelIndex == labelsList.length - 1) {
            labelIndex = 0;
        }
        return labelsList[labelIndex++];
    }

    private void drawVertex(Vertex v) {
        Circle c = new Circle(v.getX(), v.getY(), 5);
        c.setFill(Color.BLACK);
        Label label = new Label(v.getName());
        label.setTextFill(Color.LIMEGREEN);
        label.setLayoutX(v.getX()+5);
        label.setLayoutY(v.getY()-20);

        this.getChildren().add(c);
        this.getChildren().add(label);
    }

}
