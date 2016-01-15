import java.awt.*;

/**
 * Created by userdev on 1/15/2016.
 */
public abstract class Shape {
    private Color currentColor;
    private Point p1;
    private Point p2;
    private boolean isFilled;

    protected abstract void drawShape1(Graphics g);

    public void drawShape(Graphics g) {
        g.setColor(this.getCurrentColor());
        drawShape1(g);
    }


    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }
}
