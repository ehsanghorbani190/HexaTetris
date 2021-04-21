import java.util.Random;

import javafx.scene.paint.Color;

public class Hexamino {
    private int[][] points;
    private int id;
    private Color color;

    /**
     * @param points
     */
    public Hexamino(int type) {
        switch (type) {
        case 0:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 6, 0 }, { 8, 0 } };
            color = Color.LIGHTGREEN;
            break;
        case 1:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 6, 1 }, { 7, 2 } };
            color = Color.LIGHTSKYBLUE;
            break;
        case 2:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 6, 1 }, { 8, 1 } };
            color = Color.TOMATO;
            break;
        case 3:
            points = new int[][] { { 7, 0 }, { 9, 0 }, { 6, 0 }, { 8, 0 } };
            color = Color.LIGHTPINK;
            break;
        case 4:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 6, 1 }, { 5, 2 } };
            color = Color.GOLD;
            break;
        case 5:
            points = new int[][] { { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 } };
            color = Color.MAGENTA;
            break;
        case 6:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 7, 2 }, { 8, 1 } };
            color = Color.CHOCOLATE;
            break;
        }
        Random r = new Random();
        this.id = r.nextInt(7);
    }

    public boolean make() {
        for (int[] is : points) {
            if (App.BOARD[is[0]][is[1]].getId() != -1)
                return false;
        }
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].On(color, this.id);
        }
        return true;
    }

    public void destroy() {
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].Off(-1);
        }
    }
    public void stop(){
        for (int[] point : points) {
            App.BOARD[point[0]][point[1]].setId(-3);
        }
    }
    public boolean moveDown() {
        for (int[] point : points) {
            if (point[1] + 1 <= 20 && App.BOARD[point[0]][point[1] + 1].status()
                    && id != App.BOARD[point[0]][point[1] + 1].getId())
                return false;
        }
        this.destroy();
        for (int[] point : points)
            point[1]++;
        this.make();
        return true;
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
        this.destroy();
        for (int[] point : points) {
            if (even && point[0] % 2 == 1)
                point[1]--;
            else if (!even && point[0] % 2 == 0)
                point[1]++;
            point[0]++;
        }
        this.make();
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
        this.destroy();
        for (int[] point : points) {
            if (even && point[0] % 2 == 1)
                point[1]--;
            else if (!even && point[0] % 2 == 0)
                point[1]++;
            point[0]--;
        }
        this.make();
    }

    public void rotate() {

    }
}
