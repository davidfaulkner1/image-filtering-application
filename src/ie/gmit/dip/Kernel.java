package ie.gmit.dip;

public enum Kernel {

	// kernel matrix 2D arrays for filters
	IDENTITY (new double[][]{{0, 0, 0},{0, 1, 0},{0, 0, 0}}),
	EDGE_DETECTION_1 (new double[][]{{-1, -1, -1},{-1, 8, -1},{-1, -1, -1}}),
	EDGE_DETECTION_2 (new double[][]{{1, 0, -1},{0, 0, 0},{-1, 0, 1}}),
	LAPLACIAN (new double[][]{{0, -1, 0},{-1, 4, -1},{0, -1, 0}}),
	SHARPEN (new double[][]{{0, -1, 0},{-1, 5, -1},{0, -1, 0}}),
	VERTICAL_LINES (new double[][]{{-1, 2, -1},{-1, 2, -1},{-1, 2, -1}}),
	HORIZONTAL_LINES (new double[][]{{-1, -1, -1},{2, 2, 2},{-1, -1, -1}}),
	DIAGONAL_45_LINES (new double[][]{{-1, -1, 2},{-1, 2, -1},{2, -1, -1}}),
	BOX_BLUR (new double[][]{{0.111, 0.111, 0.111},{0.111, 0.111, 0.111},{0.111, 0.111, 0.111}}),
	SOBEL_HORIZONTAL (new double[][]{{-1, -2, -1},{0, 0, 0},{1, 2, 1}}),
	SOBEL_VERTICAL (new double[][]{{-1, 0, 1},{-2, 0, 2},{-1, 0, 1}}),
	SCHARR_VERTICAL (new double[][]{{3, 0, -3},{10, 0, -10},{3, 0, -3}}),
	SCHARR_HORIZONTAL (new double[][]{{3, 10, 3},{0, 0, 0},{-3, 10, -3}});
	
	//internal data
	private double[][] kernel;
	
	//constructor
	Kernel(double[][] k) {
		this.kernel = k;
	}
	
	//get kernel method
	public double[][] getKernel() { 
		return this.kernel;
	}
	
}
