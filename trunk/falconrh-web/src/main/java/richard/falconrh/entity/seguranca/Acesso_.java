package richard.falconrh.entity.seguranca;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.entity.pessoa.Usuario;

@StaticMetamodel(Acesso.class)
public class Acesso_ extends Parent_ {
	public static volatile SingularAttribute<Acesso, Usuario> usuario;
	public static volatile SingularAttribute<Acesso, String> ipUsuario;
	public static volatile SingularAttribute<Acesso, String> ipServidorAplicacao;
	public static volatile SingularAttribute<Acesso, Date> dataLogon;
	public static volatile SingularAttribute<Acesso, Date> dataLogoff;
	public static volatile SingularAttribute<Acesso, String> idSessao;
}