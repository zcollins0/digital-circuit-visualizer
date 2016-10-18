package dcv;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.io.*;

class DigitalCircuitUI {

	static Circuit circuit;
	static ArrayList<Input> inputList;
	static char inputTag = 'A';
	static public JPopupMenu popup;
	static public JFrame frame;
	static public JPanel panel;
	static boolean drag = false;
	static int mouseX = 200;
	static int mouseY = 100;
	static int clickX = 0;
	static int clickY = 0;

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
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);

		frame.addMouseListener(new PopupListener());
		updateUI();

		frame.setVisible(true);
		// Maybe check out the "Placeable" object as well.
	}

	static void displayGate(String imageFile){
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		File inputFile = new File(imageFile);
		FileInputStream istream = null;
		BufferedImage image = null;
		
		try {
			istream = new FileInputStream(inputFile);
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
			System.exit(0);
		};
		try {
			image = ImageIO.read(istream);
		} catch (IOException e1) {
			System.out.println("Could not read image");
			System.exit(0);
		} 
		Image temp = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(temp);
		
		JLabel label1 = new JLabel(icon);
		label1.addMouseMotionListener(new DragImageListener(){});
	    label1.addMouseListener(new DragImageListener(){});
		
		JPanel newPanel = new JPanel();
		newPanel.setLayout(null);
		
		label1.setLocation(PopupListener.xcord, PopupListener.ycord);
		
		newPanel.setSize(50,50);
		newPanel.setBounds(300, 300, 50, 50);
		newPanel.setLocation(PopupListener.xcord, PopupListener.ycord);
		newPanel.add(label1);
		newPanel.setVisible(true);
		
		panel.add(label1);
		
		System.out.println(newPanel.getPreferredSize());
		
		frame.add(newPanel);
		newPanel.revalidate();
		newPanel.repaint();
		panel.revalidate();
		panel.repaint();
		
	}
	
	static void addGateMenu(){
		popup = new JPopupMenu();
		JMenuItem addGate = new JMenuItem("Add AND Gate");
		addGate.addMouseListener(new PopupListener());
		addGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked AND");
				displayGate("ANDimage.png");
			}
		});
		popup.add(addGate);

		addGate = new JMenuItem("Add OR Gate");
		addGate.addMouseListener(new PopupListener());
		addGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked OR");
				displayGate("ORimage.png");
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
