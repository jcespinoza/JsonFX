package jsonfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jay C Espinoza
 */
public class JsonFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        /*PowerOffWindow pof = new PowerOffWindow(null);
        
        StackPane root = new StackPane();
        root.getChildren().add(pof);*/
        AnchorPane root = new PowerOffWindow(null);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("JSonFX");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResource("/res/JsonFX.png").toString()));
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
