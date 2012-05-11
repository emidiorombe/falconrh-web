package richard.falconrh.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.entity.banco.Agencia_;
import richard.falconrh.entity.banco.Banco_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AgenciaServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/AgenciaServices", mappedName="AgenciaServices")
public class AgenciaServicesImpl extends AbstractServicesImpl<Agencia> implements AgenciaServices {

	/**
	 * Method obterListaPeloIdBanco.
	 * @param idBanco Long
	
	
	
	 * @return Set<Agencia> * @throws ServicesException * @see richard.falconrh.service.AgenciaServices#obterListaPeloIdBanco(Long) */
	@Override
	public Set<Agencia> obterListaPeloIdBanco(Long idBanco) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Agencia> criteriaQuery = criteriaBuilder.createQuery(Agencia.class);
		Root<Agencia> from = criteriaQuery.from(Agencia.class);
		
		Predicate restricaoIdBanco = criteriaBuilder.equal(from.get(Agencia_.banco).get(Banco_.id), idBanco);
		
		criteriaQuery.where(restricaoIdBanco);
		TypedQuery<Agencia> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Agencia>(typedQuery.getResultList());
	}
	
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity Agencia
	
	
	 * @return Set<Agencia> * @throws ServicesException */
	@Override
	public Set<Agencia> obterListaPeloExemplo(Agencia entity) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Agencia> criteriaQuery = criteriaBuilder.createQuery(Agencia.class);
		Root<Agencia> from = criteriaQuery.from(Agencia.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(entity.getNome())){
			Predicate restricaoNome =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Agencia_.nome)), "%" + entity.getNome().toLowerCase()+ "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNome);
		}
		if(StringUtils.isNotBlank(entity.getNumero())){
			Predicate restricaoNumero =  criteriaBuilder.equal(from.get(Agencia_.numero), entity.getNumero());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNumero);
		}
		if(StringUtils.isNotBlank(entity.getDigitoVerificador())){
			Predicate restricaoDV =  criteriaBuilder.equal(from.get(Agencia_.digitoVerificador), entity.getDigitoVerificador());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoDV);
		}
		if(entity.getBanco()!=null){
			if(entity.getBanco().getId()!=null){
				Predicate restricaoIdBanco = criteriaBuilder.equal(from.get(Agencia_.banco), entity.getBanco());
				listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoIdBanco);
			}else{
				if(StringUtils.isNotBlank(entity.getBanco().getCodigoFebraban())){
					Predicate restricaoCodigoFebraban =  criteriaBuilder.equal(from.get(Agencia_.banco).get(Banco_.codigoFebraban), entity.getBanco().getCodigoFebraban());
					listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoCodigoFebraban);
				}
				if(StringUtils.isNotBlank(entity.getBanco().getNome())){
					Predicate restricaoNome =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Agencia_.banco).get(Banco_.nome)), "%" + entity.getBanco().getNome().toLowerCase()+ "%");
					listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNome);
				}
			}
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Agencia> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Agencia>(typedQuery.getResultList());
	}
}
