import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Ehsan
 */
public class App extends Application {

    @Override
    public void start(Stage stage){
        stage.setTitle("HexaTetris!");
        Pane root = new Pane();
        Hexagon [][] a = new Hexagon[15][21];
        for (int i = 0; i < 15 ; i++) {
            for (int j = 0; j < 21 ; j++) {
                a[i][j] = new Hexagon(i, j);
                if(i==0 || i== 14 || j == 20) a[i][j].getHexagon().setFill(Color.ORANGE);
                else {
                    a[i][j].getHexagon().setFill(Color.DODGERBLUE);
                    if(i%2==0||j%2==0) a[i][j].getHexagon().setOpacity(0.5);
                    else a[i][j].getHexagon().setOpacity(0.75);
                }
                root.getChildren().add(a[i][j].getHexagon());   
            }    
        }
        // Hexagon h = new Hexagon(7, 0);
        // root.getChildren().add(h.getHexagon());
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        // Timeline fiveSecondsWonder = new Timeline (new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        //     int i = 0;
        //     @Override
        //     public void handle(ActionEvent event) {
        //         if(i<20){
        //         h.getHexagon().setTranslateY(i*Math.sqrt(3) * 15.0);
        //         i++;
        //         }
        //     }
        //   }));
        //   fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        //   fiveSecondsWonder.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}