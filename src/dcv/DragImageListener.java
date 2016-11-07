package dcv;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DragImageListener extends JFrame implements MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void mouseClicked(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) {
		//if mouse button held down, get coordinates and set drag to true
		DigitalCircuitUI.drag=true;
		DigitalCircuitUI.clickX = e.getX();
		DigitalCircuitUI.clickY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//if mouse is released, set stop dragging
		DigitalCircuitUI.drag = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		//if drag is true, set the location of the component being dragged
		if(DigitalCircuitUI.drag==true){
			DigitalCircuitUI.mouseX = e.getX();
			DigitalCircuitUI.mouseY = e.getY();
			JComponent jc = (JComponent)e.getSource();
			//some offsetting was necessary to get the image to move smoothly and appear in the correct location
			jc.setLocation(jc.getX()+e.getX()-DigitalCircuitUI.clickX, jc.getY()+e.getY()-DigitalCircuitUI.clickY);
			if(((Gate)jc).isInput()){
				Input in = ((Input) jc);
				in.active.setLocation(jc.getX()+e.getX()-DigitalCircuitUI.clickX, jc.getY()+e.getY()-DigitalCircuitUI.clickY+30);
			}
		}	
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

}
