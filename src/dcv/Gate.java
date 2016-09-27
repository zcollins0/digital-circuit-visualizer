package dcv;

// Abstract class to represent logic gates. 
// Has a constructor for convenience in child classes.
// Only supports 2-input gates right now. Possible future enhancement is to add 3-input gates.
public abstract class Gate {
	
	// Enum for which position to put a child gate in
	public enum childPosition {
		// TODO: Consider implementation for 3 input gate
		POS_TOP,
		POS_BOTTOM
	}

	Gate topChild;
	Gate bottomChild;
	
	// Constructor for convenience in child classes
	public Gate(Gate top, Gate bottom) {
		topChild = top;
		bottomChild = bottom;
	}
	
	// Method to add a child to selected gate
	public void addChildGate(Gate g, childPosition pos) throws InvalidNodeException {
		if (pos == childPosition.POS_TOP) {
			if (topChild == null) {
				topChild = g;
			}
			else {
				throw new InvalidNodeException("Top child position is occupied.");
			}
		}
		else {
			if (bottomChild == null) {
				bottomChild = g;
			}
			else {
				throw new InvalidNodeException("Bottom child position is occupied.");
			}
		}
	}
	
	// Method to add input to selected gate
	public void addInput() throws InvalidNodeException {
		if (topChild != null) {
			Input tempInput = new Input(DigitalCircuitUI.inputTag);
			DigitalCircuitUI.inputTag++;
			topChild = tempInput;
			DigitalCircuitUI.inputList.add(tempInput);
		}
		else if (bottomChild != null) {
			Input tempInput = new Input(DigitalCircuitUI.inputTag);
			DigitalCircuitUI.inputTag++;
			bottomChild = tempInput;
			DigitalCircuitUI.inputList.add(tempInput);
		}
		else {
			throw new InvalidNodeException("The selected gate is full.");
		}
	}
	
	// Returns whether the gate is active based on its inputs
	public abstract boolean isActive();
}
