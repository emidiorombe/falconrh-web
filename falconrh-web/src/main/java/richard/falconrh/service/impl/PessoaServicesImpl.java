package richard.falconrh.service.impl;

import java.util.Set;

import javax.ejb.Stateless;

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.PessoaServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/PessoaServices", mappedName="PessoaServices")
public class PessoaServicesImpl extends AbstractServicesImpl<Pessoa> implements PessoaServices{

	/**
	 * Method obterListaTodasPessoas.
	
	
	
	 * @return Set<Pessoa> * @throws ServicesException * @see richard.falconrh.service.PessoaServices#obterListaTodasPessoas() */
	@Override
	public Set<Pessoa> obterListaTodasPessoas() throws ServicesException {
		// TODO Auto-generated method stub
		return null;
	}
}