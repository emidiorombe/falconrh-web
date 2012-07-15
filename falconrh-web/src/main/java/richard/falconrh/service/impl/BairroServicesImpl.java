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

import richard.falconrh.entity.localizacao.Bairro;
import richard.falconrh.entity.localizacao.Bairro_;
import richard.falconrh.entity.localizacao.Municipio_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.BairroServices;


/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/BairroServices", mappedName="BairroServices")
public class BairroServicesImpl extends AbstractServicesImpl<Bairro> implements BairroServices {

	/**
	 * Method obterListaPeloIdMunicipio.
	 * @param idMunicipio Long
	
	
	
	 * @return Set<Bairro> * @throws ServicesException * @see richard.falconrh.service.BairroServices#obterListaPeloIdMunicipio(Long) */
	@Override
	public Set<Bairro> obterListaPeloIdMunicipio(Long idMunicipio) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Bairro> criteriaQuery = criteriaBuilder.createQuery(Bairro.class);
		Root<Bairro> from = criteriaQuery.from(Bairro.class);
		
		Predicate restricaoIdMunicipio = criteriaBuilder.equal(from.get(Bairro_.municipio).get(Municipio_.id), idMunicipio);
		criteriaQuery.where(restricaoIdMunicipio);
		TypedQuery<Bairro> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new TreeSet<Bairro>(typedQuery.getResultList());
	}
	
	/**
	 * Method obterListaPeloExemplo.
	 * @param bairro Bairro
	 * @return Set<Bairro> * @throws ServicesException
	 */
	@Override
	public Set<Bairro> obterListaPeloExemplo(Bairro bairro) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Bairro> criteriaQuery = criteriaBuilder.createQuery(Bairro.class);
		Root<Bairro> from = criteriaQuery.from(Bairro.class);
		from.fetch(Bairro_.municipio);//para evitar o lazy ao ler a uf, por exemplo
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(bairro.getNome())){
			Predicate restricaoNome = criteriaBuilder.like(criteriaBuilder.lower(from.get(Bairro_.nome)), "%" + bairro.getNome().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(restricaoNome);
		}
		if(bairro.getMunicipio()!=null){
			if(bairro.getMunicipio().getUf()!=null){
				Predicate restricaoUF = criteriaBuilder.equal(from.get(Bairro_.municipio).get(Municipio_.uf), bairro.getMunicipio().getUf());
				listaRestricoes = criteriaBuilder.and(restricaoUF);
			}
			if(StringUtils.isNotBlank(bairro.getMunicipio().getNome())){
				Predicate restricaoNome = criteriaBuilder.like(criteriaBuilder.lower(from.get(Bairro_.municipio).get(Municipio_.nome)), "%" + bairro.getMunicipio().getNome().toLowerCase() + "%");
				listaRestricoes = criteriaBuilder.and(restricaoNome);
			}
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Bairro> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new TreeSet<Bairro>(typedQuery.getResultList());
	}
}
