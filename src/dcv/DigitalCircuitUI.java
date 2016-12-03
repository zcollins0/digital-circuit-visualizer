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
import javax.swing.JScrollPane;
import javax.swing.JTable;

class DigitalCircuitUI {

	static Circuit circ = new Circuit();
	static Gate parentGate;

	static ArrayList<Input> inputList = new ArrayList<Input>();
	static char inputTag = 'A';
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	static ArrayList<Gate> gateList = new ArrayList<Gate>();

	static public JPopupMenu popup;
	static public JPopupMenu inputMenu;
	static public JFrame frame;
	static public JPanel panel;
	static public JTable table = null;
	static public JScrollPane scrollPane;
	static MouseListener frameListener = null;

	static boolean drag = false;
	static int mouseX = 200;
	static int mouseY = 100;
	static int clickX = 0;
	static int clickY = 0;

	static boolean first = true; 

	static void updateUI() {
		// Call this when you need to redraw
		for (JLabel l: labels){
			panel.add(l);			
		}
		
		for(Gate g: gateList){
			if(g.child1 != null && g.child1.comp!=null){
				g.child1.comp.setPreferredSize(new Dimension(500,500));
				g.child1.comp.setBounds(0,0,800,800);
				DigitalCircuitUI.panel.add(g.child1.comp);
			}
		}
		DigitalCircuitUI.frame.add(DigitalCircuitUI.panel);
		
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

		JLabel resultLabel = new JLabel("Results: ");
		Dimension rsize = resultLabel.getPreferredSize();
		resultLabel.setBounds(200, 500, rsize.width, rsize.height);

		//Create Evaluate All Instances button and give it an action listener
		JButton button = new JButton();
		button.setText("Evaluate All Instances");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//delete the previous table
				if(table!=null){
					panel.remove(scrollPane);
				}

				circ.giveSolver();
				boolean[] res = circ.solver.solveAll();

				//All this is just to build the table 
				//TODO: Should probably go back through and use Zach's code in Solver for this instead of rewriting it
				Boolean[] states = new Boolean[inputList.size()];
				String[] cols = new String[inputList.size()+1];
				Object[][] data = new Object[(int) Math.pow(2, (double)inputList.size())][inputList.size()+1];

				for(int i = 0; i<inputList.size(); i++){
					cols[i] = String.valueOf(inputList.get(i).tag);
				}

				for(int i = 0; i<Math.pow(2, (double)inputList.size()); i++){
					for(int j=0; j<inputList.size(); j++){
						String binaryString = Integer.toBinaryString(i);
						while (binaryString.length() != inputList.size()) {
							binaryString = "0" + binaryString;
						}
						char[] binaryChars = binaryString.toCharArray();
						for (int k = 0; k < binaryChars.length; k++) {
							states[k] = (binaryChars[j] == '1');
							data[i][j] = states[k];
						}
					}
					data[i][inputList.size()] = res[i];
				}

				cols[inputList.size()] = "Result";

				table = new JTable(data, cols);
				scrollPane = new JScrollPane(table);
				
				//Format the table and add it to the panel
				Dimension tablesize = table.getPreferredSize();
				if (tablesize.height < 150){
					scrollPane.setBounds(280, 500, tablesize.width, tablesize.height+22);
				}
				else{
					scrollPane.setBounds(280, 500, tablesize.width, 150);
				}
				panel.add(scrollPane);
				
				//Solver changes whether gates are active or not, so this updates the labels to match
				for(int i=0;i<inputList.size();i++){
					if(inputList.get(i).getLabel()!=null){
						inputList.get(i).getLabel().setText(String.valueOf(inputList.get(i).isActive()));
					}
				}
				frame.add(panel);
				//updateUI();

			}

		});
		
		Dimension bsize = button.getPreferredSize();
		button.setBounds(5, 30, bsize.width, bsize.height);

		//Create button to Evaluate single instance and give it an action listener
		JButton button2 = new JButton();
		button2.setText("Evaluate Single Instance");
		button2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//delete the old table
				if(table!=null){
					panel.remove(scrollPane);
				}
				circ.giveSolver();

				//build table
				Boolean[] states = new Boolean[inputList.size()];
				String[] cols = new String[inputList.size()+1];
				Object[][] data = new Object[1][inputList.size()+1];
				for(int i = 0; i<inputList.size(); i++){
					states[i] = inputList.get(i).isActive();
					cols[i] = String.valueOf(inputList.get(i).tag);
					data[0][i] = inputList.get(i).isActive();
				}

				cols[inputList.size()] = "Result";
				Boolean res = circ.solver.solveInstance(states);

				//format table
				table = new JTable(data, cols);
				table.setValueAt(res, 0, inputList.size());
				scrollPane = new JScrollPane(table);
				Dimension tablesize = table.getPreferredSize();
				scrollPane.setBounds(280, 500, tablesize.width, tablesize.height+22);

				panel.add(scrollPane);
				frame.add(panel);
				//updateUI();	
			}

		});
		Dimension bsize2 = button2.getPreferredSize();
		button2.setBounds(200, 30, bsize2.width, bsize2.height);

		//Add all the elements to the UI
		panel.add(label);
		panel.add(button, BorderLayout.PAGE_END);
		panel.add(button2);
		panel.add(resultLabel);
		frame.add(panel);
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);

		//create listener for right click popup menu
		frameListener = new PopupListener();
		frame.addMouseListener(frameListener);

		frame.setVisible(true);
	}

	static void displayGate(JLabel label1, String imageFile){

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

		label1.setIcon(icon);

		Dimension lsize = label1.getPreferredSize();
		//Listener to allow image to be dragged
		label1.addMouseMotionListener(new DragImageListener(){});
		label1.addMouseListener(new DragImageListener(){});
		label1.setBounds(100, 100, lsize.width, lsize.height);

		//Listener on image to create child gates
		if(((Gate) label1).isInput()){
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

		//If you've already added a gate, create submenu for child gates
		popup = new JPopupMenu();
		if(!first){
			submenu = new JMenu("Add Child Gate");
		}

		//creating popup menu
		JMenuItem and = new JMenuItem("Add AND Gate");

		//if Add AND Gate is clicked, call displayGate with ANDimage.png
		and.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AND newGate = new AND(null, null);
				//if top level gate, set as new parentGate, otherwise call addChildGate on parentGate
				if(!first){
					//Only allow for 2 children
					try {
						parentGate.addChildGate(newGate);
						displayGate(newGate, "ANDimage.png");
						parentGate.addConnector(newGate);
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = newGate;
					circ.setTop(parentGate);
					displayGate(newGate, "ANDimage.png");
					first = false;
				}

				gateList.add(newGate);
			}
		});

		JMenuItem or = new JMenuItem("Add OR Gate");
		or.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OR newGate = new OR(null, null);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate(newGate, "ORimage.png");
						parentGate.addConnector(newGate);
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = newGate;
					circ.setTop(parentGate);
					displayGate(newGate, "ORimage.png");
					first = false;
				}
				gateList.add(newGate);
			}
		});

		JMenuItem not = new JMenuItem("Add NOT Gate");
		not.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gate tmp = null;
				NOT newGate = new NOT(tmp);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate(newGate, "NOTimage.png");
						parentGate.addConnector(newGate);
					} catch (InvalidNodeException e1) {
						System.out.println("NOT can only have 1 child");
					}				
				}
				else{
					parentGate = newGate;
					circ.setTop(parentGate);
					displayGate(newGate, "NOTimage.png");
					first = false;
				}
				gateList.add(newGate);
			}
		});

		JMenuItem nand = new JMenuItem("Add NAND Gate");
		nand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAND newGate = new NAND(null, null);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate(newGate, "NANDimage.png");
						parentGate.addConnector(newGate);
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = newGate;
					circ.setTop(parentGate);
					displayGate(newGate, "NANDimage.png");
					first = false;
				}
				gateList.add(newGate);
			}
		});

		JMenuItem nor = new JMenuItem("Add NOR Gate");
		nor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NOR newGate = new NOR(null, null);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate(newGate, "NORimage.png");
						parentGate.addConnector(newGate);
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = newGate;
					circ.setTop(parentGate);
					displayGate(newGate, "NORimage.png");
					first = false;
				}
				gateList.add(newGate);
			}
		});

		JMenuItem xor = new JMenuItem("Add XOR Gate");
		xor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XOR newGate = new XOR(null, null);
				if(!first){
					try {
						parentGate.addChildGate(newGate);
						displayGate(newGate, "XORimage.png");
						parentGate.addConnector(newGate);
					} catch (InvalidNodeException e1) {
						System.out.println("Cannot add more than 2 children");
					}				
				}
				else{
					parentGate = newGate;
					circ.setTop(parentGate);
					displayGate(newGate, "XORimage.png");
					first = false;
				}
				gateList.add(newGate);
			}
		});

		//To add an input
		JMenuItem inp = new JMenuItem("Add Input");
		inp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Input newGate = new Input(inputTag);
				
				try {
					parentGate.addChildGate(newGate);
					//build the filename using the input tag
					String buildImgName = Character.toString(inputTag) + "image.png";
					displayGate(newGate, buildImgName);
					parentGate.addConnector(newGate);
					//Add to inputList and increment the input tag
					inputList.add(newGate);
					inputTag++;
					
					//Create a new label to display whether the input is active or not
					newGate.addJLabel(new JLabel("Active: "));
					newGate.activeLabel.setText(String.valueOf(newGate.isActive()));
					
					//formatting
					Dimension dim = newGate.activeLabel.getPreferredSize();
					newGate.activeLabel.setBounds(newGate.getX(), newGate.getY()+30, dim.width, dim.height);
					panel.add(newGate.activeLabel);
					gateList.add(newGate);

				} catch (InvalidNodeException e1) {
					System.out.println("Input cannot have children");
				}
			}
		});

		//Remove a gate
		JMenuItem removeGate = new JMenuItem("Remove Item");
		removeGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parentGate.removeGate();
				
				//If removing the top level gate, re-enable the frame listener and allow for a new top level gate to be created
				if(gateList.isEmpty()){
					first = true;
					frameListener = new PopupListener();
					frame.addMouseListener(frameListener);
				}
			}
		});


		//add menu items to the popup menu if first gate
		if(first){
			popup.add(and);
			popup.add(or);
			popup.add(not);
			popup.add(nand);
			popup.add(nor);
			popup.add(xor);
		}
		//add menu items to the submenu if not first gate
		else{
			submenu.add(and);
			submenu.add(or);
			submenu.add(not);
			submenu.add(nand);
			submenu.add(nor);
			submenu.add(xor);
			popup.add(submenu);
			popup.add(inp);
			popup.add(removeGate);
		}
		
		//remove frame listener so it's only possible to add child gates after first gate
		frame.removeMouseListener(frameListener);
	}

	//Right click options for inputs
	public static void addInputMenu() {
		inputMenu = new JPopupMenu();

		JMenuItem ACTIVE = new JMenuItem("Make Active");
		ACTIVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Make Active");
				((Input) parentGate).setActive(true);
				((Input) parentGate).activeLabel.setText(String.valueOf(parentGate.isActive()));
			}

		});

		JMenuItem INACTIVE = new JMenuItem("Make Inactive");
		INACTIVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Make Inactive");
				((Input) parentGate).setActive(false);
				((Input) parentGate).activeLabel.setText(String.valueOf(parentGate.isActive()));
			}

		});
		
		JMenuItem removeGate = new JMenuItem("Remove Item");
		removeGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parentGate.removeGate();
			}
		});
		
		inputMenu.add(ACTIVE);
		inputMenu.add(INACTIVE);
		inputMenu.add(removeGate);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} });

	}
}
