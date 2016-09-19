package dcv;

import java.lang.InstantiationException;

abstract class Gate {
	Gate topChild;
	Gate bottomChild;
	
	boolean isActive() throws InstantiationException {
		throw new InstantiationException("Cannot create a class of generic type Gate");
	}
}
