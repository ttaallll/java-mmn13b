import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

/**
 * Created by userdev on 1/15/2016.
 */
public class DrawingPanel extends JPanel {
    private Point p1;
    private Point p2;

    private Config currentConfig;

    private ArrayList<Shape> shapes;
    private Shape currentShape;

    public DrawingPanel(final Config currentConfig) {
        super();

        this.shapes = new ArrayList<Shape>();
        this.currentConfig = currentConfig;

        addMouseMotionListener(
                new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent e) {

                        /* when mouse is dragged, save the point2 for drawing the shape */

                        p2 = e.getPoint();
                        repaint();
                    }
                });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                /* when first time mouse pressed, save it to point1 to source of the shape */

                p1 = e.getPoint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                /* when released save the shape to the shapes array */

                p1 = null;
                p2 = null;
                if (currentShape != null)
                    shapes.add(currentShape);
            }
        });
    }

    /* for undo action */
    public void removeLastObject() {
        if (shapes.size() > 0) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

    /* for clear action */
    public void removeAll() {
        shapes.clear();
        repaint();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        /* draw all the saved shapes */
        for (int i = 0; i < shapes.size(); ++i) {
            shapes.get(i).drawShape(g);
        }

        if (p1 == null || p2 == null)
            return;

        /* create the shape, add its parameters and call to its abstract draw funtion */
        Shape s = null;
        if (currentConfig.currentShape == 0) {
            s = new Line();
        }
        else if (currentConfig.currentShape == 1) {
            s = new Circle();
        }
        else if (currentConfig.currentShape == 2) {
            s = new Rectangle();
        }
        else {
            s = new RoundRectangle();
        }
        s.setCurrentColor(currentConfig.currentColor);
        s.setP1(p1);
        s.setP2(p2);
        s.setFilled(currentConfig.isFilled);

        s.drawShape(g);

        currentShape = s;
    }
}
