import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ehsan
 */
public class App extends Application {
    // GameBoard
    static final Hexagon[][] BOARD = new Hexagon[15][21];

    @Override
    public void start(Stage stage) {
        stage.setTitle("HexaTetris!");

        Pane root = new Pane();
        // Initializing GameBoard
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 21; j++) {
                BOARD[i][j] = new Hexagon(i, j);
                if (i == 0 || i == 14 || j == 20) {
                    BOARD[i][j].On(Color.ORANGE);
                    BOARD[i][j].setId(-2);
                } else
                    BOARD[i][j].Off();
                root.getChildren().add(BOARD[i][j].getHexagon());
            }
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.WHEAT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Hexamino h = new Hexamino(new int[][] { { 3, 2 }, { 2, 0 }, { 2, 1 }, { 1, 2 } } , Color.GREEN);
        // playing motions
        Timeline play = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                h.moveDown();
            }
        }));
        play.setCycleCount(Timeline.INDEFINITE);
        play.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}