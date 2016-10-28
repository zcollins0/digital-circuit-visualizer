package dcv;

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

	Gate child1;
	Gate child2;
	
	public Gate(ImageIcon img){
		super(img);
	}
	
	// Constructor for convenience in child classes
	public Gate(Gate top, Gate bottom) {
		child1 = top;
		child2 = bottom;
	}
	
	// Method to add a child to selected gate
	public void addChildGate(Gate g) throws InvalidNodeException {
			if (child1 == null) {
				child1 = g;
			}
			else if(child2 == null) {
				child2 = g; 
				System.out.println("Child 1 position is occupied.Putting in child 2 position");
			}
			else {
				throw new InvalidNodeException("Child 2 position is occupied.");
			}
	}
	
	// Method to add input to selected gate
	public void addInput() throws InvalidNodeException {
		if (child1 != null) {
			Input tempInput = new Input(DigitalCircuitUI.inputTag);
			DigitalCircuitUI.inputTag++;
			child1 = tempInput;
			DigitalCircuitUI.inputList.add(tempInput);
		}
		else if (child2 != null) {
			Input tempInput = new Input(DigitalCircuitUI.inputTag);
			DigitalCircuitUI.inputTag++;
			child2 = tempInput;
			DigitalCircuitUI.inputList.add(tempInput);
		}
		else {
			throw new InvalidNodeException("The selected gate is full.");
		}
	}
	
	// Returns whether the gate is active based on its inputs
	public abstract boolean isActive();
}
