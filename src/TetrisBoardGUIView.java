import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * TetrisBoardTextView creates a GUI view of a TetrisBoard.
 * 
 * @author Vi Nguyen
 * @version 1.0
 *
 */
public class TetrisBoardGUIView extends JComponent {
	private TetrisBoard board;

	/**
	 * Constructor
	 * 
	 * @param b
	 *            input board
	 */
	public TetrisBoardGUIView(TetrisBoard b) {
		board = b;
	}

	/**
	 * Paint the game
	 */
	public void paint(Graphics g) {
		// Block size
		int blockSize = computeBlockSize();

		// Paint board game
		paintBoardOutline(g, blockSize);

		// Loop through board matrix
		for (int row = 0; row < TetrisBoard.NUM_ROWS; row++) {
			for (int col = 0; col < TetrisBoard.NUM_COLS; col++) {
				if (board.hasBlock(row, col))
					// Print blocks
					paintBlock(g, row, col, blockSize);
			}
		}
	}

	/**
	 * Paint the block at grid row, grid column
	 * 
	 * @param g
	 *            graphics
	 * @param row
	 *            row
	 * @param col
	 *            column
	 * @param blockSize
	 *            block size
	 */
	private void paintBlock(Graphics g, int row, int col, int blockSize) {
		//paint one block
		g.setColor(Color.PINK);
		g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
		
		g.setColor(Color.BLACK);
		g.drawRect(col * blockSize, row * blockSize, blockSize, blockSize);
	}

	/**
	 * Compute the best block size for the current width and height
	 * 
	 * @return size (for a square block) in pixels
	 */
	private int computeBlockSize() {
		// Get board height and width
		int boardWidth = getWidth();
		int boardHeight = getHeight();

		// Return block size
		return Math.min(boardWidth / TetrisBoard.NUM_COLS, boardHeight / TetrisBoard.NUM_ROWS);
	}

	/**
	 * Paint the board outline
	 * 
	 * @param g
	 *            graphics
	 * @param blockSize
	 *            block size
	 */
	private void paintBoardOutline(Graphics g, int blockSize) {
		g.drawRect(0, 0, blockSize * TetrisBoard.NUM_COLS, blockSize * TetrisBoard.NUM_ROWS);
	}

}
