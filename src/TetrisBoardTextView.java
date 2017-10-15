/**
 * TetrisBoardTextView creates a String view of a TetrisBoard.
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisBoardTextView {
	private TetrisBoard board;
	
	/**
	 * Constructor
	 * @param b input board
	 */
	public TetrisBoardTextView(TetrisBoard b) { board = b; }
	
	/**
	 * @return the string view of board
	 */
	public String getBoardString() {
		//Initialize string view
		StringBuilder view = new StringBuilder();
		
		//Loop through board matrix
		for (int row = 0; row < TetrisBoard.NUM_ROWS; row++) {
			//view.append(row+1+" ");
			for (int col = 0; col < TetrisBoard.NUM_COLS; col++) {
				if (board.hasBlock(row, col)) view.append("x");
				else view.append(" ");
			}
			view.append("\n");
		}
		return view.toString();
	}
}
