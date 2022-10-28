
//importing libraries
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Draw extends JComponent {

	// Declaring variables and initializing variables
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static BufferedImage image;
	JFrame frame = new JFrame("Fractal Set");

	/***********************************************************************
	 * Method that declares the buffered image and setting the frame
	 ***********************************************************************/
	public void render() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(this);
		frame.getContentPane().validate();
	}

	/***********************************************************************
	 * Method to make the frame Visible
	 ************************************************************************/
	public void makeVisible() {
		frame.pack();
		frame.setVisible(true);
	}

	/************************************************************************
	 * Method to insert colour to the buffered image
	 * 
	 * @param pColor
	 ************************************************************************/
	public void putColourto(List<PixelColor> pColor) {
		for (int index = 0; index < pColor.size(); index++) {
			int x = pColor.get(index).getxCoordinate();
			int y = pColor.get(index).getyCoordinate();
			int color = pColor.get(index).getPixColor();
			image.setRGB(x, y, color);
		}
	}

	/********************************************************************
	 * Overridden Methods
	 ********************************************************************/
	@Override
	public void addNotify() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}

}