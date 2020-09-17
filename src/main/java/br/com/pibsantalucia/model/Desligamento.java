package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TBL_DESLIGAMENTO")
public class Desligamento implements Serializable {

	private static final long serialVersionUID = -2583691214111473639L;

	@Id
	@Column(name = "DES_CODIGO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long desCodigo;

	@Column(name = "DES_DTCADASTRO")
	private Calendar desDtCadastro;

	@Column(name = "DES_OBSERVACAO")
	private String desObservacao;

	@Column(name = "DES_DTDESLIGAMENTO")
	private Date desDtDesligamento;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PES_CODIGO")
	private Pessoa pessoa;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UME_CODIGO")
	private UsuarioMembro usuarioMembro;

	public long getDesCodigo() {
		return desCodigo;
	}

	public void setDesCodigo(long desCodigo) {
		this.desCodigo = desCodigo;
	}

	public Calendar getDesDtCadastro() {
		return desDtCadastro;
	}

	public void setDesDtCadastro(Calendar desDtCadastro) {
		this.desDtCadastro = desDtCadastro;
	}

	public String getDesObservacao() {
		return desObservacao;
	}

	public void setDesObservacao(String desObservacao) {
		this.desObservacao = desObservacao;
	}

	public Date getDesDtDesligamento() {
		return desDtDesligamento;
	}

	public void setDesDtDesligamento(Date desDtDesligamento) {
		this.desDtDesligamento = desDtDesligamento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public UsuarioMembro getUsuarioMembro() {
		return usuarioMembro;
	}

	public void setUsuarioMembro(UsuarioMembro usuarioMembro) {
		this.usuarioMembro = usuarioMembro;
	}
}
