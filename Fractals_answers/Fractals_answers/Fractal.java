
/***************************************************************************************
 * E/17/122  PROJECT-1
 ***************************************************************************************/

//Importing libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//the class including the main method
public class Fractal {

	// Declaring variables and initializing variables
	private static final String MANDELBROT = "Mandelbrot";
	private static final String JULIA = "Julia";
	private static final int NO_OF_THREADS = Runtime.getRuntime().availableProcessors();
	// creating the number of threads according to the processors available

	// main method
	public static void main(String[] args) {

		// Instantiating an instance from theThread class
		Thread[] threads = new Thread[NO_OF_THREADS];
		// Creating a list to store pixel information
		List<PixelColor> pColor = Collections.synchronizedList(new ArrayList<>());
		// Initializing and Declaring instances from Julia class
		Julia jFractal = null;
		// Initializing and Declaring instances from Julia class
		Mandelbrot mFractal = null;

		/*****************************************************************************************
		 * Generate the given set ny Thread
		 ****************************************************************************************/
		// Executes only when the arguments are valid
		if (isValid(args)) {

			// Set the first argument to the string type
			String type = args[0];

			// try catch block to handle the exceptions in Threads
			try {

				// if the first argument is JULIA
				if ((type).equalsIgnoreCase(JULIA)) {
					// loop the following till threadindex reaches NO_OF_THREADS-1
					for (int threadIndex = 0; threadIndex < NO_OF_THREADS; threadIndex++) {
						jFractal = generateJulia(args, pColor, threadIndex); // Send the arguments to generateJulia
																				// method
						jFractal.render(); // call the frame
						threads[threadIndex] = new Thread(jFractal); // send the object to the thread
						threads[threadIndex].start(); // start the thread
					}

					// if the first argument is MANDELBROT
				} else if ((type).equalsIgnoreCase(MANDELBROT)) {
					// loop the following till threadindex reaches NO_OF_THREADS-1
					for (int threadIndex = 0; threadIndex < NO_OF_THREADS; threadIndex++) {
						mFractal = generateMandelbrot(args, pColor, threadIndex); // Send the arguments to generateJulia
																					// method
						mFractal.render(); // call the frame
						threads[threadIndex] = new Thread(mFractal); // send the object to the thread
						threads[threadIndex].start(); // start the thread
					}
				}

				// Waiting for a thread to die and complete its execution
				for (int threadIndex = 0; threadIndex < NO_OF_THREADS; threadIndex++) {
					threads[threadIndex].join();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// if the first argument is JULIA
			if ((type).equalsIgnoreCase(JULIA)) {
				jFractal.putColourto(pColor); // calling the putColourto method to assign colours to the image
				jFractal.makeVisible(); // making the frame visible

				// if the first argument is MANDELBROT
			} else if ((type).equalsIgnoreCase(MANDELBROT)) {
				mFractal.putColourto(pColor); // calling the putColourto method to assign colours to the image
				mFractal.makeVisible(); // making the frame visible
			}

		}
	}

	/***********************************************************************************************
	 * Method to send the correct arguments to the JULIA Class
	 * 
	 * @param args
	 * @param pColor
	 * @param threadIndex
	 * @return the object
	 *********************************************************************************************/
	private static Julia generateJulia(String[] args, List<PixelColor> pColor, int threadIndex) {
		Julia jFractal = new Julia();
		jFractal.setPColor(pColor);
		jFractal.setThreadIndex(threadIndex);
		jFractal.setThreadCounter(NO_OF_THREADS);
		try {
			if (args.length == 4) {
				jFractal.setConsX(Double.parseDouble(args[1]));
				jFractal.setConsY(Double.parseDouble(args[2]));
				jFractal.setIterations(Integer.parseInt(args[3]));
			}
		} catch (Exception e) { // handling the error of trying to convert a character which is not a number
								// into double or integer
			System.out.println("Only enter numbers after the 1st argument");
			System.exit(0);
		}
		return jFractal;
	}

	/***********************************************************************************************
	 * Method to send the correct arguments to the MANDELBROT Class
	 * 
	 * @param args
	 * @param pColor
	 * @param threadIndex
	 * @return the object
	 *********************************************************************************************/

	private static Mandelbrot generateMandelbrot(String[] args, List<PixelColor> pColor, int threadIndex) {
		Mandelbrot mFractal = new Mandelbrot();
		mFractal.setPColor(pColor);
		mFractal.setThreadIndex(threadIndex);

		mFractal.setThreadCounter(NO_OF_THREADS);
		try {
			if (args.length == 5) {
				mFractal.setXMin(Double.parseDouble(args[1]));
				mFractal.setXMax(Double.parseDouble(args[2]));
				mFractal.setYMin(Double.parseDouble(args[3]));
				mFractal.setYMax(Double.parseDouble(args[4]));
			}
			if (args.length == 6) {
				mFractal.setXMin(Double.parseDouble(args[1]));
				mFractal.setXMax(Double.parseDouble(args[2]));
				mFractal.setYMin(Double.parseDouble(args[3]));
				mFractal.setYMax(Double.parseDouble(args[4]));
				mFractal.setIterations(Integer.parseInt(args[5]));
			}
		} catch (Exception e) { // handling the error of trying to convert a character which is not a number
								// into double or integer
			System.out.println("Only enter numbers after the 1st argument");
			System.exit(0);
		}
		return mFractal;
	}

	/*********************************************************************************************************
	 * Argument Handling
	 ********************************************************************************************************/
	private static boolean isValid(String[] args) {
		String type;

		if (args.length > 0) {
			type = args[0];
		} else {
			return handleError("Enter arguments"); // if there are no arguments display error message
		}
		// Display the error message if the argument length is 1 but 1st argument is
		// neither MANDELBROT nor JULIA
		if ((args.length == 1) && (!((type.equalsIgnoreCase(MANDELBROT)) || (type.equalsIgnoreCase(JULIA))))) {
			return handleError("Enter fractal set");
			// Display the error message if the argument length is 4 but 1st argument is not
			// JULIA
		} else if (args.length == 4 && !(type.equalsIgnoreCase(JULIA))) {
			return handleError("Invalid arguments");
			// Display the error message if the argument length is 5 or 6 but 1st argument
			// is not MANDELBROT
		} else if (!type.equalsIgnoreCase(MANDELBROT) && (args.length == 5 || args.length == 6)) {
			return handleError("Invalid arguments");
			// Display the error message if the 1st argument is MANDELBROT but argument
			// length is not 1 or 5 or 6
		} else if (type.equalsIgnoreCase(MANDELBROT) && !(args.length == 1 || args.length == 5 || args.length == 6)) {
			return handleError("Invalid number of arguments");
			// Display the error message if the 1st argument is JULIA but argument length is
			// not 1 or 4
		} else if (type.equalsIgnoreCase(JULIA) && !(args.length == 1 || args.length == 4)) {
			return handleError("Invalid number of arguments");
		} else {
			return true;
		}
	}

	/**************************************************************************************************************
	 * MEthod to print error messages
	 * 
	 * @param errorMessage
	 * @return
	 */
	private static boolean handleError(String errorMessage) {
		System.err.println("Error : " + errorMessage);
		System.out.println("USAGE : java Fractal Mandelbrot ");
		System.out.println("        java Fractal Mandelbrot xMin xMax yMin yMax ");
		System.out.println("        java Fractal Mandelbrot xMin xMax yMin yMax Iterations");
		System.out.println("        java Fractal Julia ");
		System.out.println("        java Fractal Julia cons_real cons_img ");
		System.exit(0);
		return false;
	}

}
