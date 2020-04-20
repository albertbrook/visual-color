import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

class Functions {
    private static Functions functions;

    private Paint paint;
    private List<Shape> shapes;

    private boolean move = false;
    private Point mouse;
    private List<Point> origins;
    private List<Integer> indexes;

    static Functions getFunctions() {
        if (functions == null) {
            functions = new Functions();
        }
        return functions;
    }

    private Functions() {
        paint = Paint.getPaint();
        shapes = Plate.getPlate().getShapes();

        origins = new ArrayList<>();
        indexes = new ArrayList<>();
    }

    void event() {
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point downPoint = new Point(e.getX(), e.getY());
                origins.clear();
                indexes.clear();
                for (int i = 0; i < shapes.size(); i++) {
                    if (shapes.get(i).contains(downPoint)) {
                        move = true;
                        mouse = downPoint;
                        if (shapes.get(i) instanceof Ellipse2D) {
                            origins.add(new Point(
                                    (int) ((Ellipse2D.Double) shapes.get(i)).x,
                                    (int) ((Ellipse2D.Double) shapes.get(i)).y
                            ));
                        } else if (shapes.get(i) instanceof Rectangle2D) {
                            origins.add(new Point(
                                    (int) ((Rectangle2D.Double) shapes.get(i)).x,
                                    (int) ((Rectangle2D.Double) shapes.get(i)).y
                            ));
                        }
                        indexes.add(i);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (move) {
                    move = false;
                }
            }
        };
        MouseMotionListener mouseMotionListener = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (move) {
                    int dx = e.getX() - mouse.x;
                    int dy = e.getY() - mouse.y;
                    for (int i = 0; i < indexes.size(); i++) {
                        Shape shape = shapes.get(indexes.get(i));
                        Point origin = origins.get(i);
                        if (shape instanceof Rectangle2D) {
                            ((Rectangle2D.Double) shape).x = origin.x + dx;
                            ((Rectangle2D.Double) shape).y = origin.y + dy;
                        } else if (shape instanceof Ellipse2D) {
                            ((Ellipse2D.Double) shape).x = origin.x + dx;
                            ((Ellipse2D.Double) shape).y = origin.y + dy;
                        }
                        for (Shape s : shapes) {
                            List<Point> result = isOver(shape, s);
                            if (result != null) {

                            }
                        }
                    }
                    paint.repaint();
                }
            }
        };
        paint.addMouseListener(mouseListener);
        paint.addMouseMotionListener(mouseMotionListener);
    }

    private List<Point> isOver(Shape s1, Shape s2) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i <= s1.getBounds().width; i++) {
            for (int j = 0; j <= s1.getBounds().height; j++) {
                if (s1.contains(i, j) && s2.contains(i, j))
                    points.add(new Point(i, j));
            }
        }
        if (points.size() == 0)
            return null;
        else
            return points;
    }

    private void superpose() {

    }
}
