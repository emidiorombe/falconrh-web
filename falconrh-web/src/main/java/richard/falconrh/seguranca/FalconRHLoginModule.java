package richard.falconrh.seguranca;

import java.security.acl.Group;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.exception.ServicesException;
import richard.falconrh.service.LoginServices;

public class FalconRHLoginModule extends UsernamePasswordLoginModule {
	private Usuario usuario;
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
		super.initialize(subject, callbackHandler, sharedState, options);
	}
	
	@Override
	protected String getUsersPassword() throws LoginException {
		try {
			usuario = getLoginServices().obterUsuarioPeloLogin(getUsername());
		} catch (ServicesException e) {
			e.printStackTrace();
			throw new LoginException(e.getMessage());
		}
		if(usuario!=null){
			return usuario.getSenha();
		}
		return null;
	}

	@Override
	protected Group[] getRoleSets() throws LoginException {
		if(usuario!=null){
			if(!usuario.getAtivo()){
				throw new LoginException("Conta de usuário inativa");
			}
			if(usuario.getPerfilAcesso()!=null){
				if(!usuario.getPerfilAcesso().getAtivo()){
					throw new LoginException("Perfil de acesso da conta do usuário está inativa");
				}
				return new Group[]{usuario.getPerfilAcesso()};
			}else{
				throw new LoginException("O usuário não possui um perfil de acesso vinculado a sua conta.");
			}
		}else{
			throw new LoginException("Imposssivel obter usuário");
		}
	}
	
	public LoginServices getLoginServices(){
		try {
			Context context = new InitialContext();
			String name = "java:global/falconrh-web/ejb/LoginServices";
			LoginServices services = (LoginServices) context.lookup(name);
			return services;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
