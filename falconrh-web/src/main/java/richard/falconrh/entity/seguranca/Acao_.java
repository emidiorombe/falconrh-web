package richard.falconrh.entity.seguranca;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

@StaticMetamodel(Acao.class)
public class Acao_ extends Parent_{
	public static volatile SingularAttribute<Acao, String> nome;
}
