package richard.falconrh.entity.localizacao;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(Endereco.class)
public class Endereco_ extends Parent_ {
	public static volatile SingularAttribute<Endereco, String> numero;
	public static volatile SingularAttribute<Endereco, String> complemento;
	public static volatile SingularAttribute<Endereco, Logradouro> logradouro;
}
