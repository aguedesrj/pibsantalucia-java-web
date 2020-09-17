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
@Table(name = "TBL_TRANSFERENCIA")
public class Transferencia implements Serializable {

	private static final long serialVersionUID = -8555180263002659586L;

	@Id
	@Column(name = "TRA_CODIGO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long traCodigo;

	@Column(name = "TRA_DTCADASTRO")
	private Calendar traDtCadastro;

	@Column(name = "TRA_MOTIVO")
	private String traMotivo;

	@Column(name = "TRA_NOMEIGREJA")
	private String traNomeIgreja;

	@Column(name = "TRA_STATUS")
	private String traStatus;

	@Column(name = "TRA_OBSERVACAO")
	private String traObservacao;

	@Column(name = "TRA_DTTRANFERENCIA")
	private Date traDtTransferencia;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PES_CODIGO")
	private Pessoa pessoa;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UME_CODIGO")
	private UsuarioMembro usuarioMembro;

	public long getTraCodigo() {
		return traCodigo;
	}

	public void setTraCodigo(long traCodigo) {
		this.traCodigo = traCodigo;
	}

	public Calendar getTraDtCadastro() {
		return traDtCadastro;
	}

	public void setTraDtCadastro(Calendar traDtCadastro) {
		this.traDtCadastro = traDtCadastro;
	}

	public String getTraMotivo() {
		return traMotivo;
	}

	public void setTraMotivo(String traMotivo) {
		this.traMotivo = traMotivo;
	}

	public String getTraNomeIgreja() {
		return traNomeIgreja;
	}

	public void setTraNomeIgreja(String traNomeIgreja) {
		this.traNomeIgreja = traNomeIgreja;
	}

	public String getTraStatus() {
		return traStatus;
	}

	public void setTraStatus(String traStatus) {
		this.traStatus = traStatus;
	}

	public String getTraObservacao() {
		return traObservacao;
	}

	public void setTraObservacao(String traObservacao) {
		this.traObservacao = traObservacao;
	}

	public Date getTraDtTransferencia() {
		return traDtTransferencia;
	}

	public void setTraDtTransferencia(Date traDtTransferencia) {
		this.traDtTransferencia = traDtTransferencia;
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
