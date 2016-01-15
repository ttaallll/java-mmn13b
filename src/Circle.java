import java.awt.*;

/**
 * Created by userdev on 1/15/2016.
 */
public class Circle extends Shape {
    @Override
    public void drawShape1(Graphics g) {
        int startX = Math.min(getP1().x, getP2().x);
        int startY = Math.min(getP1().y, getP2().y);
        int width = Math.abs(getP1().x - getP2().x);
        int height = Math.abs(getP1().y - getP2().y);

        if (isFilled())
            g.fillOval(startX, startY, width, height);
        else
            g.drawOval(startX, startY, width, height);
    }
}
