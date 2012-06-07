package richard.falconrh.service;

import javax.ejb.Remote;

import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.exception.ServicesException;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Remote
public interface LoginServices{
	public Usuario obterUsuarioPeloLogin(String login) throws ServicesException;
	public Usuario autenticar(String login, String senha) throws ServicesException;
}
