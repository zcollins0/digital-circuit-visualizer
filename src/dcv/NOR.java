package dcv;

import javax.swing.ImageIcon;

// Two input NOR gate - active when no inputs are active
public class NOR extends OR {

	public NOR(Gate top, Gate bottom) {
		super(top, bottom);
	}
	
	public NOR(ImageIcon img){
		super(img);
	}

	@Override
	public boolean isActive() {
		return !(super.isActive());
	}
}
