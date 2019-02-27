package ConnectFour;

/**
 * A simple program to check if a game of connect four has been won.
 * 
 * @author Luke Turczynski
 * 
 */
public class Checker {
	/**
	 * Height of the Board
	 */
	private static final int HEIGHT = 6;
	/**
	 * Width of the Board
	 */
	private static final int WIDTH = 7;

	/**
	 * Tests the array values for a completed connect four
	 * 
	 * @param b (two dimensional integer array
	 * @return 0 if the game has not been won, 1 if the first player has won, and 2
	 *         if the second player has won.
	 */
	public static int winTest(int[][] b) {
		for (int y = 0; y < HEIGHT; y++) { // checks horizontally from top to bottom
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x][y] == b[x + 1][y] && b[x][y] == b[x + 2][y] && b[x][y] == b[x + 3][y] && b[x][y] != 0) {
					return b[x][y];
				}
			}
		}
		for (int y = 0; y < HEIGHT - 3; y++) { // checks vertically from left to right
			for (int x = 0; x < WIDTH; x++) {
				if (b[x][y] == b[x][y + 1] && b[x][y] == b[x][y + 2] && b[x][y] == b[x][y + 3] && b[x][y] != 0) {
					return b[x][y];
				}
			}
		}
		for (int y = 0; y < HEIGHT - 3; y++) { // checks diagonally left to right, top to bottom
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x][y] == b[x + 1][y + 1] && b[x][y] == b[x + 2][y + 2] && b[x][y] == b[x + 3][y + 3]
						&& b[x][y] != 0) {
					return b[x][y];
				}
			}
		}
		for (int y = 0; y < HEIGHT - 3; y++) { // checks diagonally right to left, top to bottom
			for (int x = WIDTH - 1; x > WIDTH - 4; x--) {
				if (b[x][y] == b[x - 1][y + 1] && b[x][y] == b[x - 2][y + 2] && b[x][y] == b[x - 3][y + 3]
						&& b[x][y] != 0) {
					return b[x][y];
				}
			}
		}
		for (int y = HEIGHT - 1; y > HEIGHT - 3; y--) { // checks diagonally left to right, bottom to top
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x][y] == b[x + 1][y - 1] && b[x][y] == b[x + 2][y - 2] && b[x][y] == b[x + 3][y - 3]
						&& b[x][y] != 0) {
					return b[x][y];
				}
			}
		}
		for (int y = HEIGHT - 1; y > HEIGHT - 3; y--) { // checks diagonally right to left, bottom to top
			for (int x = WIDTH - 1; x > WIDTH - 4; x--) {
				if (b[x][y] == b[x - 1][y - 1] && b[x][y] == b[x - 2][y - 2] && b[x][y] == b[x - 3][y - 3]
						&& b[x][y] != 0) {
					return b[x][y];
				}
			}
		}
		return 0;
	}
}
