package toprow;
//need to import the dpad

public class TopRow {
	private int numCols = 7;
	private int[] colors = new int[numCols];
	private int currentPos = 0;
	
	public TopRow() {
		
		for (int i = 0; i < numCols; i++) {
			colors[i] = 0;
		}
	}
	
	public int getPos()
	{
		return currentPos;
	}
	
	public int[] getVals() {
		return colors;
		
	}
	
	public void moveRight() {
		if (currentPos < numCols - 1)
		{
			colors[currentPos] = 0;
			currentPos++;
			colors[currentPos] = 1;
	
		}
	}
	public void moveLeft() {
		if (currentPos > 0)
		{
			colors[currentPos] = 0;
			currentPos--;
			colors[currentPos] = 1;
		}
	}
	
}
