package dcv;

class NAND extends AND {
	
	@Override
	boolean isActive() {
		return !(super.isActive());
	}
}
