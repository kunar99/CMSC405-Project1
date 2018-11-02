import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;

/**
 *
 */
public class Project1 extends JPanel {
    public static final long serialVersionUID = 1L;

    /* The main method of the project.  It creates the window, sets up the frame and adjusts the
     * dimensions of the view.  It also starts a timer used for animations.
     */
    public static void main(String[] args) {
        JFrame window;
        window = new JFrame("Jesse Young Project 1");  // The parameter shows in the window title bar.
        final Project1 panel = new Project1(); // The drawing area.
        window.setContentPane( panel ); // Show the panel in the window.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End program when window closes.
        window.pack();  // Set window size based on the preferred sizes of its contents.
        window.setResizable(false); // Don't let user resize window.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( // Center window on screen.
                (screen.width - window.getWidth())/2,
                (screen.height - window.getHeight())/2 );
        Timer animationTimer;  // A Timer that will emit events to drive the animation.
        final long startTime = System.currentTimeMillis();
        animationTimer = new Timer(16, (ActionEvent arg0) -> {
            panel.frameNumber++;
            panel.elapsedTimeMillis = System.currentTimeMillis() - startTime;
            panel.repaint();
        });
        window.setVisible(true); // Open the window, making it visible on the screen.
        animationTimer.start();  // Start the animation running.
    }

    private int frameNumber;        // A counter that increases by one in each frame.
    private long elapsedTimeMillis; // The time, in milliseconds, since the animation started.
    private float pixelSize;        // This is the measure of a pixel in the coordinate system

    /**
     * This constructor sets the size of the drawing area.  (The size is set as a "preferred size,"
     * which will be used by the pack() command in the main() routine.)
     */
    public Project1() {
        setPreferredSize( new Dimension(800,600) ); // Set size of drawing area, in pixels.
    }

    /**
     * The paintComponent method draws the content of the JPanel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();

        /* Turn on antialiasing in this graphics context, for better drawing. */
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /* Fill in the entire drawing area with white. */
        g2.setPaint(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());

        // set up a new coordinate system on the drawing area
        applyWindowToViewportTransformation(g2, -100, 100, -100, 100, true);

        drawSigns(g2, 76, -80); // draw three signs, with a padding of 76 and screen offset of -80
    }

    /* Create the three road signs and animates them on the screen.  It uses individual variables
     * to track the total x, y, scale, and rotation angle for each of the signs.
     */
    private void drawSigns(Graphics2D g2, int padding, int offset) {
        BinaryImage[] roadSign = new BinaryImage[3];
        int pause = 60; // number of frames to hold each transformation
        int increment = frameNumber % 420;
        for(int i=0; i<3; i++) { // go through the 3 road signs
            AffineTransform savedTransform = g2.getTransform(); //save transform
            double transX = 0.0; // used to cumulate transformations
            double transY = 0.0;
            double scaleX = 1.0;
            double scaleY = 1.0;
            int rotationAngle = 0;
            roadSign[i] = new BinaryImage(i);

            // begin animation, as the increment increases the animation will add transformations
            if(increment >= 0) {
                transX += i*padding+offset; // starting point on canvas
            }
            if(increment > pause) {
                transX += -5;               // combine with -5 in the x direction
            }
            if(increment > (pause*2)) {
                transY += 7;                // combine with +7 in the y direction
            }
            if(increment > (pause*3)) {
                rotationAngle += 45;        // rotate 45 degrees counter-clockwise
            }
            if(increment > (pause*4)) {
                rotationAngle += -90;       // combine rotation of 90 clockwise
            }
            if(increment > (pause*5)) {
                scaleX = 2;                 // scale 2 for the x component
            }
            if(increment > (pause*6)) {   // this is the last animation
                scaleY = 0.5;               // scale 0.5 for y component
            }

            // apply all transformations
            g2.translate(transX, transY);
            g2.rotate(rotationAngle);
            g2.scale(scaleX, scaleY);
            g2.drawImage(roadSign[i].getImage(), -13, -13, (JComponent)roadSign[i]); //draw centered
            g2.setTransform(savedTransform);  //restore the saved transform
        }
    }

    /**
     * Applies a coordinate transform to a Graphics2D graphics context.  The upper
     * left corner of the viewport where the graphics context draws is assumed to
     * be (0,0).  The coordinate transform will make a requested view window visible
     * in the drawing area.  The requested limits might be adjusted to preserve the
     * aspect ratio.
     *     This method sets the value of the global variable pixelSize, which is defined as the
     * maximum of the width of a pixel and the height of a pixel as measured in the
     * coordinate system.  (If the aspect ratio is preserved, then the width and
     * height will agree.
     * @param g2 The drawing context whose transform will be set.
     * @param left requested x-value at left of drawing area.
     * @param right requested x-value at right of drawing area.
     * @param bottom requested y-value at bottom of drawing area; can be less than
     *     top, which will reverse the orientation of the y-axis to make the positive
     *     direction point upwards.
     * @param top requested y-value at top of drawing area.
     * @param preserveAspect if preserveAspect is false, then the requested view window
     *     rectangle will exactly fill the viewport; if it is true, then the limits will be
     *     expanded in one direction, horizontally or vertically, if necessary, to make the
     *     aspect ratio of the view window match the aspect ratio of the viewport.
     *     Note that when preserveAspect is false, the units of measure in the horizontal
     *     and vertical directions will be different.
     */
    private void applyWindowToViewportTransformation(Graphics2D g2,
                                                     double left, double right, double bottom, double top,
                                                     boolean preserveAspect) {
        int width = getWidth();   // The width of this drawing area, in pixels.
        int height = getHeight(); // The height of this drawing area, in pixels.
        if (preserveAspect) {
            // Adjust the limits to match the aspect ratio of the drawing area.
            double displayAspect = Math.abs((double)height / width);
            double requestedAspect = Math.abs(( bottom-top ) / ( right-left ));
            if (displayAspect > requestedAspect) {
                // Expand the viewport vertically.
                double excess = (bottom-top) * (displayAspect/requestedAspect - 1);
                bottom += excess/2;
                top -= excess/2;
            }
            else if (displayAspect < requestedAspect) {
                // Expand the viewport vertically.
                double excess = (right-left) * (requestedAspect/displayAspect - 1);
                right += excess/2;
                left -= excess/2;
            }
        }
        g2.scale( width / (right-left), height / (bottom-top) );
        g2.translate( -left, -top );
        double pixelWidth = Math.abs(( right - left ) / width);
        double pixelHeight = Math.abs(( bottom - top ) / height);
        pixelSize = (float)Math.max(pixelWidth,pixelHeight);
    }
}