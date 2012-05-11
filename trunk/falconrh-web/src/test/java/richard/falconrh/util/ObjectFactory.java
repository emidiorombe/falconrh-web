package richard.falconrh.util;

import java.util.ArrayList;
import java.util.List;

import richard.falconrh.entity.banco.Agencia;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class ObjectFactory {
	/**
	 * Method getAgencia.
	
	 * @return Agencia */
	public static Agencia getAgencia(){
		Long idAgencia = 1L;
		Agencia agencia = new Agencia(idAgencia);
		//TODO incluir o set aos outros atributos
		return agencia;
	}

	/**
	 * Method getListaAgencias.
	
	 * @return List<Agencia> */
	public static List<Agencia> getListaAgencias() {
		List<Agencia> listaAgencias = new ArrayList<Agencia>();
		listaAgencias.add(getAgencia());
		//TODO incluir mais objetos a lista
		return listaAgencias;
	}
}
