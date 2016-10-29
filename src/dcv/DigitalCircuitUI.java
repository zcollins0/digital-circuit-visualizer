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

		Gate label1 = null;

		if(imageFile.equals("ANDimage.png")){
			label1 = new AND(icon);
		}
		else if(imageFile.equals("ORimage.png")){
			label1 = new OR(icon);
		}
		else if(imageFile.equals("NOTimage.png")){
			label1 = new NOT(icon);
		}
		else if(imageFile.equals("NANDimage.png")){
			label1 = new NAND(icon);
		}
		else if(imageFile.equals("NORimage.png")){
			label1 = new NOR(icon);
		}
		else if(imageFile.equals("XORimage.png")){
			label1 = new XOR(icon);
		}
		else{
			label1 = new Input(icon);
		}

		Dimension lsize = label1.getPreferredSize();
		//Listener to allow image to be dragged
		label1.addMouseMotionListener(new DragImageListener(){});
		label1.addMouseListener(new DragImageListener(){});
		label1.setBounds(100, 100, lsize.width, lsize.height);

		//Listener on image to create child gates
		if(label1.isInput()){
			label1.addMouseListener(new InputListener(){});
		}
		else{
			label1.addMouseListener(new PopupListener(){});
		}

		labels.add(label1);
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
				final Gate newGate = new AND(null, null);
				//if top level gate, set as new parentGate, otherwise call addChildGate on parentGate
				if(!first){
					//Only allow for 2 children
					try {
						parentGate.addChildGate(newGate);
						displayGate("ANDimage.png");
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = new AND(null, null);
					displayGate("ANDimage.png");
					first = false;
				}
			}
		});

		JMenuItem OR = new JMenuItem("Add OR Gate");
		OR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked OR");
				final Gate newGate = new OR(null, null);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate("ORimage.png");
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = new OR(null, null);
					displayGate("ORimage.png");
					first = false;
				}
			}
		});

		JMenuItem NOT = new JMenuItem("Add NOT Gate");
		NOT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NOT");
				Gate tmp = null;
				final Gate newGate = new NOT(tmp);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate("NOTimage.png");
					} catch (InvalidNodeException e1) {
						System.out.println("NOT can only have 1 child");
					}				
				}
				else{
					parentGate = new NOT(tmp);
					displayGate("NOTimage.png");
					first = false;
				}
			}
		});

		JMenuItem NAND = new JMenuItem("Add NAND Gate");
		NAND.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NAND");
				final Gate newGate = new NAND(null, null);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate("NANDimage.png");
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = new NAND(null, null);
					displayGate("NANDimage.png");
					first = false;
				}
			}
		});

		JMenuItem NOR = new JMenuItem("Add NOR Gate");
		NOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked NOR");
				final Gate newGate = new NOR(null, null);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate("NORimage.png");
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = new NOR(null, null);
					displayGate("NORimage.png");
					first = false;
				}
			}
		});

		JMenuItem XOR = new JMenuItem("Add XOR Gate");
		XOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked XOR");
				final Gate newGate = new XOR(null, null);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate("XORimage.png");
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = new XOR(null, null);
					displayGate("XORimage.png");
					first = false;
				}
			}
		});

		JMenuItem inp = new JMenuItem("Add Input");
		inp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Add Input");
				final Gate newGate = new Input(inputTag);
				inputList.add((Input) newGate);
				try {
					parentGate.addChildGate(newGate);

				} catch (InvalidNodeException e1) {
					System.out.println("Input cannot have children");
				}

				String buildImgName = Character.toString(inputTag) + "image.png";
				displayGate(buildImgName);
				inputTag++;
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
