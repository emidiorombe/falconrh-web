package richard.falconrh.entity.banco;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(value=Banco.class)
public class Banco_ extends Parent_{
	public static volatile SingularAttribute<Banco, String> codigoFebraban;
	public static volatile SingularAttribute<Banco, String> nome;
	public static volatile SetAttribute<Banco, Agencia> listaAgencias;
}
