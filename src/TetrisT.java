/**
 * T piece
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisT extends TetrisPiece {
	public TetrisT() {
		//First rotation
		//        __
		//	   __|  |__
		//    |________|
		filledSquares[0][0][1] = filledSquares[0][1][0] = filledSquares[0][1][1] = filledSquares[0][1][2] = true;
		
		//Second rotation
		//     __
		//    |  |__
		//    |     |
		//    |   __|
		//    |__|
		filledSquares[1][0][0] = filledSquares[1][1][0] = filledSquares[1][1][1] = filledSquares[1][2][0] = true;
		
		//Third rotation
		//   ________
		//  |__    __|
		//     |__|
		filledSquares[2][0][0] = filledSquares[2][0][1] = filledSquares[2][0][2] = filledSquares[2][1][1] = true;
				
		//Fourth rotation
		//       __
		//    __|  |
		//   |     |
		//   |__   |
		//      |__|
		filledSquares[3][0][1] = filledSquares[3][1][0] = filledSquares[3][1][1] = filledSquares[3][2][1] = true;
}
		
}
