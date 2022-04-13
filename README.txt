Title: OOP Project Aug-2021
Author: David Faulkner G00299507
College: GMIT - Dept. Computer Science & Applied Physics
Course: H.Dip in Science (Software Development)

_________________________________________________________________________________________________________________________________________________________

Project Description:

The program is a command-line image editing Java application. It reads in PNG images and filters the image using a convolution kernel. The user can choose an image, edit it with a selection of filters and/or a cropping tool, and then export the image to a destination set by the user with a file name of their choice.

Features of the program:
	1. Filters:
		1) Identity
		2) Edge Detection 1
		3) Edge Detection 2
		4) Laplacian
		5) Sharpen
		6) Vertical Lines
		7) Horizontal Lines
		8) Diagonal 45 Lines
		9) Box Blur
		10) Sobel Horizontal
		11) Sobel Vertical
		12) Scharr Vertical
		13) Scharr Horizontal
		14) Grayscale
	
	Note: 14) Grayscale converts an RGB image to a grayscale image.
	
	2. Many filters can be used on the same image. The program allows the user to select a filter on an image, and then select multiple filters without overwriting the edit each time. Cropping can also be used in conjunction with filters.
	
	3. Cropping can be performed on the image. The user selects the desired width and height of the image, then selects where the top left-hand corner should be located by using (x, y) coordinates.
	
	4. The application uses the modulus operator when the pixels are located along the edges of the image. The pixels at the opposite side of the image are taken into the equation and thus cropping the image is not required after using filters.
	
	4. The user uses BufferedImage to read in an image and they can then rename the output image and export it to a destination of their choice.
	
	5. The application loops through each pixel of the image and traverses through a convolution kernel using a 2D array structure to get the result of the edited pixel. 
	
	6. The Kernel class is converted to enums as they are constants.
_________________________________________________________________________________________________________________________________________________________

Program Menu:


*****************************************************
** GMIT - Dept. Computer Science & Applied Physics **
**                                                 **
**           Image Filtering System V0.1           **
**     H.Dip in Science (Software Development)     **
**                                                 **
**                  David Faulkner                 **
*****************************************************

1) Enter Image File Path
2) Select a Filter
3) Crop Image
4) Export Image
5) Quit

Select Option [1-5]>


_________________________________________________________________________________________________________________________________________________________

How to use:

1. Run the application in the command-line after compilation by using the following syntax from the src directory of the zip file:
		<java ie.gmit.dip.Runner>
		

2. Enter Image File Path

Enter the relative or absolute file path of the image in the command-line to select an image to edit. 

	Examples:
		Relative: <../png/bridge-rgb.png>
		Absolute: <C:/Users/david/eclipse-workspace/ConvolutionKernel/png/bridge-rgb.png>

Note: in this case, the images to edit are located in the "png" folder located in the main zip folder.


3. Select a Filter

Select between 14 different filters to use on an image by inputting a number from 1-14.


4. Crop Image

Crop the image by selecting the width and height of the desired image and select where the top left-hand corner should be located by using (x, y) coordinates.


5. Export Image

Enter the relative or absolute file path of the image in the console to rename the image and select the destination to export to. 

	Examples:
		Relative: <../output/out>
		Absolute: <C:/Users/david/eclipse-workspace/ConvolutionKernel/output/out>
		
Note: in this case, the edited images are exported to the "output" folder located in the main zip folder.


6. Quit

Quitting the program will cause the menu loop and program to terminate.