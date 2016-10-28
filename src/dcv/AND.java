package dcv;

// Two input AND gate - active when both of its inputs are high
public class AND extends Gate {
	
	public AND(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	public boolean isActive() {
		return (child1.isActive() && child2.isActive());
	}
}
