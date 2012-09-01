package richard.falconrh.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.entity.seguranca.Acesso;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Nacionalidade;
import richard.falconrh.modelo.enums.Sexo;

public class TesteUtils {
	public static Acesso getAcesso(){
		Calendar calendar= Calendar.getInstance(new Locale("pt", "BR"));
		calendar.clear();
		calendar.set(2012, Calendar.JANUARY, 1);
		Date dataLogoff = calendar.getTime();
		Date dataLogon = calendar.getTime();
		Acesso acesso = new Acesso();
		acesso.setDataLogoff(dataLogoff);
		acesso.setDataLogon(dataLogon);
		acesso.setId(1L);
		acesso.setIdSessao("ABCDEFGH1234567890");
		acesso.setIpServidorAplicacao("1.1.1.1");
		acesso.setIpUsuario("1.1.1.1");
		acesso.setUsuario(getUsuario());
		return acesso;
	}
	
	public static Usuario getUsuario(){
		Usuario usuario = new Usuario();
		try {
			BeanUtils.copyProperties(usuario, getPessoa());
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuario.setAtivo(Boolean.TRUE);
		usuario.setLogin("nome.sobrenome");
		usuario.setSenha("1234567890");
		return usuario;
	}
	
	public static Pessoa getPessoa(){
		String nome = "Juciara Mendes Madureira";
		Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
		calendar.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		
		calendar.clear();
		calendar.set(1996, Calendar.NOVEMBER, 22);
		Date dataNascimento = calendar.getTime();
		EstadoCivil estadoCivil = EstadoCivil.SOLTEIRO;
		Etnia etnia = Etnia.NEGRA;
		Nacionalidade nacionalidade = Nacionalidade.BRASILEIRO;
		Sexo sexo = Sexo.FEMININO;
		Boolean deficienteFisico = Boolean.FALSE;
		String email = "neguinhamm@hotmail.com";
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome(nome);
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setEstadoCivil(estadoCivil);
		pessoa.setEtnia(etnia);
		pessoa.setNacionalidade(nacionalidade);
		pessoa.setSexo(sexo);
		pessoa.setDeficienteFisico(deficienteFisico);
		pessoa.setEmail(email);
		return pessoa;
	}
}
