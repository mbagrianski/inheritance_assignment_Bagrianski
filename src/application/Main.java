package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

/**
 * Example of using the Canvas and GraphicsContext class within JavaFX
 *
 */

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

    double width = 600, height = 600;
    Group root = new Group();
    Scene scene = new Scene(root);


	@Override
	public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing Functions Test");
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        Arc line = new Arc(100, 40, -60);
        line.setColour(Color.TURQUOISE);
        line.setName("Line 1");
        line.setDomain(-50, 50);

        line.draw(canvas);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

	// test method for drawing - you can use this as an example for drawing various types of lines


}
