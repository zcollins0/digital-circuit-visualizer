package dcv;

import java.util.ArrayList;

public class DigitalCircuitUI {
	
	static Circuit circuit;
	static ArrayList<Input> inputList;
	
	static void DoUI() {
		
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} });

	}

}
