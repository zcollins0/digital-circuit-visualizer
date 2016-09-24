package dcv;

// Two input NOR gate - active when no inputs are active
public class NOR extends OR {
	
	NOR(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return !(super.isActive());
	}
}
