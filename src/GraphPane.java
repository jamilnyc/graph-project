import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Collections;

public class GraphPane extends Pane {
    public static final double DESIRED_HEIGHT = 500;
    public static final double DESIRED_WIDTH = 500;

    private Graph graph;

    public GraphPane(Graph graph) {
        this.setMinHeight(DESIRED_HEIGHT);
        this.setMinWidth(DESIRED_WIDTH);

        this.graph = graph;
    }

    public void redraw() {

    }
}
