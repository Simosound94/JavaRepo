package sample5_FINIRE;

import java.io.Serializable;
import java.net.InetAddress;

public class PrintRequest implements Serializable {
	private String textToPrint;
	private String printerName;
	private String orientation; // "portrait"/"landscape"
	private int requestId;
	private String senderAddrForResponse;
	private int senderPortForResponse;
	
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
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	public String getSenderAddr() {
		return senderAddrForResponse;
	}
	public void setSenderAddr(String senderAddr) {
		this.senderAddrForResponse = senderAddr;
	}
	public int getSenderPort() {
		return senderPortForResponse;
	}
	public void setSenderPort(int senderPort) {
		this.senderPortForResponse = senderPort;
	}
	
	
}

