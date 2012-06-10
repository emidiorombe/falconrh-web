package richard.falconrh.service;

import javax.ejb.Remote;

import richard.falconrh.entity.pessoa.Usuario;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Remote
public interface UsuarioServices extends AbstractServices<Usuario>{
}
