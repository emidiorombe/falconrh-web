package richard.falconrh.entity.inss;

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
@StaticMetamodel(TabelaINSS.class)
public class TabelaINSS_ extends Parent_ {
	public static volatile SingularAttribute<TabelaINSS, String> descricao;
	public static volatile SingularAttribute<TabelaINSS, Date> dataInicioVigencia;
	public static volatile SingularAttribute<TabelaINSS, Date> dataTerminoVigencia;
	public static volatile ListAttribute<TabelaINSS, AliquotaINSS> listaAliquotasINSS;
}
