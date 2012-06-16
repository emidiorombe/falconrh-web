package richard.falconrh.entity.empresa;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.entity.localizacao.Endereco;


@StaticMetamodel(Empresa.class)
public class Empresa_ extends Parent_{
	public static volatile SingularAttribute<Empresa, String> nomeEmpresarial;
	public static volatile SingularAttribute<Empresa, String> titulo;
	public static volatile SingularAttribute<Empresa, Integer> cnpj;
	public static volatile SingularAttribute<Empresa, Endereco> endereco;
}
