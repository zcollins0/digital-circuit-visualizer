package dcv;

class AND extends Gate {
	
	@Override
	boolean isActive() {
		return (topChild.isActive() && bottomChild.isActive());
	}
}
