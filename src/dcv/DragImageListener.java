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
	
	public void updateLocation(Gate child){
		Point p = child.getLocation();
		int x1=p.x+child.pGate.childoffsetx;
		int y1=p.y+child.pGate.childoffsety;
		
		Point pDims = child.pGate.getLocation();
		int x2 = pDims.x;
		int y2;
		
		if(child == child.pGate.child1){
			y2 = pDims.y+child.pGate.parentoffsety;
		}
		else{
			y2 = pDims.y+child.pGate.parentoffsety*2;
		}
		
		child.comp.redrawLine(x1,y1,x2,y2);
		DigitalCircuitUI.updateUI();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//if mouse is released, set stop dragging
		DigitalCircuitUI.drag = false;
		JComponent jc = (JComponent)e.getSource();
		if(((Gate)jc).pGate!=null){
			Point p = ((Gate)jc).getLocation();
			int x1=p.x+((Gate)jc).pGate.childoffsetx;
			int y1=p.y+((Gate)jc).pGate.childoffsety;
			Point pDims = ((Gate)jc).pGate.getLocation();
			int x2 = pDims.x;
			int y2;
			
			if((Gate) jc == ((Gate)jc).pGate.child1){
				y2 = pDims.y+((Gate)jc).pGate.parentoffsety;
			}
			else{
				y2 = pDims.y+((Gate)jc).pGate.parentoffsety*2;
			}
	
			((Gate)jc).comp.redrawLine(x1,y1,x2,y2);
			DigitalCircuitUI.updateUI();
		}	
		if(((Gate)jc).child1 != null){
			updateLocation(((Gate)jc).child1);
		}
		if(((Gate)jc).child2 != null){
			updateLocation(((Gate)jc).child2);
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
