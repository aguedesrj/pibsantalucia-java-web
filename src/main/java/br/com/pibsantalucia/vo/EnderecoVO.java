package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class EnderecoVO implements Serializable {
	
	private static final long serialVersionUID = -4743157296057869513L;
	
	private long endCodigo;
	private String endLogradouro;
	private String endComplemento;
	private String endNumero;
	private String endBairro;
	private String endCidade;
	private String endCep;
	private String endEstado;
	
	public long getEndCodigo() {
		return endCodigo;
	}

	public void setEndCodigo(long endCodigo) {
		this.endCodigo = endCodigo;
	}

	public String getEndLogradouro() {
		return endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}

	public String getEndComplemento() {
		return endComplemento;
	}

	public void setEndComplemento(String endComplemento) {
		this.endComplemento = endComplemento;
	}

	public String getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(String endNumero) {
		this.endNumero = endNumero;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public String getEndCidade() {
		return endCidade;
	}

	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}

	public String getEndCep() {
		return endCep;
	}

	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public String getEndEstado() {
		return endEstado;
	}

	public void setEndEstado(String endEstado) {
		this.endEstado = endEstado;
	}
}
