package richard.falconrh.entity.banco;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(value=Agencia.class)
public class Agencia_ extends Parent_{
	public static volatile SingularAttribute<Agencia, String> nome;
	public static volatile SingularAttribute<Agencia, String> numero;
	public static volatile SingularAttribute<Agencia, String> digitoVerificador;
	public static volatile SingularAttribute<Agencia, Banco> banco;
	public static volatile ListAttribute<Agencia, ContaCorrente> listaContasCorrentes;
}
