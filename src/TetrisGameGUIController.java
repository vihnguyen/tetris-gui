import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisGameGUIController extends JPanel implements KeyListener, ActionListener {
	/** Default drop rate **/
	public static final int DEFAULT_DROP_RATE = 1000;

	/** View of game board **/
	private TetrisBoardGUIView view;

	/** Tetris game **/
	private TetrisGame game;

	/** Number of lines cleared **/
	private JLabel linesLabel;

	/** Number of Tetrises cleared **/
	private JLabel tetrisesLabel;
	
	/** Reset game button **/
	private JButton resetButton;

	/** Timer **/
	private Timer gameTimer;

	/** Drop rate **/
	private int dropRate;

	/**
	 * Constructor
	 */
	public TetrisGameGUIController() {
		// Use a border layout
		super(new BorderLayout());

		// Add key listener
		addKeyListener(this);
		setFocusable(true);
			
		//Reset the game and set up timer
		resetGame();
		setupTimer();

	}

	private void resetGame() {
		//Get user choice of level
		Object[] levels = {"easy", "medium", "hard"};
		String s = (String) JOptionPane.showInputDialog(this, "Pick a level: ", "Let's play Tetris!",
				JOptionPane.QUESTION_MESSAGE, null, levels, "easy");
		
		//Exit game or return to current game
		if (s == null) {
			if (game == null) System.exit(0);
			else return;
		}
		//Modify dropRate to level chosen
		if (s.equals("easy")) dropRate = DEFAULT_DROP_RATE;
		else if (s.equals("medium")) dropRate = DEFAULT_DROP_RATE / 2;
		else if (s.equals("hard")) dropRate = DEFAULT_DROP_RATE / 5;
		
		//Remove all old components
		removeAll();
		
		// Set drop rate and timer
		if (gameTimer != null) {
			gameTimer.setDelay(dropRate);
			gameTimer.restart();
		}
		
		// Create view of board
		createView();
		
		// Create scores
		createScore();
		
		//Create buttons
		createResetButton();
		
		//Re-validate board
		revalidate();
	}

	/**
	 * Refresh display
	 */
	public void refreshDisplay() {
		// Refresh view
		view.repaint();

		// Refresh score
		linesLabel.setText("Lines cleared: " + game.getNumLines());
		tetrisesLabel.setText("Tetrises cleared: " + game.getNumTetrises());
	}

	/**
	 * Set up timer
	 */
	private void setupTimer() {
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Move down and repaint the view
				game.attemptMove(TetrisGame.DOWN);
				refreshDisplay();
			}
		};
		// Start timer
		gameTimer = new Timer(dropRate, taskPerformer);
		gameTimer.setRepeats(true);
		gameTimer.start();
	}

	/**
	 * Creates view of scores
	 */
	private void createScore() {
		//Get number of lines cleared and tetrises cleared
		int numLines = game.getNumLines();
		int numTetrises = game.getNumTetrises();

		//Put them in JLabels
		linesLabel = new JLabel("Lines cleared: " + numLines);
		tetrisesLabel = new JLabel("Tetrises cleared: " + numTetrises);

		//Display scores
		JPanel scores = new JPanel(new BorderLayout());
		scores.add(linesLabel, BorderLayout.NORTH);
		scores.add(tetrisesLabel, BorderLayout.SOUTH);

		add(scores, BorderLayout.NORTH);
	}
	
	/**
	 * Creates reset button
	 */
	private void createResetButton() {
		//Create reset button and add a listener 
		resetButton = new JButton("Reset Game");
		resetButton.addActionListener(this);
		
		//Display button
		add(resetButton, BorderLayout.EAST);
	}
	
	/**
	 * Create view of game board
	 */
	private void createView() {
		// Create a new Tetris game and view
		game = new TetrisGame();
		view = new TetrisBoardGUIView(game.getTetrisBoard());

		// Display view
		add(view, BorderLayout.CENTER);
	}

	/**
	 * r: right l: left d: down z: cw x: ccw
	 * 
	 * @param move
	 *            move type
	 */
	private void moveEntered(char key) {
		if (key == 'r')
			game.attemptMove(TetrisGame.RIGHT);
		else if (key == 'l')
			game.attemptMove(TetrisGame.LEFT);
		else if (key == 'z')
			game.attemptMove(TetrisGame.CW);
		else if (key == 'x')
			game.attemptMove(TetrisGame.CCW);
	}

	/**
	 * Handle the key typed event
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Handle the key-pressed event
	 */
	public void keyPressed(KeyEvent e) {
		char key = Character.toLowerCase(e.getKeyChar());

		// Check if user input is valid
		if (key == 'l' || key == 'r' || key == 'z' || key == 'x') {
			// Move current falling piece according to user input and display it
			moveEntered(key);
			refreshDisplay();
		}
	}

	/**
	 * Handle the key-released event
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Resets game when user clicks on 'reset button'
	 * @param e action even
	 */
	public void actionPerformed(ActionEvent e) {
		resetGame();
	}
}
