package Models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Box {
    private int number;
    private BufferedImage image;
    private int rowPosition;
    private int columnPosition;
    private boolean paired;

    public Box(int number, int rowPosition, int columnPosition) throws IOException {
        this.number = number;
        this.image = ImageIO.read(getClass().getResourceAsStream("/assets/" + Integer.toString(number) + ".png"));
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.paired = false;
    }

    public int getNumber() {
        return number;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public boolean isPaired() {
        return paired;
    }

    public void setPaired(boolean paired) {
        this.paired = paired;
    }

}
