package dcv;

import javax.swing.ImageIcon;

// Two input OR gate - active as long as either or both of its inputs is high
public class OR extends Gate {

	public OR(Gate top, Gate bottom) {
		super(top, bottom);
	}
	
	public OR(ImageIcon img){
		super(img);
	}

	@Override
	public Boolean isActive() {
		return (child1.isActive() || child2.isActive());
	}

}
