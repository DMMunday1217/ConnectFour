package gameBoard;

public class Board {

	/**
	 * Tracks the state of the board, where the pieces are, and which pieces are
	 * there.
	 */
	private int gameBoard[][];

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
	 * @param columnNum the number of column that they chose to place the piece
	 */
	public void placePiece(int columnNum) {
		for(int j = 0; j < HEIGHT; j++) {
			if(gameBoard[columnNum][j] == 0) {
				gameBoard[columnNum][j] = whoseTurn(currentTurnsPlayed);
			}
		}
	}
	
	/**
	 * Returns the turn of current player, 1 is red, 2 is blue.
	 * @param currentTurnsPlayed The number of turns played so far.
	 * @return returns the turn of current player.
	 */
	public int whoseTurn(int currentTurnsPlayed) {
		return (currentTurnsPlayed % 2 + 1);
	}
	
	/**
	* Returns the board to be passed and checked for if somebody has won.
	* @return returns current state of the board.
	*/
	public int[][] returnBoard(){
		return gameBoard;
	}
}
