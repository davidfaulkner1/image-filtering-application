package ie.gmit.dip;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

/*
 * This class contains all methods to edit selected image
 */

public class EditImage {
	private Scanner scan; // scanner for user to input requested info
	
	// constructor for user input scanner
	public EditImage() {
		scan = new Scanner(System.in);
	}

	
	// this method
	public BufferedImage createFilter(BufferedImage img, String filter) throws IOException {
		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("\n[INFO] Render image");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
			
		/*
		 * code referenced from:
		 * http://tech.abdulfatir.com/2014/05/kernel-image-processing.html?m=1
		 */

		double[][] kernel = new double[3][3]; // initialise 2D array for kernel matrix

		// switch case to select filter depending on filter variable called
		switch (filter) {
			case "identity" -> kernel = Kernel.IDENTITY.getKernel();
			case "edge detection 1" -> kernel = Kernel.EDGE_DETECTION_1.getKernel();
			case "edge detection 2" -> kernel = Kernel.EDGE_DETECTION_2.getKernel();
			case "laplacian" -> kernel = Kernel.LAPLACIAN.getKernel();
			case "sharpen" -> kernel = Kernel.SHARPEN.getKernel();
			case "vertical lines" -> kernel = Kernel.VERTICAL_LINES.getKernel();
			case "horizontal lines" -> kernel = Kernel.HORIZONTAL_LINES.getKernel();
			case "diagonal 45" -> kernel = Kernel.DIAGONAL_45_LINES.getKernel();
			case "box blur" -> kernel = Kernel.BOX_BLUR.getKernel();
			case "sobel horizontal" -> kernel = Kernel.SOBEL_HORIZONTAL.getKernel();
			case "sobel vertical" -> kernel = Kernel.SOBEL_VERTICAL.getKernel();
			case "scharr vertical" -> kernel = Kernel.SCHARR_VERTICAL.getKernel();
			case "scharr horizontal" -> kernel = Kernel.SCHARR_HORIZONTAL.getKernel();
			default -> System.out.println("[ERROR] Invalid input selection.");
		}

		float sumElements = 0.0f; // sum of elements in kernel matrix

		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("\n[INFO] The Kernel Matrix for " + filter + " is:\n");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		
		// loop through each element in kernel matrix and print it to console
		System.out.println(ConsoleColour.YELLOW_BRIGHT); // console text colour start
		for (int i = 0; i < kernel.length; i++) { // for each row 
			for (int j = 0; j < kernel[i].length; j++) { // for each element in row
				System.out.print("\t" + kernel[i][j]);
				sumElements += kernel[i][j];
			}
			System.out.println();
		}
		System.out.print(ConsoleColour.YELLOW_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);

		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("\n[INFO] Sum of matrix elements: " + sumElements);
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		
		// initialise width and height of image to edit using BufferedImage method
		int width = img.getWidth();
		int height = img.getHeight();

		BufferedImage output = new BufferedImage(width, height, img.getType()); // initialise and set new edited pic as output

		//iterate through each pixel in image against the kernel matrix and produces new pixel depending on surrounding pixels and kernel matrix structure
		for (int y = 0; y < height; y++) { // iterate through each row in image
			for (int x = 0; x < width; x++) { // iterate through each pixel in row
				float red = 0f, green = 0f, blue = 0f; // initialise rgb values

				try {
					//iterate through kernel matrix and pixels in image
					for (int i = 0; i < kernel.length; i++) {
						for (int j = 0; j < kernel[i].length; j++) {
							/*
							 * determine the x and y coordinates of the pixel to be multiplied with current kernel element use modulus (%) symbol in the case of edge pixels, where it takes the pixels from the opposite side into account
							 */
							int imgX = (x - kernel.length / 2 + i + width) % width;
							int imgY = (y - kernel[i].length / 2 + j + height) % height;

							int rgb = img.getRGB(imgX, imgY); // get rgb values of pixel at given coordinates
							int r = (rgb >> 16) & 0xff; // red value
							int g = (rgb >> 8) & 0xff; // green value
							int b = (rgb) & 0xff; // blue value

							// multiply the rgb values with the kernel element at (i, j) and add this to the variables red, green and blue
							red += (r * kernel[j][i]);
							green += (g * kernel[j][i]);
							blue += (b * kernel[j][i]);

						}
					}

					int redOut, greenOut, blueOut; // initialise output rgb values
					// truncate the values of the outputted variables of red, green and blue to 0 and 255 if the values go outside of this range
					redOut = Math.min(Math.max((int) (red * 1.0), 0), 255);
					greenOut = Math.min(Math.max((int) (green * 1.0), 0), 255);
					blueOut = Math.min(Math.max((int) (blue * 1.0), 0), 255);
					// pixel is written to output image
					output.setRGB(x, y, new Color(redOut, greenOut, blueOut).getRGB());
				} catch (Exception e) {
					continue;
				}
			}
		}

		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("\n[INFO] Rendering complete.");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);

		return output; // Export edited image
	}

	
	//convert image from rgb to grayscale
	public BufferedImage toGrayscale(BufferedImage img) {
		/*
		 * code referenced from: 
		 * https://dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-grayscale-image-in-java
		 */
		
		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("[INFO] Convert coloured image to grayscale");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		
		// initialise width and height of image to edit using BufferedImage method
		int width = img.getWidth();
		int height = img.getHeight();

		BufferedImage output = new BufferedImage(width, height, img.getType()); // set new edited pic as output

		for (int y = 0; y < height; y++) { // iterate through each row in image
			for (int x = 0; x < width; x++) { // iterate through each pixel in row
				int pixel = img.getRGB(x, y); // get rgb values at (x, y) cooradinates and initialise as "pixel" variable

				int alpha = (pixel >> 24) & 0xff; // alpha value
				int red = (pixel >> 16) & 0xff; // red value
				int green = (pixel >> 8) & 0xff; // green value
				int blue = (pixel) & 0xff; // blue value

				int avg = (red + green + blue) / 3; // average of rgb values
				pixel = (alpha << 24) | (avg << 16) | (avg << 8) | avg; // replace rgb values with average value

				output.setRGB(x, y, pixel); // set new pixel in output image to be the pixel value at coordinates (x, y)
			}
		}

		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("[INFO] Rendering complete.");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		
		return output; //return output complete image
	}

	
	// crop image method
	public BufferedImage cropImage(BufferedImage img) throws IOException {
		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("\n[INFO] Crop image");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		
		/*
		 * code referenced from: 
		 * https://gist.github.com/madan712/3602189
		 */
		System.out.println(ConsoleColour.PURPLE_BRIGHT); // console text colour start
			System.out.println("\nOriginal image dimension: " + img.getWidth() + "(w) x " + img.getHeight() + "(h)");
			System.out.println("\nSelect image width: ");
			int width = Integer.parseInt(scan.next()); // allow user to select image width
	
			System.out.println("\nSelect image height: ");
			int height = Integer.parseInt(scan.next()); // allow user to select image height
	
			System.out.println("\nSelect the x coordinate of the upper left hand corner of the cropped image: ");
			int x = Integer.parseInt(scan.next()); // allow user to select x coordinate
	
			System.out.println("\nSelect the y coordinate of the upper left hand corner of the cropped image: ");
			int y = Integer.parseInt(scan.next()); // allow user to select y coordinate
	
			BufferedImage croppedImg = img.getSubimage(x, y, width, height);
			System.out.println("\nCropped image dimension: " + croppedImg.getWidth() + "x" + croppedImg.getHeight());
		System.out.print(ConsoleColour.PURPLE_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		return croppedImg; // return cropped image 
	}

}
