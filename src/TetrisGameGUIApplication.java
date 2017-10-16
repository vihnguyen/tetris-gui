import javax.swing.JFrame;

public class TetrisGameGUIApplication {
	public static void main(String[] args) {
		JFrame guiFrame;

		// create a new JFrame to hold a Tetris game
		guiFrame = new JFrame("Tetris");

		// set size
		guiFrame.setSize(600, 900);

		// create a Tetris game and add it
		guiFrame.add(new TetrisGameGUIController());

		// exit normally on closing the window
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// show frame
		guiFrame.setVisible(true);
	}
}
