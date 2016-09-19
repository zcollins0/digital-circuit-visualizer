package dcv;

class Input extends Gate {
	boolean signal;
	
	@Override
	boolean isActive() {
		return signal;
	}

}
