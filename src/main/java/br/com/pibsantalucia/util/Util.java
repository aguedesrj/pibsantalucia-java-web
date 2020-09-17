package br.com.pibsantalucia.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;

public class Util {
	
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_DATA_HORA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_DATA = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Obter a data atual no formato dd/MM/yyyy
	 * 
	 * @return String
	 */
	public static String obterDataAtual(final SimpleDateFormat format) {
		return format.format(new Date());
	}

	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor String
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(String valor) {
		return (valor != null && !"".equals(valor.trim()));
	}

	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Long valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}

	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Integer valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}

	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Double valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}

	/**
	 * Formatar data no formato "dd/mm/yyyy 00:00:00"
	 * 
	 * @param data Calendar
	 * @return String
	 */
	public static String converterCalendarParaString(final Calendar data, final SimpleDateFormat format) {
		if (data != null) {
			return format.format(data.getTime());
		}
		return "";
	}

	/**
	 * 
	 * @param data
	 * @param format
	 * @return
	 */
	public static String converterDateParaString(final Date data) {
		if (data != null) {
			return SIMPLE_DATE_FORMAT_DATA.format(data);
		}
		return "";
	}
	
	public static String converterDateParaString(final Date data, String formato) {
		if (data != null) {
			SimpleDateFormat SIMPLE_DATE_FORMAT_DATA = new SimpleDateFormat(formato);
//			Locale locale = new Locale("pt","BR");
			
//			SIMPLE_DATE_FORMAT_DATA = DateFormat.getDateInstance(DateFormat.FULL, locale);
			
			return SIMPLE_DATE_FORMAT_DATA.format(data);
		}
		return "";
	}

	public static java.sql.Date converterStringParaDate(final String data) throws Exception {
		if (data != null && !data.equals("")) {
			Date date = SIMPLE_DATE_FORMAT_DATA.parse(data);
			return new java.sql.Date (date.getTime());
		}
		return null;
	}
	
	public static Date converterStringParaDate(final String data, String formato) throws Exception {
		if (data != null && !data.equals("")) {
			SimpleDateFormat SIMPLE_DATE = new SimpleDateFormat(formato);
			return (Date) SIMPLE_DATE.parse(data);
		}
		return null;
	}

	/**
	 * Formatar data no formato "yyyy-MM-dd"
	 * 
	 * @param data String
	 * @return String
	 */
	public static String formatarDataString(String data) {
		return data.substring(6, 10) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
	}

	/**
	 * Converter data tipo String para data tipo Calendar.
	 * 
	 * @param valorData String
	 * @return Calendar
	 * @throws Exception
	 */
	public static Calendar converterStringParaCalendar(final String valorData, final SimpleDateFormat formato) throws Exception {
		if (valorData != null && !valorData.equals("")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formato.parse(valorData));
			return calendar;
		}
		return null;
	}

	public static String somenteNumeros(final String valor) {
		if (valor != null) {
			return valor.replaceAll("[^0-9]", "");
		}
		return null;
	}

	/**
	 * Transforma um arquivo(String) em byte[].
	 * 
	 * @param filePath String
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] readBytesFromFile(String filePath) throws IOException {
		File inputFile = new File(filePath);
		FileInputStream inputStream = new FileInputStream(inputFile);

		byte[] fileBytes = new byte[(int) inputFile.length()];
		inputStream.read(fileBytes);
		inputStream.close();

		return fileBytes;
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isDataValida(String valor) {
		try {
			SIMPLE_DATE_FORMAT_DATA.parse(valor);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isDataMaiorQueAtual(String valor) {
		try {
			Calendar dataAtual = new GregorianCalendar();
			Calendar dataInformada = new GregorianCalendar();
			dataInformada.setTime(SIMPLE_DATE_FORMAT_DATA.parse(valor));
			return dataInformada.before(dataAtual);
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isCpfValido(String valor) {
		valor = somenteNumeros(valor);
		// considera-se erro valor's formados por uma sequencia de numeros iguais
		if (valor.equals("00000000000") || valor.equals("11111111111") || valor.equals("22222222222") || valor.equals("33333333333") || valor.equals("44444444444") || valor.equals("55555555555") || valor.equals("66666666666") || valor.equals("77777777777") || valor.equals("88888888888") || valor.equals("99999999999") || (valor.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do valor em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0         
				// (48 eh a posicao de '0' na tabela ASCII)         
				num = (int) (valor.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (valor.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == valor.charAt(9)) && (dig11 == valor.charAt(10)))
				return (true);
			else
				return (false);
		} catch (Exception erro) {
			return (false);
		}
	}

	/**
	 * 
	 * @param pesCodigo
	 * @param pesFoto
	 */
	public static void carregaFotosCongregados(long pesCodigo, byte[] pesFoto, String namePhoto) throws Exception {
		// verifica se tem foto na base de dados e se tem a imagem como arquivo, senão criar arquivo.
		if (pesFoto != null) {
			writePhotoCongregado(pesCodigo, pesFoto, namePhoto);
		}
	}

	/**
	 * 
	 * @param pesCodigo
	 * @return String
	 */
	public static String getPathPhotoCongregado(long pesCodigo, String namePhoto) throws Exception {
		StringBuilder stringHTTP = new StringBuilder();

		if (System.getenv("DSV") != null) {
			stringHTTP.append("http://192.168.0.133:9080/PIBSantaLucia/");
		} else {
			stringHTTP.append(Constantes.INSTANCE_AWS+"/");
		}
		
		File arquivoFoto = new File(stringCaminhoFoto(pesCodigo, namePhoto));
		if (arquivoFoto.isFile()) {
			return stringHTTP.toString() + "resources/photo/congregados/" + namePhoto + ".png";
		} else {
			return null;
		}
	}

	public static void writePhotoCongregado(long pesCodigo, byte[] pesFoto, String namePhoto) throws Exception {
		if (pesFoto != null) {
			FileUtils.writeByteArrayToFile(new File(stringCaminhoFoto(pesCodigo, namePhoto)), pesFoto);
		}
	}

	private static String stringCaminhoFoto(long pesCodigo, String namePhoto) throws Exception {
		StringBuilder string = new StringBuilder();
		if (System.getenv("DSV") != null) {
			string.append(System.getProperty("catalina.base"));
			string.append("/wtpwebapps/PIBSantaLucia/resources/photo/congregados/");
		} else {
			string.append("/usr/share/tomcat8/webapps/ROOT/resources/photo/congregados/");
		}
		string.append(namePhoto);
		string.append(".png");

		return string.toString();
	}
	
	public static String gerarNomeFotoCongregado() {
		return RandomStringUtils.randomAlphabetic(25);
		
		
		
//		try {
//			StringBuilder string = new StringBuilder();
//			string.append(pesCodigo);
//			string.append(SIMPLE_DATE_FORMAT_NAME_PHOTO.format(Calendar.getInstance().getTime()));
//			return string.toString();
//		} catch (Exception e) {
//			return String.valueOf(pesCodigo);
//		}
	}
	
	public static String formatarTelefone(String telefone) {
		try {
			if (telefone.length() == 9) {
				return (telefone.substring(0, 5)+"-"+telefone.substring(telefone.length()-4, telefone.length()));
			} else if (telefone.length() == 8) {
				return (telefone.substring(0, 4)+"-"+telefone.substring(telefone.length()-4, telefone.length()));
			} else {
				return telefone;
			}
		} catch (Exception e) {
			return telefone;
		}
	}
	
	public static String getAniversario(Date pesDtNascimento) {
		try {
			StringBuilder aniversario = new StringBuilder();
			Calendar calendar = Calendar.getInstance(new Locale("pt","BR"));
			calendar.setTime(pesDtNascimento);
			
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
			return "";
		}
	}
	
	public static String getAniversarioNew(Date pesDtNascimento) {
		try {
			StringBuilder aniversario = new StringBuilder();
			Calendar calendar = Calendar.getInstance(new Locale("pt","BR"));
			calendar.setTime(pesDtNascimento);
			
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
			return "";
		}
	}
	
	public static String setEstadoPorUf(String uf) {
		uf = uf.toUpperCase().trim();
		
		if (uf.equals("AC")) {
			return "Acre";
		} else if (uf.equals("AL")) {
			return "Alagoas";
		} else if (uf.equals("AP")) {
			return "Amapá";
		} else if (uf.equals("AM")) {
			return "Amazonas";
		} else if (uf.equals("BA")) {
			return "Bahia";
		} else if (uf.equals("CE")) {
			return "Ceará";
		} else if (uf.equals("DF")) {
			return "Distrito Federal";
		} else if (uf.equals("ES")) {
			return "Espírito Santo";
		} else if (uf.equals("GO")) {
			return "Goiás";
		} else if (uf.equals("MA")) {
			return "Maranhão";
		} else if (uf.equals("MT")) {
			return "Mato Grosso";
		} else if (uf.equals("MS")) {
			return "Mato Grosso do Sul";
		} else if (uf.equals("MG")) {
			return "Minas Gerais";
		} else if (uf.equals("PA")) {
			return "Pará";
		} else if (uf.equals("PB")) {
			return "Paraíba";
		} else if (uf.equals("PR")) {
			return "Paraná";
		} else if (uf.equals("PE")) {
			return "Pernambuco";
		} else if (uf.equals("PI")) {
			return "Piauí";
		} else if (uf.equals("RJ")) {
			return "Rio de Janeiro";
		} else if (uf.equals("RN")) {
			return "Rio Grande do Norte";
		} else if (uf.equals("RS")) {
			return "Rio Grande do Sul";
		} else if (uf.equals("RO")) {
			return "Rondônia";
		} else if (uf.equals("RR")) {
			return "Roraima";
		} else if (uf.equals("SC")) {
			return "Santa Catarina";
		} else if (uf.equals("SP")) {
			return "São Paulo";
		} else if (uf.equals("SE")) {
			return "Sergipe";
		} else if (uf.equals("TO")) {
			return "Tocantins";
		}
		return "";
	}
}
