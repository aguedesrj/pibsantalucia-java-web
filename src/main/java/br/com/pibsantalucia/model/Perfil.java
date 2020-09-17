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
@Table(name = "TBL_PERFIL")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 8920382546140291191L;

	@Id
	@Column(name = "PER_CODIGO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int perCodigo;

	@Column(name = "PER_NOME")
	private String perNome;

	public int getPerCodigo() {
		return perCodigo;
	}

	public void setPerCodigo(int perCodigo) {
		this.perCodigo = perCodigo;
	}

	public String getPerNome() {
		return perNome;
	}

	public void setPerNome(String perNome) {
		this.perNome = perNome;
	}
}
