package richard.falconrh.service.impl;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.entity.pessoa.Usuario_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.LoginServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/LoginServices", mappedName="LoginServices")
public class LoginServicesImpl extends AbstractServicesImpl<Parent> implements LoginServices {

	@Override
	public Usuario obterUsuarioPeloLogin(String login) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> from = criteriaQuery.from(Usuario.class);
		
		if(StringUtils.isNotBlank(login)){
			Predicate restricaoLogin =  criteriaBuilder.equal(from.get(Usuario_.login), login);
			criteriaQuery.where(restricaoLogin);
			TypedQuery<Usuario> typedQuery = getEntityManager().createQuery(criteriaQuery);
			return typedQuery.getSingleResult();
		}
		return null;
	}

	@Override
	public Usuario autenticar(String login, String senha) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> from = criteriaQuery.from(Usuario.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		Predicate restricaoLogin =  criteriaBuilder.equal(from.get(Usuario_.login), login);
		listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoLogin);
		Predicate restricaoSenha =  criteriaBuilder.equal(from.get(Usuario_.senha), senha);
		listaRestricoes = criteriaBuilder.and(listaRestricoes, restricaoSenha);
		
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Usuario> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getSingleResult();
	}
	
	
}
