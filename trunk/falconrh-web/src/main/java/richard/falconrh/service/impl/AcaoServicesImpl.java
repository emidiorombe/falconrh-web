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

import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.entity.seguranca.Acao_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AcaoServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/AcaoServices", mappedName="AcaoServices")
public class AcaoServicesImpl extends AbstractServicesImpl<Acao> implements AcaoServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity Acao
	 * @return Set<Acao> * @throws ServicesException */
	@Override
	public Set<Acao> obterListaPeloExemplo(Acao acao) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Acao> criteriaQuery = criteriaBuilder.createQuery(Acao.class);
		Root<Acao> from = criteriaQuery.from(Acao.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(acao.getNome())){
			Predicate restricaoNome =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Acao_.nome)), "%" + acao.getNome().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNome);
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Acao> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Acao>(typedQuery.getResultList());
	}

	/**
	 * Método que obterm a lista de todas as ações
	 * @return uma instância de  HashSet<Acao> contendo todas as ações do sistema. 
	 * @throws ServicesException
	 */
	@Override
	public Set<Acao> obterListaTodasAcoes() throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Acao> criteriaQuery = criteriaBuilder.createQuery(Acao.class);
		criteriaQuery.from(Acao.class);
		TypedQuery<Acao> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Acao>(typedQuery.getResultList());
	}
}
