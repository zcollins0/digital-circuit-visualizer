package dcv;

class NOR extends OR {
	
	@Override
	boolean isActive() {
		return !(super.isActive());
	}
}
