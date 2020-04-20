import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

class Plate {
    private static Plate plate;

    private List<Shape> shapes;
    private List<Color> colors;

    static Plate getPlate() {
        if (plate == null) {
            plate = new Plate();
        }
        return plate;
    }

    List<Shape> getShapes() {
        return shapes;
    }

    List<Color> getColors() {
        return colors;
    }

    private Plate() {
        shapes = new ArrayList<>();
        colors = new ArrayList<>();
    }

    void addRound(int x, int y, Color color) {
        Ellipse2D shape = new Ellipse2D.Double(x, y, Settings.UNIFORM_SIZE, Settings.UNIFORM_SIZE);
        shapes.add(shape);
        colors.add(color);
    }

    void addRectangle(int x, int y, Color color) {
        Rectangle2D shape = new Rectangle2D.Double(x, y, Settings.UNIFORM_SIZE, Settings.UNIFORM_SIZE);
        shapes.add(shape);
        colors.add(color);
    }
}
