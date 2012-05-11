package richard.falconrh.entity.inss;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(AliquotaINSS.class)
public class AliquotaINSS_ extends Parent_{
	public static volatile SingularAttribute<AliquotaINSS, TabelaINSS> tabelaINSS;
	public static volatile SingularAttribute<AliquotaINSS, String> descricao;
	public static volatile SingularAttribute<AliquotaINSS, Double> aliquota;
	public static volatile SingularAttribute<AliquotaINSS, Double> valorInicial;
	public static volatile SingularAttribute<AliquotaINSS, Double> valorFinal;
}
