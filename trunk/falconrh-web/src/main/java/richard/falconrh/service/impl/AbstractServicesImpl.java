package richard.falconrh.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.ejb.HibernateEntityManager;

import richard.falconrh.entity.Parent;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AbstractServices;

/**
 * Classe abstrata com métodos comuns para os services.
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
public abstract class AbstractServicesImpl<T extends Parent> implements AbstractServices<T> {
	private static final Logger LOGGER = Logger.getLogger(AbstractServicesImpl.class);
	
	@PersistenceContext(unitName = "falconrh-ejb")
	private EntityManager entityManager;

	/**
	 * Method getEntityManager.
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * Method setEntityManager.
	 * @param entityManager EntityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Method cadastrar.
	 * @param entity T
	 * @throws ServicesException * @see richard.falconrh.service.AbstractServices#cadastrar(T)
	 */
	@Override
	public void cadastrar(T entity) throws ServicesException {
		LOGGER.debug("Inicio do cadastro da entitdade " + entity.getClass().getSimpleName());
		try{
			getEntityManager().persist(entity);
		}catch(Exception e){
			LOGGER.error("Erro ao cadastrar entitydade " + entity.getClass().getSimpleName(), e);
			throw new ServicesException("Erro ao cadastrar entitydade " + entity.getClass().getSimpleName(), e);
		}finally{
			LOGGER.debug("Fim do cadastro da entitidade " + entity.getClass().getSimpleName());
		}
	}

	/**
	 * Method alterar.
	 * @param entity T
	 * @throws ServicesException * @see richard.falconrh.service.AbstractServices#alterar(T)
	 */
	@Override
	public void alterar(T entity) throws ServicesException {
		LOGGER.debug("Inicio da alteracao da entitdade " + entity.getClass().getSimpleName());
		try{
			getEntityManager().merge(entity);
		}catch(Exception e){
			LOGGER.error("Erro ao alterar entidade " + entity.getClass().getSimpleName(), e);
			throw new ServicesException("Erro ao alterar entidade " + entity.getClass().getSimpleName(), e);
		}finally{
			LOGGER.debug("Fim da alteracao da entitidade " + entity.getClass().getSimpleName());
		}
	}

	/**
	 * Method excluirPeloId.
	 * @param clazz Class<T>
	 * @param id Long
	 * @throws ServicesException * @see richard.falconrh.service.AbstractServices#excluirPeloId(Class<T>, Long)
	 */
	@Override
	public void excluirPeloId(Class<T> clazz, Long id) throws ServicesException {
		LOGGER.debug("Inicio da exclusao da entitdade " + clazz.getSimpleName());
		try{
			T entity = getEntityManager().find(clazz, id);
			getEntityManager().remove(entity);
		}catch(Exception e){
			LOGGER.error("Erro ao excluir entidade " + clazz.getSimpleName(), e);
			throw new ServicesException("Erro ao excluir entidade " + clazz.getSimpleName(), e);
		}finally{
			LOGGER.debug("Fim da exclusao da entitidade " +clazz.getSimpleName());
		}		
	}

	/**
	 * Method obterPeloId.
	 * @param clazz Class<T>
	 * @param id Long
	 * @return T * @throws ServicesException * @see richard.falconrh.service.AbstractServices#obterPeloId(Class<T>, Long)
	 */
	@Override
	public T obterPeloId(Class<T> clazz, Long id) throws ServicesException {
		LOGGER.debug("Pesquisando entitdade " + clazz.getSimpleName());
		try{
			return getEntityManager().find(clazz, id);
		}catch(Exception e){
			LOGGER.error("Erro ao pesquisar entidade " + clazz.getSimpleName(), e);
			throw new ServicesException("Erro ao pesquisar entidade " + clazz.getSimpleName(), e);
		}finally{
			LOGGER.debug("Fim da pesquisa da entitidade " +clazz.getSimpleName());
		}
	}

	/**
	 * Method obterListaPeloExemplo.
	 * @param entity T
	 * @return Set<T> * @throws ServicesException * @see richard.falconrh.service.AbstractServices#obterListaPeloExemplo(T)
	 */
	@SuppressWarnings("unchecked")
	public Set<T> obterListaPeloExemplo(T entity) throws ServicesException{
		LOGGER.debug("Pesquisando entidade pelo exemplo");
		Set<T> lista = new HashSet<T>();
		try{
			Session session = ((HibernateEntityManager)getEntityManager()).getSession();
			Example example = Example.create(entity);
			example.enableLike(MatchMode.ANYWHERE);
			example.excludeZeroes();

			Criteria criteria = session.createCriteria(entity.getClass());
			criteria.add(example);
			lista = new HashSet<T>(criteria.list());
		}catch(Exception e){
			LOGGER.error("Erro ao pesquisar entidade pelo exemplo", e);
			throw new ServicesException();
		}
		return lista;
	}
	
}