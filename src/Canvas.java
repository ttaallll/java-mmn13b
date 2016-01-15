import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Canvas extends JFrame implements ActionListener {

    private static String exitString = "Exit";
    private static String colorString = "Color";
    private static String undoString = "Undo";
    private static String clearString = "Clear";

    private static String filledString = "Filled";
    private static String emptyString = "Empty";


    private static String shapeLinesString = "Lines";
    private static String shapeCirclesString = "Circles";
    private static String shapeRectanglesString = "Rectangles";
    private static String shapeRoundedRectanglesString = "Rounded Rectangles";

    private JLabel label;
    private JButton colorBtn;
    private DrawingPanel drawingPanel;

    private Config currentConfig;

    public Canvas() {
        super("Canvas");

        currentConfig = new Config();


        JLabel labelMe = new JLabel("Tal Pais (:", SwingConstants.CENTER);
        labelMe.setSize(100, 30);
        setLabelFontSize(labelMe);
        add(labelMe, BorderLayout.SOUTH);


        createToolbarButtons();

        drawingPanel = new DrawingPanel(currentConfig);
        add(drawingPanel, BorderLayout.CENTER);

        setSize(800, 800);
        setVisible(true);
    }

    private void setLabelFontSize(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }

    public void actionPerformed(ActionEvent e) {

        String btnPressed = e.getActionCommand();

        if (btnPressed.equals(exitString)) {
            System.exit(0);
        }
        else if (btnPressed.equals(colorString)) {
            currentConfig.currentColor = JColorChooser.showDialog(Canvas.this, "select a color", currentConfig.currentColor);
            colorBtn.setBackground(currentConfig.currentColor);
        }
        else if (btnPressed.equals(filledString)) {
            currentConfig.isFilled = true;
        }
        else if (btnPressed.equals(emptyString)) {
            currentConfig.isFilled = false;
        }
        else if (btnPressed.equals(undoString)) {
            drawingPanel.removeLastObject();
        }
        else if (btnPressed.equals(clearString)) {
            drawingPanel.removeAll();
        }
    }

    private void createToolbarButtons() {

        JPanel toolbar1 = new JPanel();
        toolbar1.setLayout(new GridLayout(1,6));


        /* combo of shapes */
        createShapesButtons(toolbar1);

        /* color chooser */
        colorBtn = new JButton(colorString);
        colorBtn.addActionListener(this);
        toolbar1.add(colorBtn);

        /* filled or empty */
        createFilledRadio(toolbar1);

        JButton button1;

        /* undo button */
        button1 = new JButton(undoString);
        button1.addActionListener(this);
        toolbar1.add(button1);

        /* clear button */
        button1 = new JButton(clearString);
        button1.addActionListener(this);
        toolbar1.add(button1);

        /* exit button */
        button1 = new JButton(exitString);
        button1.addActionListener(this);
        toolbar1.add(button1);




        add(toolbar1, BorderLayout.NORTH);
    }

    private void createFilledRadio(JPanel toolbar) {
        JPanel fillingPanel = new JPanel();
        ButtonGroup fillingType = new ButtonGroup();

        /* filled button */
        JRadioButton radioBtn1 = new JRadioButton(filledString);
        radioBtn1.addActionListener(this);
        fillingType.add(radioBtn1);
        fillingPanel.add(radioBtn1);

        /* empty button */
        JRadioButton radioBtn2 = new JRadioButton(emptyString, true);
        radioBtn2.addActionListener(this);
        fillingType.add(radioBtn2);
        fillingPanel.add(radioBtn2);

        toolbar.add(fillingPanel);
    }

    private void createShapesButtons(JPanel toolbar) {

        /* create shapes combo box */

        /* change the config when pressing shape type */

        String names[]={shapeLinesString, shapeCirclesString, shapeRectanglesString, shapeRoundedRectanglesString};
        JComboBox combo = new JComboBox(names);
        combo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String btnPressed = (String)((JComboBox) e.getSource()).getSelectedItem();

                if (btnPressed.equals(shapeLinesString)) {
                    currentConfig.currentShape = 0;
                }
                else if (btnPressed.equals(shapeCirclesString)) {
                    currentConfig.currentShape = 1;
                }
                else if (btnPressed.equals(shapeRectanglesString)) {
                    currentConfig.currentShape = 2;
                }
                else if (btnPressed.equals(shapeRoundedRectanglesString)) {
                    currentConfig.currentShape = 3;
                }
            }
        });

        toolbar.add(combo);
    }

    public static void main(String[] args) {
        Canvas frame = new Canvas();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
