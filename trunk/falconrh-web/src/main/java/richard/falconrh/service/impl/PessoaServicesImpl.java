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

import richard.falconrh.entity.documento.Documento;
import richard.falconrh.entity.localizacao.Telefone;
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
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
		cq.from(Pessoa.class);
		TypedQuery<Pessoa> query = getEntityManager().createQuery(cq);
		return new TreeSet<Pessoa>(query.getResultList());
	}
	
	@Override
	public Set<Pessoa> obterListaPeloExemplo(Pessoa pessoa) throws ServicesException {
		try{
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
			TreeSet<Pessoa> listaRetorno = new TreeSet<Pessoa>(query.getResultList());
			for(Pessoa p :  listaRetorno){//gambiarra para evitar o problema de lazy
				for(Telefone telefone : p.getListaTelefones()){
					telefone.getTipoTelefone();
					telefone.getDdd();
					telefone.getNumero();
				}
				for(Documento documento : p.getListaDocumentos()){
					documento.getTipoDocumento();
					documento.getDataEmissao();
					documento.getNumero();
				}
				p.getEndereco().getNumero();
				p.getEndereco().getLogradouro().getNome();
				p.getEndereco().getLogradouro().getBairro().getNome();
				p.getEndereco().getLogradouro().getBairro().getMunicipio().getNome();
				p.getEndereco().getLogradouro().getBairro().getMunicipio().getUf().getNome();
				
			}
			return listaRetorno;
		} catch(Exception e){
			throw new ServicesException(e);
		}
	}
	
	@Override
	public Pessoa obterPeloId(Class<Pessoa> clazz, Long id)throws ServicesException {
		try{
			String jpql = "select p from Pessoa p left join p.listaTelefones left join p.listaDocumentos where p.id = :idPessoa";
			TypedQuery<Pessoa> q = getEntityManager().createQuery(jpql, Pessoa.class);
			q.setParameter("idPessoa", id);
			return q.getSingleResult();
		} catch(Exception e){
			throw new ServicesException(e);
		}
	}
	
	@Override
	protected void beforeCadastrar(Pessoa pessoa) {
		if(pessoa.getListaDocumentos()!=null && !pessoa.getListaDocumentos().isEmpty()){
			for(Documento documento : pessoa.getListaDocumentos()){
				documento.setPessoa(pessoa);
			}
		}
	}
}