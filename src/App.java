import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
    static Hexamino hexamino;
    static int score;

    @Override
    public void start(Stage stage) {
        stage.setTitle("HexaTetris!");

        Pane root = new Pane();
        // Initializing GameBoard
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 21; j++) {
                BOARD[i][j] = new Hexagon(i, j);
                if (i == 0 || i == 14 || j == 20) {
                    BOARD[i][j].On(Color.SADDLEBROWN, -2);
                } else
                    BOARD[i][j].Off(-1);
                root.getChildren().add(BOARD[i][j].getHexagon());
            }
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Random id = new Random();
        hexamino = new Hexamino(id.nextInt(7));
        hexamino.make();
        // playing motions
        Timeline goDown = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean stoped = hexamino.moveDown();
                if (!stoped) {
                    hexamino.stop();
                    int row = rowFill();
                    do {
                        if (row != -1){
                            gainScore(row);
                        }
                        row = rowFill();
                    } while (row != -1);

                    hexamino = new Hexamino(id.nextInt(7));
                    hexamino.make();
                }
            }
        }));
        goDown.setCycleCount(Timeline.INDEFINITE);
        goDown.play();
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D)
                hexamino.moveRight();
            else if (e.getCode() == KeyCode.A)
                hexamino.moveLeft();
            else if (e.getCode() == KeyCode.S)
                hexamino.moveDown();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public int rowFill() {
        int row = -1;
        for (int i = 0; i < 20; i++) {
            for (int j = 1; j < 14; j++) {
                if (BOARD[j][i].getId() != -3) {
                    row = -1;
                    break;
                } else
                    row = i;
            }
        }
        return row;
    }

    public void gainScore(int row) {
        for (int i = 1; i < 14; i++) {
            BOARD[i][row].Off(-1);
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 1; j < 14; j++) {
                if (BOARD[j][i].getId() == -3) {
                    Color color = BOARD[j][i].getColor();
                    BOARD[j][i].Off(-1);
                    BOARD[j][i + 1].On(color, -3);
                }
            }
        }
        score += 10;
    }
}