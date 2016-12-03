package dcv;

import java.awt.Container;
import java.awt.Dimension;
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
	Gate pGate = null;
	JLabel connectorImg = null;
	public LineComponent comp = null;
	
	int childoffsety;
	int childoffsetx;
	int parentoffsety;

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
			child1.pGate = this;
		}
		else if(child2 == null) {
			child2 = g; 
			child2.pGate = this;
			//System.out.println("Child 1 position is occupied.Putting in child 2 position");
		}
		else {
			throw new InvalidNodeException("Child 2 position is occupied.");
		}
	}

	//function to remove a gate and all it's child gates
	public void removeGate(){
		
		//remove the child gates and set them to null
		while(this.child1!=null){
			this.child1.removeGate();
			this.child1 = null;
		}

		while(this.child2!=null){
			this.child2.removeGate();
			this.child2 = null;
		}

		//remove the gate from gateList and remove the JLabels
		DigitalCircuitUI.gateList.remove(this);
		DigitalCircuitUI.labels.remove(this);
		
		//if gate is an input, remove it from input list
		if(this.isInput()){
			DigitalCircuitUI.inputList.remove(this);
		}
		
		//remove the activeLabel for inputs
		Container parent = this.getParent();
		if(parent != null){
			if(this.isInput()){
				parent.remove(((Input)this).activeLabel);
				//DigitalCircuitUI.inputTag--;
			}
			parent.remove(this);
			parent.revalidate();
			parent.repaint();
		}
		
		//set the parent gate's child/children to null
		if(this.pGate.child1 == this){
			this.pGate.child1 = null;
		}
		
		if(this.pGate.child2 == this){
			this.pGate.child2 = null;
		}
	}

	public void addConnector(Gate child){
		int startx = this.getLocation().x;
		int starty = this.getLocation().y;
		int endx = child.getLocation().x;
		int endy = child.getLocation().y;
		this.childoffsety = child.getHeight()/2;
		this.childoffsetx = child.getWidth();
		System.out.println(this.childoffsetx);
		this.parentoffsety = this.getHeight()/3;
		child.comp = new LineComponent();
		child.comp.setPreferredSize(new Dimension(500,500));
		child.comp.setBounds(0,0,800,800);
		
		child.comp.addLine(startx, starty+parentoffsety, endx+childoffsetx, endy+childoffsety);
		
		DigitalCircuitUI.panel.add(child.comp);
		DigitalCircuitUI.frame.add(DigitalCircuitUI.panel);
		//DigitalCircuitUI.updateUI();
	}


	// Returns whether the gate is active based on its inputs
	public abstract Boolean isActive();
	public abstract JLabel getLabel();
}
