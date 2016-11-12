package dcv;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
// Abstract class to represent logic gates. 
// Has a constructor for convenience in child classes.
// Only supports 2-input gates right now. Possible future enhancement is to add 3-input gates.
public abstract class Gate extends JLabel{

	// Enum for which position to put a child gate in
	public enum childPosition {
		// TODO: Consider implementation for 3 input gate
		POS_TOP,
		POS_BOTTOM
	}

	Gate child1 = null;
	Gate child2 = null;
	JLabel label = null;
	
	public Gate(ImageIcon img){
		super(img);
	}
	
	// Constructor for convenience in child classes
	public Gate(Gate top, Gate bottom) {
		child1 = top;
		child2 = bottom;
	}
	
	public boolean isInput(){
		return false;
	}
	// Method to add a child to selected gate
	public void addChildGate(Gate g) throws InvalidNodeException {
			if (child1 == null) {
				child1 = g;
			}
			else if(child2 == null) {
				child2 = g; 
				//System.out.println("Child 1 position is occupied.Putting in child 2 position");
			}
			else {
				throw new InvalidNodeException("Child 2 position is occupied.");
			}
	}
	
	public void addConnector(Gate parent, Gate child){
		int startx = parent.getLocation().x;
		int starty = parent.getLocation().y;
		double offset = parent.getHeight()/3;
		FileInputStream istream = null;
		BufferedImage image = null;
		try {
			istream = new FileInputStream("verticalLine.png");
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

		/*Graphics2D g = (Graphics2D) image.getGraphics();
		g.rotate(Math.toRadians(90), 0,0);
		g.drawImage(image, 0, 0, null);*/
		Image temp = image.getScaledInstance(10, 50, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(temp);
		
		JLabel label1 = new JLabel();
		label = label1;
		label1.setIcon(icon);
		label1.setBounds(startx-5, starty-40, 10, 50);
		DigitalCircuitUI.labels.add(label1);
		DigitalCircuitUI.updateUI();
	}

	
	// Returns whether the gate is active based on its inputs
	public abstract Boolean isActive();
}
