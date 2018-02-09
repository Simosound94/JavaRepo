import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.util.*;

public class DoodleFrameWithMemory extends JPanel {

	
	public DoodleFrameWithMemory(int V_SIZE, int H_SIZE){
		this.setSize(H_SIZE, V_SIZE*11/12);
		this.memory = new ArrayList<Line2D>();
		
		this.addMouseListener(new HandleMouse());
		this.addMouseMotionListener(new HandleMouseMotion());
		
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		for (Line2D l : memory) {
			Graphics2D g2 = (Graphics2D)g;
			g2.draw(l);
		}
	}
	
	private int lastX;
	private int lastY;
	private ArrayList<Line2D> memory;

	private class HandleMouse extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			lastX = e.getX();
			lastY = e.getY();
		}
	}
	
	private class HandleMouseMotion extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			Line2D nextLine = new Line2D.Double(lastX, lastY, x, y);
			memory.add(nextLine);
			lastX = x;
			lastY = y;
			repaint();
		}
	}
}
