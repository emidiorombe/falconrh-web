package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.entity.seguranca.Funcionalidade;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Remote
public interface FuncionalidadeServices extends AbstractServices<Funcionalidade>{
	public Set<Funcionalidade> obterListaTodasFuncionalidades();
}
