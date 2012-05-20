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

import richard.falconrh.entity.inss.TabelaINSS;
import richard.falconrh.entity.inss.TabelaINSS_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.TabelaINSSServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/TabelaINSSServices", mappedName="TabelaINSSServices")
public class TabelaINSSServicesImpl extends AbstractServicesImpl<TabelaINSS> implements TabelaINSSServices{
	
	@Override
	public Set<TabelaINSS> obterListaPeloExemplo(TabelaINSS tabelaINSS)throws ServicesException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<TabelaINSS> cq = cb.createQuery(TabelaINSS.class);
		Root<TabelaINSS> from = cq.from(TabelaINSS.class);
		
		Predicate listaRestricoes = cb.disjunction();
		if(StringUtils.isNotBlank(tabelaINSS.getDescricao())){
			Predicate restricaoDescricao = cb.like(cb.lower(from.get(TabelaINSS_.descricao)), "%" +tabelaINSS.getDescricao() + "%");
			listaRestricoes = cb.or(listaRestricoes, restricaoDescricao);
		}
		if(tabelaINSS.getDataInicioVigencia()!=null){
			Predicate restricaoDataInicio = cb.equal(from.get(TabelaINSS_.dataInicioVigencia), tabelaINSS.getDataInicioVigencia());
			listaRestricoes = cb.or(listaRestricoes, restricaoDataInicio);
		}
		if(tabelaINSS.getDataTerminoVigencia()!=null){
			Predicate restricaoDataTermino = cb.equal(from.get(TabelaINSS_.dataTerminoVigencia), tabelaINSS.getDataTerminoVigencia());
			listaRestricoes = cb.or(listaRestricoes, restricaoDataTermino);
		}
		if(tabelaINSS.getListaAliquotasINSS()!=null && !tabelaINSS.getListaAliquotasINSS().isEmpty()){
			//TODO incluir restrições para as aliquotas de INSS na pesquisa por exemplo
		}
		
		cq.where(listaRestricoes);
		
		TypedQuery<TabelaINSS> query = getEntityManager().createQuery(cq);
		return new HashSet<TabelaINSS>(query.getResultList());
	}
}
