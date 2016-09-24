package dcv;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitalCircuitUI {
	
	static Circuit circuit;
	static ArrayList<Input> inputList;
	static char inputTag = 'A';
	
	static void updateUI() {
		// Call this when you need to redraw
	}
	
	static void DoUI() {
		// DoUI should initialize the UI, and call an update function.
		// The structure of the circuit should be a horizontal tree.
		// Highest level node should go on the right.
		// A Graphics2D might be the best way to implement this. We'll have to draw lines
		// to represent wires connecting circuit components.
		
		// Maybe check out the "Placeable" object as well
		JFrame frame = new JFrame("Digital Circuit Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel emptyLabel = new JLabel("");
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		
		updateUI();

		frame.pack();
		frame.setVisible(true);
		// Maybe check out the "Placeable" object as well.
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} });

	}

}
