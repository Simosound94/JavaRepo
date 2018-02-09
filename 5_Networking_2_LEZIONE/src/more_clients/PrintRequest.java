package more_clients;

import java.io.Serializable;

public class PrintRequest implements Serializable {
	private String textToPrint;
	private String printerName;
	private String orientation; // "portrait"/"landscape"
	
	public String getTextToPrint() {
		return textToPrint;
	}
	public void setTextToPrint(String textToPrint) {
		this.textToPrint = textToPrint;
	}
	public String getPrinterName() {
		return printerName;
	}
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	public String toString() {
		return "Text: " + this.textToPrint + 
				" Printer: " + this.printerName + 
				" Orient: " + this.orientation;
	}
	
	
}
