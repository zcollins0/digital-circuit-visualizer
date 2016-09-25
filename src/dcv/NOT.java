package dcv;

// 1-input inverter. Should only have one child.
public class NOT extends Gate {

	public NOT(Gate top, Gate bottom) {
		super(top, null);
	}

	@Override
	public boolean isActive() {
		return (topChild != null) ? !topChild.isActive() : !bottomChild.isActive();
	}
	
	@Override
	public void addChildGate(Gate g, childPosition pos) throws InvalidNodeException {
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
