package richard.falconrh.entity.banco;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(value=ContaCorrente.class)
public class ContaCorrente_ extends Parent_{
	public static volatile SingularAttribute<ContaCorrente, Agencia> agencia;
	public static volatile SingularAttribute<ContaCorrente, String> numero;
	public static volatile SingularAttribute<ContaCorrente, String> digitoVerificador;
}
