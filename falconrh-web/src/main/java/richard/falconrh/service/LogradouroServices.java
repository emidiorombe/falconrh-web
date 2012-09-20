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
	 * Método que obtem a lista de logradouros a partir do identificador do Bairro
	 * @param id o identificador do bairro
	 * @return uma lista de logradouros pertencentes ao id do bairro informado.
	 */
	public Set<Logradouro> obterListaPeloIdBairro(Long id) throws ServicesException;
}
