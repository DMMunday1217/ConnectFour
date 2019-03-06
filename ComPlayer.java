package connectFour;

import java.util.*;

/**
 * A Computer Player Object for connect4
 * 
 * @author Luke Turczynski
 *
 */
public class ComPlayer {
	/**
	 * difficulty level. false if easy, true if hard
	 */
	private boolean hardMode;
	/**
	 * height of a game board
	 */
	private static final int HEIGHT = 6;
	/**
	 * width of a game board
	 */
	private static final int WIDTH = 7;
	/**
	 * game board for selecting piece placement
	 */
	int[][] b = new int[WIDTH][HEIGHT];

	/**
	 * constructs a new computer player object
	 * 
	 * @param hardMode difficulty level of the bot. false if easy. true if hard
	 */
	public ComPlayer(boolean hardMode) {
		this.hardMode = hardMode;
	}

	/**
	 * 
	 * @param board the game board with 0's at empty spaces, 1's at the human player
	 *              spaces, and 2's and com player's spaces
	 * @return the x location of the placed piece
	 */
	public int placePiece(int[][] board) {
		b = board;
		Random rand = new Random();
		if (hardMode) {
			// piece placement for a hard bot
			if (this.findPlace() != -1) {
				return this.findPlace() + 1;
			} else {
				return rand.nextInt(7) + 1;
			}
		} else {
			// piece placement for an easy bot
			return rand.nextInt(7) + 1;
		}
	}

	private int findPlace() {
		for (int y = 0; y < HEIGHT; y++) { // checks horizontally from top to bottom
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x][y] == b[x + 1][y] && b[x][y] == b[x + 2][y] && b[x][y] != 0 && b[x + 3][y] == 0
						&& (y + 1 < HEIGHT && b[x + 3][y + 1] == 0)) {
					return x + 3;
				}
			}
		}
		for (int y = 0; y < HEIGHT - 3; y++) { // checks vertically from left to right
			for (int x = 0; x < WIDTH; x++) {
				if (b[x][y] == b[x][y + 1] && b[x][y] == b[x][y + 2] && b[x][y] != 0 && b[x][y + 3] == 0) {
					return y + 3;
				}
			}
		}
		for (int y = 0; y < HEIGHT - 3; y++) { // checks diagonally left to right, top to bottom
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x][y] == b[x + 1][y + 1] && b[x][y] == b[x + 2][y + 2] && 0 == b[x + 3][y + 3] && b[x][y] != 0
						&& (y + 4 < HEIGHT && b[x + 3][y + 4] == 0)) {
					return x + 3;
				}
			}
		}
		for (int y = 0; y < HEIGHT - 3; y++) { // checks diagonally right to left, top to bottom
			for (int x = WIDTH - 1; x > WIDTH - 4; x--) {
				if (b[x][y] == b[x - 1][y + 1] && b[x][y] == b[x - 2][y + 2] && b[x - 3][y + 3] == 0 && b[x][y] != 0) {
					return x - 3;
				}
			}
		}
		for (int y = HEIGHT - 1; y > HEIGHT - 3; y--) { // checks diagonally left to right, bottom to top
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x][y] == b[x + 1][y - 1] && b[x][y] == b[x + 2][y - 2] && 0 == b[x + 3][y - 3] && b[x][y] != 0
						&& b[x + 3][y - 2] != 0) {
					return x + 3;
				}
			}
		}
		for (int y = HEIGHT - 1; y > HEIGHT - 3; y--) { // checks diagonally right to left, bottom to top
			for (int x = WIDTH - 1; x > WIDTH - 4; x--) {
				if (b[x][y] == b[x - 1][y - 1] && b[x][y] == b[x - 2][y - 2] && 0 == b[x - 3][y - 3] && b[x][y] != 0
						&& b[x - 3][y - 2] != 0) {
					return x - 3;
				}
			}
		}
		return -1;
	}

}
