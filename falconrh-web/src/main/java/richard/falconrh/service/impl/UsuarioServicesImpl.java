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

import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.entity.pessoa.Usuario_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.UsuarioServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/UsuarioServices", mappedName="UsuarioServices")
public class UsuarioServicesImpl extends AbstractServicesImpl<Usuario> implements UsuarioServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity Usuario
	 * @return Set<Usuario> * @throws ServicesException */
	@Override
	public Set<Usuario> obterListaPeloExemplo(Usuario usuario) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> from = criteriaQuery.from(Usuario.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(StringUtils.isNotBlank(usuario.getNome())){
			Predicate restricaoNome =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Usuario_.nome)), "%" + usuario.getNome().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoNome);
		}
		if(StringUtils.isNotBlank(usuario.getLogin())){
			Predicate restricaoLogin =  criteriaBuilder.like(criteriaBuilder.lower(from.get(Usuario_.login)), "%" + usuario.getLogin().toLowerCase() + "%");
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoLogin);
		}
		if(usuario.getAtivo()!=null){
			Predicate restricaoAtivo =  criteriaBuilder.equal(from.get(Usuario_.ativo), usuario.getAtivo());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoAtivo);
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Usuario> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Usuario>(typedQuery.getResultList());
	}
}