import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

class Hexagon {
    private final double SIDE = 15.0;
    private final double WIDTH = 2 * SIDE;
    private final double HEIGHT = Math.sqrt(3) * SIDE;
    private final double DISTANCEX = 7.5;
    private Double[] points = new Double[] { DISTANCEX, 0.0, DISTANCEX + SIDE, 0.0, WIDTH, HEIGHT / 2, DISTANCEX + SIDE, HEIGHT, DISTANCEX,
            HEIGHT, 0.0, HEIGHT / 2 };
    private int x, y;
    private Polygon hexagon = new Polygon();

    /**
     * @param points
     * @param x
     * @param y
     */
    public Hexagon(int x, int y) {
        this.setX(x);
        this.setY(y);
        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 0)
                points[i] += x*WIDTH*0.75;
            else
                points[i] += (y + (x%2)/2.0)*HEIGHT ;
        }
        this.hexagon.getPoints().addAll(this.points);
        this.hexagon.setStroke(Color.WHITE);
    }

    /**
     * @return the hexagon
     */
    public Polygon getHexagon() {
        return hexagon;
    }

    /**
     * @param hexagon the hexagon to set
     */
    public void setHexagon(Polygon hexagon) {
        this.hexagon = hexagon;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

}
