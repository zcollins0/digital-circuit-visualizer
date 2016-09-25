package dcv;

// Two input NAND gate - active when no or one input is active
public class NAND extends AND {
	
	public NAND(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	public boolean isActive() {
		return !(super.isActive());
	}
}
