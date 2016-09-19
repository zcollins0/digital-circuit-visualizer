package dcv;

class OR extends Gate {

	@Override
	boolean isActive() {
		return (topChild.isActive() || bottomChild.isActive());
	}

}
