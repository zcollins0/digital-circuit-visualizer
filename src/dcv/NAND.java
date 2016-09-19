package dcv;

public class NAND extends AND {
	
	@Override
	boolean isActive() {
		return !(super.isActive());
	}
}
