package richard.falconrh.web.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.service.UsuarioServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean extends BaseBean<Usuario, UsuarioServices>{
	private static final long serialVersionUID = -8606481061914629482L;

	@EJB(name="ejb/UsuarioServices")
	private UsuarioServices usuarioServices;
	
	private Pessoa pessoa;
	
	public UsuarioBean(){
		super();
	}
	
	@Override
	public void inicializaEntity() {
		setEntity(new Usuario());
		setListaEntities(new ArrayList<Usuario>());
	}
	
	/**
	 * Method getServices.
	 * @return usuarioServices 
	 */
	@Override
	public UsuarioServices getServices() {
		return usuarioServices;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.usuario";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.usuario";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.usuario";
		SUCESSO_PESQUISA = "sucesso.pesquisa.usuario";
		ERRO_CADASTRO = "erro.cadastro.usuario";
		ERRO_ATUALIZACAO = "erro.atualizacao.usuario";
		ERRO_EXCLUSAO = "erro.exclusao.usuario";
		ERRO_PESQUISA = "erro.pesquisa.usuario";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.usuario.nao.encontrado";
	}
	
	@Override
	public void cadastrar(ActionEvent event) {
		try {
			BeanUtils.copyProperties(getEntity(), getPessoa());
		} catch (IllegalAccessException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao  tentar copiar propriedades de pessoa para usuario");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		} catch (InvocationTargetException e){
			FacesMessage mensagem = new FacesMessage("Erro ao  tentar copiar propriedades de pessoa para usuario");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		super.atualizar(event);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}