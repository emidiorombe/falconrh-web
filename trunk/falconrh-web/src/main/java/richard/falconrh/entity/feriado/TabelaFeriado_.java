package richard.falconrh.entity.feriado;

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
@StaticMetamodel(value=TabelaFeriado.class)
public class TabelaFeriado_ extends Parent_ {
	public static volatile SingularAttribute<TabelaFeriado, String> descricao;
	public static volatile SingularAttribute<TabelaFeriado, Integer> ano;
	public static volatile ListAttribute<TabelaFeriado, Feriado> listaFeriados;
}
