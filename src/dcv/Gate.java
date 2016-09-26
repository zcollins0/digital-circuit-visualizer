package dcv;

// Abstract class to represent logic gates. 
// Has a constructor for convenience in child classes.
// Only supports 2-input gates right now. Possible future enhancement is to add 3-input gates.
public abstract class Gate {
	
	public enum childPosition {
		// TODO: Consider implementation for 3 input gate
		POS_TOP,
		POS_BOTTOM
	}

	Gate topChild;
	Gate bottomChild;
	
	public Gate(Gate top, Gate bottom) {
		topChild = top;
		bottomChild = bottom;
	}
	
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
	
	public void addInput() throws InvalidNodeException {
		if (topChild != null) {
			Input tempInput = new Input(null, null, DigitalCircuitUI.inputTag);
			DigitalCircuitUI.inputTag++;
			topChild = tempInput;
			DigitalCircuitUI.inputList.add(tempInput);
		}
		else if (bottomChild != null) {
			Input tempInput = new Input(null, null, DigitalCircuitUI.inputTag);
			DigitalCircuitUI.inputTag++;
			bottomChild = tempInput;
			DigitalCircuitUI.inputList.add(tempInput);
		}
		else {
			throw new InvalidNodeException("The selected gate is full.");
		}
	}
	
	public abstract boolean isActive();
}
