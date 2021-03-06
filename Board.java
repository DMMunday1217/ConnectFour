package ConnectFour;

public class Board {

	/**
	 * Tracks the state of the board, where the pieces are, and which pieces are
	 * there.
	 */
	private int[][] gameBoard = new int[WIDTH][HEIGHT];

	/**
	 * Constant height of the playing board, use j during for loops
	 */
	private static final int HEIGHT = 6;

	/**
	 * Constant width of the playing board, use i during for loops
	 */
	private static final int WIDTH = 7;

	/**
	 * tracks how many turns have been played, use method with this to find whose
	 * turn it is.
	 */
	private int currentTurnsPlayed = 0;

	/**
	 * Constructs the board, fills array with 0's to indicate the board is empty.
	 */
	public Board() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				gameBoard[i][j] = 0;
			}
		}
	}

	/**
	 * Places piece of current player where they choose.
	 * 
	 * @param columnNum the number of column that they chose to place the piece
	 */
	public boolean placePiece(int columnNum) {
		columnNum--;
		boolean placedThisTurn = false;
		boolean columnFull = false;
		if (gameBoard[columnNum][0] > 0) {
			columnFull = true;
			System.out.println("Please choose new column");
			return false;
		}
		for (int j = HEIGHT - 1; j >= 0; j--) {
			if (gameBoard[columnNum][j] == 0 && columnFull == false) {
				gameBoard[columnNum][j] = whoseTurn(currentTurnsPlayed);
				placedThisTurn = true;
				break;
			}
		}
		if (placedThisTurn == true && columnFull == false) {
			currentTurnsPlayed++;
			return true;
		}
		return false;

	}

	/**
	 * Returns the turn of current player, 1 is red, 2 is blue.
	 * 
	 * @param currentTurnsPlayed The number of turns played so far.
	 * @return returns the turn of current player.
	 */
	public int whoseTurn(int currentTurnsPlayed) {
		return (currentTurnsPlayed % 2 + 1);
	}

	public int[][] returnBoard() {
		return gameBoard;
	}

	public void printBoard() {

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				System.out.print(gameBoard[j][i] + "   ");
			}
			System.out.println("");
		}
	}

	public int returnTurns() {
		return currentTurnsPlayed;
	}
}
