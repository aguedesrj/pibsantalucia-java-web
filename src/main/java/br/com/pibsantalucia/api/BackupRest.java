package br.com.pibsantalucia.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.facade.BackupFacade;
import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.model.Anuncio;
import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.Desligamento;
import br.com.pibsantalucia.model.DeviceMobile;
import br.com.pibsantalucia.model.Endereco;
import br.com.pibsantalucia.model.Escala;
import br.com.pibsantalucia.model.EscalaMembro;
import br.com.pibsantalucia.model.Obito;
import br.com.pibsantalucia.model.Pastoral;
import br.com.pibsantalucia.model.Perfil;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.Reconciliacao;
import br.com.pibsantalucia.model.Transferencia;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.TipoContatoVO;

@RestController
@RequestMapping("/Api/BackupRest")
public class BackupRest {

	private final Logger LOGGER = LoggerFactory.getLogger(BackupRest.class);
	
	private final int TAMANHO_BUFFER = 12048;
	private byte[] dados = new byte[TAMANHO_BUFFER];
	private int cont;
	
	private String nomeArquivoBackup = "BackupPibSantaLucia.sql";
	private String nomeArquivoBackupZip = "BackupPibSantaLucia.zip";
	
	@Autowired
	private PessoaFacade pessoaFacade;
	
	@Autowired
	private BackupFacade backupFacade;
	
