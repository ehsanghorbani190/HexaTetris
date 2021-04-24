import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

class Hexagon {
    private final double SIDE = 15.0;
    private final double WIDTH = 2 * SIDE;
    private final double HEIGHT = Math.sqrt(3) * SIDE;
    private final double DISTANCEX = 7.5;
    private Double[] points = new Double[] { DISTANCEX, HEIGHT / 2, DISTANCEX + SIDE, HEIGHT / 2, WIDTH, HEIGHT,
            DISTANCEX + SIDE, 3 * HEIGHT / 2, DISTANCEX, 3 * HEIGHT / 2, 0.0, HEIGHT };
    private Polygon hexagon = new Polygon();
    private boolean on = false;
    private int id;
    private Color color;

    /**
     * @param points
     * @param x
     * @param y
     */
    public Hexagon(int x, int y) {
        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 0)
                points[i] += x * WIDTH * 0.75;
            else
                points[i] += (y - (x % 2) / 2.0) * HEIGHT;
        }
        this.hexagon.getPoints().addAll(this.points);
        this.hexagon.setStroke(App.bgcolor);
        this.hexagon.setStrokeWidth(2);
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the hexagon
     */
    public Polygon getHexagon() {
        return hexagon;
    }

    public void On(Color color, int id) {
        this.hexagon.setFill(color);
        this.setColor(color);
        this.on = true;
        this.setId(id);
    }

    public void Off(int id) {
        this.hexagon.setFill(App.bgcolor);
        this.setColor(App.bgcolor);
        this.on = false;
        this.setId(id);
    }

    public boolean status() {
        return this.on;
    }
}
