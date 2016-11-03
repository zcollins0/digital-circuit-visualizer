package dcv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

class DigitalCircuitUI {

	static Circuit circuit;
	static ArrayList<Input> inputList = new ArrayList<Input>();
	static char inputTag = 'A';
	static public JPopupMenu popup;
	static public JPopupMenu inputMenu;
	static public JFrame frame;
	static public JPanel panel;
	static boolean drag = false;
	static int mouseX = 200;
	static int mouseY = 100;
	static int clickX = 0;
	static int clickY = 0;
	static boolean first = true; 
	static Circuit circ = new Circuit();
	static MouseListener frameListener = null;
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	static Gate parentGate;
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

		frame = new JFrame("Digital Circuit Visualizer");
		panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel emptyLabel = new JLabel("");
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

		//need null layout so users can drag gates
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

		//create listener for right click popup menu
		frameListener = new PopupListener();
		frame.addMouseListener(frameListener);

		frame.setVisible(true);
	}

	static void displayGate(String imageFile){

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;

		//grabs image of gate from src	
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

		Gate newGate = null;


		if(imageFile.equals("ANDimage.png")){
			newGate = new AND(null, null);
			//if top level gate, set as new parentGate, otherwise call addChildGate on parentGate
			if(!first){
				//Only allow for 2 children
				try {
					parentGate.addChildGate(newGate);
					newGate.setIcon(icon);
				} catch (InvalidNodeException e1) {
					System.out.println("Cannot add more than 2 children");
				}				
			}
			else{
				parentGate = newGate;
				newGate.setIcon(icon);
				first = false;
			}
		}
		else if(imageFile.equals("ORimage.png")){
			newGate = new OR(null, null);
			if(!first){
				try {
					parentGate.addChildGate(newGate);
					newGate.setIcon(icon);
				} catch (InvalidNodeException e1) {
					System.out.println("Cannot add more than 2 children");
				}				
			}
			else{
				parentGate = newGate;
				newGate.setIcon(icon);
				first = false;
			}
		}
		else if(imageFile.equals("NOTimage.png")){
			NOT tmp = null;
			newGate = new NOT(tmp);
			if(!first){
				try {
					parentGate.addChildGate(newGate);
					newGate.setIcon(icon);
				} catch (InvalidNodeException e1) {
					System.out.println("NOT can only have 1 child");
				}				
			}
			else{
				parentGate = newGate;
				newGate.setIcon(icon);
				first = false;
			}
			
		}
		else if(imageFile.equals("NANDimage.png")){
			newGate = new NAND(null, null);
			if(!first){
				try {
					parentGate.addChildGate(newGate);
					newGate.setIcon(icon);
				} catch (InvalidNodeException e1) {
					System.out.println("Cannot add more than 2 children");
				}				
			}
			else{
				parentGate = newGate;
				newGate.setIcon(icon);
				first = false;
			}
		}
		else if(imageFile.equals("NORimage.png")){
			newGate = new NOR(null, null);
			if(!first){
				try {
					parentGate.addChildGate(newGate);
					newGate.setIcon(icon);
				} catch (InvalidNodeException e1) {
					System.out.println("Cannot add more than 2 children");
				}				
			}
			else{
				parentGate = newGate;
				newGate.setIcon(icon);
				first = false;
			}
		}
		else if(imageFile.equals("XORimage.png")){
			newGate = new XOR(null, null);
			if(!first){
				try {
					parentGate.addChildGate(newGate);
					newGate.setIcon(icon);
				} catch (InvalidNodeException e1) {
					System.out.println("Cannot add more than 2 children");
				}				
			}
			else{
				parentGate = newGate;
				newGate.setIcon(icon);
				first = false;
			}
		}
		else{
			newGate = new Input(inputTag);
			((Input) newGate).setActive(true);
			inputList.add((Input) newGate);
			newGate.setIcon(icon);
			try {
				parentGate.addChildGate(newGate);

			} catch (InvalidNodeException e1) {
				System.out.println("Input cannot have children");
			}
			inputTag++;
		}
		
		Dimension lsize = newGate.getPreferredSize();
		//Listener to allow image to be dragged
		newGate.addMouseMotionListener(new DragImageListener(){});
		newGate.addMouseListener(new DragImageListener(){});
		newGate.setBounds(100, 100, lsize.width, lsize.height);

		//Listener on image to create child gates
		if(newGate.isInput()){
			newGate.addMouseListener(new InputListener(){});
		}
		else{
			newGate.addMouseListener(new PopupListener(){});
		}

		labels.add(newGate);
		updateUI();
	}

	static void addGateMenu(){
		//This function creates a right click menu button for the user to select gate type
		JMenu submenu = null;

		if(first){
			circ.setTop(parentGate);
		}

		//If you've already added a gate, create submenu for child gates
		popup = new JPopupMenu();
		if(!first){
			submenu = new JMenu("Add Child Gate");
		}

		//creating popup menu
		JMenuItem AND = new JMenuItem("Add AND Gate");

		//if Add AND Gate is clicked, call displayGate with ANDimage.png
		AND.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: make parent
				System.out.println("Clicked AND");
				displayGate("ANDimage.png");
			}
		});

		JMenuItem OR = new JMenuItem("Add OR Gate");
		OR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked OR");
				displayGate("ORimage.png");
			}
		});

		JMenuItem NOT = new JMenuItem("Add NOT Gate");
		NOT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NOT");
				displayGate("NOTimage.png");
			}
		});

		JMenuItem NAND = new JMenuItem("Add NAND Gate");
		NAND.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NAND");
				displayGate("NANDimage.png");
			}
		});

		JMenuItem NOR = new JMenuItem("Add NOR Gate");
		NOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NOR");
				displayGate("NORimage.png");
			}
		});

		JMenuItem XOR = new JMenuItem("Add XOR Gate");
		XOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked XOR");
				displayGate("XORimage.png");
			}
		});

		JMenuItem inp = new JMenuItem("Add Input");
		inp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Add Input");
				String buildImgName = Character.toString(inputTag) + "image.png";
				displayGate(buildImgName);
			}
		});


		//add menu items to the popup menu if first gate
		if(first){
			popup.add(AND);
			popup.add(OR);
			popup.add(NOT);
			popup.add(NAND);
			popup.add(NOR);
			popup.add(XOR);
		}
		//add menu items to the submenu if not first gate
		else{
			submenu.add(AND);
			submenu.add(OR);
			submenu.add(NOT);
			submenu.add(NAND);
			submenu.add(NOR);
			submenu.add(XOR);
			submenu.add(inp);
			popup.add(submenu);
		}

		//remove frame listener so it's only possible to add child gates after first gate
		frame.removeMouseListener(frameListener);
		updateUI();
	}

	public static void addInputMenu() {
		inputMenu = new JPopupMenu();

		JMenuItem ACTIVE = new JMenuItem("Make Active");
		ACTIVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Make Active");
				((Input) parentGate).setActive(true);
			}

		});

		JMenuItem INACTIVE = new JMenuItem("Make Inactive");
		INACTIVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Make Inactive");
				((Input) parentGate).setActive(false);
			}

		});
		inputMenu.add(ACTIVE);
		inputMenu.add(INACTIVE);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} });

	}
}
