package richard.falconrh.entity.localizacao;

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
@StaticMetamodel(Bairro.class)
public class Bairro_ extends Parent_ {
	public static volatile SingularAttribute<Bairro, String> nome;
	public static volatile SingularAttribute<Bairro, Municipio> municipio;
	public static volatile SetAttribute<Bairro, Logradouro> listaLogradouros;
}
