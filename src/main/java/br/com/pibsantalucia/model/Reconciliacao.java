package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.util.Calendar;
import java.sql.Date;

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
@Table(name = "TBL_RECONCILIACAO")
public class Reconciliacao implements Serializable {

	private static final long serialVersionUID = 8458169421414030046L;

	@Id
	@Column(name = "REC_CODIGO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recCodigo;

	@Column(name = "REC_DTCADASTRO")
	private Calendar recDtCadastro;

	@Column(name = "REC_OBSERVACAO")
	private String recObservacao;

	@Column(name = "REC_DTRECONCILIACAO")
	private Date recDtReconciliacao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PES_CODIGO")
	private Pessoa pessoa;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UME_CODIGO")
	private UsuarioMembro usuarioMembro;

	public long getRecCodigo() {
		return recCodigo;
	}

	public void setRecCodigo(long recCodigo) {
		this.recCodigo = recCodigo;
	}

	public Calendar getRecDtCadastro() {
		return recDtCadastro;
	}

	public void setRecDtCadastro(Calendar recDtCadastro) {
		this.recDtCadastro = recDtCadastro;
	}

	public String getRecObservacao() {
		return recObservacao;
	}

	public void setRecObservacao(String recObservacao) {
		this.recObservacao = recObservacao;
	}

	public Date getRecDtReconciliacao() {
		return recDtReconciliacao;
	}

	public void setRecDtReconciliacao(Date recDtReconciliacao) {
		this.recDtReconciliacao = recDtReconciliacao;
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
