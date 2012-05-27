package richard.falconrh.entity.seguranca;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

@StaticMetamodel(value=Funcionalidade.class)
public class Funcionalidade_ extends Parent_{
	public static volatile SingularAttribute<Funcionalidade, String> nome;
	public static volatile SingularAttribute<Funcionalidade, String> link;
	public static volatile SingularAttribute<Funcionalidade, Boolean> ativa;
	public static volatile SetAttribute<Funcionalidade, Acao> listaAcoes;
}
