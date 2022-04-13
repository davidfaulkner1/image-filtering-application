package ie.gmit.dip;

import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.*;

/*
 * This class displays all menu options and the user can select what tasks to complete on a chosen image
 */

public class Menu {
	private Scanner scan; // scanner for user to input requested info
	private boolean keepRunning = true; // boolean to keep showing the menu after user chooses an option and completes task
	private Image i = new Image(); // Image object set up to call read and write image methods
	private EditImage edit = new EditImage(); // EditImage object to call filter and image editing options

	// constructor for user input scanner
	public Menu() {
		scan = new Scanner(System.in);
	}

	// show menu and allow user to select options
	public void start() throws IOException {
		// while loop to keep menu running until user chooses to quit
		while (keepRunning) {
			showMenu();
			int userInput = Integer.parseInt(scan.next());
			
			System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
			switch (userInput) {
				case 1 -> i.readImage();
				case 2 -> selectFilter();
				case 3 -> cropImage();
				case 4 -> i.exportImage();
				case 5 -> {
					System.out.println("[INFO] Shutting down, please wait...");
					keepRunning = false;
				}
				default -> System.out.println("[ERROR] Invalid input selection.");
			}
			System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
			System.out.println(ConsoleColour.RESET);
			System.out.println();
		}
	}

	// display menu to console
	private void showMenu() {
		System.out.println(ConsoleColour.CYAN_BOLD_BRIGHT); // console text colour start
		System.out.println("*****************************************************");
		System.out.println("** GMIT - Dept. Computer Science & Applied Physics **");
		System.out.println("**                                                 **");
		System.out.println("**           Image Filtering System V0.1           **");
		System.out.println("**     H.Dip in Science (Software Development)     **");
		System.out.println("**                                                 **");
		System.out.println("**                  David Faulkner                 **");
		System.out.println("*****************************************************");

		// image tagname which is currently in edit mode
		try {
			if (!(i.getInputImage().equals(null))) {
				System.out.println("You are editing: " + i.getFilePath());
			} else {
				System.out.println();
			}
		} catch (Exception e) {

		}

		// menu options
		System.out.println("\n1) Enter Image File Path"); // prompt user for file to edit
		System.out.println("2) Select a Filter"); // all filters available from Kernel class
		System.out.println("3) Crop Image"); // crop image
		System.out.println("4) Export Image"); // write output image to "output" folder
		System.out.println("5) Quit"); // end menu loop
		System.out.println("\nSelect Option [1-5] >>");
		System.out.print(ConsoleColour.CYAN_BOLD_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
	}

	// user is prompted to choose filter to edit image with
	private void selectFilter() throws IOException {
		
		// filter options
		System.out.println(ConsoleColour.GREEN_BRIGHT); // console text colour start
		System.out.println("\n[INFO] Select filter >>");
		System.out.print(ConsoleColour.GREEN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		
		System.out.println(ConsoleColour.CYAN_BRIGHT); // console text colour start
		System.out.println("1) Identity"); // Identity filter should be here
		System.out.println("2) Edge Detection 1");
		System.out.println("3) Edge Detection 2");
		System.out.println("4) Laplacian");
		System.out.println("5) Sharpen");
		System.out.println("6) Vertical Lines");
		System.out.println("7) Horizontal Lines");
		System.out.println("8) Diagonal 45 Lines");
		System.out.println("9) Box Blur");
		System.out.println("10) Sobel Horizontal");
		System.out.println("11) Sobel Vertical");
		System.out.println("12) Scharr Vertical");
		System.out.println("13) Scharr Horizontal");
		System.out.println("14) Grayscale");
		System.out.print(ConsoleColour.CYAN_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
		
		int userInput = Integer.parseInt(scan.next()); // allow user to select filter option

		BufferedImage inputOutput = checkForOutput(); // set "inputOutput" as either the input or output image to edit to allow for merging of multiple edits on the same image

		// switch case tp call createFilter method in EditImage.java class depending on the filter chosen and this equals output image in Image.java class
		System.out.println(ConsoleColour.RED_BRIGHT); // console text colour start
		switch (userInput) {
			case 1 -> i.setOutputImage(edit.createFilter(inputOutput, "identity"));
			case 2 -> i.setOutputImage(edit.createFilter(inputOutput, "edge detection 1"));
			case 3 -> i.setOutputImage(edit.createFilter(inputOutput, "edge detection 2"));
			case 4 -> i.setOutputImage(edit.createFilter(inputOutput, "laplacian"));
			case 5 -> i.setOutputImage(edit.createFilter(inputOutput, "sharpen"));
			case 6 -> i.setOutputImage(edit.createFilter(inputOutput, "vertical lines"));
			case 7 -> i.setOutputImage(edit.createFilter(inputOutput, "horizontal lines"));
			case 8 -> i.setOutputImage(edit.createFilter(inputOutput, "diagonal 45"));
			case 9 -> i.setOutputImage(edit.createFilter(inputOutput, "box blur"));
			case 10 -> i.setOutputImage(edit.createFilter(inputOutput, "sobel horizontal"));
			case 11 -> i.setOutputImage(edit.createFilter(inputOutput, "sobel vertical"));
			case 12 -> i.setOutputImage(edit.createFilter(inputOutput, "scharr vertical"));
			case 13 -> i.setOutputImage(edit.createFilter(inputOutput, "scharr horizontal"));
			case 14 -> i.setOutputImage(edit.toGrayscale(inputOutput));
			default -> System.out.println("[ERROR] Invalid input selection.");
			
		}
		System.out.print(ConsoleColour.RED_BRIGHT); // console text colour end
		System.out.println(ConsoleColour.RESET);
	}
	
	// crop image
	private void cropImage() throws IOException {
		BufferedImage inputOutput = checkForOutput(); // set inputOutput as either the input or output image to edit to allow for merging of multiple edits on the same image
		i.setOutputImage(edit.cropImage(inputOutput));
	}

	// method that checks if there is already an image in edit mode. It allows multiple edits of the image preventing overwrites
	private BufferedImage checkForOutput() {
		// returns either the input or output image depending on if the image has been edited already to allow for merging of edited images
		if (i.getOutputImage() == null) {
			return i.getInputImage(); // if no image has been edited then edit input image
		} else {
			return i.getOutputImage(); // if the image has been edited, then use this for further edits
		}
	}

}
