package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.exception.ServicesException;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Remote
public interface BairroServices extends AbstractServices<Bairro>{
	/**
	 * Method obterListaPeloIdMunicipio.
	 * @param idMunicipio Long
	
	
	 * @return Set<Bairro> * @throws ServicesException */
	public Set<Bairro> obterListaPeloIdMunicipio(Long idMunicipio) throws ServicesException;
}
