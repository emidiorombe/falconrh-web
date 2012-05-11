package richard.falconrh.entity.localizacao;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.modelo.enums.UF;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(Municipio.class)
public class Municipio_ extends Parent_ {
	public static volatile SingularAttribute<Municipio, String> nome;
	public static volatile SingularAttribute<Municipio, UF> uf;
	public static volatile ListAttribute<Municipio, Bairro> listaBairros;
}
