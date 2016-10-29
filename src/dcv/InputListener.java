package dcv;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			DigitalCircuitUI.parentGate = (Gate) (e.getComponent());
			DigitalCircuitUI.addInputMenu();
			DigitalCircuitUI.inputMenu.show(e.getComponent(), e.getX(), e.getY());
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
