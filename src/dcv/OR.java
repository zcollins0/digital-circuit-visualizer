package dcv;

// Two input OR gate - active as long as either or both of its inputs is high
class OR extends Gate {

	OR(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return (topChild.isActive() || bottomChild.isActive());
	}

}
