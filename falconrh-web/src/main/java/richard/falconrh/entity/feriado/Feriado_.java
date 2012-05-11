package richard.falconrh.entity.feriado;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.modelo.enums.TipoFeriado;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(value=Feriado.class)
public class Feriado_ extends Parent_ {
	public static volatile SingularAttribute<Feriado, String> descricao;
	public static volatile SingularAttribute<Feriado, TipoFeriado> tipoFeriado;
	public static volatile SingularAttribute<Feriado, TabelaFeriado> tabelaFeriado;
	public static volatile SingularAttribute<Feriado, Date> data;
}
