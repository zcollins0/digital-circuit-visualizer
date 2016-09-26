package dcv;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

class DigitalCircuitUI {

	static Circuit circuit;
	static ArrayList<Input> inputList;
	static char inputTag = 'A';
	static public JPopupMenu popup;
	static public JFrame frame;
	static public JPanel panel;

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
		frame = new JFrame("Digital Circuit Visualizer");
		panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel emptyLabel = new JLabel("");
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel("Right Click to Select Gate Type");
		JButton button = new JButton();
		button.setText("Evaluate");
		panel.add(label);
		panel.add(button, BorderLayout.PAGE_END);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);

		frame.addMouseListener(new PopupListener());
		updateUI();

		frame.setVisible(true);
		// Maybe check out the "Placeable" object as well.
	}

	static void addGateMenu(){
		popup = new JPopupMenu();
		JMenuItem addGate = new JMenuItem("Add AND Gate");
		addGate.addMouseListener(new PopupListener());
		addGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked AND");
			}
		});
		popup.add(addGate);

		addGate = new JMenuItem("Add OR Gate");
		addGate.addMouseListener(new PopupListener());
		addGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked OR");
			}
		});
		popup.add(addGate);

		addGate = new JMenuItem("Add NOT Gate");
		addGate.addMouseListener(new PopupListener());
		addGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NOT");
			}
		});
		popup.add(addGate);

		addGate = new JMenuItem("Add NAND Gate");
		addGate.addMouseListener(new PopupListener());
		addGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NAND");
			}
		});
		popup.add(addGate);

		addGate = new JMenuItem("Add NOR Gate");
		addGate.addMouseListener(new PopupListener());
		addGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NOR");
			}
		});
		popup.add(addGate);

		addGate = new JMenuItem("Add XOR Gate");
		addGate.addMouseListener(new PopupListener());
		addGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked XOR");
			}
		});
		popup.add(addGate);

		updateUI();
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} });

	}

}
