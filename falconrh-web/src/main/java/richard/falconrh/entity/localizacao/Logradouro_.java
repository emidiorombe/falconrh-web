package richard.falconrh.entity.localizacao;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.modelo.enums.TipoLogradouro;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(Logradouro.class)
public class Logradouro_ extends Parent_ {
	public static volatile SingularAttribute<Logradouro, TipoLogradouro> tipoLogradouro;
	public static volatile SingularAttribute<Logradouro, String> nome;
	public static volatile SingularAttribute<Logradouro, Bairro> bairro;
	public static volatile SingularAttribute<Logradouro, Long> cep;
}