	@RequestMapping(path = "/Backup", method = RequestMethod.GET)
	public void backup() {
		
		try {
			LOGGER.info("Realizando Backup...");
			
			String caminhoArquivoBackup = "/Users/andrelessaguedes/Documents/";
			if (System.getenv("DSV") == null) {
				caminhoArquivoBackup = "/home/backup/";
			}
			
			Writer output;
			output = new BufferedWriter(new FileWriter(caminhoArquivoBackup+nomeArquivoBackup));
			
			// Tabela Pessoa
			output.append("DELETE FROM `pibsantalucia`.`TBL_PESSOA` WHERE `PES_CODIGO` > 0;\n");
			List<Pessoa> listaPessoa = backupFacade.listaPessoa();
			for (Pessoa pessoa: listaPessoa) {
				String PES_DTNASCIMENTO = null;
				String PES_DTCASAMENTO = null;
				String PES_DTBATISMO = null;
				String PES_FOTO = "null";
				if (pessoa.getPesDtNascimento() != null ) {
					PES_DTNASCIMENTO = "'"+Util.converterDateParaString(pessoa.getPesDtNascimento(), "yyyy-MM-dd")+"'";
				}
				if (pessoa.getPesDtCasamento() != null ) {
					PES_DTCASAMENTO = "'"+Util.converterDateParaString(pessoa.getPesDtCasamento(), "yyyy-MM-dd")+"'";
				}
				if (pessoa.getPesDtBatismo() != null ) {
					PES_DTBATISMO = "'"+Util.converterDateParaString(pessoa.getPesDtBatismo(), "yyyy-MM-dd")+"'";
				}
				if (pessoa.getPesFoto() != null) {
					PES_FOTO = "'"+Base64Utils.encodeToString(pessoa.getPesFoto())+"'";
				}
				
				
				output.append("INSERT INTO `pibsantalucia`.`TBL_PESSOA` (`PES_CODIGO`, `PES_NOME`, `PES_DTNASCIMENTO`, `PES_SEXO`, `PES_CPF`, `PES_TIPOSANGUINEO`, `PES_NATURALIDADE`, ");
				output.append("`PES_NACIONALIDADE`, `PES_NOMEMAE`, `PES_NOMEPAI`, `PES_DTCASAMENTO`, `END_CODIGO`, `PES_FLGMEMBRO`, `PES_FOTO`, `PES_OBSERVACAO`, ");
				output.append("`PES_DTBATISMO`, `PES_DTCADASTRO`, `UME_CODIGO`, `PES_PROFISSAO`) VALUES ");
				
				output.append("("+pessoa.getPesCodigo()+", '"+pessoa.getPesNome()+"', "+PES_DTNASCIMENTO+", '"+pessoa.getPesSexo()+"', ");
				output.append("'"+pessoa.getPesCpf()+"', '"+pessoa.getPesTipoSanguineo()+"', '"+pessoa.getPesNaturalidade()+"', '"+pessoa.getPesNacionalidade()+"', '"+pessoa.getPesNomeMae()+"', ");
				output.append("'"+pessoa.getPesNomePai()+"', "+PES_DTCASAMENTO+", ");
				output.append(""+pessoa.getEndereco().getEndCodigo()+", '"+pessoa.isPesFglMembro()+"', "+PES_FOTO+", '"+pessoa.getPesObservacao()+"', ");
				output.append(""+PES_DTBATISMO+", '"+Util.converterCalendarParaString(pessoa.getPesDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"', "+pessoa.getUsuarioMembro().getUmeCodigo()+", ");
				output.append("'');\n ");
			}
			output.append("\n");
			
			// Tabela TipoContato
			output.append("DELETE FROM `pibsantalucia`.`TBL_TIPOCONTATO` WHERE `TPC_CODIGO` > 0;\n");
			List<TipoContatoVO> listaTipoContatos = pessoaFacade.listaTipoContatos();
			for (TipoContatoVO tipoContatoVO: listaTipoContatos) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_TIPOCONTATO` (`TPC_CODIGO`, `TPC_DESCRICAO`) VALUES ("+tipoContatoVO.getTpcCodigo()+", '"+tipoContatoVO.getTpcDescricao()+"'); \n");
			}
			output.append("\n");
			
			// Tabela Endereço
			output.append("DELETE FROM `pibsantalucia`.`TBL_ENDERECO` WHERE `END_CODIGO` > 0;\n");
			List<Endereco> listaEndereco = backupFacade.listaEndereco();
			for (Endereco endereco: listaEndereco) {
				String endEstado = "";
				if (!endereco.getEndLogradouro().trim().isEmpty()) {
					endEstado = "Rio de Janeiro";
				}
				
				output.append("INSERT INTO `pibsantalucia`.`TBL_ENDERECO` (`END_CODIGO`, `END_LOGRADOURO`, `END_COMPLEMENTO`, `END_NUMERO`, `END_BAIRRO`, `END_CIDADE`, `END_CEP`, `END_ESTADO`) VALUES ");
				output.append("("+endereco.getEndCodigo()+", '"+endereco.getEndLogradouro()+"', '"+endereco.getEndComplemento()+"', ");
				output.append("'"+endereco.getEndNumero()+"', '"+endereco.getEndBairro()+"', '"+endereco.getEndCidade()+"', ");
				output.append("'"+endereco.getEndCep()+"', '"+endEstado+"'); \n");
			}
			output.append("\n");
			
			// Tabela Óbito
			output.append("DELETE FROM `pibsantalucia`.`TBL_OBITO` WHERE `OBI_CODIGO` > 0;\n");
			List<Obito> listaObito = backupFacade.listaObito();
			for (Obito obito: listaObito) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_OBITO` (`OBI_CODIGO`, `OBI_DTCADASTRO`, `OBI_CAUSA`, `OBI_OBSERVACAO`, `OBI_DTOBITO`, `UME_CODIGO`, `PES_CODIGO`) VALUES ");
				output.append("("+obito.getObiCodigo()+", '"+Util.converterCalendarParaString(obito.getObiDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"', '"+obito.getObiCausa()+"', ");
				output.append("'"+obito.getObiObservacao()+"', '"+Util.converterDateParaString(obito.getObiDtObito(), "yyyy-MM-dd")+"', "+obito.getUsuarioMembro().getUmeCodigo()+", "+obito.getPessoa().getPesCodigo()+");\n ");
			}
			output.append("\n");
			
			// Tabela Desligamento
			output.append("DELETE FROM `pibsantalucia`.`TBL_DESLIGAMENTO` WHERE `DES_CODIGO` > 0;\n");
			List<Desligamento> listaDesligamento = backupFacade.listaDesligamento();
			for (Desligamento desligamento: listaDesligamento) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_DESLIGAMENTO` (`DES_CODIGO`, `DES_DTCADASTRO`, `DES_OBSERVACAO`, `DES_DTDESLIGAMENTO`, `UME_CODIGO`, `PES_CODIGO`) VALUES ");
				output.append("("+desligamento.getDesCodigo()+", '"+Util.converterCalendarParaString(desligamento.getDesDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"', '"+desligamento.getDesObservacao()+"', ");
				output.append("'"+Util.converterDateParaString(desligamento.getDesDtDesligamento(), "yyyy-MM-dd")+"', "+desligamento.getUsuarioMembro().getUmeCodigo()+", "+desligamento.getPessoa().getPesCodigo()+");\n ");
			}
			output.append("\n");
			
			// Tabela Contato
			output.append("DELETE FROM `pibsantalucia`.`TBL_CONTATO` WHERE `CTO_CODIGO` > 0;\n");
			List<Contato> listaContato = backupFacade.listaContato();
			for (Contato contato: listaContato) {
				String CTO_DDD = "";
				String CTO_NUMEROTELEFONE = "";
				String CTO_DESCRICAOEMAIL = "";
				if (contato.getTipoContato().getTpcCodigo() != 1 ) {
					CTO_DDD = contato.getCtoDdd();
					CTO_NUMEROTELEFONE = contato.getCtoNumeroTelefone();
				} else {
					CTO_DESCRICAOEMAIL = contato.getCtoDescricaoEmail();
				}
				
				output.append("INSERT INTO `pibsantalucia`.`TBL_CONTATO` (`CTO_CODIGO`, `CTO_DESCRICAOEMAIL`, `CTO_DDD`, `CTO_NUMEROTELEFONE`, `TPC_CODIGO`, `PES_CODIGO`) VALUES ");
				output.append("("+contato.getCtoCodigo()+", '"+CTO_DESCRICAOEMAIL+"', '"+CTO_DDD+"', '"+CTO_NUMEROTELEFONE+"', ");
				output.append(""+contato.getTipoContato().getTpcCodigo()+", "+contato.getPessoa().getPesCodigo()+");\n ");
			}
			output.append("\n");
			
			// Tabela Perfil
			output.append("DELETE FROM `pibsantalucia`.`TBL_PERFIL` WHERE `PER_CODIGO` > 0;\n");
			List<Perfil> listaPerfil = backupFacade.listaPerfil();
			for (Perfil perfil: listaPerfil) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_PERFIL` (`PER_CODIGO`, `PER_NOME`) VALUES ");
				output.append("("+perfil.getPerCodigo()+", '"+perfil.getPerNome()+"');\n ");
			}
			output.append("\n");
			
			// Tabela UsuarioMembro
			output.append("DELETE FROM `pibsantalucia`.`TBL_USUARIO_MEMBRO` WHERE `UME_CODIGO` > 0;\n");
			List<UsuarioMembro> listaUsuarioMembro = backupFacade.listaUsuarioMembro();
			for (UsuarioMembro usuarioMembro: listaUsuarioMembro) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_USUARIO_MEMBRO` (`UME_CODIGO`, `UME_DTCADASTRO`, `UME_SENHA`, `PER_CODIGO`, `PES_CODIGO`, `UME_SENHA_TEMP`) VALUES ");
				output.append("("+usuarioMembro.getUmeCodigo()+", '"+Util.converterCalendarParaString(usuarioMembro.getUsuDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"', '"+usuarioMembro.getUmeSenha()+"', ");
				output.append(""+usuarioMembro.getPerfil().getPerCodigo()+", "+usuarioMembro.getPessoa().getPesCodigo()+", "+usuarioMembro.getUmeSenhaTemp()+");\n ");
			}
			output.append("\n");
			
			// Tabela Transferencia
			output.append("DELETE FROM `pibsantalucia`.`TBL_TRANSFERENCIA` WHERE `TRA_CODIGO` > 0;\n");
			List<Transferencia> listaTransferencia = backupFacade.listaTransferencia();
			for (Transferencia transferencia: listaTransferencia) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_TRANSFERENCIA` (`TRA_CODIGO`, `TRA_DTCADASTRO`, `TRA_MOTIVO`, `TRA_NOMEIGREJA`, `TRA_OBSERVACAO`, `TRA_DTTRANFERENCIA`, `UME_CODIGO`, `PES_CODIGO`, `TRA_STATUS`) VALUES ");
				output.append("("+transferencia.getTraCodigo()+", '"+Util.converterCalendarParaString(transferencia.getTraDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"', '"+transferencia.getTraMotivo()+"', ");
				output.append("'"+transferencia.getTraNomeIgreja()+"', '"+transferencia.getTraObservacao()+"', '"+Util.converterDateParaString(transferencia.getTraDtTransferencia(), "yyyy-MM-dd")+"', ");
				output.append(""+transferencia.getUsuarioMembro().getUmeCodigo()+", "+transferencia.getPessoa().getPesCodigo()+", '"+transferencia.getTraStatus()+"');\n ");
			}
			output.append("\n");
			
			// Tabela Reconciliacao
			output.append("DELETE FROM `pibsantalucia`.`TBL_RECONCILIACAO` WHERE `REC_CODIGO` > 0;\n");
			List<Reconciliacao> listaReconciliacao = backupFacade.listaReconciliacao();
			for (Reconciliacao reconciliacao: listaReconciliacao) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_RECONCILIACAO` (`REC_CODIGO`, `REC_DTCADASTRO`, `REC_DTRECONCILIACAO`, `REC_OBSERVACAO`, `UME_CODIGO`, `PES_CODIGO`) VALUES ");
				output.append("("+reconciliacao.getRecCodigo()+", '"+Util.converterCalendarParaString(reconciliacao.getRecDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"', '"+Util.converterDateParaString(reconciliacao.getRecDtReconciliacao(), "yyyy-MM-dd")+"', ");
				output.append("'"+reconciliacao.getRecObservacao()+"', "+reconciliacao.getUsuarioMembro().getUmeCodigo()+", "+reconciliacao.getPessoa().getPesCodigo()+");\n ");
			}
			output.append("\n");
			
			// Tabela Reconciliacao
			output.append("DELETE FROM `pibsantalucia`.`TBL_DEVICE_MOBILE` WHERE `PES_CODIGO` > 0;\n");
			List<DeviceMobile> listaDevice = backupFacade.listaDevice();
			for (DeviceMobile device: listaDevice) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_DEVICE_MOBILE` (`DEM_ID`, `DEM_OS`, `DEM_VERSAO`, `DEM_TOKENFIREBASE`, `PES_CODIGO`, `DEM_DTCADASTRO`) VALUES ");
				output.append("('"+device.getDemId()+"', '"+device.getDemOs()+"', '"+device.getDemVersao()+"', '"+device.getDemTokenFirebase()+"', "+device.getPessoa().getPesCodigo()+", '"+Util.converterCalendarParaString(device.getDemDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"');\n ");
			}
			output.append("\n");
			
			// Tabela Pastoral
			output.append("DELETE FROM `pibsantalucia`.`TBL_PASTORAL` WHERE `PAS_CODIGO` > 0;\n");
			List<Pastoral> listaPastoral = backupFacade.listaPastoral();
			for (Pastoral pastoral: listaPastoral) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_PASTORAL` (`PAS_CODIGO`, `PAS_DTCADASTRO`, `PAS_DTREFERENCIA`, `PAS_PASTORAL`, `UME_CODIGO`) VALUES ");
				output.append("("+pastoral.getPasCodigo()+", '"+Util.converterCalendarParaString(pastoral.getPasDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"', '"+Util.converterDateParaString(pastoral.getPasDtReferencia(), "yyyy-MM-dd")+"', '"+pastoral.getPasPastoral()+"', "+pastoral.getUsuarioMembro().getUmeCodigo()+");\n ");
			}
			output.append("\n");
			
			// Tabela Escala
			output.append("DELETE FROM `pibsantalucia`.`TBL_ESCALA` WHERE `ESC_CODIGO` > 0;\n");
			List<Escala> listaEscala = backupFacade.listaEscala();
			for (Escala escala: listaEscala) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_ESCALA` (`ESC_CODIGO`, `ESC_DTESCALA`, `ESC_FLGTIPO`, `ESC_DESCRICAO`, `ESC_ANOMES`) VALUES ");
				output.append("("+escala.getEscCodigo()+", '"+Util.converterDateParaString(escala.getEscDtEscala(), "yyyy-MM-dd")+"', "+escala.getEscFlgTipo()+", '"+escala.getEscDescricao()+"', "+escala.getEscAnoMes()+");\n ");
			}
			output.append("\n");
			
			// Tabela EscalaMembro
			output.append("DELETE FROM `pibsantalucia`.`TBL_ESCALA_MEMBROS` WHERE `PAS_CODIGO` > 0;\n");
			List<EscalaMembro> listaEscalaMembro = backupFacade.listaEscalaMembro();
			for (EscalaMembro escalaMembro: listaEscalaMembro) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_ESCALA_MEMBROS` (`ESC_CODIGO`, `PES_CODIGO`) VALUES ");
				output.append("("+escalaMembro.getEscCodigo()+", "+escalaMembro.getPesCodigo()+");\n ");
			}
			output.append("\n");
			
			// Tabela Anuncio
			output.append("DELETE FROM `pibsantalucia`.`TBL_ANUNCIO` WHERE `ANU_CODIGO` > 0;\n");
			List<Anuncio> listaAnuncio = backupFacade.listaAnuncio();
			for (Anuncio anuncio: listaAnuncio) {
				output.append("INSERT INTO `pibsantalucia`.`TBL_ANUNCIO` (`ANU_CODIGO`, `ANU_DESCRICAO`, `ANU_DTLIMITE`, `ANU_DTCADASTRO`, `UME_CODIGO`) VALUES ");
				output.append("("+anuncio.getAnuCodigo()+", '"+anuncio.getAnuDescricao()+"', '"+Util.converterDateParaString(anuncio.getAnuDtLimite(), "yyyy-MM-dd")+"', '"+Util.converterCalendarParaString(anuncio.getAnuDtCadastro(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))+"', "+anuncio.getUsuarioMembro().getUmeCodigo() +");\n ");
			}
			output.append("\n");
			
			output.close();
			LOGGER.info("Backup realizado com sucesso!!!");
			
			// FAKE
			this.sendEmailComBackup(caminhoArquivoBackup);
		} catch (Exception exception) {
			LOGGER.error("Erro ao realizer Backup.", exception);
		}
	}
	
	private void sendEmailComBackup(String caminhoArquivoBackup) {
		try {
			LOGGER.info("Iniciando o envio de email com o Backup em anexo.");
			
			// zipando o arquivo.
			FileOutputStream destino = new FileOutputStream(caminhoArquivoBackup + nomeArquivoBackupZip);
			ZipOutputStream saida = new ZipOutputStream(new BufferedOutputStream(destino));
			FileInputStream streamDeEntrada = new FileInputStream(new File(caminhoArquivoBackup + nomeArquivoBackup));
			BufferedInputStream origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
			ZipEntry entry = new ZipEntry(nomeArquivoBackup);
			saida.putNextEntry(entry);
			while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
				saida.write(dados, 0, cont);
			}
			saida.close();
			origem.close();
			
			// preparando o email
			Properties properties = System.getProperties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			// configurando o email
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("", "");
				}
			});
			
			// envio do email.
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(""));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(""));
			message.setSubject("Sistema PIB Santa Lúcia - Backup");
			message.setText("Backup segue em anexo.");
			
			// arquivo em anexo.
			MimeBodyPart anexoBody = new MimeBodyPart();
			anexoBody.setText("your text");
			
			Multipart multipart = new MimeMultipart();
			FileDataSource fds = new FileDataSource(caminhoArquivoBackup + nomeArquivoBackupZip);
			anexoBody.setDataHandler(new DataHandler(fds));
			anexoBody.setFileName(fds.getName());
			multipart.addBodyPart(anexoBody);
			
			message.setContent(multipart);
			Transport.send(message);
			LOGGER.info("Finalizado o envio de email com o Backup.");
		} catch (Exception exception) {
			LOGGER.error("Erro enviar email:", exception);
		}
	}
}
