package dcv;

// Two input AND gate - active when both of its inputs are high
public class AND extends Gate {
	
	AND(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return (topChild.isActive() && bottomChild.isActive());
	}
}
