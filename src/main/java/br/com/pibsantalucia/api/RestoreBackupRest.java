package br.com.pibsantalucia.api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.util.Util;

@RestController
@RequestMapping("/Api/RestoreBackupRest")
public class RestoreBackupRest {

	@Autowired
	private PessoaFacade pessoaFacade;
	
	@RequestMapping(path = "/RestoreBackup", method = RequestMethod.GET)
	public void restoreBackup() {
		String PES_NOME = "";
		long pesCodigo = 0;
		try {
//			pessoaFacade.restorePessoa("delete FROM pibsantalucia.TBL_PESSOA where PES_CODIGO > 0;");
//			
//			FileInputStream stream = new FileInputStream("/Users/andrelessaguedes/Downloads/BackupPibSantaLucia 12.sql");
//	        InputStreamReader reader = new InputStreamReader(stream);
//	        BufferedReader br = new BufferedReader(reader);
//	        String linha = br.readLine();
//	        StringBuilder insert = new StringBuilder();
//	        
//	        System.out.println("Processando tabela Pessoa...");
//	        while(linha != null) {
//	            String[] arrayValues = linha.split("VALUES");
//	            String[] array = arrayValues[1].split(",");
//	            
//	            insert.setLength(0);
//	            
//	            insert.append("INSERT INTO `pibsantalucia`.`TBL_PESSOA` (`PES_CODIGO`, `PES_NOME`, `PES_DTNASCIMENTO`, `PES_SEXO`, `PES_CPF`, `PES_TIPOSANGUINEO`, `PES_NATURALIDADE`, `PES_NACIONALIDADE`, `PES_NOMEMAE`, `PES_NOMEPAI`, `PES_DTCASAMENTO`, `END_CODIGO`, `PES_FLGMEMBRO`, `PES_OBSERVACAO`, `PES_DTBATISMO`, `PES_DTCADASTRO`, `UME_CODIGO`) VALUES (");
//	            
//	            Long PES_CODIGO = Long.parseLong(array[0].replace("(", "").trim());
//	            PES_NOME = array[1].trim();
//	            
//	            if (PES_CODIGO == 151) {
//	            	System.out.print("");
//	            }
//	            
//	            insert.append(PES_CODIGO);
//	            insert.append(", ");
//	            insert.append(PES_NOME);
//	            insert.append(", ");
//	            insert.append(formatarDataString(array[2].trim()));
//	            insert.append(", ");
//	            insert.append(array[3].trim());
//	            insert.append(", ");
//	            insert.append(array[4].trim());
//	            insert.append(", ");
//	            insert.append(array[5].trim());
//	            insert.append(", ");
//	            insert.append(array[6].trim());
//	            insert.append(", ");
//	            insert.append(array[7].trim());
//	            insert.append(", ");
//	            insert.append(array[8].trim());
//	            insert.append(", ");
//	            insert.append(array[9].trim());
//	            insert.append(", ");
//	            insert.append(formatarDataString(array[10].trim()));
//	            insert.append(", ");
//	            insert.append(array[11].trim());
//	            insert.append(", ");
//	            insert.append(array[12].trim());
//	            insert.append(", ");
//	            insert.append(array[14].trim());
//	            insert.append(", ");
//	            insert.append(formatarDataString(array[15].trim()));
//	            insert.append(", ");
//	            insert.append(array[16].trim());
//	            insert.append(", ");
//	            insert.append(array[17].trim());
//	            
//	            pessoaFacade.restorePessoa(insert.toString());
//	            
//	            linha = br.readLine();
//	        }
	        
	        System.out.println("Iniciar o processo das fotos...");
			FileInputStream stream = new FileInputStream("/Users/andrelessaguedes/Downloads/BackupPibSantaLucia 19.sql");
	        InputStreamReader reader = new InputStreamReader(stream);
	        BufferedReader br = new BufferedReader(reader);
	        String linha = br.readLine();
	        
	        while(linha != null) {
	            String[] arrayValues = linha.split("VALUES");
	            String[] array = arrayValues[1].split(",");
	         
	            pesCodigo = Long.parseLong(array[0].replace("(", "").trim());
	            
	            if (pesCodigo == 35) {
	            	System.out.print("");
	            }
	            
	            PES_NOME = array[1].trim(); 
	            
	            String foto = array[13].replace("'", "").trim();
	            Pessoa pessoa = pessoaFacade.obterDadosPessoa(pesCodigo);
	            if (!foto.isEmpty() && !foto.equals("null")) {
		            pessoa.setPesFoto(Base64.decodeBase64(foto));
	            } else {
	            	pessoa.setPesFoto(null);
	            	pessoa.setPesNamePhoto(null);
	            }
	            pessoaFacade.restoreFotoPessoa(pessoa);
	            
	            linha = br.readLine();
	        }
	        
	        System.out.println("FIM do processamento da tabela Pessoa...");
		} catch (Exception e) {
			System.out.println("PES_NOME: "+PES_NOME+" - Código: "+pesCodigo+"\n"+e);
		}
	}
	
	private String formatarDataString(String data) {
		try {
			String date = data.replace("'", "").trim();
			if (!date.isEmpty()) {
				return "'"+Util.formatarDataString(date)+"'";
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
