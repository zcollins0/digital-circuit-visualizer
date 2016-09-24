package dcv;

// An exception to be used if someone is trying to place a node in the wrong place
public class InvalidNodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvalidNodeException(String message) {
		System.out.println(message);
	}
}
