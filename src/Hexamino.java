import java.util.Random;

import javafx.scene.paint.Color;

public class Hexamino {
    private int[][] points = new int[4][2];
    private int id;
    private Color color;

    /**
     * @param points
     */
    public Hexamino(int[][] points, Color color) {
        this.points = points;
        Random r = new Random();
        this.id = r.nextInt(7);
        this.color = color;
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].On(color);
            App.BOARD[point[0]][point[1]].setId(this.id);
        }
    }

    public void moveDown() {
        for (int[] point : points) {
            if (point[1] + 1 <= 20 && App.BOARD[point[0]][point[1] + 1].status()
                    && id != App.BOARD[point[0]][point[1] + 1].getId())
                return;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].Off();
            App.BOARD[point[0]][point[1]].setId(-1);
            point[1]++;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].On(color);
            App.BOARD[point[0]][point[1]].setId(id);
        }
    }

    public void moveRight() {
        for (int[] point : points) {
            if ((point[0] + 2 <= 14 && App.BOARD[point[0] + 2][point[1]].status()
                    && id != App.BOARD[point[0] + 2][point[1]].getId())
                    || (point[0] + 1 <= 14 && App.BOARD[point[0] + 1][point[1]].status()
                            && id != App.BOARD[point[0] + 1][point[1]].getId()))
                return;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].Off();
            App.BOARD[point[0]][point[1]].setId(-1);
            point[0] += 2;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].On(color);
            App.BOARD[point[0]][point[1]].setId(id);
        }
    }

    public void moveLeft() {
        for (int[] point : points) {
            if ((point[0] - 2 >= 0 && App.BOARD[point[0] - 2][point[1]].status()
                    && id != App.BOARD[point[0] - 2][point[1]].getId())
                    || (point[0] - 1 >= 0 && App.BOARD[point[0] - 1][point[1]].status()
                            && id != App.BOARD[point[0] - 1][point[1]].getId()))
                return;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].Off();
            App.BOARD[point[0]][point[1]].setId(-1);
            point[0] -= 2;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].On(color);
            App.BOARD[point[0]][point[1]].setId(id);
        }
    }
}
