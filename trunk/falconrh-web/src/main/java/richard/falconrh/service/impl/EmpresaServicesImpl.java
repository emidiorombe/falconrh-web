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

import richard.falconrh.entity.empresa.Empresa;
import richard.falconrh.entity.empresa.Empresa_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.EmpresaServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/EmpresaServices", mappedName="EmpresaServices")
public class EmpresaServicesImpl extends AbstractServicesImpl<Empresa> implements EmpresaServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity Empresa
	 * @return Set<Empresa> * @throws ServicesException */
	@Override
	public Set<Empresa> obterListaPeloExemplo(Empresa empresa) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> from = criteriaQuery.from(Empresa.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(empresa.getNomeEmpresarial())){
			Predicate restricaoNomeEmpresarial =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Empresa_.nomeEmpresarial)), "%" + empresa.getNomeEmpresarial().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNomeEmpresarial);
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Empresa> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Empresa>(typedQuery.getResultList());
	}
}
