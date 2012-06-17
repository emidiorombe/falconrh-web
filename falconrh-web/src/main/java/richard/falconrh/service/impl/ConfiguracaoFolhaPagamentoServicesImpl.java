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

import richard.falconrh.entity.banco.Agencia_;
import richard.falconrh.entity.folha.ConfiguracaoFolhaPagamento;
import richard.falconrh.entity.folha.ConfiguracaoFolhaPagamento_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.ConfiguracaoFolhaPagamentoServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/ConfiguracaoFolhaPagamentoServices", mappedName="ConfiguracaoFolhaPagamentoServices")
public class ConfiguracaoFolhaPagamentoServicesImpl extends AbstractServicesImpl<ConfiguracaoFolhaPagamento> implements ConfiguracaoFolhaPagamentoServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity ConfiguracaoFolhaPagamento
	 * @return Set<ConfiguracaoFolhaPagamento> * @throws ServicesException
	 */
	@Override
	public Set<ConfiguracaoFolhaPagamento> obterListaPeloExemplo(ConfiguracaoFolhaPagamento configuracaoFolhaPagamento) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ConfiguracaoFolhaPagamento> criteriaQuery = criteriaBuilder.createQuery(ConfiguracaoFolhaPagamento.class);
		Root<ConfiguracaoFolhaPagamento> from = criteriaQuery.from(ConfiguracaoFolhaPagamento.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(configuracaoFolhaPagamento.getAgenciaFGTS()!=null){
			if(StringUtils.isNotBlank(configuracaoFolhaPagamento.getAgenciaFGTS().getNome())){
				Predicate restricao = criteriaBuilder.like(criteriaBuilder.lower(from.get(ConfiguracaoFolhaPagamento_.agenciaFGTS).get(Agencia_.nome)), "%" + configuracaoFolhaPagamento.getAgenciaFGTS().getNome().toLowerCase() + "%");
				listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
			}
			
			if(StringUtils.isNotBlank(configuracaoFolhaPagamento.getAgenciaFGTS().getNumero())){
				Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.agenciaFGTS).get(Agencia_.numero), configuracaoFolhaPagamento.getAgenciaFGTS().getNumero());
				listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
			}
			
			if(StringUtils.isNotBlank(configuracaoFolhaPagamento.getAgenciaFGTS().getDigitoVerificador())){
				Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.agenciaFGTS).get(Agencia_.digitoVerificador), configuracaoFolhaPagamento.getAgenciaFGTS().getDigitoVerificador());
				listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
			}
		}
		if(configuracaoFolhaPagamento.getForcarDiaPagamentoFolhaAdiantamentoFeriasManual()!=null){
			Predicate restricao =  criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.forcarDiaPagamentoFolhaAdiantamentoFeriasManual), configuracaoFolhaPagamento.getForcarDiaPagamentoFolhaAdiantamentoFeriasManual());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(configuracaoFolhaPagamento.getForcarDiaPagamentoFolhaNormalManual()!=null){
			Predicate restricaoForcarDataFolhaNormal =  criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.forcarDiaPagamentoFolhaNormalManual), configuracaoFolhaPagamento.getForcarDiaPagamentoFolhaNormalManual());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoForcarDataFolhaNormal);
		}
		if(configuracaoFolhaPagamento.getDiaPagamentoFolhaNormal()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.diaPagamentoFolhaNormal), configuracaoFolhaPagamento.getDiaPagamentoFolhaNormal());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(configuracaoFolhaPagamento.getDiaPagamentoFolha13()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.diaPagamentoFolha13), configuracaoFolhaPagamento.getDiaPagamentoFolha13());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(configuracaoFolhaPagamento.getDiaPagamentoFolhaAdiantamentoFerias()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.diaPagamentoFolhaAdiantamentoFerias), configuracaoFolhaPagamento.getDiaPagamentoFolhaAdiantamentoFerias());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(configuracaoFolhaPagamento.getDiaPagamentoFolhaAdiantamento13()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.diaPagamentoFolhaAdiantamento13), configuracaoFolhaPagamento.getDiaPagamentoFolhaAdiantamento13());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(configuracaoFolhaPagamento.getDiaPagamentoFolhaNormalManual()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.diaPagamentoFolhaNormalManual), configuracaoFolhaPagamento.getDiaPagamentoFolhaNormalManual());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(configuracaoFolhaPagamento.getDiaPagamentoFolhaNormalManual()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.diaPagamentoFolhaAdiantamentoFeriasManual), configuracaoFolhaPagamento.getDiaPagamentoFolhaAdiantamentoFeriasManual());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(configuracaoFolhaPagamento.getMesCobrancaContribuicaoSindical()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.mesCobrancaContribuicaoSindical), configuracaoFolhaPagamento.getMesCobrancaContribuicaoSindical());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		
		if(configuracaoFolhaPagamento.getMesLimitePagamentoFolhaAdiantamento13()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.mesLimitePagamentoFolhaAdiantamento13), configuracaoFolhaPagamento.getMesLimitePagamentoFolhaAdiantamento13());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		
		if(configuracaoFolhaPagamento.getMesPagamentoFolhaAdiantamento13()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(ConfiguracaoFolhaPagamento_.mesPagamentoFolhaAdiantamento13), configuracaoFolhaPagamento.getMesPagamentoFolhaAdiantamento13());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		
		criteriaQuery.where(listaRestricoes);
		TypedQuery<ConfiguracaoFolhaPagamento> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<ConfiguracaoFolhaPagamento>(typedQuery.getResultList());
	}
}