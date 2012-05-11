package richard.falconrh.entity.documento;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.modelo.enums.TipoDocumento;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(value=Documento.class)
public class Documento_ extends Parent_{
	public static volatile SingularAttribute<Documento, Date> dataEmissao;
	public static volatile SingularAttribute<Documento, String> numero;
	public static volatile SingularAttribute<Documento, TipoDocumento> tipoDocumento;
	public static volatile SingularAttribute<Documento, Pessoa> pessoa;
}
