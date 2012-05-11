package richard.falconrh.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.entity.banco.Agencia_;
import richard.falconrh.entity.banco.Banco;
import richard.falconrh.entity.banco.Banco_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.BancoServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/BancoServices", mappedName="BancoServices")
public class BancoServicesImpl extends AbstractServicesImpl<Banco> implements BancoServices{

	/**
	 * Method obterListaAgenciasPeloIdBanco.
	 * @param idBanco Long
	
	
	
	 * @return SortedSet<Agencia> * @throws ServicesException * @see richard.falconrh.service.BancoServices#obterListaAgenciasPeloIdBanco(Long) */
	@Override
	public SortedSet<Agencia> obterListaAgenciasPeloIdBanco(Long idBanco) throws ServicesException{
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Agencia> criteriaQuery = criteriaBuilder.createQuery(Agencia.class);
		Root<Agencia> from = criteriaQuery.from(Agencia.class);
		
		Predicate restricaoIdBanco = criteriaBuilder.equal(from.get(Agencia_.banco).get(Banco_.id), idBanco);
		
		criteriaQuery.where(restricaoIdBanco);
		TypedQuery<Agencia> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new TreeSet<Agencia>(typedQuery.getResultList());
	}

	/**
	 * Method obterListaTodosBancos.
	
	
	
	 * @return SortedSet<Banco> * @throws ServicesException * @see richard.falconrh.service.BancoServices#obterListaTodosBancos() */
	@Override
	public SortedSet<Banco> obterListaTodosBancos() throws ServicesException {
		TypedQuery<Banco> typedQuery = getEntityManager().createQuery("select b from Banco b", Banco.class);
		return new TreeSet<Banco>(typedQuery.getResultList());
	}
	
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity Banco
	
	
	 * @return Set<Banco> * @throws ServicesException */
	@Override
	public Set<Banco> obterListaPeloExemplo(Banco entity) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Banco> criteriaQuery = criteriaBuilder.createQuery(Banco.class);
		Root<Banco> from = criteriaQuery.from(Banco.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(entity.getCodigoFebraban())){
			Predicate restricaoCodigoFebraban =  criteriaBuilder.like(from.get(Banco_.codigoFebraban), "%" + entity.getCodigoFebraban()+ "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoCodigoFebraban);
		}
		if(StringUtils.isNotBlank(entity.getNome())){
			Predicate restricaoNome =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Banco_.nome)), "%" + entity.getNome().toLowerCase()+ "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNome);
		}
		
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Banco> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Banco>(typedQuery.getResultList());
	}
}
