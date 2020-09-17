package br.com.pibsantalucia.api.vo;

import java.io.Serializable;

public class ReturnApiObject implements Serializable {

	private static final long serialVersionUID = 8701993944089468984L;
	
	private Integer code;
	private String message;
	private Object object;
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.object = null;
		
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.code = null;
		this.message = null;
		
		this.object = object;
	}
	
	public void setObjectWithMessage(Object object) {
		this.object = object;
	}
}
