package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TBL_ESCALA")
public class Escala implements Serializable {

	private static final long serialVersionUID = -7929404833754882049L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ESC_CODIGO", updatable = false, nullable = false)
	private long escCodigo;

	@Column(name = "ESC_DTESCALA")
	private Date escDtEscala;

	@Column(name = "ESC_FLGTIPO")
	private int escFlgTipo;

	@Column(name = "ESC_DESCRICAO")
	private String escDescricao;
	
	@Column(name = "ESC_ANOMES")
	private int escAnoMes;
	
	public long getEscCodigo() {
		return escCodigo;
	}

	public void setEscCodigo(long escCodigo) {
		this.escCodigo = escCodigo;
	}

	public Date getEscDtEscala() {
		return escDtEscala;
	}

	public void setEscDtEscala(Date escDtEscala) {
		this.escDtEscala = escDtEscala;
	}

	public int getEscFlgTipo() {
		return escFlgTipo;
	}

	public void setEscFlgTipo(int escFlgTipo) {
		this.escFlgTipo = escFlgTipo;
	}

	public String getEscDescricao() {
		return escDescricao;
	}

	public void setEscDescricao(String escDescricao) {
		this.escDescricao = escDescricao;
	}

	public int getEscAnoMes() {
		return escAnoMes;
	}

	public void setEscAnoMes(int escAnoMes) {
		this.escAnoMes = escAnoMes;
	}
}
