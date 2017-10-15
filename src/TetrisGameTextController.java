import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TetrisGameTextController plays Tetris from the command line, printing the
 * game after each move.
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisGameTextController {
	/** Line break **/
	private static final String LINE_BREAK = "----------\n";
	/** Tetris game logic **/
	private TetrisGame game;
	/** Tetris game view **/
	private TetrisBoardTextView view;

	/**
	 * Constructor: starts the game when run
	 */
	public TetrisGameTextController() {
		resetGame();
	}

	/**
	 * Print text view of the game.
	 */
	private void refreshDisplay() {
		// First, print the number of tetrises cleared
		System.out.println("Number of lines cleared: " + game.getNumTetrises());

		// Second, print the number of lines cleared
		System.out.println("Number of Tetrises cleared: " + game.getNumLines());

		// Then, print the tetris board
		System.out.print(LINE_BREAK + view.getBoardString() + LINE_BREAK);
		
		//Prompt user for next move
		System.out.println("Please enter a move (l,r,d,z,x) or type 'Quit' to end.");
	}

	/**
	 * r: right l: left d: down z: cw x: ccw
	 * 
	 * @param move
	 *            move type
	 */
	private void moveEntered(String move) {
		if (move.equals("r"))
			game.attemptMove(TetrisGame.RIGHT);
		else if (move.equals("l"))
			game.attemptMove(TetrisGame.LEFT);
		else if (move.equals("d"))
			game.attemptMove(TetrisGame.DOWN);
		else if (move.equals("z"))
			game.attemptMove(TetrisGame.CW);
		else if (move.equals("x"))
			game.attemptMove(TetrisGame.CCW);
	}

	/**
	 * Resets game board
	 */
	private void resetGame() {
		game = new TetrisGame();
		view = new TetrisBoardTextView(game.getTetrisBoard());
		System.out.println("Welcome to Tetris! Type 'help' for instructions!");
		refreshDisplay();

		// Starts game
		readInput();
	}

	/**
	 * Get input from the user, looping until the user types "Quit" Handles
	 * unexpected values with exceptions
	 **/
	private void readInput() {
		// Buffered readers are used to read text input from the command line
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Used to hold what the user inputs
		String line = "";

		// BufferedReaders require a try/catch block, to prevent exceptions from
		// crashing your program
		try {
			// Try to read a line
			// This function potentially throws an IOException
			line = in.readLine();

			// Loop until the user types "quit"
			while (!line.toLowerCase().equals("quit")) {
				// If the user types "help", print out help
				if (line.toLowerCase().equals("help")) {
					System.out.println("Using the keyboard, you can adjust where and how the Tetriminos fall. "
							+ "By pressing the 'l' and 'r' keys, you can slide the falling Tetrimino from side to side. "
							+ "You can’t slide a Tetrimino past the edge of the Matrix. "
							+ "By pressing the 'z' and 'x' key, you can rotate the Tetrimino 90 degrees clockwise or counter-clockwise. "
							+ "The Tetrimino will lock down as soon as it lands on the skirt of the gameboard. "
							+ "At that point, the next Tetrimino will begin to fall.");
				}
				// Otherwise, check to make sure their input is valid
				else {
					if (line.equals("l") || line.equals("r") || line.equals("d") || line.equals("z")
							|| line.equals("x")) {
						moveEntered(line);
						refreshDisplay();
					} else {
						System.out.println(
								"Error: Input must be r for right, l for left, d for down, z for cw, x for ccw. "
										+ "Enter help for help.");
					}
				}
				// Ask for the next input
				line = in.readLine();
			}
		}
		// catch I/O exception
		catch (IOException ioenfe) {
			// inform user of problem
			System.out.println("Error: IOException. ");
		}
	}
}
