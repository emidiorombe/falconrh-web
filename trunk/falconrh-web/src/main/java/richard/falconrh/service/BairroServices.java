package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.exception.ServicesException;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Remote
public interface BairroServices extends AbstractServices<Bairro>{
	/**
	 * Método que obterm a lista de bairros a partir do identificador (chave primária) do município.
	 * @param idMunicipio o identificador único do município (chave primária).
	 * @return Set<Bairro> a lista de bairros que pertencem ao identificador do município informado.
	 * @throws ServicesException exceção de aplicação que é lançada quando ocorrer qualquer erro no método.
	 */
	public Set<Bairro> obterListaPeloIdMunicipio(Long idMunicipio) throws ServicesException;
}
