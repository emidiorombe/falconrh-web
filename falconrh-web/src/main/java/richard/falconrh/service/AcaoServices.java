package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.exception.ServicesException;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Remote
public interface AcaoServices extends AbstractServices<Acao>{
	/**
	 * Método que obém todas as ações cadastradas em bancoi
	 * @return um HashSet que contem todas as Ações cadastradas em banco.
	 * @throws ServicesException
	 */
	public Set<Acao> obterListaTodasAcoes() throws ServicesException;
}
