package dcv;

import javax.swing.ImageIcon;

// Two input XOR gate - active when one but not both of its inputs is active
public class XOR extends Gate {

	public XOR(Gate top, Gate bottom) {
		super(top, bottom);
	}
	
	public XOR(ImageIcon img){
		super(img);
	}

	@Override
	public Boolean isActive() {
		return (child1.isActive() != child2.isActive());
	}

}
