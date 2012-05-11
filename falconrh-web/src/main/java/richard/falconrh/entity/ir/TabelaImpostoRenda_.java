
package richard.falconrh.entity.ir;

import java.util.Date;

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
@StaticMetamodel(TabelaImpostoRenda.class)
public class TabelaImpostoRenda_ extends Parent_ {
	public static volatile SingularAttribute<TabelaImpostoRenda, String> descricao;
	public static volatile SingularAttribute<TabelaImpostoRenda, Integer> anoBase;
	public static volatile SingularAttribute<TabelaImpostoRenda, Date> dataInicioVigencia;
	public static volatile SingularAttribute<TabelaImpostoRenda, Date> dataTerminoVigencia;
	public static volatile ListAttribute<TabelaImpostoRenda, AliquotaImpostoRenda> listaAliquotasImpostoRenda;

}
