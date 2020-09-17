package br.com.pibsantalucia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TBL_TIPOCONTATO")
public class TipoContato implements Serializable {

	private static final long serialVersionUID = -5555980162468269686L;

	@Id
	@Column(name = "TPC_CODIGO")
	private int tpcCodigo;

	@Column(name = "TPC_DESCRICAO")
	private String tpcDescricao;

	public int getTpcCodigo() {
		return tpcCodigo;
	}

	public void setTpcCodigo(int tpcCodigo) {
		this.tpcCodigo = tpcCodigo;
	}

	public String getTpcDescricao() {
		return tpcDescricao;
	}

	public void setTpcDescricao(String tpcDescricao) {
		this.tpcDescricao = tpcDescricao;
	}
}
