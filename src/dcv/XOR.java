package dcv;

public class XOR extends Gate {

	public XOR(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return (topChild.isActive() != bottomChild.isActive());
	}

}
