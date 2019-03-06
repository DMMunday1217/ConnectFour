package gameBoard;
import gameBoard.ComPlayer;
import java.util.*;

public class GameBoardTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean compPlayer = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Computer Player (y/n)? ");
		compPlayer = (sc.next().equalsIgnoreCase("y"));
		if (compPlayer) {
			
			Board playBoard = new Board();
			ComPlayer AIPlayer = new ComPlayer(true);
			playBoard.printBoard();
			do {
				int spotToPlace = sc.nextInt();

				playBoard.placePiece(spotToPlace);

				System.out.println("");

				if(Checker.winTest(playBoard.returnBoard()) == 0) {
					playBoard.placePiece(AIPlayer.placePiece(playBoard.returnBoard()));
				}
				
				playBoard.printBoard();

				System.out.println("");

			} while (Checker.winTest(playBoard.returnBoard()) == 0 && playBoard.returnTurns() < 42);

			System.out.println("PLAYER " + Checker.winTest(playBoard.returnBoard()) + " WINS!");
			sc.close();
			
		} else if (!compPlayer) {
			Board playBoard = new Board();
			playBoard.printBoard();
			do {
				int spotToPlace = sc.nextInt();

				playBoard.placePiece(spotToPlace);

				System.out.println("");

				playBoard.printBoard();

				System.out.println("");

			} while (Checker.winTest(playBoard.returnBoard()) == 0 && playBoard.returnTurns() < 42);

			System.out.println("PLAYER " + Checker.winTest(playBoard.returnBoard()) + " WINS!");
			sc.close();
		}
	}
}