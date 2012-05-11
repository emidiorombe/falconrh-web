package richard.falconrh.service.impl;

import java.util.Set;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.entity.localizacao.Municipio_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.modelo.enums.UF;
import richard.falconrh.service.MunicipioServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/MunicipioServices", mappedName="MunicipioServices")
public class MunicipioServicesImpl extends AbstractServicesImpl<Municipio> implements MunicipioServices{

	/**
	 * Method obterListaMunicipiosPelaUF.
	 * @param uf UF
	
	
	
	 * @return Set<Municipio> * @throws ServicesException * @see richard.falconrh.service.MunicipioServices#obterListaMunicipiosPelaUF(UF) */
	@Override
	public Set<Municipio> obterListaMunicipiosPelaUF(UF uf) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Municipio> criteriaQuery = criteriaBuilder.createQuery(Municipio.class);
		Root<Municipio> from = criteriaQuery.from(Municipio.class);
		
		Predicate restricaoUF = criteriaBuilder.equal(from.get(Municipio_.uf), uf);
		
		criteriaQuery.where(restricaoUF);
		TypedQuery<Municipio> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new TreeSet<Municipio>(typedQuery.getResultList());
	}
	
	/**
	 * Method obterListaPeloExemplo.
	 * @param municipio Municipio
	
	
	 * @return Set<Municipio> * @throws ServicesException */
	@Override
	public Set<Municipio> obterListaPeloExemplo(Municipio municipio) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Municipio> criteriaQuery = criteriaBuilder.createQuery(Municipio.class);
		Root<Municipio> from = criteriaQuery.from(Municipio.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(municipio.getUf()!=null){
			Predicate restricaoUF = criteriaBuilder.equal(from.get(Municipio_.uf), municipio.getUf());
			listaRestricoes = criteriaBuilder.and(restricaoUF);
		}
		if(StringUtils.isNotBlank(municipio.getNome())){
			Predicate restricaoNome = criteriaBuilder.like(criteriaBuilder.lower(from.get(Municipio_.nome)), "%" + municipio.getNome().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(restricaoNome);
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Municipio> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new TreeSet<Municipio>(typedQuery.getResultList());
	}
}