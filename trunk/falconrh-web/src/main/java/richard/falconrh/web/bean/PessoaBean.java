package richard.falconrh.web.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.service.PessoaServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="pessoaBean")
@ViewScoped
public class PessoaBean extends BaseBean<Pessoa, PessoaServices>{
	private static final long serialVersionUID = 2170966828493855669L;

	@EJB(name="ejb/PessoaServices")
	PessoaServices pessoaServices;
	
	public PessoaBean(){
		super();
	}
	
	public void inicializaEntity() {
		setEntity(new Pessoa());
		getEntity().setDeficienteFisico(false);
	}

	/**
	 * Method getServices.
	
	 * @return PessoaServices */
	@Override
	public PessoaServices getServices() {
		return pessoaServices;
	}

	@Override
	public void setMensagensInformativas() {
		// TODO Auto-generated method stub
		
	}
}