package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class ErroVO implements Serializable {
	
	private static final long serialVersionUID = -418874807915625737L;
	
	private int codigo;
	private String mensagem;
	
	public ErroVO(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public ErroVO() {}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
