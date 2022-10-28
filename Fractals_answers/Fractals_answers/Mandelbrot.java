

//import libraries
import java.awt.*;

//class Julia which is a subclass of Set
public class Mandelbrot extends Set {

	/*********************************************************************************
	 * Method which returns the relevant color according to the number of iterations
	 *********************************************************************************/
	public int fractalSet(double real, double img, int iterations) {
		double consReal = real;
		double consImg = img;
		int times;

		// Iterating till the variable times reaches number of "iterations"
		for (times = 0; times < iterations; times++) {
			// real part when two complex numbers are multiplied
			double newReal = (real * real) - (img * img) + consReal;
			// imaginary part when two complex numbers are multiplied
			double newImg = (2 * img * real) + consImg;

			real = newReal;
			img = newImg;

			if (((real * real) + (img * img)) > 16) {
				break;
			}
		}

		// returning the color according to the number of iterations
		if (times == iterations) {
			return 0x00000000;
		} else {
			return Color.HSBtoRGB(times / 500f, 1, times / (times + 8f));
		}
	}

	/**************************************************************************
	 * Method run which executes when Thread is started
	 ***************************************************************************/
	public void run() {
		try {

			// looping till threadIndex reaches WIDTH while threadIndex is incremented by
			// number of Threads
			for (int x = threadIndex; x < WIDTH; x = x + threadCounter) {
				for (int y = 0; y < HEIGHT; y++) {

					// mapping the complex plane to the pixel plane
					float xpercent = (float) (xMax - xMin) / WIDTH;
					float ypercent = (float) (yMax - yMin) / HEIGHT;
					double newx = ((x) * xpercent) + xMin;
					double newy = (HEIGHT - y) * ypercent + yMin;
					// calling the method to get the colour of the pixel
					int color = fractalSet(newx, newy, iterations);
					// calling the method to set the pixelcolour to the buffered image
					generatePixelColor(x, y, color);
				}
			}
		} catch (Exception err) {
			System.out.println("Error while running Mandelbrot: " + err);
		}
	}

}
