package dcv;

// Two input OR gate - active as long as either or both of its inputs is high
public class OR extends Gate {

	public OR(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	public boolean isActive() {
		return (child1.isActive() || child2.isActive());
	}

}
