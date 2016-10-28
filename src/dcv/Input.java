package dcv;

import javax.swing.ImageIcon;

// Class to implement an input. Should not have any children.
// The value of the input is explicitly set when solving the circuit.
public class Input extends Gate {
	
	// The name of the input (A, B, C...)
	char tag;
	
	public Input(char tag) {
		super(null, null);
		this.tag = tag;
	}
	
	public Input(ImageIcon img){
		super(img);
	}
	
	private boolean signal;
	
	@Override
	public boolean isInput(){
		return true;
	}
	
	@Override
	public boolean isActive() {
		return signal;
	}
	
	@Override
	public void addChildGate(Gate g)  throws InvalidNodeException {
		throw new InvalidNodeException("Cannot give child to input.");
	}
	
	public void setActive(boolean active) {
		signal = active;
	}

}
