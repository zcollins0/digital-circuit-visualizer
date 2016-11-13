package dcv;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// Class to implement an input. Should not have any children.
// The value of the input is explicitly set when solving the circuit.
public class Input extends Gate {
	
	// The name of the input (A, B, C...)
	char tag;
	private boolean signal;
	JLabel active;	
	
	public Input(char tag) {
		super(null, null);
		this.tag = tag;
	}
	
	public Input(ImageIcon img){
		super(img);
	}
	
	
	@Override
	public boolean isInput(){
		return true;
	}
	
	@Override
	public Boolean isActive() {
		return signal;
	}
	
	@Override
	public void addChildGate(Gate g)  throws InvalidNodeException {
		throw new InvalidNodeException("Cannot give child to input.");
	}
	
	public void setActive(boolean active) {
		signal = active;
	}
	
	public void addJLabel(JLabel lab){
		active = lab;
	}
	
	public JLabel getLabel(){
		return active;
	}

}
