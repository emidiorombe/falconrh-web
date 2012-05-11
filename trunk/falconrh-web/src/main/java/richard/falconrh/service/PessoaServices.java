package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.exception.ServicesException;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Remote
public interface PessoaServices extends AbstractServices<Pessoa>{

	/**
	 * Method obterListaTodasPessoas.
	
	
	 * @return Set<Pessoa> * @throws ServicesException */
	Set<Pessoa> obterListaTodasPessoas()throws ServicesException;
}
