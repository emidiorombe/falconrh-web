package richard.falconrh.service;

import java.util.Set;

import richard.falconrh.entity.Parent;
import richard.falconrh.exception.ServicesException;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public interface AbstractServices<T extends Parent> {
	/**
	 * Method cadastrar.
	 * @param entity T
	
	 * @throws ServicesException */
	public void cadastrar(T entity) throws ServicesException;
	/**
	 * Method alterar.
	 * @param entity T
	
	 * @throws ServicesException */
	public void alterar(T entity) throws ServicesException;
	/**
	 * Method excluirPeloId.
	 * @param clazz Class<T>
	 * @param id Long
	
	 * @throws ServicesException */
	public void excluirPeloId(Class<T> clazz, Long id) throws ServicesException;
	/**
	 * Method obterPeloId.
	 * @param clazz Class<T>
	 * @param id Long
	
	
	 * @return T * @throws ServicesException */
	public T obterPeloId(Class<T> clazz, Long id) throws ServicesException;
	/**
	 * Method obterListaPeloExemplo.
	 * @param exampleEntity T
	
	
	 * @return Set<T> * @throws ServicesException */
	public Set<T> obterListaPeloExemplo(T exampleEntity) throws ServicesException;
}
