import java.awt.*;

/**
 * Created by userdev on 1/15/2016.
 */
public class Line extends Shape {
    @Override
    public void drawShape1(Graphics g) {
        g.drawLine(getP1().x, getP1().y, getP2().x, getP2().y);
    }
}
