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

import richard.falconrh.exception.ServicesException;
import richard.falconrh.scheduler.Tarefa;
import richard.falconrh.scheduler.Tarefa_;
import richard.falconrh.service.TarefaServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/TarefaServices", mappedName="TarefaServices")
public class TarefaServicesImpl extends AbstractServicesImpl<Tarefa> implements TarefaServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity Tarefa
	 * @return Set<Tarefa> * @throws ServicesException
	 */
	@Override
	public Set<Tarefa> obterListaPeloExemplo(Tarefa tarefa) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Tarefa> criteriaQuery = criteriaBuilder.createQuery(Tarefa.class);
		Root<Tarefa> from = criteriaQuery.from(Tarefa.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(tarefa.getNome())){
			Predicate restricaoNome =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Tarefa_.nome)), "%" + tarefa.getNome().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNome);
		}
		if(StringUtils.isNotBlank(tarefa.getDescricao())){
			Predicate restricaoDescricao =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Tarefa_.descricao)), "%" + tarefa.getDescricao().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoDescricao);
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Tarefa> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Tarefa>(typedQuery.getResultList());
	}

	@Override
	public SortedSet<Tarefa> obterListaTodasTarefas() throws ServicesException {
		TypedQuery<Tarefa> typedQuery = getEntityManager().createQuery("select t from Tarefa t", Tarefa.class);
		return new TreeSet<Tarefa>(typedQuery.getResultList());
	}

}
