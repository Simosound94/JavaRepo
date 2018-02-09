package sample5_FINIRE;

import java.io.Serializable;

public class PrintResponse implements Serializable {
	private String errorCode;
	private String errorDesc;
	private String senderAddrForResponse;
	private int senderPortForResponse;
	
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	public String toString() {
		return "Error Code: " + this.errorCode + " Desc: " + this.errorDesc;
	}
	public String getSenderAddrForResponse() {
		return senderAddrForResponse;
	}
	public void setSenderAddrForResponse(String senderAddrForResponse) {
		this.senderAddrForResponse = senderAddrForResponse;
	}
	public int getSenderPortForResponse() {
		return senderPortForResponse;
	}
	public void setSenderPortForResponse(int senderPortForResponse) {
		this.senderPortForResponse = senderPortForResponse;
	}
}
