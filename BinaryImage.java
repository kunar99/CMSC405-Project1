import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BinaryImage extends JComponent {
    public static final long serialVersionUID = 2L;
    private BufferedImage image;

    /* This is the default Constructor, which creates a binary version of a stop sign
     * by calling BinaryImage(id:int)
     */
    public BinaryImage() {
        this(0);
    }

    /* Constructor that takes an integer as an id.  The id represents the index of the sign that
     *  this BinaryImage will be, 0 for stop, 1 for crossing, and 2 for yield.
     */
    public BinaryImage(int id) {
        int[][] matrix = initAs(id); // initialize 2D matrix by id
        int rows = matrix.length; // support changing of image size
        int cols = matrix[0].length;
        image = new BufferedImage(rows, cols, BufferedImage.TYPE_INT_ARGB);
        for (int x=0; x<rows; x++) {
            for (int y=0; y<cols; y++) {
                image.setRGB(x,y,matrix[x][y]);
            }
        }
    }

    // getter for image
    public BufferedImage getImage() {
        return this.image;
    }

    /* The method initAs(id:int) will return a 2D matrix representing a street sign in binary.
     * It supports translucency, and the colors red, green, blue, black, white, yellow, orange
     */
    private int[][] initAs(int id) {
        int a = Color.TRANSLUCENT;
        int r = Color.RED.getRGB();
        int g = Color.GREEN.getRGB();
        int b = Color.BLUE.getRGB();
        int k = Color.BLACK.getRGB();
        int w = Color.WHITE.getRGB();
        int l = Color.YELLOW.getRGB();
        int o = Color.ORANGE.getRGB();
        //array of three street signs, stop sign, crossing sign, and yield sign
        int signs[][][]=
                { {     {a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,r,r,w,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,r,r,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,r,r,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,w,w,w,w,w,w,a},
                        {a,w,w,r,r,w,w,w,w,w,w,w,w,w,r,r,r,r,w,w,w,r,r,w,a},
                        {a,w,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,w,a},
                        {a,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,a},
                        {a,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,a},
                        {a,w,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,w,a},
                        {a,w,w,r,r,w,w,w,w,w,w,w,w,w,r,r,r,r,w,w,w,r,r,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,r,r,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,r,r,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,r,r,r,r,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,r,r,w,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,a},
                        {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,a},
                        {a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a} },

                        {       {a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a},
                                {a,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,a},
                                {a,k,k,k,k,k,k,k,k,k,w,w,w,w,w,k,k,k,k,k,k,k,k,k,a},
                                {a,k,k,k,k,k,k,k,w,w,w,w,w,w,w,w,w,k,k,k,k,k,k,k,a},
                                {a,k,k,k,k,k,k,w,w,w,w,w,w,w,w,w,w,w,k,k,k,k,k,k,a},
                                {a,k,k,k,k,w,w,w,k,k,k,k,k,k,k,k,k,w,w,w,k,k,k,k,a},
                                {a,k,k,k,w,w,k,k,k,k,k,k,k,k,k,k,k,k,k,w,w,k,k,k,a},
                                {a,k,k,w,w,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,w,w,k,k,a},
                                {a,k,w,w,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,w,w,k,a},
                                {a,k,w,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,w,k,a},
                                {a,k,w,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,w,k,a},
                                {a,k,w,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,w,k,a},
                                {a,k,w,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,w,k,a},
                                {a,k,w,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,w,k,a},
                                {a,k,w,k,k,k,k,k,k,k,k,w,k,k,k,k,k,k,k,k,k,k,w,k,a},
                                {a,k,w,k,k,k,k,k,w,k,k,w,k,k,w,k,k,k,k,k,k,k,w,k,a},
                                {a,k,w,k,k,k,k,k,k,w,k,w,k,w,k,k,k,k,k,k,k,k,w,k,a},
                                {a,k,k,w,k,k,k,k,k,k,w,w,w,k,k,k,k,k,k,k,k,w,k,k,a},
                                {a,k,k,k,w,k,k,w,w,w,w,w,w,w,w,w,w,k,k,k,w,k,k,k,a},
                                {a,k,k,k,k,k,k,k,k,k,w,w,w,k,k,k,k,k,k,k,k,k,k,k,a},
                                {a,k,k,k,k,k,k,k,k,w,k,w,k,w,k,k,k,k,k,k,k,k,k,k,a},
                                {a,k,k,k,k,k,k,k,w,k,k,w,k,k,w,k,k,k,k,k,k,k,k,k,a},
                                {a,k,k,k,k,k,k,k,k,k,k,w,k,k,k,k,k,k,k,k,k,k,k,k,a},
                                {a,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,k,a},
                                {a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a} },

                        {       {a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a},
                                {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,b,w,w,w,w,w,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,w,b,b,w,w,b,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,w,w,b,b,b,w,w,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,w,w,w,b,b,w,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,b,b,b,b,w,w,w,w,b,b,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,b,b,w,b,b,w,w,w,w,w,b,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,b,b,w,w,b,b,w,w,w,w,w,w,b,b,w,w,w,w,a},
                                {a,w,w,w,w,b,b,w,w,w,b,b,w,w,w,w,w,w,b,b,b,w,w,w,a},
                                {a,w,w,w,b,b,w,w,w,w,b,b,w,w,w,w,w,w,b,w,b,b,w,w,a},
                                {a,w,w,b,b,w,w,w,w,w,b,b,w,w,w,w,w,w,b,b,w,b,b,w,a},
                                {a,w,w,b,b,w,w,w,w,w,b,b,w,w,w,w,w,w,b,b,w,b,b,w,a},
                                {a,w,w,w,b,b,w,w,w,w,b,b,w,w,w,w,w,w,b,w,b,b,w,w,a},
                                {a,w,w,w,w,b,b,w,w,w,b,b,w,w,w,w,w,w,w,b,b,w,w,w,a},
                                {a,w,w,w,w,w,b,b,w,w,b,b,w,w,w,w,w,w,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,b,b,w,b,b,w,w,w,w,w,b,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,b,b,b,b,w,w,w,w,b,b,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,w,w,w,b,b,w,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,w,w,b,b,w,w,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,w,b,b,w,b,b,w,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,b,b,w,w,w,b,b,b,w,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,b,b,w,w,w,w,w,w,b,b,w,w,w,w,a},
                                {a,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,a},                              
                                {a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a} }

                       };
        return signs[id];
    }
}
