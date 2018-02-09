import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	Color penColor;
	JLabel lblColor;
	int span;
	
	
	public ButtonPanel(int V_SIZE, int H_SIZE){
		this.setSize(H_SIZE, V_SIZE/12);
		penColor = Color.BLACK;
		this.setLayout(new FlowLayout());
		
		JPanel colorPanel = new JPanel();
		JPanel spanPanel = new JPanel();
		
		colorPanel.add(new JLabel("Pen color:"));
		lblColor = new JLabel("YEE");
		lblColor.setBackground(penColor);
		colorPanel.add(lblColor);
		colorPanel.setVisible(true);
		
		colorPanel.add(new JLabel("Pen Span:"));
		JComboBox<Integer> spanCbx = new JComboBox<Integer>();
		for(int j = 8; j<27;j+=2){
			spanCbx.addItem(j);
		}
		spanPanel.setName("Span");
		spanPanel.add(spanCbx);
		
		
		this.add(colorPanel);
		this.add(spanPanel);
		
		
		
		
		
		this.setVisible(true);
	}
	
	
}
