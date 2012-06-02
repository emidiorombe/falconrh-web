package richard.falconrh.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import richard.falconrh.entity.seguranca.Funcionalidade;
import richard.falconrh.entity.seguranca.Funcionalidade_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.FuncionalidadeServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/FuncionalidadeServices", mappedName="FuncionalidadeServices")
public class FuncionalidadeServicesImpl extends AbstractServicesImpl<Funcionalidade> implements FuncionalidadeServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity Funcionalidade
	 * @return Set<Funcionalidade> * @throws ServicesException
	 */
	@Override
	public Set<Funcionalidade> obterListaPeloExemplo(Funcionalidade funcionalidade) throws ServicesException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Funcionalidade> criteriaQuery = cb.createQuery(Funcionalidade.class);
		Root<Funcionalidade> from = criteriaQuery.from(Funcionalidade.class);
		
		Predicate listaRestricoes = cb.conjunction();
		if(StringUtils.isNotBlank(funcionalidade.getNome())){
			Predicate restricaoNome =  cb.like(cb.lower(from.get(Funcionalidade_.nome)), "%" + funcionalidade.getNome().toLowerCase() + "%");
			listaRestricoes = cb.and(listaRestricoes, restricaoNome);
		}
		if(StringUtils.isNotBlank(funcionalidade.getLink())){
			Predicate restricaoLink =  cb.like(cb.lower(from.get(Funcionalidade_.link)), "%" + funcionalidade.getLink().toLowerCase() + "%");
			listaRestricoes = cb.and(listaRestricoes, restricaoLink);
		}
		if(funcionalidade.getAtiva()!=null){
			Predicate restricaoAtiva =  cb.equal(from.get(Funcionalidade_.ativa), funcionalidade.getAtiva());
			listaRestricoes = cb.and(listaRestricoes, restricaoAtiva);
		}
		if(funcionalidade.getListaAcoes()!=null){
			//TODO implementar a restrições de lista de ações
		}
		
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Funcionalidade> typedQuery = getEntityManager().createQuery(criteriaQuery);
		List<Funcionalidade> resultList = typedQuery.getResultList();
		for(Funcionalidade f : resultList){
			f.getListaAcoes().size();
		}
		return new HashSet<Funcionalidade>(resultList);
	}

	@Override
	public void cadastrar(Funcionalidade entity) throws ServicesException {
		entity = getEntityManager().merge(entity);
		super.cadastrar(entity);
	}
	
	@Override
	public void alterar(Funcionalidade entity) throws ServicesException {
		entity = getEntityManager().merge(entity);
		super.alterar(entity);
	}
	
	@Override
	public Funcionalidade obterPeloId(Class<Funcionalidade> clazz, Long id)throws ServicesException {
		Funcionalidade f = super.obterPeloId(clazz, id);
		f.getListaAcoes().size();
		return f;
	}

	@Override
	public Set<Funcionalidade> obterListaTodasFuncionalidades() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Funcionalidade> cq = cb.createQuery(Funcionalidade.class);
		cq.from(Funcionalidade.class);
		TypedQuery<Funcionalidade> tq = getEntityManager().createQuery(cq);
		return new HashSet<Funcionalidade>(tq.getResultList());
	}
}
