package com.kakaopay.investment.common;

import com.kakaopay.investment.type.ErrorCodeType;

public class InvestmentException extends RuntimeException {

	private static final long serialVersionUID = -6373512715863980524L;

	private String errorCode;

	public InvestmentException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public InvestmentException(ErrorCodeType errorCodeEnum) {
		super(errorCodeEnum.label);
		this.errorCode = errorCodeEnum.value;
	}

	public InvestmentException(ErrorCodeType errorCodeEnum, String message) {
		super(message);
		this.errorCode = errorCodeEnum.value;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
	
}