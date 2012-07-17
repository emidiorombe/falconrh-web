package richard.falconrh.scheduler;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;

@StaticMetamodel(value=Tarefa.class)
public class Tarefa_ extends Parent_{
	public static volatile SingularAttribute<Tarefa, String> nome;
	public static volatile SingularAttribute<Tarefa, String> descricao;
}
