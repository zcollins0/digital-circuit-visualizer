package dcv;

// 1-input inverter. Should only have one child.
public class NOT extends Gate {

	public NOT(Gate top) {
		super(top, null);
	}

	@Override
	public boolean isActive() {
		return (topChild != null) ? !topChild.isActive() : !bottomChild.isActive();
	}
	
	@Override
	public void addChildGate(Gate g, childPosition pos) throws InvalidNodeException {
		if (topChild != null) {
			throw new InvalidNodeException("Inverter already has a child.");
		}
		else {
			// Note: never allow NOt gate to have bottom child
			topChild = g;
		}
	}
}
