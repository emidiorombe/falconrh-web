package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.modelo.enums.UF;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Remote
public interface MunicipioServices extends AbstractServices<Municipio>{

	/**
	 * Method obterListaMunicipiosPelaUF.
	 * @param uf UF
	
	
	 * @return Set<Municipio> * @throws ServicesException */
	public Set<Municipio> obterListaMunicipiosPelaUF(UF uf) throws ServicesException;
}