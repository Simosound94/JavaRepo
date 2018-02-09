import java.awt.Color;
import java.awt.Stroke;


public interface CanvasInterface {
	
	public void setColor(Color c);
	public Color getColor();
	
	public void setBackColor(Color c);
	public Color getBackColor();
	
	public void setStroke(Stroke s);
	public Stroke getStroke();
	
	public void clear();

}