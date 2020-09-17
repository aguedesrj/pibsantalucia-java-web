package br.com.pibsantalucia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TBL_ENDERECO")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -3787450403643289872L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "END_CODIGO", updatable = false, nullable = false)
	private long endCodigo;

	@Column(name = "END_LOGRADOURO")
	private String endLogradouro;

	@Column(name = "END_COMPLEMENTO")
	private String endComplemento;

	@Column(name = "END_NUMERO")
	private String endNumero;

	@Column(name = "END_BAIRRO")
	private String endBairro;

	@Column(name = "END_CIDADE")
	private String endCidade;

	@Column(name = "END_CEP")
	private String endCep;
	
	@Column(name = "END_ESTADO")
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
