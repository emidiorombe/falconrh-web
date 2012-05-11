package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.entity.localizacao.Logradouro;
import richard.falconrh.exception.ServicesException;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Remote
public interface LogradouroServices extends AbstractServices<Logradouro>{

	/**
	 * Method obterLogradourosPeloIdBairro.
	 * @param id Long
	
	
	 * @return Set<Logradouro> * @throws ServicesException */
	public Set<Logradouro> obterLogradourosPeloIdBairro(Long id) throws ServicesException;
}
