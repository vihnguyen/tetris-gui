/**
 * Right L piece
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisL2 extends TetrisPiece {
	public TetrisL2() {
		//First rotation
		//     ___
		//    |   |
		//    |   |
		//    |   |
		//    |   |___
		//    |       |
		//    |_____ _|
		filledSquares[0][0][0] = filledSquares[0][1][0] = filledSquares[0][2][0] = filledSquares[0][2][1] = true;
		
		//Second rotation
		//  ___________
		// |           |
		// |    _______|
		// |   |
		// |___|
		filledSquares[1][0][0] = filledSquares[1][0][1] = filledSquares[1][0][2] = filledSquares[1][1][0] = true;
		
		//Third rotation
		//  _______
		// |       |
		// |___    |
		//     |   |
		//     |   |
		//     |___|
		filledSquares[2][0][0] = filledSquares[2][0][1] = filledSquares[2][1][1] = filledSquares[2][2][1] = true;
		
		//Fourth rotation
		//         ___
		//        |   |
		//  ______|   |
		// |          |    
		// |__________|
		filledSquares[3][0][2] = filledSquares[3][1][0] = filledSquares[3][1][1] = filledSquares[3][1][2] = true;
	}
}
