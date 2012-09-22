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

import richard.falconrh.entity.localizacao.Bairro_;
import richard.falconrh.entity.localizacao.Logradouro;
import richard.falconrh.entity.localizacao.Logradouro_;
import richard.falconrh.entity.localizacao.Municipio_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.LogradouroServices;

/**
 * Classe EJB para manipulação da entidade Logradouro
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/LogradouroServices", mappedName="LogradouroServices")
public class LogradouroServicesImpl extends AbstractServicesImpl<Logradouro> implements LogradouroServices {

	/**
	 * Método que obterm uma lista de logradouros com base na chave primária (ID) do bairro.
	 * @param idBairro Long o identificador do Bairro (chave primária).
	 * @return Set<Logradouro>, sendo a lista de logradouros do bairro
	 * @throws ServicesException caso dê algum erro ao obter a lista de logradouros
	 * @see richard.falconrh.service.LogradouroServices#obterListaPeloIdBairro(Long)
	 */
	@Override
	public Set<Logradouro> obterListaPeloIdBairro(Long idBairro) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Logradouro> criteriaQuery = criteriaBuilder.createQuery(Logradouro.class);
		Root<Logradouro> from = criteriaQuery.from(Logradouro.class);
		
		Predicate restricaoIdBairro = criteriaBuilder.equal(from.get(Logradouro_.bairro).get(Bairro_.id), idBairro);
		criteriaQuery.where(restricaoIdBairro);
		TypedQuery<Logradouro> typedQuery = getEntityManager().createQuery(criteriaQuery);
		Set<Logradouro> lista = new TreeSet<Logradouro>(typedQuery.getResultList());
		for(Logradouro l : lista){
			l.getNome();
			l.getBairro().getNome();
			l.getBairro().getId();
			l.getBairro().getMunicipio();
			l.getBairro().getMunicipio().getId();
			l.getBairro().getMunicipio().getNome();
			l.getBairro().getMunicipio().getUf();
			l.getBairro().getMunicipio().getUf().getNome();
			l.getCep();
			l.getTipoLogradouro();
		}
		return lista;
	}
	
	/**
	 * Método que obterm uma lista de logradouros com base no exemplo informado
	 * @param logradouro Logradouro o exemplo a ser pesquisado
	 * @return Set<Logradouro> a lista de logradouros encontrados
	 * @throws ServicesException exceção que é disparada quando ocorre qualquer erro no método
	 */
	@Override
	public Set<Logradouro> obterListaPeloExemplo(Logradouro logradouro) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Logradouro> criteriaQuery = criteriaBuilder.createQuery(Logradouro.class);
		Root<Logradouro> from = criteriaQuery.from(Logradouro.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		
		if(logradouro.getCep()!=null){
			Predicate restricaoCep = criteriaBuilder.equal(from.get(Logradouro_.cep), logradouro.getCep());
			listaRestricoes = criteriaBuilder.and(restricaoCep);
		}
		if(logradouro.getTipoLogradouro()!=null){
			Predicate restricaoTipoLogradouro = criteriaBuilder.equal(from.get(Logradouro_.tipoLogradouro), logradouro.getTipoLogradouro());
			listaRestricoes = criteriaBuilder.and(restricaoTipoLogradouro);
		}
		if(StringUtils.isNotBlank(logradouro.getNome())){
			Predicate restricaoNome = criteriaBuilder.like(criteriaBuilder.lower(from.get(Logradouro_.nome)), "%" + logradouro.getNome().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(restricaoNome);
		}
		if(logradouro.getBairro()!=null){
			if(StringUtils.isNotBlank(logradouro.getBairro().getNome())){
				Predicate restricaoNome = criteriaBuilder.like(criteriaBuilder.lower(from.get(Logradouro_.bairro).get(Bairro_.nome)), "%" + logradouro.getBairro().getMunicipio().getNome().toLowerCase() + "%");
				listaRestricoes = criteriaBuilder.and(restricaoNome);
			}
			if(logradouro.getBairro().getMunicipio()!=null){
				if(logradouro.getBairro().getMunicipio().getUf()!=null){
					Predicate restricaoUF = criteriaBuilder.equal(from.get(Logradouro_.bairro).get(Bairro_.municipio).get(Municipio_.uf), logradouro.getBairro().getMunicipio().getUf());
					listaRestricoes = criteriaBuilder.and(restricaoUF);
				}
				if(StringUtils.isNotBlank(logradouro.getBairro().getMunicipio().getNome())){
					Predicate restricaoNome = criteriaBuilder.like(criteriaBuilder.lower(from.get(Logradouro_.bairro).get(Bairro_.municipio).get(Municipio_.nome)), "%" + logradouro.getBairro().getMunicipio().getNome().toLowerCase() + "%");
					listaRestricoes = criteriaBuilder.and(restricaoNome);
				}
			}
		}
		
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Logradouro> typedQuery = getEntityManager().createQuery(criteriaQuery);
		Set<Logradouro> lista = new TreeSet<Logradouro>(typedQuery.getResultList());
		
		//FIXME Solução temporária para evitar o problema de Lazy. Verificar solução melhor posteriormente
		for(Logradouro log : lista){
			log.getBairro().getNome();
			log.getBairro().getMunicipio().getNome();
			log.getBairro().getMunicipio().getUf();
		}
		return lista;
	}

	@Override
	public Logradouro obterLogradouroPeloCep(Long cepPesquisa) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Logradouro> criteriaQuery = criteriaBuilder.createQuery(Logradouro.class);
		Root<Logradouro> from = criteriaQuery.from(Logradouro.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		
		if(cepPesquisa!=null){
			Predicate restricaoCep = criteriaBuilder.equal(from.get(Logradouro_.cep), cepPesquisa);
			listaRestricoes = criteriaBuilder.and(restricaoCep);
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Logradouro> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getSingleResult();
	}
}