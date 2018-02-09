import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class PaintFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;
	public static final int H_SIZE = 600;
	public static final int V_SIZE = 1000;
	
	public PaintFrame() {
		this.setSize(V_SIZE, H_SIZE);
		this.setTitle("PaintApp");
		Container cnt = getContentPane();;
		Menu menu = new Menu();
		this.setJMenuBar(menu.menu);
		GridLayout layout = new GridLayout(2,1);
		this.setLayout(layout);
		
		
		ButtonPanel buttons = new ButtonPanel(V_SIZE, H_SIZE);
		this.add(buttons);
		DoodleFrameWithMemory paint = new DoodleFrameWithMemory(V_SIZE, H_SIZE);
		this.add(paint);
		
		this.setVisible(true);
	}
	
}
