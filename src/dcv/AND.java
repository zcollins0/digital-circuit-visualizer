package dcv;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// Two input AND gate - active when both of its inputs are high
public class AND extends Gate {

	public AND(ImageIcon img){
		super(img);
	}
	
	public AND(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	public Boolean isActive() {
		return (child1.isActive() && child2.isActive());
	}

	@Override
	public JLabel getLabel() {
		return null;
	}
}
