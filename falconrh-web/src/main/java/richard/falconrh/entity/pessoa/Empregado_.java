package richard.falconrh.entity.pessoa;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.modelo.enums.SituacaoFuncional;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(Empregado.class)
public class Empregado_ extends Parent_ {
	public static volatile SingularAttribute<Empregado, Long> matricula;
	public static volatile SingularAttribute<Empregado, SituacaoFuncional> situacaoFuncional;
	public static volatile SingularAttribute<Empregado, Double> salario;
	public static volatile SingularAttribute<Empregado, Date> dataContratacao;
}
