import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Vector;

import javax.swing.JPanel;


public class Canvas extends JPanel implements CanvasInterface{

	private class LineInfo{
		public Line2D line;
		public Color color;
		public Stroke stroke;
		
		public LineInfo(Line2D l, Color c, Stroke s){
			line = l;
			color = c;
			stroke = s;
		}
	}

	private Vector<LineInfo> drawing = new Vector<LineInfo>();
	
	int lastX, lastY;
	private Color CurrColor = Color.black;
	private Stroke CurrStroke = new BasicStroke();
	private Color CurrBackColor = Color.white;
	
	public Canvas(){
		super();
		lastX = lastY = 0;
		
		setBackground(CurrBackColor);
		addMouseListener(new MouseLst());
		addMouseMotionListener(new MouseMotionLst());
	}
	

	public void setColor(Color c){
		CurrColor = c;
	}
	public Color getColor(){
		return CurrColor;
	}
	public void setStroke(Stroke s){
		CurrStroke = s;
	}
	public Stroke getStroke(){
		return CurrStroke;
	}
	public Color getBackColor() {
		return CurrBackColor;
	}
	public void setBackColor(Color c) {
		CurrBackColor = c;
		setBackground(CurrBackColor);
		repaint();
	}
	public void clear() {
		drawing.clear();
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		for(LineInfo i : drawing){
			g2.setColor(i.color);
			g2.setStroke(i.stroke);
			g2.draw(i.line);
		}
	}
	
	private void newLine(int x, int y){
		Line2D line = new Line2D.Float(lastX,lastY,x,y);
		drawing.add(new LineInfo(line, CurrColor, CurrStroke));
		repaint();
	}
	
	private class MouseLst extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			lastX = e.getX();
			lastY = e.getY();
		}
		
		public void mouseReleased(MouseEvent e){
			newLine(e.getX(),e.getY());
		}
	}
	
	private class MouseMotionLst extends MouseMotionAdapter{
		
		public void mouseDragged(MouseEvent e){
			newLine(e.getX(),e.getY());
			lastX = e.getX();
			lastY = e.getY();
		}
	}
}

