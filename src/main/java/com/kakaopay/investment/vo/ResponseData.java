package com.kakaopay.investment.vo;

import com.kakaopay.investment.type.ErrorCodeType;

import java.util.HashMap;

public class ResponseData extends HashMap<String ,Object> {

	private static final long serialVersionUID = 1L;
	
	public ResponseData() {
		this.setResultCode(ErrorCodeType.OK.value);
		this.setResultText("OK");
	}

	public ResponseData(String resultCode, String resultMsg) {
		this.setResultCode(resultCode);
		this.setResultText(resultMsg);
	}
	
	public void setResultCode(String resultCode) {
		this.put("resultCode", resultCode);
	}

//	public String getResultCode() {
//		return (String)this.get("resultCode");
//	}
	
	public void setResultText(String resultText) {
		this.put("resultText", resultText);
	}
	
//	public String getResultMessage() {
//		return (String)this.get("resultText");
//	}
	
	public void setErrorCode(ErrorCodeType errorCode) {
		if(errorCode != null) {
			this.put("resultCode", errorCode.value);
			this.put("resultText", errorCode.label);
		}
	}

}
