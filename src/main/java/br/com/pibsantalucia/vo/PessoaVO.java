package br.com.pibsantalucia.vo;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.pibsantalucia.util.Util;

public class PessoaVO implements Serializable {

	private static final long serialVersionUID = -482851135481637632L;

	private long pesCodigo;
	private String pesNome;
	private String pesDtNascimento;
	private String pesSexo;
	private String pesCpf;
	private String pesTipoSanguineo;
	private String pesNaturalidade;
	private String pesNacionalidade;
	private String pesNomeMae;
	private String pesNomePai;
	private String pesDtCasamento;
	private String pesFoto;
	private String pesFotoPath;
	private String pesObservacao;
	private String pesDtBatismo;
	private String pesDtCadastro;
	private String pesFglMembro;
	private String pesProfissao;
	private UsuarioMembroVO usuarioMembroVO;
	private EnderecoVO enderecoVO;
	private List<ContatoVO> listaContatoVO;
	private File fotoUpload;

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}

	public String getPesNome() {
		return pesNome;
	}

	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public String getPesDtNascimento() {
		return pesDtNascimento;
	}

	public void setPesDtNascimento(String pesDtNascimento) {
		this.pesDtNascimento = pesDtNascimento;
	}

	public String getPesSexo() {
		return pesSexo;
	}

	public void setPesSexo(String pesSexo) {
		this.pesSexo = pesSexo;
	}

	public String getPesCpf() {
		return pesCpf;
	}

	public void setPesCpf(String pesCpf) {
		this.pesCpf = pesCpf;
	}

	public String getPesTipoSanguineo() {
		return pesTipoSanguineo;
	}

	public void setPesTipoSanguineo(String pesTipoSanguineo) {
		this.pesTipoSanguineo = pesTipoSanguineo;
	}

	public String getPesNaturalidade() {
		return pesNaturalidade;
	}

	public void setPesNaturalidade(String pesNaturalidade) {
		this.pesNaturalidade = pesNaturalidade;
	}

	public String getPesNacionalidade() {
		return pesNacionalidade;
	}

	public void setPesNacionalidade(String pesNacionalidade) {
		this.pesNacionalidade = pesNacionalidade;
	}

	public String getPesNomeMae() {
		return pesNomeMae;
	}

	public void setPesNomeMae(String pesNomeMae) {
		this.pesNomeMae = pesNomeMae;
	}

	public String getPesNomePai() {
		return pesNomePai;
	}

	public void setPesNomePai(String pesNomePai) {
		this.pesNomePai = pesNomePai;
	}

	public String getPesDtCasamento() {
		return pesDtCasamento;
	}

	public void setPesDtCasamento(String pesDtCasamento) {
		this.pesDtCasamento = pesDtCasamento;
	}

	public String getPesFoto() {
		return pesFoto;
	}

	public void setPesFoto(String pesFoto) {
		this.pesFoto = pesFoto;
	}

	public String getPesObservacao() {
		return pesObservacao;
	}

	public void setPesObservacao(String pesObservacao) {
		this.pesObservacao = pesObservacao;
	}

	public String getPesDtBatismo() {
		return pesDtBatismo;
	}

	public void setPesDtBatismo(String pesDtBatismo) {
		this.pesDtBatismo = pesDtBatismo;
	}

	public String getPesDtCadastro() {
		return pesDtCadastro;
	}

	public void setPesDtCadastro(String pesDtCadastro) {
		this.pesDtCadastro = pesDtCadastro;
	}

	public String getPesFglMembro() {
		return pesFglMembro;
	}

	public void setPesFglMembro(String pesFglMembro) {
		this.pesFglMembro = pesFglMembro;
	}

	public EnderecoVO getEnderecoVO() {
		return enderecoVO;
	}

	public void setEnderecoVO(EnderecoVO enderecoVO) {
		this.enderecoVO = enderecoVO;
	}

	public String getPesFotoPath() {
		return pesFotoPath;
	}

	public void setPesFotoPath(String pesFotoPath) {
		this.pesFotoPath = pesFotoPath;
	}

	public List<ContatoVO> getListaContatoVO() {
		return listaContatoVO;
	}

	public void setListaContatoVO(List<ContatoVO> listaContatoVO) {
		this.listaContatoVO = listaContatoVO;
	}

	public File getFotoUpload() {
		return fotoUpload;
	}

	public void setFotoUpload(File fotoUpload) {
		this.fotoUpload = fotoUpload;
	}

	public String getPesProfissao() {
		return pesProfissao;
	}

	public void setPesProfissao(String pesProfissao) {
		this.pesProfissao = pesProfissao;
	}

	public UsuarioMembroVO getUsuarioMembroVO() {
		return usuarioMembroVO;
	}

	public void setUsuarioMembroVO(UsuarioMembroVO usuarioMembroVO) {
		this.usuarioMembroVO = usuarioMembroVO;
	}
	
	public String getEnderecoCompleto() {
		if (getEnderecoVO() != null && Util.isCampoPreenchido(enderecoVO.getEndLogradouro()) &&
				Util.isCampoPreenchido(enderecoVO.getEndBairro()) && Util.isCampoPreenchido(enderecoVO.getEndCidade())) {
			
			StringBuilder endereco = new StringBuilder();
			endereco.append(enderecoVO.getEndLogradouro());
			if (Util.isCampoPreenchido(enderecoVO.getEndNumero())) {
				endereco.append(", ");
				endereco.append(enderecoVO.getEndNumero());
			}
			endereco.append(" - ");
			endereco.append(enderecoVO.getEndBairro());
			endereco.append(" - ");
			endereco.append(enderecoVO.getEndCidade());
			
			return endereco.toString();
		}
		return "Não informado";
	}
	
	public String getTelefoneCompleto() {
		if (getListaContatoVO() != null && getListaContatoVO().size() > 0) {
			StringBuilder telefone = new StringBuilder();
			for (ContatoVO contatoVO: getListaContatoVO()) {
				if (contatoVO.getTipoContatoVO().getTpcCodigo() == 2 || contatoVO.getTipoContatoVO().getTpcCodigo() == 4) {
					telefone.append(Util.formatarTelefone(contatoVO.getCtoNumeroTelefone()));
					if (contatoVO.getTipoContatoVO().getTpcCodigo() == 2) {
						telefone.append(" (Celular)\n");
					} else {
						telefone.append(" (Residencial)\n");
					}
				}
			}
			if (telefone.length() == 0) {
				telefone.append("Não informado");
			}
			return telefone.toString();
		}
		return "Não informado";
	}
	
	public String getDtAniversarioFmt() {
		try {
			return Util.getAniversarioNew(Util.converterStringParaDate(pesDtNascimento));
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getAniversario() {
		try {
			StringBuilder aniversario = new StringBuilder();
			Date dataAniversario = Util.converterStringParaDate(pesDtNascimento);
			Calendar calendar = Calendar.getInstance(new Locale("pt","BR"));
			calendar.setTime(dataAniversario);
			
			aniversario.append("Aniversário: ");
			aniversario.append(calendar.get(Calendar.DAY_OF_MONTH));
			aniversario.append(" de ");
			
			switch (calendar.get(Calendar.MONTH)) {
			case 0:
				aniversario.append("Janeiro");
				break;
			case 1:
				aniversario.append("Fevereiro");
				break;
			case 2:
				aniversario.append("Março");
				break;
			case 3:
				aniversario.append("Abril");
				break;
			case 4:
				aniversario.append("Maio");
				break;
			case 5:
				aniversario.append("Junho");
				break;
			case 6:
				aniversario.append("Julho");
				break;
			case 7:
				aniversario.append("Agosto");
				break;
			case 8:
				aniversario.append("Setembro");
				break;
			case 9:
				aniversario.append("Outubro");
				break;
			case 10:
				aniversario.append("Novembro");
				break;
			default:
				aniversario.append("Dezembro");
				break;
			}
			
			return aniversario.toString();
		} catch (Exception e) {
			return "Não informado";
		}
	}
}
