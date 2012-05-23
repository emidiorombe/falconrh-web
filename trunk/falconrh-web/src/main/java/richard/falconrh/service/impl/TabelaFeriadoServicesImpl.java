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

import richard.falconrh.entity.feriado.TabelaFeriado;
import richard.falconrh.entity.feriado.TabelaFeriado_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.TabelaFeriadoServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/TabelaFeriadoServices", mappedName="TabelaFeriadoServices")
public class TabelaFeriadoServicesImpl extends AbstractServicesImpl<TabelaFeriado> implements TabelaFeriadoServices{
	
	@Override
	public Set<TabelaFeriado> obterListaPeloExemplo(TabelaFeriado tabelaFeriado)throws ServicesException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<TabelaFeriado> cq = cb.createQuery(TabelaFeriado.class);
		Root<TabelaFeriado> from = cq.from(TabelaFeriado.class);
		
		Predicate listaRestricoes = cb.disjunction();
		
		if(StringUtils.isNotBlank(tabelaFeriado.getDescricao())){
			Predicate restricaoDescricao = cb.like(cb.lower(from.get(TabelaFeriado_.descricao)), "%" + tabelaFeriado.getDescricao().toLowerCase() + "%");
			listaRestricoes = cb.or(listaRestricoes, restricaoDescricao);
		}
		if(tabelaFeriado.getAno()!=null){
			Predicate restricaoAno = cb.equal(from.get(TabelaFeriado_.ano), tabelaFeriado.getAno());
			listaRestricoes = cb.or(listaRestricoes, restricaoAno);
		}
		if(tabelaFeriado.getListaFeriados()!=null && !tabelaFeriado.getListaFeriados().isEmpty()){
			//TODO incluir restrições para as listas de feriados na pesquisa pelo exemplo
		}
		
		cq.where(listaRestricoes);
		
		TypedQuery<TabelaFeriado> query = getEntityManager().createQuery(cq);
		return new HashSet<TabelaFeriado>(query.getResultList());
	}
}
