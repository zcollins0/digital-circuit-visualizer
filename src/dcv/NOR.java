package dcv;

// Two input NOR gate - active when no inputs are active
public class NOR extends OR {
	
	public NOR(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	public boolean isActive() {
		return !(super.isActive());
	}
}
