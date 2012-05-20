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

import richard.falconrh.entity.ir.TabelaImpostoRenda;
import richard.falconrh.entity.ir.TabelaImpostoRenda_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.ImpostoRendaServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/ImpostoRendaServices", mappedName="ImpostoRendaServices")
public class ImpostoRendaServicesImpl extends AbstractServicesImpl<TabelaImpostoRenda> implements ImpostoRendaServices{
	
	@Override
	public Set<TabelaImpostoRenda> obterListaPeloExemplo(TabelaImpostoRenda tabelaImpostoRenda) throws ServicesException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<TabelaImpostoRenda> cq = cb.createQuery(TabelaImpostoRenda.class);
		Root<TabelaImpostoRenda> from = cq.from(TabelaImpostoRenda.class);
		
		Predicate listaRestricoes = cb.disjunction();
		
		if(tabelaImpostoRenda.getAnoBase()!=null){
			Predicate restricaoAnoBase = cb.equal(from.get(TabelaImpostoRenda_.anoBase), tabelaImpostoRenda.getAnoBase());
			listaRestricoes = cb.or(listaRestricoes, restricaoAnoBase);
		}
		if(tabelaImpostoRenda.getDataInicioVigencia()!=null){
			Predicate restricaoDataInicio = cb.equal(from.get(TabelaImpostoRenda_.dataInicioVigencia), tabelaImpostoRenda.getDataInicioVigencia());
			listaRestricoes = cb.or(listaRestricoes, restricaoDataInicio);
		}
		if(tabelaImpostoRenda.getDataTerminoVigencia()!=null){
			Predicate restricaoDataTermino = cb.equal(from.get(TabelaImpostoRenda_.dataTerminoVigencia), tabelaImpostoRenda.getDataTerminoVigencia());
			listaRestricoes = cb.or(listaRestricoes, restricaoDataTermino);
		}
		if(StringUtils.isNotBlank(tabelaImpostoRenda.getDescricao())){
			Predicate restricaoDescricao = cb.like(cb.lower(from.get(TabelaImpostoRenda_.descricao)), "%" + tabelaImpostoRenda.getDescricao().toLowerCase() + "%");
			listaRestricoes = cb.or(listaRestricoes, restricaoDescricao);
		}
		if(tabelaImpostoRenda.getListaAliquotasImpostoRenda()!=null){
			//TODO incluir restrições de aliquotas de imposto de renda na pesquisa por exemplo 
		}
		
		cq.where(listaRestricoes);
		TypedQuery<TabelaImpostoRenda> query = getEntityManager().createQuery(cq);
		return new HashSet<TabelaImpostoRenda>(query.getResultList());
	}
}
