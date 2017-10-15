/**
 * The TetrisPiece abstract class represents a piece made of 4 TetrisBlocks. 
 * It maintains 4 rotations (0 degrees, 90 degrees, 180 degrees and 270 degrees), 
 * with each being a 4x4 grid with certain filled squares
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public abstract class TetrisPiece {
	/** 3 dimensional array maintaining which squares are filled first dimension is rotation 
	 * (index 0: 0 degrees, index 1: 90 degrees, index 2: 180 degrees, index 3: 270 degrees) second and 
	 * third dimensions create 4x4 grid with true values indicating filled squares**/
	boolean[][][] filledSquares = new boolean[4][4][4];
	
	/** Maintains the current rotation. 0: 0 degrees, 1: 90 degrees, 2: 180 degrees, 3: 270 degrees **/
	int pieceRotation;
	
	/**
	 * Default constructor
	 */
	public TetrisPiece() {
	}
	
	/**
	 * Rotate the piece clockwise by 90 degrees
	 */
	public void rotateCW() {
		pieceRotation = (pieceRotation + 1) % 4;
	}
	
	/**
	 * Rotate the piece counter-clockwise by 90 degrees
	 */
	public void rotateCCW() {
		pieceRotation = pieceRotation - 1;
		
		if (pieceRotation < 0) pieceRotation = 3;
	}
	
	/**
	 * Get the current rotation
	 * @return current rotation piece
	 */
	public int getPieceRotation() {
		return pieceRotation;
	}
	
	/**
	 * Checks if there is a TetrisBlock at the (row, col) position 
	 * for the rotation rot, where rot is 0, 90, 180 or 270 degrees
	 * @param rot rotation piece
	 * @param row row of piece
	 * @param col column of piece
	 * @return true if the square is filled and false otherwise
	 */
	public boolean isFilled(int rot, int row, int col) {
		return filledSquares[rot][row][col];
	}
}
