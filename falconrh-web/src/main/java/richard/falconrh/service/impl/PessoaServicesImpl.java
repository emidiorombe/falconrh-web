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

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.entity.pessoa.Pessoa_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.PessoaServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/PessoaServices", mappedName="PessoaServices")
public class PessoaServicesImpl extends AbstractServicesImpl<Pessoa> implements PessoaServices{

	/**
	 * Method obterListaTodasPessoas.
	 * @return Set<Pessoa> * @throws ServicesException * @see richard.falconrh.service.PessoaServices#obterListaTodasPessoas() */
	@Override
	public Set<Pessoa> obterListaTodasPessoas() throws ServicesException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Pessoa> obterListaPeloExemplo(Pessoa pessoa) throws ServicesException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
		Root<Pessoa> from = cq.from(Pessoa.class);
		
		Predicate listaRestricoes = cb.conjunction();
		
		if(StringUtils.isNotBlank(pessoa.getNome())){
			Predicate restricao = cb.like(cb.lower(from.get(Pessoa_.nome)), "%" +pessoa.getNome().toLowerCase() + "%");
			listaRestricoes = cb.and(listaRestricoes, restricao);
		}
		if(StringUtils.isNotBlank(pessoa.getEmail())){
			Predicate restricao = cb.like(cb.lower(from.get(Pessoa_.email)), "%" +pessoa.getEmail().toLowerCase() + "%");
			listaRestricoes = cb.and(listaRestricoes, restricao);
		}
		if(pessoa.getDataNascimento()!=null){
			Predicate restricao = cb.equal(from.get(Pessoa_.dataNascimento), pessoa.getDataNascimento());
			listaRestricoes = cb.and(listaRestricoes, restricao);
		}
		if(pessoa.getDeficienteFisico()!=null){
			Predicate restricao = cb.equal(from.get(Pessoa_.deficienteFisico), pessoa.getDeficienteFisico());
			listaRestricoes = cb.and(listaRestricoes, restricao);
		}
		if(pessoa.getNacionalidade()!=null){
			Predicate restricao = cb.equal(from.get(Pessoa_.nacionalidade), pessoa.getNacionalidade());
			listaRestricoes = cb.and(listaRestricoes, restricao);
		}
		if(pessoa.getEtnia()!=null){
			Predicate restricao = cb.equal(from.get(Pessoa_.etnia), pessoa.getEtnia());
			listaRestricoes = cb.and(listaRestricoes, restricao);
		}
		if(pessoa.getSexo()!=null){
			Predicate restricao = cb.equal(from.get(Pessoa_.sexo), pessoa.getSexo());
			listaRestricoes = cb.and(listaRestricoes, restricao);
		}
		if(pessoa.getEstadoCivil()!=null){
			Predicate restricao = cb.equal(from.get(Pessoa_.estadoCivil), pessoa.getEstadoCivil());
			listaRestricoes = cb.and(listaRestricoes, restricao);
		}
		if(pessoa.getListaTelefones()!=null && !pessoa.getListaTelefones().isEmpty()){
			//TODO INCLUIR RESTRIÇÃO
		}
		cq.where(listaRestricoes);
		TypedQuery<Pessoa> query = getEntityManager().createQuery(cq);
		return new TreeSet<Pessoa>(query.getResultList());
	}
}