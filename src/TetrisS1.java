/**
 * Left Dog piece
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisS1 extends TetrisPiece{
	public TetrisS1() {
		//First rotation
		//  _____
		// |__   |__
		//    |_____|
		filledSquares[0][0][0] = filledSquares[0][0][1] = filledSquares[0][1][1] = filledSquares[0][1][2] = true;
		filledSquares[2][0][0] = filledSquares[2][0][1] = filledSquares[2][1][1] = filledSquares[2][1][2] = true;
		
		//Second rotation
		//      ___
		//     |   |
		//   __|   |
		//  |      |
		//  |    __|
		//  |   |
		//  |___|
		filledSquares[1][0][1] = filledSquares[1][1][0] = filledSquares[1][1][1] = filledSquares[1][2][0] = true;
		filledSquares[3][0][1] = filledSquares[3][1][0] = filledSquares[3][1][1] = filledSquares[3][2][0] = true;
	}
}
