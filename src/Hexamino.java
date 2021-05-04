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
            color = Color.rgb(38, 222, 129);
            break;
        case 1:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 6, 1 }, { 7, 2 } };
            color = Color.rgb(0, 216, 214);
            break;
        case 2:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 6, 1 }, { 8, 1 } };
            color = Color.rgb(255, 56, 56);
            break;
        case 3:
            points = new int[][] { { 7, 0 }, { 9, 0 }, { 6, 0 }, { 8, 0 } };
            color = Color.rgb(84, 109, 229);
            break;
        case 4:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 6, 1 }, { 5, 2 } };
            color = Color.rgb(255, 159, 26);
            break;
        case 5:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 7, 2 }, { 7, 3 } };
            color = Color.rgb(252, 66, 123);
            break;
        case 6:
            points = new int[][] { { 7, 1 }, { 7, 0 }, { 7, 2 }, { 8, 1 } };
            color = Color.rgb(202, 211, 200);
            break;
        }
        Random r = new Random();
        this.id = r.nextInt(7);
    }

    public boolean make() {
        for (int[] point : points) {
            if (point[0] <= 0 || point[0] >= 14 || point[1] < 0 || point[1] >= 20
                    || App.BOARD[point[0]][point[1]].getId() != -1)
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

    public void stop() {
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

    public void move(char way) {
        boolean even;
        int min = points[0][1], index = 0;
        for (int i = 1; i < 4; i++) {
            if (points[i][1] < min || points[i][1] == min && points[index][0] % 2 == 0) {
                index = i;
                min = (points[i][1] < min) ? points[i][1] : min;
            }
        }
        even = (points[index][0] % 2 == 0) ? true : false;
        int[][] temp = new int[4][2];
        hardCopy(points, temp);
        this.destroy();
        for (int[] point : points) {
            if (even && point[0] % 2 == 1)
                point[1]--;
            else if (!even && point[0] % 2 == 0)
                point[1]++;
            if (way == 'R')
                point[0]++;
            else
                point[0]--;
        }
        if (!this.make()) {
            hardCopy(temp, points);
            this.make();
        }
    }

    public void rotate() {
        int x = points[0][0], y = points[0][1], even = x % 2, Xdis, Ydis;
        int[][] temp = new int[4][2];
        hardCopy(points, temp);
        this.destroy();
        for (int i = 1; i < points.length; i++) {
            Xdis = x - points[i][0];
            Ydis = y - points[i][1];
            if (Xdis == 1) {
                if (Ydis == even) {
                    points[i][0]++;
                    points[i][1] += even - 1;
                } else if (Ydis == even - 1) {
                    points[i][1]--;
                } else if (Ydis == even + 1)
                    points[i][0] += 2;
                else if (Ydis == even - 2) {
                    points[i][0]--;
                    points[i][1] += even - 2;
                }
            } else if (Xdis == 0) {
                if (Ydis == 1 || Ydis == -1) {
                    points[i][0] += Ydis;
                    points[i][1] += (Ydis > 0) ? 1 - even : -even;
                } else if (Ydis == 2 || Ydis == -2) {
                    points[i][0] += Ydis;
                    points[i][1] += Ydis / 2;
                }
            } else if (Xdis == -1) {
                if (Ydis == even) {
                    points[i][1]++;
                } else if (Ydis == even - 1) {
                    points[i][0]--;
                    points[i][1] += even;
                } else if (Ydis == even + 1) {
                    points[i][0]++;
                    points[i][1] += 1 + even;
                } else if (Ydis == even - 2)
                    points[i][0] -= 2;
            } else if (Xdis == 2) {
                if (Ydis == 1) {
                    points[i][1]--;
                    points[i][0] += Xdis;
                } else if (Ydis == 0) {
                    points[i][0]++;
                    points[i][1] -= 1 + even;
                } else {
                    points[i][1] -= Xdis;
                }
            } else if (Xdis == -2) {
                if (Ydis == -1) {
                    points[i][1]++;
                    points[i][0] += Xdis;
                } else if (Ydis == 0) {
                    points[i][0]--;
                    points[i][1] += 2 - even;
                } else {
                    points[i][1] -= Xdis;
                }
            }
        }
        if (!this.make()) {
            hardCopy(temp, points);
            this.make();
        }
    }

    public static void hardCopy(int[][] a, int[][] b) {
        for (int i = 0; i < 4; i++) {
            b[i][0] = a[i][0];
            b[i][1] = a[i][1];
        }
    }
}
