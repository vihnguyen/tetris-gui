/**
 * Square piece
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisSquare extends TetrisPiece{
	public TetrisSquare() {
		//Square only has one rotation
		//   ______
		//  |      |
		//  |      |
		//  |______|
		filledSquares[0][0][0] = filledSquares[0][0][1] = filledSquares[0][1][0] = filledSquares[0][1][1] = true;
		filledSquares[1][0][0] = filledSquares[1][0][1] = filledSquares[1][1][0] = filledSquares[1][1][1] = true;
		filledSquares[2][0][0] = filledSquares[2][0][1] = filledSquares[2][1][0] = filledSquares[2][1][1] = true;
		filledSquares[3][0][0] = filledSquares[3][0][1] = filledSquares[3][1][0] = filledSquares[3][1][1] = true;
	}
}
