package richard.falconrh.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = ParentPorVigencia.class)
public class ParentPorVigencia_ extends Parent_ {
	public static volatile SingularAttribute<ParentPorVigencia_, Date> dataInicioVigencia;
	public static volatile SingularAttribute<ParentPorVigencia_, Date> dataTerminoVigencia;
}
