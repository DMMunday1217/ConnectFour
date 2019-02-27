package toprow;
//need to import the dpad


/**
* Meant to be used with the dpad
* Top row of the Connect 4 board, equipped w/ LED that
* is used to show the user where they're about to drop
* their piece
* When the dpad is pushed right or left this should move 
* an LED over to the left or right.
*/ 
public class TopRow {
	/**
	* Stores the number of columns used for the top row
	* For our purposes, this will always be 7.
	*/
	private int numCols = 7;
	
	/**
	* Stores an array of integer values that should be
	* used to store the color value of the LED
	*/
	private int[] colors = new int[numCols];
	
	/**
	* Stores the current position of the blinking light
	*/
	private int currentPos = 0;
	
	/**
	* Makes a new TopRow object with all colors set to zero.
	*/
	public TopRow()
	{
		for (int i = 0; i < numCols; i++) {
			colors[i] = 0;
		}
	}
	
	/**
	* Returns the position that the blinking light is in
	*/ 
	public int getPos() {
		return currentPos;
	}
	
	/**
	* Returns the array of colors
	*/ 
	public int[] getVals() {
		return colors;	
	}
	
	/**
	* Moves the position of the LED over right by one
	*/
	public void moveRight() {
		if (currentPos < numCols - 1)
		{
			colors[currentPos] = 0;
			currentPos++;
			colors[currentPos] = 1;
		}
	}
	
	/**
	* Moves the position of the LED left by one
	*/
	public void moveLeft() {
		if (currentPos > 0)
		{
			colors[currentPos] = 0;
			currentPos--;
			colors[currentPos] = 1;
		}
	}
}
