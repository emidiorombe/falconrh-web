package richard.falconrh.web.bean;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import richard.falconrh.entity.feriado.Feriado;
import richard.falconrh.entity.feriado.TabelaFeriado;
import richard.falconrh.service.TabelaFeriadoServices;

/**
 * ManagedBean JSF utilizado em arquivos xhtml
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@ManagedBean(name="tabelaFeriadoBean")
@ViewScoped
public class TabelaFeriadoBean extends BaseBean<TabelaFeriado, TabelaFeriadoServices>{
	private static final long serialVersionUID = 3252063837702084394L;

	private Feriado feriado;
	
	@EJB(name="ejb/TabelaFeriadoServices")
	TabelaFeriadoServices tabelaFeriadoServices;
	
	public TabelaFeriadoBean(){
		super();
	}
	
	public void inicializaEntity() {
		setEntity(new TabelaFeriado());
		getEntity().setListaFeriados(new ArrayList<Feriado>());
		setFeriado(new Feriado());
	}
	
	/**
	 * Method reinitFeriado.
	 * @return String
	 */
	public String reinitFeriado(){
		setFeriado(new Feriado());
		return null;
	}
	
	/**
	 * Method getFeriado.
	 * @return Feriado
	 */
	public Feriado getFeriado() {
		return feriado;
	}

	/**
	 * Method setFeriado.
	 * @param feriado Feriado
	 */
	public void setFeriado(Feriado feriado) {
		this.feriado = feriado;
	}

	@Override
	public void setMensagensInformativas() {
		SUCESSO_CADASTRO = "sucesso.cadastro.tabelaFeriado";
		SUCESSO_ATUALIZACAO = "sucesso.atualizacao.tabelaFeriado";
		SUCESSO_EXCLUSAO = "sucesso.exclusao.tabelaFeriado";
		SUCESSO_PESQUISA = "sucesso.pesquisa.tabelaFeriado";
		ERRO_CADASTRO = "erro.cadastro.tabelaFeriado";
		ERRO_ATUALIZACAO = "erro.atualizacao.tabelaFeriado";
		ERRO_EXCLUSAO = "erro.exclusao.tabelaFeriado";
		ERRO_PESQUISA = "erro.pesquisa.tabelaFeriado";
		PESQUISA_NAO_ENCONTRADA = "pesquisa.tabelaFeriado.nao.encontrada";
	}
}