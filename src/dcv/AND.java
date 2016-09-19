package dcv;

public class AND extends Gate {
	
	@Override
	boolean isActive() {
		return (topChild.isActive() && bottomChild.isActive());
	}
}
