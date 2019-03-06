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
			if(earlyGame() != -1) {
				//if the middle 3 in the bottom row are open, it places it in one of those spaces, prioritizing the center one. 
				//This keeps a player from winning ny putting pieces in the loactions 4, 3, 2, and then 1.
				return earlyGame();
			} else if (findPlace() != -1) {
				//checks for 3 in a rows, if any are found, it places a piece in in the fourth spot
				return findPlace() + 1;
			} else if (checkGaps() != -1) {
				//in the case of a row going 1011, places a piece in the spot that is empty.
				//This occurs regardless of which team has a piece there
				return checkGaps() + 1;
			} else {
				//if the bot can't find a better location, it places it like an easy bot
				return rand.nextInt(7) + 1;
			}
		} else {
			// piece placement for an easy bot
			return rand.nextInt(7) + 1;
		}
	}
	
	private int earlyGame() {
		if(b[3][0] == 0) {
			return 4;
		} else if(b[2][0] == 0) {
			return 3;
		}else if(b[4][0] == 0) {
			return 5;
		}else {
			return -1;
		}
	}
	
	private int checkGaps() {
		for (int y = 0; y < HEIGHT; y++) {// checks 3rd spot in a set of 4
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x][y] == b[x + 1][y] && b[x][y] == b[x + 3][y] && b[x][y] != 0) {
					if (y == 0 || b[x + 2][y - 1] != 0) {
						return x + 2;
					}
				}
			}
		}
		for (int y = 0; y < HEIGHT; y++) {// checks 2nd spot in a set of 4
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x][y] == b[x + 2][y] && b[x][y] == b[x + 3][y] && b[x][y] != 0) {
					if (y == 0 || b[x + 1][y - 1] != 0) {
						return x + 1;
					}
				}
			}
		}
		for (int y = 0; y < HEIGHT; y++) {// checks 1st spot in a set of 4
			for (int x = 0; x < WIDTH - 3; x++) {
				if (b[x + 1][y] == b[x + 2][y] && b[x][y] == b[x + 3][y] && b[x][y] != 0) {
					if (y == 0 || b[x][y - 1] != 0) {
						return x;
					}
				}
			}
		}
		return -1;
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
