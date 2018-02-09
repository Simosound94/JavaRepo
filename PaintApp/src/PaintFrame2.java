import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PaintFrame2 extends JFrame {
	
	private JPanel   canvas  = new Canvas();
	private JToolBar toolBox = new PaintBar((CanvasInterface) canvas);
	private JMenuBar menu    = new JMenuBar();
	
	public PaintFrame2(String title){
		super(title);
		//canvas.setPreferredSize(new Dimension(800,600));
		add(toolBox, BorderLayout.NORTH);
		add(new JScrollPane(canvas), BorderLayout.CENTER);	
		
		makeMenu();
		setJMenuBar(menu);
	}
	
	private void  makeMenu(){
		JMenu FileMenu = new JMenu("File");
		menu.add(FileMenu);

		JMenu AboutMenu = new JMenu("About");
		menu.add(AboutMenu);
		
		JMenuItem FileNew = new JMenuItem("New");
		FileNew.addActionListener(new NewListener((CanvasInterface)canvas));
		FileMenu.add(FileNew);
		
		JMenuItem FileExit = new JMenuItem("Exit");
		FileExit.addActionListener(new ExitListener(this));
		FileMenu.add(FileExit);
		
		JMenuItem AboutAboutMe = new JMenuItem("About Paint");
		AboutAboutMe.addActionListener(new AboutListener(this));
		AboutMenu.add(AboutAboutMe);
	}
	
	private class ExitListener implements ActionListener {
		private JFrame parent;
		
		public ExitListener(JFrame p){
			parent = p;
		}
		public void actionPerformed(ActionEvent e) {
			Object[] options = { "Ok", "Cancel" };
			int answer = -1;
			answer = JOptionPane.showOptionDialog(parent, "Would you really like to exit?", 
					"Paint - Warning", JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			
			if(answer == 0)
				System.exit(0);
		}
	}
	private class AboutListener implements ActionListener{
		private JFrame parent;
		
		public AboutListener(JFrame p){
			parent = p;
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(parent, "A simple paint application", 
					"Paint - About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private class NewListener implements ActionListener{
		private CanvasInterface canvasRef;
		
		public NewListener(CanvasInterface canvas){
			canvasRef = canvas;
		}
		
		public void actionPerformed(ActionEvent e) {
			canvasRef.clear();
		}
	}

}
