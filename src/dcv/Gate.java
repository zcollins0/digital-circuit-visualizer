package dcv;

// Abstract class to represent logic gates. 
// Has a constructor for convenience in child classes.
// Only supports 2-input gates right now. Possible future enhancement is to add 3-input gates.
abstract class Gate {

	Gate topChild;
	Gate bottomChild;
	
	Gate(Gate top, Gate bottom) {
		topChild = top;
		bottomChild = bottom;
	}
	
	abstract boolean isActive();
}
