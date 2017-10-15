/**
 * The TetrisGame class maintains a Tetris game.
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisGame {
	/** Right move **/
	public static final int RIGHT = 1;
	/** Left move **/
	public static final int LEFT = 2;
	/** Down move **/
	public static final int DOWN = 3;
	/** Rotate clockwise move **/
	public static final int CW = 4;
	/** Rotate counter-clockwise move **/
	public static final int CCW = 5;

	/** Number of lines so far **/
	private int numLines;
	/** Number of lines so far **/
	private int numTetrises;

	/** Tetris board **/
	private TetrisBoard tetrisBoard;

	/**
	 * Constructor
	 */
	public TetrisGame() {
		resetGame();
	}

	/**
	 * Resets current game
	 */
	public void resetGame() {
		tetrisBoard = new TetrisBoard();
		numLines = 0;
		numTetrises = 0;
	}

	/**
	 * Try to move the current piece with RIGHT, LEFT, DOWN, CW, CCW
	 * 
	 * @param moveType move type
	 */
	public void attemptMove(int moveType) {
		switch (moveType) {
		case RIGHT:
			tetrisBoard.moveRight();
			break;
		case LEFT:
			tetrisBoard.moveLeft();
			break;
		case DOWN:
			//If the Tetris piece cannot be moved down further, land it and end round immediately
			if (!tetrisBoard.moveDown()) {
				tetrisBoard.landPiece();
				endRound();
			}
			break;
		case CW:
			tetrisBoard.rotateCW();
			break;
		case CCW:
			tetrisBoard.rotateCCW();
			break;
		default:
			break;
		}
	}

	/**
	 * Performed when a piece cannot move down anymore. Ends the round by
	 * checking for newly formed lines and adding a new piece
	 */
	private void endRound() {
		// Check for newly formed lines
		numLines += tetrisBoard.numberOfFormedLines();

		// Add a new piece
		tetrisBoard.addNewPiece();
		numTetrises++;
	}

	/**
	 * @return the numLines
	 */
	public int getNumLines() {
		return numLines;
	}

	/**
	 * @return the numTetrises
	 */
	public int getNumTetrises() {
		return numTetrises;
	}

	/**
	 * @return the tetrisBoard
	 */
	public TetrisBoard getTetrisBoard() {
		return tetrisBoard;
	}
}
