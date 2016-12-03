package dcv;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DragImageListener extends JFrame implements MouseMotionListener, MouseListener {
	int x2, y2;
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
		JComponent jc = (JComponent)e.getSource();
		if(((Gate)jc).pGate!=null){
			Point p = ((Gate)jc).getLocation();
			Rectangle r = ((Gate)jc).comp.getDimensions();
			int x1=p.x+((Gate)jc).pGate.childoffsetx;// +e.getX()-DigitalCircuitUI.clickX;
			System.out.println(((Gate)jc).pGate.childoffsetx);
			int y1=p.y+((Gate)jc).pGate.childoffsety;//+e.getY()-DigitalCircuitUI.clickY;
			Point pDims = ((Gate)jc).pGate.getLocation();
			int x2 = pDims.x;
			int y2 = pDims.y+((Gate)jc).pGate.parentoffsety;
			//System.out.println((Gate)jc);
			//System.out.println(((Gate)jc).pGate);
			((Gate)jc).comp.redrawLine(x1,y1,x2,y2);
			DigitalCircuitUI.updateUI();
			System.out.println("(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")");
		}	
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
				in.activeLabel.setLocation(jc.getX()+e.getX()-DigitalCircuitUI.clickX, jc.getY()+e.getY()-DigitalCircuitUI.clickY+30);
			}		
		}	
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

}
