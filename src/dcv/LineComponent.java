package dcv;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LineComponent extends JPanel{

	public static final ArrayList<LineComponent> lines = new ArrayList<LineComponent>();
	protected int X1, Y1, X2, Y2;

	private static class Line2D{
		final int x1, y1, x2, y2;

		public Line2D(int x1, int y1, int x2, int y2){
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	public void addLine(int x1, int x2, int x3, int x4){
		this.X1=x1; this.Y1=x2; this.X2=x3; this.Y2=x4;
		lines.add(this);

		repaint();
	}

	public void redrawLine(int x1, int x2, int x3, int x4){
		lines.remove(this);
		Container parent = this.getParent();
		if (parent!=null){
			parent.remove(this);
			parent.revalidate();
		}
		addLine(x1, x2, x3, x4);
	}

	public Rectangle getDimensions(){
		return new Rectangle(X1, Y1, X2, Y2);
	}
	
	public void setDimensions(int x1, int x2, int x3, int x4){
		this.X1=x1; this.Y1=x2; this.X2=x3; this.Y2=x4;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		for(LineComponent line: lines){
			g2d.drawLine(line.X1, line.Y1,line.X2,line.Y2);
		}
	}
}
