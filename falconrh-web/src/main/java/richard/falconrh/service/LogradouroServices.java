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

	/**
	 * Método que obtem o logradouro a partir do cep informado.
	 * @param cepPesquisa o cep a ser pesquisado.
	 * @return uma instância de Logradouro caso encontre alguma informação, ou null, caso contrário.
	 */
	public Logradouro obterLogradouroPeloCep(Long cepPesquisa);
}
