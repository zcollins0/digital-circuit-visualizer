package dcv;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChildPopupListener implements MouseListener {



	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			System.out.println("Right Clicked Again");
			DigitalCircuitUI.addChildMenu();
			DigitalCircuitUI.childPopup.show(e.getComponent(), e.getX(), e.getY());
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