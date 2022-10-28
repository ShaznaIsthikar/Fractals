

//importing libraries
import java.util.List;

//Abstract class Set which is a subclass of Draw and also implements Runnable
abstract class Set extends Draw implements Runnable {

	// Declaring and Initializing variables
	protected double xMin = -1d;
	protected double xMax = 1d;
	protected double yMin = -1d;
	protected double yMax = 1d;
	protected double consX = -0.4d;
	protected double consY = 0.6d;
	protected int iterations = 1000;
	protected int threadIndex;
	protected int threadCounter;
	protected List<PixelColor> pColor;

	/********************************************
	 * Getters and Stteres for the parameters
	 *******************************************/
	public void setXMin(double xMin) {
		this.xMin = xMin;
	}

	public void setXMax(double xMax) {
		this.xMax = xMax;
	}

	public void setYMin(double yMin) {
		this.yMin = yMin;
	}

	public void setYMax(double yMax) {
		this.yMax = yMax;
	}

	public void setConsX(double consX) {
		this.consX = consX;
	}

	public void setConsY(double consY) {
		this.consY = consY;
	}

	public void setIterations(int iTTERATIONS) {
		this.iterations = iTTERATIONS;
	}

	public void setThreadCounter(int threadCounter) {
		this.threadCounter = threadCounter;
	}

	public void setPColor(List<PixelColor> pColor) {
		this.pColor = pColor;
	}

	public void setThreadIndex(int i) {
		this.threadIndex = i;
	}

	/******************************************************************
	 * Abstract methods to be used in Julia and Mandelbrot class
	 ******************************************************************/
	abstract int fractalSet(double real, double img, int itterations);

	public abstract void run();

	/****************************************************************
	 * Method to set parameters to the object pixelColor
	 ***************************************************************/
	public void generatePixelColor(int x, int y, int color) {
		PixelColor pixelColor = new PixelColor();
		pixelColor.setxCoordinate(x);
		pixelColor.setyCoordinate(y);
		pixelColor.setPixColor(color);
		pColor.add(pixelColor);
	}

}
