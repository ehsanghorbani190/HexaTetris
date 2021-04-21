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
        boolean even;
        int min = points[0][1], index = 0;
        for (int[] point : points) {
            if (point[0] + 1 <= 14
                    && (App.BOARD[point[0] + 1][point[1]].status() && id != App.BOARD[point[0] + 1][point[1]].getId())
                    || (point[1] + 1 <= 20 && App.BOARD[point[0] + 1][point[1] + 1].status()
                            && id != App.BOARD[point[0] + 1][point[1] + 1].getId()))
                return;
        }
        for (int i = 1; i < 4; i++) {
            if (points[i][1] < min) {
                index = i;
                min = points[i][1];
            } else if (points[i][1] == min && points[index][0] % 2 == 0) {
                index = i;
            }
        }
        even = (points[index][0] % 2 == 0) ? true : false;
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].Off();
            App.BOARD[point[0]][point[1]].setId(-1);
            if (even && point[0] % 2 == 1)
                point[1]--;
            else if (!even && point[0] % 2 == 0)
                point[1]++;
            point[0]++;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].On(color);
            App.BOARD[point[0]][point[1]].setId(id);
        }
    }

    public void moveLeft() {
        boolean even;
        int min = points[0][1], index = 0;
        for (int[] point : points) {
            if (point[0] - 1 >= 0
                    && (App.BOARD[point[0] - 1][point[1]].status() && id != App.BOARD[point[0] - 1][point[1]].getId())
                    || (point[1] + 1 <= 20 && App.BOARD[point[0] - 1][point[1] + 1].status()
                            && id != App.BOARD[point[0] - 1][point[1] + 1].getId()))
                return;
        }
        for (int i = 1; i < 4; i++) {
            if (points[i][1] < min) {
                index = i;
                min = points[i][1];
            } else if (points[i][1] == min && points[index][0] % 2 == 0) {
                index = i;
            }
        }
        even = (points[index][0] % 2 == 0) ? true : false;
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].Off();
            App.BOARD[point[0]][point[1]].setId(-1);
            if (even && point[0] % 2 == 1)
                point[1]--;
            else if (!even && point[0] % 2 == 0)
                point[1]++;
            point[0]--;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].On(color);
            App.BOARD[point[0]][point[1]].setId(id);
        }
    }

    public void rotate() {

    }
}
