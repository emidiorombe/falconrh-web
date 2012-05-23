package richard.falconrh.entity.pessoa;

import java.util.Date;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.entity.documento.Documento;
import richard.falconrh.entity.localizacao.Endereco;
import richard.falconrh.entity.localizacao.Telefone;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Nacionalidade;
import richard.falconrh.modelo.enums.Sexo;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(Pessoa.class)
public class Pessoa_ extends Parent_{
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> email;
	public static volatile SingularAttribute<Pessoa, Date> dataNascimento;
	public static volatile SingularAttribute<Pessoa, Boolean> deficienteFisico;
	public static volatile SingularAttribute<Pessoa, EstadoCivil> estadoCivil;
	public static volatile SingularAttribute<Pessoa, Etnia> etnia;
	public static volatile SingularAttribute<Pessoa, Nacionalidade> nacionalidade;
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile ListAttribute<Pessoa, Telefone> listaTelefones;
	public static volatile ListAttribute<Pessoa, Documento> listaDocumentos;
	public static volatile SingularAttribute<Pessoa, Sexo> sexo;
}
