package dcv;

public class NOT extends Gate {

	@Override
	boolean isActive() {
		return (topChild != null) ? !topChild.isActive() : !bottomChild.isActive();
	}

}
