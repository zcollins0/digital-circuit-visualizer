package dcv;

abstract class Gate {
	Gate topChild;
	Gate bottomChild;
	
	abstract boolean isActive();
}
