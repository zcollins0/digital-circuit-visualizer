package dcv;

// 1-input inverter. Should only have one child.
public class NOT extends Gate {

	NOT(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return (topChild != null) ? !topChild.isActive() : !bottomChild.isActive();
	}
	
	@Override
	void addChildGate(Gate g, childPosition pos) throws InvalidNodeException {
		if (topChild != null || bottomChild != null) {
			throw new InvalidNodeException("Inverter already has a child.");
		}
		else {
			if (pos == Gate.childPosition.POS_TOP) {
				topChild = g;
			}
			else {
				bottomChild = g;
			}
		}
	}

}
