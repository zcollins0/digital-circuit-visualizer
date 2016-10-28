package dcv;

import javax.swing.ImageIcon;

// Two input NAND gate - active when no or one input is active
public class NAND extends AND {
	
	public NAND(Gate top, Gate bottom) {
		super(top, bottom);
	}
	
	public NAND(ImageIcon img){
		super(img);
	}

	@Override
	public boolean isActive() {
		return !(super.isActive());
	}
}
