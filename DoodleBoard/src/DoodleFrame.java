import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class DoodleFrame extends JFrame {
	
	public static final int H_SIZE = 400;
	public static final int V_SIZE = 400;
	
	public DoodleFrame() {
		this.setSize(V_SIZE, H_SIZE);
		this.setVisible(true);
		scrivi = false;
		
		// Devo collegare gli ascoltatori definiti al JFrame
		this.addMouseListener(new HandleMouse());
		this.addMouseMotionListener(new HandleMouseMotion());
	}

	
	private int lastX, lastY;
	private boolean scrivi;
	
	private class HandleMouse implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
						
		}

		public void mouseExited(MouseEvent e) {
					
		}

		public void mousePressed(MouseEvent e) {
//			System.out.println("Mouse pressed at: " +e.getX()+" , "+e.getY());
			if(scrivi == false)	scrivi = true;
			else scrivi = false;
			lastX = e.getX();
			lastY = e.getY();
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
		
	}
	
	private class HandleMouseMotion implements MouseMotionListener{
		/*
		 * MouseMotionEvent lancia un evento non ogni muovimento ma quando lo riesce a lanciare (circa)
		 * se muovo il mouse lentamente ne lancia di più
		 * rispetto a se lo muovo lentamente
		 */

		public void mouseDragged(MouseEvent e) {
//			System.out.println("Mouse dragged to: " +e.getX()+" , "+e.getY()+" button: "+e.getButton());
			
			/*
			 * getGraphics è una funzione per disegnare qualcosa su un JFrame
			 * appartiene alla classe JFrame (estesa dalla classe padre del listener)
			 */
			
				Graphics2D g = (Graphics2D)getGraphics();
				g.drawLine(e.getX(), e.getY(), lastX, lastY);
				lastX = e.getX();
				lastY = e.getY();
		}


		public void mouseMoved(MouseEvent e) {
			if(scrivi){
				Graphics2D g = (Graphics2D)getGraphics();
				g.drawLine(e.getX(), e.getY(), lastX, lastY);
				lastX = e.getX();
				lastY = e.getY();
			}
		}
		
	}
}
