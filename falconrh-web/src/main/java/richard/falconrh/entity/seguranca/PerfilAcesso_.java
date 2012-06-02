package richard.falconrh.entity.seguranca;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.modelo.enums.NivelAcesso;


/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0.<br/>
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(PerfilAcesso.class)
public class PerfilAcesso_ extends Parent_{
	public static volatile SingularAttribute<PerfilAcesso, String> nome;
	public static volatile SingularAttribute<PerfilAcesso, NivelAcesso> nivelAcesso;
	public static volatile SingularAttribute<PerfilAcesso, Boolean> ativo;
}
