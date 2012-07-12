package richard.falconrh.entity.folha;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import richard.falconrh.entity.Parent_;
import richard.falconrh.entity.banco.Agencia;

@StaticMetamodel(ConfiguracaoFolhaPagamento.class)
public class ConfiguracaoFolhaPagamento_ extends Parent_{
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Agencia> agenciaFGTS;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> diaPagamentoFolhaNormal;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> diaPagamentoFolha13;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> diaPagamentoFolhaAdiantamentoFerias;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> diaPagamentoFolhaAdiantamento13;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> mesCobrancaContribuicaoSindical;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> mesLimitePagamentoFolhaAdiantamento13;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> mesPagamentoFolhaAdiantamento13;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> diaPagamentoFolhaNormalManual;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Integer> diaPagamentoFolhaAdiantamentoFeriasManual;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Boolean> forcarDiaPagamentoFolhaNormalManual;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Boolean> forcarDiaPagamentoFolhaAdiantamentoFeriasManual;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Date> dataInicioVigencia;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, Date> dataTerminoVigencia;
	public static volatile SingularAttribute<ConfiguracaoFolhaPagamento, String> descricao;
	
}