package connectFour;

import connectFour.ComPlayer;
import java.util.*;

public class GameBoardTest {

	public static void main(String[] args) {
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

				if (Checker.winTest(playBoard.returnBoard()) == 0) {
					int loc;
					do {
						loc = AIPlayer.placePiece(playBoard.returnBoard());
					} while(!playBoard.placePiece(loc));
					System.out.println("Computer Player placing at: " + loc);
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
