package richard.falconrh.entity.localizacao;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.modelo.enums.TipoTelefone;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(Telefone.class)
public class Telefone_ extends Parent_ {
	public static volatile SingularAttribute<Telefone, TipoTelefone> tipoTelefone;
	public static volatile SingularAttribute<Telefone, Integer> ddd;
	public static volatile SingularAttribute<Telefone, Long> numero;	
	public static volatile ListAttribute<Telefone, Pessoa> listaPessoas;
}
