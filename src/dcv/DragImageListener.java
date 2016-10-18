package dcv;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DragImageListener extends JFrame implements MouseMotionListener, MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
			DigitalCircuitUI.drag=true;
			DigitalCircuitUI.clickX = e.getX();
			DigitalCircuitUI.clickY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		DigitalCircuitUI.drag = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(DigitalCircuitUI.drag==true){
			DigitalCircuitUI.mouseX = e.getX();
			DigitalCircuitUI.mouseY = e.getY();
			JComponent jc = (JComponent)e.getSource();
			jc.setLocation(jc.getX()+e.getX()-DigitalCircuitUI.clickX, jc.getY()+e.getY()-DigitalCircuitUI.clickY);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

}
