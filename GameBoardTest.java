package gameBoard;
import java.util.*;

public class GameBoardTest{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Board playBoard = new Board();
		Scanner sc = new Scanner(System.in);
		do {

			int spotToPlace = sc.nextInt();
			
			playBoard.placePiece(spotToPlace);
			
			System.out.println("");

			playBoard.printBoard();

			System.out.println("");

		} while (Checker.winTest(playBoard.returnBoard()) == 0 && playBoard.returnTurns() < 42);

		System.out.println("PLAYER " + Checker.winTest(playBoard.returnBoard()) + " WINS!");

	}

}
