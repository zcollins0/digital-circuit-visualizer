package dcv;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupListener extends MouseAdapter{

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			System.out.println("Right Clicked");
			if(!DigitalCircuitUI.first){
				DigitalCircuitUI.parentGate = (Gate) (e.getComponent());
			}
			DigitalCircuitUI.addGateMenu();
			DigitalCircuitUI.popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}


}