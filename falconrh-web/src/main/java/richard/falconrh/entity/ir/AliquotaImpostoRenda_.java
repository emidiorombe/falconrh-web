package richard.falconrh.entity.ir;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(AliquotaImpostoRenda.class)
public class AliquotaImpostoRenda_ extends Parent_ {
	public static volatile SingularAttribute<AliquotaImpostoRenda,TabelaImpostoRenda> tabelaImpostoRenda;
	public static volatile SingularAttribute<AliquotaImpostoRenda,String> descricao;
	public static volatile SingularAttribute<AliquotaImpostoRenda,Double> aliquota;
	public static volatile SingularAttribute<AliquotaImpostoRenda,Double> valorInicial;
	public static volatile SingularAttribute<AliquotaImpostoRenda,Double> valorFinal;
	public static volatile SingularAttribute<AliquotaImpostoRenda,Double> valorParcelaADeduzir;
}
