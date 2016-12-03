package dcv;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 1-input inverter. Should only have one child.
public class NOT extends Gate {
	
	public NOT(Gate top) {
		super(top, null);
	}
	
	public NOT(ImageIcon img){
		super(img);
	}

	@Override
	public Boolean isActive() {
		return (child1 != null) ? !child1.isActive() : !child2.isActive();
	}
	
	@Override
	public void addChildGate(Gate g) throws InvalidNodeException {
		if (child1 != null) {
			throw new InvalidNodeException("Inverter already has a child.");
		}
		else {
			// Note: never allow NOt gate to have bottom child
			child1 = g;
			child1.pGate = this;
		}
	}

	@Override
	public JLabel getLabel() {
		return null;
	}
	
	@Override
	public void addConnector(Gate child){
		int startx = this.getLocation().x;
		int starty = this.getLocation().y;
		int endx = child.getLocation().x;
		int endy = child.getLocation().y;
		this.childoffsety = child.getHeight()/2;
		this.childoffsetx = child.getWidth();
		System.out.println(this.childoffsetx);
		this.parentoffsety = this.getHeight()/2;
		child.comp = new LineComponent();
		child.comp.setPreferredSize(new Dimension(500,500));
		child.comp.setBounds(0,0,800,800);
		
		child.comp.addLine(startx, starty+parentoffsety, endx+childoffsetx, endy+childoffsety);
		
		DigitalCircuitUI.panel.add(child.comp);
		DigitalCircuitUI.frame.add(DigitalCircuitUI.panel);
		//DigitalCircuitUI.updateUI();
	}
}
