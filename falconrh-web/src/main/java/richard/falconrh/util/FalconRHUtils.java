package richard.falconrh.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import richard.falconrh.exception.ImpossivelObterHashSenhaException;
import sun.misc.BASE64Encoder;

/**
 * Classe Utilitária para metodos utilitários diversos
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
public class FalconRHUtils {
	private static final Logger LOGGER = Logger.getLogger(FalconRHUtils.class);
	public static final String BLANK = "";
	public static final String PONTO = ".";
	public static final String SHA_256 = "SHA-256";
	public static final String UTF_8 = "UTF-8";
	
	/**
	 * Transforma a senha em um hash de 44 caracteres
	 * @param senhaPlana - A senha que será transformada em hash
	
	
	
	 * @throws Exception * @return o hash que representa a senha, contendo 44 caracteres * @throws ImpossivelObterHashSenhaException * @throws ImpossivelObterHashSenhaException
	 */
	public static final String getHashSenha(String senhaPlana) throws ImpossivelObterHashSenhaException{
		LOGGER.debug("Criptografando senha...");
		if(StringUtils.isBlank(senhaPlana)){
			return senhaPlana;
		}
		try{
			MessageDigest digest = MessageDigest.getInstance(SHA_256);      
			digest.update(senhaPlana.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();      
			return encoder.encode (digest.digest());
		}catch(Exception e){
			LOGGER.error("Erro ao criptografar a senha", e);
			throw new ImpossivelObterHashSenhaException(e);
		}finally{
			LOGGER.debug("Fim da criptografacao da senha");
		}
	}
	
	/**
	 * Concatena duas string, separando-as com um ponto (".")
	 * @param string1 - a String que será a parte inicial
	 * @param string2 - a String que será a parte final
	
	 * @return uma String que contem as duas strings informadas, separada por um ponto. */
	public static String concat(String string1, String string2){
		return string1 +"." + string2;
	}
	
	/**
	 * Method getDigitoVerificador.
	 * @param numero String
	
	 * @return String */
	public static String getDigitoVerificador(String numero){
		int totalDigitos = numero.length();
		int total = 0;
		int multiplicador = 9;
		for(int i = totalDigitos-1; i>=0; i--){
			int n = Integer.valueOf(numero.substring(i, i+1));
			total += n*multiplicador--;
		}
		int resto = total%11;
		String dvEncontrado;
		if(resto==10){
			dvEncontrado = "X";
		}else{
			dvEncontrado = String.valueOf(resto);
		}
		return dvEncontrado;
	}
	
	/**
	 * Method isCPFValido.
	 * @param strCpf String
	
	 * @return boolean */
	public static boolean isCPFValido(String strCpf){
		String[] blackList = new String[]{
				"00000000000",	"11111111111",	"22222222222",	"33333333333",	"44444444444",
				"55555555555",	"66666666666",	"77777777777",	"88888888888",	"99999999999"};
		for(String n : blackList){
			if(n.equals(strCpf)){
				return false;
			}
		}
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();
			d1 = d1 + (11 - nCount) * digitoCPF;
			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		resto = d1%11;

		// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
		if (resto < 2){
			digito1 = 0;
		} else{
			digito1 = 11 - resto;
		}

		d2 += 2 * digito1;
		resto = d2%11;
		if (resto < 2){
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}
		String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
		return nDigVerific.equals(nDigResult);
	}
	
	/**
	 * Method isCNPJValido.
	 * @param strCNPJ String
	
	 * @return boolean */
	public static boolean isCNPJValido(String strCNPJ) {
	    int iSoma = 0, iDigito;
	    char[] chCaracteresCNPJ;
	    String strCNPJ_Calculado;
	 
	    if (! strCNPJ.substring(0,1).equals("")){
	        try{
	            strCNPJ=strCNPJ.replace('.',' ');
	            strCNPJ=strCNPJ.replace('/',' ');
	            strCNPJ=strCNPJ.replace('-',' ');
	            strCNPJ=strCNPJ.replaceAll(" ","");
	            strCNPJ_Calculado = strCNPJ.substring(0,12);
	            if ( strCNPJ.length() != 14 ) return false;
	            chCaracteresCNPJ = strCNPJ.toCharArray();
	            for(int i = 0; i < 4; i++) {
	                if ((chCaracteresCNPJ[i]-48 >= 0) && (chCaracteresCNPJ[i]-48 <= 9)) {
	                    iSoma += (chCaracteresCNPJ[i] - 48 ) * (6 - (i + 1)) ;
	                }
	            }
	            for( int i = 0; i < 8; i++ ) {
	                if ((chCaracteresCNPJ[i+4]-48 >= 0) && (chCaracteresCNPJ[i+4]-48 <= 9)) {
	                    iSoma += (chCaracteresCNPJ[i+4] - 48 ) * (10 - (i + 1)) ;
	                }
	            }
	            iDigito = 11 - (iSoma % 11);
	            strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0" : Integer.toString(iDigito);
	               /* Segunda parte */
	            iSoma = 0;
	            for (int i = 0; i < 5; i++) {
	                if ((chCaracteresCNPJ[i]-48 >= 0) && (chCaracteresCNPJ[i]-48 <= 9)) {
	                    iSoma += (chCaracteresCNPJ[i] - 48) * (7 - (i + 1)) ;
	                }
	            }
	            for (int i = 0; i < 8; i++) {
	                if ((chCaracteresCNPJ[i+5]-48 >= 0) && (chCaracteresCNPJ[i+5]-48 <= 9)) {
	                    iSoma += (chCaracteresCNPJ[i+5] - 48) * (10 - (i + 1)) ;
	                }
	            }
	            iDigito = 11 - (iSoma % 11);
	            strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0" : Integer.toString(iDigito);
	            return strCNPJ.equals(strCNPJ_Calculado);
	        } catch (Exception e) {
	        	LOGGER.error("erro ao verificar validade do CNPJ", e);
	            return false;
	        }
	    } else return false;
	}
	
	/**
	 * Method isPISValido.
	 * @param numPIS String
	
	 * @return boolean */
	public static boolean isPISValido(String numPIS) {
		final String ftap = "3298765432";
		int total=0;
		int resto=0;
		String strResto="";
		
		if(StringUtils.isBlank(numPIS)){
			return false;
		}
		for(int i=0;i<=9;i++){
			total += Integer.valueOf(numPIS.substring(i, i+1))*Integer.valueOf(ftap.substring(i,i+1));
		}
		resto = total%11;
		if (resto != 0){
			resto=11-resto;
		}
		if (resto==10 || resto==11){
			strResto=String.valueOf(resto);
			resto = Integer.valueOf(strResto.substring(1,2));
		}
		if (resto!=(Integer.valueOf(numPIS.substring(10,11)))){
			return false;
		}
		return true;
	}
	
	/**
	 * Method isCEPValido.
	 * @param value String
	
	 * @return boolean */
	public static boolean isCEPValido(String value){
		return false;
	}
	
	/**
	 * Method isDataValida.
	 * @param value String
	
	 * @return boolean */
	public static boolean isDataValida(String value){
		return false;
	}
	
	/**
	 * Method isMoedaValida.
	 * @param value String
	
	 * @return boolean */
	public static boolean isMoedaValida(String value){
		return false;
	}
	
	/**
	 * Method isCEPValido.
	 * @param value Long
	
	 * @return boolean */
	public static boolean isCEPValido(Long value){
		return false;
	}
	
	/**
	 * Method isCPFValido.
	 * @param value Long
	
	 * @return boolean */
	public static boolean isCPFValido(Long value){
		return false;
	}

	/**
	 * Method getSomenteNumeros.
	 * @param value String
	
	 * @return String */
	public static String getSomenteNumeros(String value) {
		LOGGER.debug("Retirando caracteres não numéricos");
		final String regex = "[^0123456789]";
		if(StringUtils.isNotBlank(value)){
			return value.replaceAll(regex, BLANK);
		}
		return null;
	}

	/**
	 * Method getCPFCormatado.
	 * @param cpf Long
	
	 * @return String */
	public static String getCPFCormatado(Long cpf) {
		String cpfRetorno = StringUtils.leftPad(String.valueOf(cpf), 11, '0');
		return cpfRetorno.substring(0,3) + "." + cpfRetorno.substring(3,6) + "." + cpfRetorno.substring(6,9) + "-" + cpfRetorno.substring(9);
	}
	
	/**
	 * Method getCEPFormatado.
	 * @param cep Long
	
	 * @return String */
	public static String getCEPFormatado(Long cep){
		String cepRetorno = StringUtils.rightPad(String.valueOf(cep), 8, '0');
		return cepRetorno.substring(0,2) + "." + cepRetorno.substring(2,5) + "-" + cepRetorno.substring(5); 
	}
	
	/**
	 * Method getCNPJFormatado.
	 * @param cnpj Long
	
	 * @return String */
	public static String getCNPJFormatado(Long cnpj){
		String cnpjRetorno = StringUtils.leftPad(String.valueOf(cnpj), 14, '0');
		return cnpjRetorno.substring(0,2) + "." + cnpjRetorno.substring(2,5) + "." + cnpjRetorno.substring(5,8) + "/" + cnpjRetorno.substring(8,12) + "-" + cnpjRetorno.substring(12);
	}
	
	/**
	 * Method getDataFormatada.
	 * @param data Date
	
	 * @return String */
	public static String getDataFormatada(Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
}
