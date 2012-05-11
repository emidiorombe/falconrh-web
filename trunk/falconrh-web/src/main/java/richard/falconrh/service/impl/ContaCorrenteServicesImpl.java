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

import richard.falconrh.entity.banco.Agencia_;
import richard.falconrh.entity.banco.Banco_;
import richard.falconrh.entity.banco.ContaCorrente;
import richard.falconrh.entity.banco.ContaCorrente_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.ContaCorrenteServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/ContaCorrenteServices", mappedName="ContaCorrenteServices")
public class ContaCorrenteServicesImpl extends AbstractServicesImpl<ContaCorrente> implements ContaCorrenteServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity ContaCorrente
	
	
	 * @return Set<ContaCorrente> * @throws ServicesException */
	@Override
	public Set<ContaCorrente> obterListaPeloExemplo(ContaCorrente entity) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ContaCorrente> criteriaQuery = criteriaBuilder.createQuery(ContaCorrente.class);
		Root<ContaCorrente> from = criteriaQuery.from(ContaCorrente.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(entity.getNumero())){
			Predicate restricaoNumero =  criteriaBuilder.equal(from.get(ContaCorrente_.numero), entity.getNumero());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNumero);
		}
		if(StringUtils.isNotBlank(entity.getDigitoVerificador())){
			Predicate restricaoDV =  criteriaBuilder.equal(from.get(ContaCorrente_.digitoVerificador), entity.getNumero());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoDV);
		}
		if(entity.getAgencia()!=null){
			if(entity.getAgencia().getId()!=null){
				Predicate restricaoIdAgencia = criteriaBuilder.equal(from.get(ContaCorrente_.agencia), entity.getAgencia());
				listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoIdAgencia);
			}else{
				if(StringUtils.isNotBlank(entity.getAgencia().getNome())){
					Predicate restricaoNomeAgencia = criteriaBuilder.like(criteriaBuilder.lower(from.get(ContaCorrente_.agencia).get(Agencia_.nome)), "%" + entity.getAgencia().getNome().toLowerCase() + "%");
					listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNomeAgencia);
				}
				if(StringUtils.isNotBlank(entity.getAgencia().getNumero())){
					Predicate restricaoNumeroAgencia = criteriaBuilder.equal(from.get(ContaCorrente_.agencia).get(Agencia_.numero), entity.getAgencia().getNumero());
					listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNumeroAgencia);
				}
				if(StringUtils.isNotBlank(entity.getAgencia().getNumero())){
					Predicate restricaoDVAgencia = criteriaBuilder.equal(from.get(ContaCorrente_.agencia).get(Agencia_.digitoVerificador), entity.getAgencia().getDigitoVerificador());
					listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoDVAgencia);
				}
				if(entity.getAgencia().getBanco()!=null){
					if(entity.getAgencia().getBanco().getId()!=null){
						Predicate restricaoIdBanco = criteriaBuilder.equal(from.get(ContaCorrente_.agencia).get(Agencia_.banco), entity.getAgencia().getBanco());
						listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoIdBanco);
					}else{
						if(StringUtils.isNotBlank(entity.getAgencia().getBanco().getCodigoFebraban())){
							Predicate restricaoCodigoFebraban =  criteriaBuilder.equal(from.get(ContaCorrente_.agencia).get(Agencia_.banco).get(Banco_.codigoFebraban), entity.getAgencia().getBanco().getCodigoFebraban());
							listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoCodigoFebraban);
						}
						if(StringUtils.isNotBlank(entity.getAgencia().getBanco().getNome())){
							Predicate restricaoNome =  criteriaBuilder.like(criteriaBuilder.lower(from.get(ContaCorrente_.agencia).get(Agencia_.banco).get(Banco_.nome)), "%" + entity.getAgencia().getBanco().getNome().toLowerCase()+ "%");
							listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNome);
						}
					}
				}
			}
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<ContaCorrente> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<ContaCorrente>(typedQuery.getResultList());
	}
}
