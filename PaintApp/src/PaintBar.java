import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PaintBar extends JToolBar {

	private JPanel SelectedColor     = new JPanel();
	private JPanel SelectedBackColor = new JPanel();
	
	public PaintBar(CanvasInterface canvas){
		super();
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		// Color Panel
		JPanel ColorPanel = new JPanel();
		ColorPanel.setBorder(new TitledBorder("Color:"));
		
		JLabel lblColor = new JLabel("Brush Color:");
		lblColor.setLabelFor(SelectedColor);
		SelectedColor.setBackground(canvas.getColor());
		
		ColorPanel.add(lblColor);
		ColorPanel.add(SelectedColor);
		
		JButton color = new JButton("Change...");
		color.addActionListener(new ColorChooser(canvas, true));
		ColorPanel.add(color);
		
		JLabel lblBackColor = new JLabel("Background Color:");
		lblColor.setLabelFor(SelectedBackColor);
		SelectedBackColor.setBackground(canvas.getBackColor());
		
		ColorPanel.add(lblBackColor);
		ColorPanel.add(SelectedBackColor);
		
		JButton backColor = new JButton("Change...");
		backColor.addActionListener(new ColorChooser(canvas, false));
		ColorPanel.add(backColor);
		
		add(ColorPanel);
		
		//Stroke Panel:
		JPanel StrokePanel = new JPanel();
		StrokePanel.setBorder(new TitledBorder("Stroke:"));
		
		JComboBox SizeList = new JComboBox();
		for(float i = 0.5F;i<=5;i += 0.5F){
			SizeList.addItem(i);
		}
		SizeList.setSelectedItem(((BasicStroke) canvas.getStroke()).getLineWidth());
		SizeList.addActionListener(new SizeLst(canvas));
		JLabel lblSize = new JLabel("Stroke Size:");
		lblSize.setLabelFor(SizeList);
		
		StrokePanel.add(lblSize);
		StrokePanel.add(SizeList);
		add(StrokePanel);
	}
	
	private class SizeLst implements ActionListener{
		private CanvasInterface CanvasRef;
		public SizeLst(CanvasInterface canvas){
			CanvasRef = canvas;
		}
		public void actionPerformed(ActionEvent e){
			Stroke newStroke = new BasicStroke((Float) ((JComboBox) e.getSource()).getSelectedItem());
			CanvasRef.setStroke(newStroke);
		}
	}
	
	private class ColorChooser implements ActionListener{
		private CanvasInterface CanvasRef;
		boolean flag = false;
		
		public ColorChooser(CanvasInterface canvas, boolean f){
			CanvasRef = canvas;
			flag = f;
		}
		
		public void actionPerformed(ActionEvent e){
			Color temp;
			temp = JColorChooser.showDialog(null, "My Paint - Choose Color:", 
					SelectedColor.getBackground());
			if(temp == null)
				return;
			else{
				if (flag){
					SelectedColor.setBackground(temp);
					CanvasRef.setColor(temp);
				}
				else{
					SelectedBackColor.setBackground(temp);
					CanvasRef.setBackColor(temp);
				}
			}
		}
		
		
	}

}

