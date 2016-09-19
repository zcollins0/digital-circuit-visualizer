package dcv;

abstract class Gate {

	Gate topChild;
	Gate bottomChild;
	
	Gate(Gate top, Gate bottom) {
		topChild = top;
		bottomChild = bottom;
	}
	
	abstract boolean isActive();
}
