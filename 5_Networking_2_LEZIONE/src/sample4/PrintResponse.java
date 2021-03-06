package sample4;

import java.io.Serializable;
import java.net.InetAddress;

public class PrintResponse implements Serializable {
	private String errorCode;
	private String errorDesc;
	
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
	
	
	
}

