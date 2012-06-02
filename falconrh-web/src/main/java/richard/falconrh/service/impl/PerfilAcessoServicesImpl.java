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

import richard.falconrh.entity.seguranca.PerfilAcesso;
import richard.falconrh.entity.seguranca.PerfilAcesso_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.PerfilAcessoServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/PerfilAcessoServices", mappedName="PerfilAcessoServices")
public class PerfilAcessoServicesImpl extends AbstractServicesImpl<PerfilAcesso> implements PerfilAcessoServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity PerfilAcesso
	 * @return Set<PerfilAcesso> * @throws ServicesException
	 */
	@Override
	public Set<PerfilAcesso> obterListaPeloExemplo(PerfilAcesso perfilAcesso) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PerfilAcesso> criteriaQuery = criteriaBuilder.createQuery(PerfilAcesso.class);
		Root<PerfilAcesso> from = criteriaQuery.from(PerfilAcesso.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(perfilAcesso.getNome())){
			Predicate restricaoNome =  criteriaBuilder.like(criteriaBuilder.lower(from.get(PerfilAcesso_.nome)), "%" + perfilAcesso.getNome().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNome);
		}
		if(perfilAcesso.getNivelAcesso()!=null){
			Predicate restricaoNivelAcesso = criteriaBuilder.equal(from.get(PerfilAcesso_.nivelAcesso), perfilAcesso.getNivelAcesso());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNivelAcesso);
		}
		if(perfilAcesso.getAtivo()!=null){
			Predicate restricaoAtivo = criteriaBuilder.equal(from.get(PerfilAcesso_.ativo), perfilAcesso.getAtivo());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoAtivo);
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<PerfilAcesso> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<PerfilAcesso>(typedQuery.getResultList());
	}

}