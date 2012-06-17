package richard.falconrh.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang.StringUtils;

import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.entity.pessoa.Usuario_;
import richard.falconrh.entity.seguranca.Acesso;
import richard.falconrh.entity.seguranca.Acesso_;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.AcessoServices;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/AcessoServices", mappedName="AcessoServices")
public class AcessoServicesImpl extends AbstractServicesImpl<Acesso> implements AcessoServices {
	/**
	 * Method obterListaPeloExemplo.
	 * @param entity Acesso
	 * @return Set<Acesso> * @throws ServicesException
	 */
	
	public static volatile SingularAttribute<Acesso, Usuario> usuario;
	public static volatile SingularAttribute<Acesso, String> ipUsuario;
	public static volatile SingularAttribute<Acesso, String> ipServidorAplicacao;
	public static volatile SingularAttribute<Acesso, Date> dataLogon;
	public static volatile SingularAttribute<Acesso, Date> dataLogff;
	public static volatile SingularAttribute<Acesso, String> idSessao;
	
	@Override
	public Set<Acesso> obterListaPeloExemplo(Acesso acesso) throws ServicesException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Acesso> criteriaQuery = criteriaBuilder.createQuery(Acesso.class);
		Root<Acesso> from = criteriaQuery.from(Acesso.class);
		
		Predicate listaRestricoes = criteriaBuilder.conjunction();
		if(acesso.getUsuario()!=null){
			if(acesso.getUsuario().getId()!=null){
				Predicate restricao = criteriaBuilder.equal(from.get(Acesso_.usuario).get(Usuario_.id), acesso.getUsuario().getId());
				listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
			}else{
				//TODO IMPLEMENTAR RESTANDE DOS CRITERIOS DE USUARIO
			}
		}
		if(acesso.getDataLogon()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(Acesso_.dataLogon), acesso.getDataLogon());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(acesso.getDataLogoff()!=null){
			Predicate restricao = criteriaBuilder.equal(from.get(Acesso_.dataLogoff), acesso.getDataLogoff());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(StringUtils.isNotBlank(acesso.getIpServidorAplicacao())){
			Predicate restricao = criteriaBuilder.equal(from.get(Acesso_.ipServidorAplicacao), acesso.getIpServidorAplicacao());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(StringUtils.isNotBlank(acesso.getIpUsuario())){
			Predicate restricao = criteriaBuilder.equal(from.get(Acesso_.ipUsuario), acesso.getIpUsuario());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		if(StringUtils.isNotBlank(acesso.getIdSessao())){
			Predicate restricao = criteriaBuilder.equal(from.get(Acesso_.idSessao), acesso.getIdSessao());
			listaRestricoes = criteriaBuilder.and(listaRestricoes, restricao);
		}
		criteriaQuery.where(listaRestricoes);
		TypedQuery<Acesso> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return new HashSet<Acesso>(typedQuery.getResultList());
	}
}
