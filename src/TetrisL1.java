/**
 * Left L piece
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisL1 extends TetrisPiece {
	public TetrisL1() {
		//First rotation
		//     ___
		//    |   |
		//    |   |
		//    |   |
		//  __|   |
		// |      |
		// |______|
		filledSquares[0][0][1] = filledSquares[0][1][1] = filledSquares[0][2][1] = filledSquares[0][2][0] = true;
		
		//Second rotation
		//  ___
		// |   |
		// |   |______
		// |          |    
		// |__________|
		filledSquares[1][0][0] = filledSquares[1][1][0] = filledSquares[1][1][1] = filledSquares[1][1][2] = true;
		
		//Third rotation
		//  _______
		// |       |
		// |    ___|
		// |   |
		// |   |
		// |   |
		// |___|
		filledSquares[2][0][0] = filledSquares[2][0][1] = filledSquares[2][1][0] = filledSquares[2][2][0] = true;
		
		//Fourth rotation
		//  ____________
		// |            |
		// |________    |
		//          |   |
		//          |___|
		filledSquares[3][0][0] = filledSquares[3][0][1] = filledSquares[3][0][2] = filledSquares[3][1][2] = true;
	}
}
