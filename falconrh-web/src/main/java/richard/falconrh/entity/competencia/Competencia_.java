package richard.falconrh.entity.competencia;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;


@StaticMetamodel(Competencia.class)
public class Competencia_ extends Parent_{
	public static volatile SingularAttribute<Competencia, String> descricao;
	public static volatile SingularAttribute<Competencia, Date> dataAbertura;
	public static volatile SingularAttribute<Competencia, Date> dataFechamento;
}
