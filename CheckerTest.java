import java.util.Random;

/**
 * a tester for my checker class
 * 
 * @author Luke Turczynski
 *
 */
public class CheckerTest {
	private static final int WIDTH = 7;
	private static final int HEIGHT = 6;

	public static void main(String[] args) {
		Random rand = new Random();
		int dub = 0;
		int[][] b = new int[WIDTH][HEIGHT];
		// Fills array with 0s
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				b[x][y] = 0;
			}
		}
		// Randomly fills the array with 1s or 2s left to right, bottom to top. Stopping
		// when a connect four is made
		for (int y = HEIGHT - 1; y >= 0; y--) {
			for (int x = 0; x < WIDTH; x++) {
				b[x][y] = rand.nextInt(2) + 1;
				dub = Checker.winTest(b);
				if (dub != 0) {
					break;
				}
			}
		}
		// Prints the board
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				System.out.printf("%d ", b[x][y]);
			}
			System.out.println("");
		}
	}
}
