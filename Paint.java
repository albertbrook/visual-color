import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

class Paint extends Canvas {
    private static Paint paint;

    private List<Shape> shapes;
    private List<Color> colors;

    static Paint getPaint() {
        if (paint == null) {
            paint = new Paint();
        }
        return paint;
    }

    private Paint() {
        shapes = Plate.getPlate().getShapes();
        colors = Plate.getPlate().getColors();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        for (int i = 0; i < shapes.size(); i++) {
            g2.setColor(colors.get(i));
            g2.setClip(shapes.get(i));
            g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        }

        for (int i = 0; i < shapes.size() - 1; i++) {
            for (int j = i + 1; j < shapes.size(); j++) {
                int red = (colors.get(i).getRed() + colors.get(j).getRed());
                int green = (colors.get(i).getGreen() + colors.get(j).getGreen());
                int blue = (colors.get(i).getBlue() + colors.get(j).getBlue());
                red = red > 255 ? 255 : red;
                green = green > 255 ? 255 : green;
                blue = blue > 255 ? 255 : blue;
                g2.setColor(new Color(red, green, blue));
                g2.setClip(shapes.get(i));
                g2.clip(shapes.get(j));
                g2.fillRect(0, 0, image.getWidth(), image.getHeight());
            }
        }

        int red = (colors.get(0).getRed() + colors.get(1).getRed() + colors.get(2).getRed());
        int green = (colors.get(0).getGreen() + colors.get(1).getGreen() + colors.get(2).getGreen());
        int blue = (colors.get(0).getBlue() + colors.get(1).getBlue() + colors.get(2).getBlue());
        red = red > 255 ? 255 : red;
        green = green > 255 ? 255 : green;
        blue = blue > 255 ? 255 : blue;
        g2.setColor(new Color(red, green, blue));
        g2.setClip(shapes.get(0));
        g2.clip(shapes.get(1));
        g2.clip(shapes.get(2));
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());

        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
