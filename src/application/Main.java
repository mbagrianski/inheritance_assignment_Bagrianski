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

        Linear l = new Linear(1, 1, 0);
        l.setColour(Color.RED);
        l.setName("Relation 1");
        l.setDomain(-50, 50);
        l.draw(canvas);
        
        Quadratic q = new Quadratic(0.25, -0.5, 1, 0);
        q.setColour(Color.BLUE);
        q.setName("Relation 2");
        q.setDomain(-50, 50);
        q.draw(canvas);
        
        Cubic c = new Cubic(0.35, 0.25, -0.5, -1.0, 2);
        c.setColour(Color.TURQUOISE);
        c.setName("Relation 3");
        c.setDomain(-50, 50);
        c.draw(canvas);
        
        Parabola p = new Parabola(1, 0, 0);
        p.setColour(Color.PURPLE);
        p.setName("Relation 4");
        p.setDomain(-50, 50);
        p.draw(canvas);
        
        Arc a = new Arc(400.0, 0, -2.0);
        a.setColour(Color.BLUEVIOLET);
        a.setName("Relation 2");
        a.setDomain(-50, 50);
        a.draw(canvas);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

	// test method for drawing - you can use this as an example for drawing various types of lines


}
