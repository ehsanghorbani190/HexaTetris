import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

class Hexagon {
    private final double SIDE = 15.0;
    private final double WIDTH = 2 * SIDE;
    private final double HEIGHT = Math.sqrt(3) * SIDE;
    private final double DISTANCEX = 7.5;
    private Double[] points = new Double[] { DISTANCEX, HEIGHT / 2, DISTANCEX + SIDE, HEIGHT / 2, WIDTH, HEIGHT,
            DISTANCEX + SIDE, 3 * HEIGHT / 2, DISTANCEX, 3 * HEIGHT / 2, 0.0, HEIGHT };
    private int x, y;
    private Polygon hexagon = new Polygon();
    private boolean on = false;
    /**
     * @param points
     * @param x
     * @param y
     */
    public Hexagon(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 0)
                points[i] += x * WIDTH * 0.75;
            else
                points[i] += (y - (x % 2) / 2.0) * HEIGHT;
        }
        this.hexagon.getPoints().addAll(this.points);
        this.hexagon.setStroke(Color.GRAY);
    }

    /**
     * @return the hexagon
     */
    public Polygon getHexagon() {
        return hexagon;
    }
    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

        /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    public void On(Color color){
        this.hexagon.setFill(color);
        this.on = true;
    }
    
    public void Off(){
        this.hexagon.setFill(Color.WHEAT);
        this.on = false;
    }
    public boolean status(){
        return this.on;
    }
}
