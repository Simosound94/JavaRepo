import javax.swing.*;
import java.awt.*;

public class PaintMain {
	
	public static void main(String[] args) {
		JFrame paintFrame = new PaintFrame2("My Paint Application");
		paintFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		paintFrame.setSize(screenSize.width / 2, screenSize.height / 2);
		paintFrame.setLocation(screenSize.width / 4, screenSize.height / 4);

		paintFrame.setVisible(true);
	}
	
}
