package dcv;

import java.util.ArrayList;

public class DigitalCircuitUI {
	
	static Circuit circuit;
	static ArrayList<Input> inputList;
	
	static void updateUI() {
		// Call this when you need to redraw
	}
	
	static void DoUI() {
		// DoUI should initialize the UI, and call an update function.
		// The structure of the circuit should be a horizontal tree.
		// Highest level node should go on the right.
		// A Graphics2D might be the best way to implement this. We'll have to draw lines
		// to represent wires connecting circuit components.
		
<<<<<<< HEAD
		// Maybe check out the "Placeable" object as well
		JFrame frame = new JFrame("Circuit Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel emptyLabel = new JLabel("");
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
=======
		// Maybe check out the "Placeable" object as well.
		
		updateUI();
		
>>>>>>> 4affa32986c920aae933e0a9af7c773e1eb9242c
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} });

	}

}
