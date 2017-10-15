import java.util.Random;

/**
 * The TetrisBoard class represents the model: a board on which Tetris is
 * played; it maintains the grid (the block matrix) and the current piece.
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisBoard {
	/** Number of row in board **/
	public static final int NUM_ROWS = 18;
	/** Number of columns in board **/
	public static final int NUM_COLS = 10;
	/** Number of rows in piece **/
	private int numRows = 4;
	/** Number of columns in piece **/
	private int numCols = 4;
	/** Board **/
	private boolean[][] blockMatrix;
	/** Current Tetris piece **/
	private TetrisPiece currentPiece;
	/** Anchor point of Tetris piece **/
	private int[] currentPieceGridPosition;

	/**
	 * Default constructor
	 */
	public TetrisBoard() {
		// Initialize board and current grid position of current falling piece
		initBoard();
		initCurrentGP();
		this.addNewPiece();
	}

	/**
	 * Initialize an int array of length two to keep track of the grid position
	 * of the current piece (row, col)
	 */
	private void initCurrentGP() { currentPieceGridPosition = new int[] { 0, 3 }; }

	/**
	 * Initialize the 2D board array to have all false values
	 */
	private void initBoard() { blockMatrix = new boolean[NUM_ROWS][NUM_COLS]; }

	/**
	 * Update the board array to reflect the newly landed piece's filled squares
	 * using the currentGridPosition values and the currentPiece's rotation
	 * value
	 */
	public void landPiece() {
		// Get current rotation index
		int rot = currentPiece.getPieceRotation();

		// Update board array
		for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
			for (int colIndex = 0; colIndex < numCols; colIndex++) {
				if (currentPiece.isFilled(rot, rowIndex, colIndex))
					blockMatrix[rowIndex + currentPieceGridPosition[0]][colIndex + currentPieceGridPosition[1]] = true;
			}
		}
	}

	/**
	 * Check if moving left is valid. If so, move the current piece left
	 * 
	 * @return true if valid move was performed
	 */
	public boolean moveLeft() {
		// Get current rotation index
		int rot = currentPiece.getPieceRotation();

		// Get current anchor point
		int row = currentPieceGridPosition[0];
		int col = currentPieceGridPosition[1];

		// If it's a valid move, move left
		if (validMove(currentPiece, rot, row, col - 1)) {
			currentPieceGridPosition[1]--;
			return true;
		}
		return false;
	}

	/**
	 * Check if moving right is valid. If so, move the current piece right
	 * 
	 * @return true if valid move was performed
	 */
	public boolean moveRight() {
		// Get current rotation index
		int rot = currentPiece.getPieceRotation();

		// Get current anchor point
		int row = currentPieceGridPosition[0];
		int col = currentPieceGridPosition[1];

		// If it's a valid move, move right
		if (validMove(currentPiece, rot, row, col + 1)) {
			currentPieceGridPosition[1]++;
			return true;
		}
		return false;
	}

	/**
	 * Check if moving down is valid. If so, move the current piece down
	 * 
	 * @return true if valid move was performed
	 */
	public boolean moveDown() {
		// Get current rotation index
		int rot = currentPiece.getPieceRotation();

		// Get current anchor point
		int row = currentPieceGridPosition[0];
		int col = currentPieceGridPosition[1];

		// If it's a valid move, move down
		if (validMove(currentPiece, rot, row + 1, col)) {
			currentPieceGridPosition[0]++;
			return true;
		}

		return false;
	}

	/**
	 * Check if rotating clockwise is valid. If so, rotate the current piece
	 * clockwise by 90 degrees
	 * 
	 * @return true if valid move was performed
	 */
	public boolean rotateCW() {
		// Get current rotation index
		int nextRot = (currentPiece.getPieceRotation() + 1) % 4;

		// Get current anchor point
		int row = currentPieceGridPosition[0];
		int col = currentPieceGridPosition[1];

		// If it's a valid move, rotate clockwise
		if (validMove(currentPiece, nextRot, row, col)) {
			currentPiece.rotateCW();
			return true;
		}
		return false;
	}
	
	/**
	 * Check if rotating counter-clockwise is valid. If so, rotate the current piece
	 * counter-clockwise by 90 degrees
	 * 
	 * @return true if valid move was performed
	 */
	public boolean rotateCCW() {
		// Get current rotation index
		int nextRot = currentPiece.getPieceRotation() - 1;
		if (nextRot < 0) nextRot = 3;
		
		// Get current anchor point
		int row = currentPieceGridPosition[0];
		int col = currentPieceGridPosition[1];

		// If it's a valid move, rotate counter-clockwise
		if (validMove(currentPiece, nextRot, row, col)) {
			currentPiece.rotateCCW();
			return true;
		}
		return false;
	}

	/**
	 * Detect and remove any lines formed
	 * 
	 * @return the total number found
	 */
	public int numberOfFormedLines() {
		// Keep count
		int count = 0;

		// Detect and remove formed lines
		for (int row = 0; row < blockMatrix.length; row++) {
			if (fullLine(row)) {
				removeLine(row);
				count++;
			}
		}
		return count;
	}

	/**
	 * Check if there is a full line at the row
	 * 
	 * @param row
	 * @return true if full
	 */
	private boolean fullLine(int row) {
		for (int col = 0; col < blockMatrix[row].length; col++) {
			// If block is not filled yet
			if (!blockMatrix[row][col])
				return false;
		}
		return true;
	}

	/**
	 * Remove the line at row in the model. Shift all values for rows at a lower
	 * index to be at one row higher. Make row 0 full of false values
	 * 
	 * @param row
	 */
	private void removeLine(int row) {
		// Shift values
		for (int rowIndex = row - 1; rowIndex >= 0; rowIndex--) {
			for (int colIndex = 0; colIndex < blockMatrix[row].length; colIndex++) {
				blockMatrix[rowIndex + 1][colIndex] = blockMatrix[rowIndex][colIndex];
			}
		}

		// Make row 0 full of false values
		for (int colIndex = 0; colIndex < blockMatrix[0].length; colIndex++) {
			blockMatrix[0][colIndex] = false;
		}
	}

	/**
	 * Checks if placing the piece at grid position (row, col) with the rotation
	 * rot (values can be 0, 90, 180, 270) would cause a collision (i.e., if
	 * there would be a block on an already-filled grid square)
	 * 
	 * @param piece
	 * @param rot
	 * @param gridRow
	 * @param gridCol
	 * @return true if there would be a collision
	 */
	private boolean detectCollision(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
			for (int colIndex = 0; colIndex < numCols; colIndex++) {
				// If placing the piece at grid position would cause a collision
				if (piece.isFilled(rot, rowIndex, colIndex) && blockMatrix[rowIndex + gridRow][colIndex + gridCol])
					return true;
			}
		}
		return false;
	}

	/**
	 * Checks if placing the piece at grid position (row, col) with the rotation
	 * rot (values can be 0, 90, 180, 270) would cause an out of bounds
	 * condition (i.e., if there would be a block falling off the board)
	 * 
	 * @param piece
	 * @param rot
	 * @param gridRow
	 * @param gridCol
	 * @return true if there would be a bounding error
	 */
	private boolean detectOutOfBounds(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		if (gridCol < 0) return true;
		
		for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
			for (int colIndex = 0; colIndex < numCols; colIndex++) {
				if (piece.isFilled(rot, rowIndex, colIndex)) {
					// If either rowIndex or colIndex is out of bounds
					if ((rowIndex + gridRow == NUM_ROWS) || (colIndex + gridCol == NUM_COLS))
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if placing the piece at grid position (row, col) with the rotation
	 * rot (values can be 0, 90, 180, 270) is a valid move
	 * 
	 * @param piece
	 * @param rot
	 * @param gridRow
	 * @param gridCol
	 * @return true if no collision or bounding error
	 */
	private boolean validMove(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		if (!detectOutOfBounds(piece, rot, gridRow, gridCol) && !detectCollision(piece, rot, gridRow, gridCol))
			return true;
		return false;
	}

	/**
	 * Check if there is a block in the row and column
	 * 
	 * @param row row
	 * @param col column
	 * @return true if there is a block
	 */
	public boolean hasBlock(int row, int col) { 
		if (blockMatrix[row][col]) return true;
		else if ((row >= currentPieceGridPosition[0] && row < currentPieceGridPosition[0] + numRows) &&
				(col >= currentPieceGridPosition[1] && col < currentPieceGridPosition[1] + numCols) &&
				currentPiece.isFilled(currentPiece.getPieceRotation(), row - currentPieceGridPosition[0], col - currentPieceGridPosition[1])) 
			return true;
		return false;
		}

	/**
	 * Add a new random Tetris piece to the board at grid position (0, 3)
	 */
	public void addNewPiece() {
		// Generate a random index
		Random rand = new Random();
		int randIndex = rand.nextInt(6);

		// Get a random piece using randIndex
		switch (randIndex) {
		case 0:
			currentPiece = new TetrisT();
			break;
		case 1:
			currentPiece = new TetrisStick();
			break;
		case 2:
			currentPiece = new TetrisSquare();
			break;
		case 3:
			currentPiece = new TetrisS1();
			break;
		case 4:
			currentPiece = new TetrisS2();
			break;
		case 5:
			currentPiece = new TetrisL1();
			break;
		case 6:
			currentPiece = new TetrisL2();
			break;
		default:
			break;
		}
		// Initialize current grid position at (0,3)
		initCurrentGP();
	}

	/**
	 * @return the block matrix (the grid of blocks)
	 */
	public boolean[][] getBlockMatrix() { return blockMatrix; }

	/**
	 * @return the currentPiece
	 */
	public TetrisPiece getCurrentPiece() { return currentPiece; }

	/**
	 * @return the currentPieceGridPosition
	 */
	public int[] getCurrentPieceGridPosition() { return currentPieceGridPosition; }

	/**
	 * @return the numRows in the block matrix
	 */
	public int getNumRows() { return numRows; }

	/**
	 * @return the numCols in the block matrix
	 */
	public int getNumCols() { return numCols; }
}
