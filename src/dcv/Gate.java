package dcv;

// Abstract class to represent logic gates. 
// Has a constructor for convenience in child classes.
// Only supports 2-input gates right now. Possible future enhancement is to add 3-input gates.
abstract class Gate {
	
	enum childPosition {
		// TODO: Consider implementation for 3 input gate
		POS_TOP,
		POS_BOTTOM
	}

	Gate topChild;
	Gate bottomChild;
	
	Gate(Gate top, Gate bottom) {
		topChild = top;
		bottomChild = bottom;
	}
	
	void addChildGate(Gate g, childPosition pos) throws InvalidNodeException {
		if (pos == childPosition.POS_TOP) {
			topChild = g;
		}
		else {
			bottomChild = g;
		}
	}
	
	abstract boolean isActive();
}
