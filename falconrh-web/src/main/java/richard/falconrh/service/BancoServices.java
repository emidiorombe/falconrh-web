package richard.falconrh.service;

import java.util.SortedSet;

import javax.ejb.Remote;

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.entity.banco.Banco;
import richard.falconrh.exception.ServicesException;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Remote
public interface BancoServices extends AbstractServices<Banco>{
	/**
	 * Method obterListaAgenciasPeloIdBanco.
	 * @param idBanco Long
	
	
	 * @return SortedSet<Agencia> * @throws ServicesException */
	public SortedSet<Agencia> obterListaAgenciasPeloIdBanco(Long idBanco) throws ServicesException;
	/**
	 * Method obterListaTodosBancos.
	
	
	 * @return SortedSet<Banco> * @throws ServicesException */
	public SortedSet<Banco> obterListaTodosBancos() throws ServicesException;
}
