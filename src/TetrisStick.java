/**
 * Stick piece
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisStick extends TetrisPiece {
	public TetrisStick() {
		// First rotation
		//  ___
		// |   |
		// |   |
		// |   |
		// |   |
		// |   |
		// |___|
		filledSquares[0][0][0] = filledSquares[0][1][0] = filledSquares[0][2][0] = filledSquares[0][3][0] = true;
		filledSquares[2][0][0] = filledSquares[2][1][0] = filledSquares[2][2][0] = filledSquares[2][3][0] = true;
		
		//Second rotation
		//  _______________
		// |               |
		// |_______________|
		filledSquares[1][0][0] = filledSquares[1][0][1] = filledSquares[1][0][2] = filledSquares[1][0][3] = true;
		filledSquares[3][0][0] = filledSquares[3][0][1] = filledSquares[3][0][2] = filledSquares[3][0][3] = true;
	}
}
