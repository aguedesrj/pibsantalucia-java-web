package br.com.pibsantalucia.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import br.com.pibsantalucia.api.vo.ReturnApiObject;

public class BaseRest {

	private ReturnApiObject object = new ReturnApiObject();
	
	public ReturnApiObject getObject() {
		return object;
	}

	public void setObject(ReturnApiObject object) {
		this.object = object;
	}
}
