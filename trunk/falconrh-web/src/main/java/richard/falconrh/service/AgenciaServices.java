package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.exception.ServicesException;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Remote
public interface AgenciaServices extends AbstractServices<Agencia>{
	/**
	 * Method obterListaPeloIdBanco.
	 * @param idBanco Long
	 * @return Set<Agencia> * @throws ServicesException */
	public Set<Agencia> obterListaPeloIdBanco(Long idBanco) throws ServicesException;
	
	/**
	 * Metodo que obtem todas as agencias Cadastradas
	 */
	public Set<Agencia> obterListaTodasAgencias() throws ServicesException;
}
