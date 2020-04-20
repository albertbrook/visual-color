import java.awt.*;

class Settings {
    static final Dimension SCREEN_SIZE;
    static final int UNIFORM_SIZE;

    static {
        SCREEN_SIZE = new Dimension(500, 500);
        UNIFORM_SIZE = 100;

        Plate.getPlate().addRound(50, 50, Color.RED);
        Plate.getPlate().addRound(150, 50, Color.GREEN);
        Plate.getPlate().addRectangle(200, 200, Color.BLUE);
    }

    private Settings() {}
}
