package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/* 
 * This class reads the image file and exports the image in png format for the user to the "output" folder
 * */

public class Image {
	private Scanner scan = new Scanner(System.in); // scanner for user to input requested info
	private BufferedImage inputImage; // input image, this is set as the original image which the user inputs
	private BufferedImage outputImage; // output image, set after each edit
	private String filePath; // file path of input image
	private String expFilePath; // file path of output image

	// return input image
	public BufferedImage getInputImage() {
		return inputImage;
	}

	// set input image
	public void setInputImage(BufferedImage inputImage) {
		this.inputImage = inputImage;
	}

	// return output image
	public BufferedImage getOutputImage() {
		return outputImage;
	}

	// set output image
	public void setOutputImage(BufferedImage outputImage) {
		this.outputImage = outputImage;
	}

	// return file name of image
	public String getFilePath() {
		return filePath;
	}

	// read the image in from the "png" folder. The user is prompted to input the file name without its extension
	public void readImage() throws IOException {
		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
		System.out.println("\n[INFO] Enter image file path below (e.g. <../png/bridge-rgb.png>) >>");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		this.filePath = scan.next();

		try {
			// read image -> BufferedImage
			this.inputImage = ImageIO.read(new File(filePath));
			System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("\n[INFO] Image selected: " + filePath);
			System.out.println("[INFO] Image meta-data for \"" + filePath + ": \n" + inputImage);// This shows useful meta-data about the chosen image.
			System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
			System.out.println(ConsoleColour.RESET);
		} catch (Exception e) {
			System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("[INFO] Invalid selection, please try again.");
			System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
			System.out.println(ConsoleColour.RESET);
			e.printStackTrace();
		}
	}

	// write the image to the "output" folder. The user is prompted to name exported image file name
	public void exportImage() throws IOException {
		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
		System.out.println(
				"\n[INFO] Please enter the file path and name of the image below for export (e.g. <../output/out>) >>");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		
		this.expFilePath = scan.next();
		
		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("[INFO] Exporting image...");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
			
		try {
			// write image to "output" folder
			ImageIO.write(outputImage, "png", new File(expFilePath + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			System.out.println("[INFO] Invalid file path or name!");
			System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
			System.out.println(ConsoleColour.RESET);
		}

		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
		System.out.println("\n[INFO] Image \"" + expFilePath + ".png\"" + " exported.");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);

	}

}
