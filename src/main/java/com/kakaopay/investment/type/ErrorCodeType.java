package com.kakaopay.investment.type;

import lombok.Getter;

import java.util.stream.Stream;

public enum ErrorCodeType implements CodeEnum {

	OK("0", "성공")
	
	//COMMON ERROR(00XXX)
	,INVALID_PARAMETER   	("00100", "파라미터 값이 유효하지 않습니다.")

	,SERVICE_MALFORMED_RESP ("00997", "서비스 요청 처리 중 오류가 발생하였습니다.")
	,SERVICE_NOT_AVAILABLE 	("00998", "서비스 상태로 인해 현재 서비스가 불가합니다.")
	,SERVICE_UNKNOWN_ERROR  ("00999", "오류가 발생하여 요청을 처리할 수 없습니다.")
	;
	
	@Getter 
	public final String label;
	@Getter 
	public final String value;
	
	private ErrorCodeType(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public static ErrorCodeType fromValue(String value) {
		return Stream.of(ErrorCodeType.values())
				.filter(s -> s.value.equals(value))
		        .findFirst()
		        .orElseThrow(IllegalArgumentException::new);
	}
	

}
