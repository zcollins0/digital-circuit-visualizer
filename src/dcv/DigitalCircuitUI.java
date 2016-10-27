package dcv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	static boolean first = true; 
	static Circuit circ = new Circuit();
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	static void updateUI() {
		// Call this when you need to redraw
		for (JLabel l: labels){
			panel.add(l);			
		}
		panel.revalidate();
		panel.repaint();

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
		
		panel.setLayout(null);
		
		JLabel label = new JLabel("Right Click to Select Gate Type. First gate selected will be top gate");
		Dimension size = label.getPreferredSize();
		label.setBounds(10, 5, size.width, size.height);
		
		JButton button = new JButton();
		button.setText("Evaluate");
		Dimension bsize = button.getPreferredSize();
		button.setBounds(480, 5, bsize.width, bsize.height);
		
		panel.add(label);
		panel.add(button, BorderLayout.PAGE_END);
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);

		frame.addMouseListener(new PopupListener());

		frame.setVisible(true);
		// Maybe check out the "Placeable" object as well.
	}

	static void displayGate(String imageFile){

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;

		//grabs image of gate from src	
		File inputFile = new File(imageFile);
		FileInputStream istream = null;
		BufferedImage image = null;
		try {
			istream = new FileInputStream(imageFile);
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

		//resizes the image
		Image temp = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(temp);

		//allows image to be dragged by user
		JLabel label1 = new JLabel(icon);
		Dimension lsize = label1.getPreferredSize();
		
		label1.addMouseMotionListener(new DragImageListener(){});
		label1.addMouseListener(new DragImageListener(){});
		label1.setBounds(100, 100, lsize.width, lsize.height);
		
		labels.add(label1);
		updateUI();
	}

	static void addGateMenu(){
		//This function creates a right click menu button for the user to select gate type
		Gate newGate = null;

		if (first)
			circ.setTop(newGate);
		first = false;

		popup = new JPopupMenu();

		//creating popup menu
		JMenuItem addGate = new JMenuItem("Add AND Gate");
		addGate.addMouseListener(new PopupListener());

		//if Add AND Gate is clicked, call displayGate with ANDimage.png
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
