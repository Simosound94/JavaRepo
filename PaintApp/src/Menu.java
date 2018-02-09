import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu {
	JMenuBar menu;
	
	public Menu(){
		menu = new JMenuBar();
		JMenu file = new JMenu();
		file.setText("File");
		JMenu about = new JMenu();
		about.setText("About");
		
		JMenuItem newFile = new JMenuItem();
		newFile.setText("New");
		JMenuItem saveFile = new JMenuItem();
		saveFile.setText("Save");
		JMenuItem exit = new JMenuItem();
		exit.setText("Exit");
		
		file.add(newFile);
		file.add(saveFile);
		file.add(exit);
		
		menu.add(file);
		menu.add(about);
	}
}
