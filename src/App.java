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
                if (i == 0 || i == 14 || j == 20)
                    BOARD[i][j].On(Color.ORANGE);
                else
                    BOARD[i][j].Off();
                root.getChildren().add(BOARD[i][j].getHexagon());
            }
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.WHEAT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        // playing motions
        Timeline play = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
            int i = 0;

            @Override
            public void handle(ActionEvent event) {
                if (i > 0)
                    BOARD[2][i - 1].Off();
                ;
                BOARD[2][i].On(Color.RED);
                if (i < 19)
                    i++;
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