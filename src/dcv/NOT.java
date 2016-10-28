package dcv;

import javax.swing.ImageIcon;

// 1-input inverter. Should only have one child.
public class NOT extends Gate {

	public NOT(Gate top) {
		super(top, null);
	}
	
	public NOT(ImageIcon img){
		super(img);
	}

	@Override
	public boolean isActive() {
		return (child1 != null) ? !child1.isActive() : !child2.isActive();
	}
	
	@Override
	public void addChildGate(Gate g) throws InvalidNodeException {
		if (child1 != null) {
			throw new InvalidNodeException("Inverter already has a child.");
		}
		else {
			// Note: never allow NOt gate to have bottom child
			child1 = g;
		}
	}
}
