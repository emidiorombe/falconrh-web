package richard.falconrh.service;

import java.util.Set;

import javax.persistence.EntityManager;

import richard.falconrh.entity.Parent;
import richard.falconrh.exception.ServicesException;

/**
 * Interface base que contém os métodos necessários para operações básicas na base de dados.
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
public interface AbstractServices<T extends Parent> {
	/**
	 * Método responsável pela persistência de objetos não existente no banco de dados
	 * @param entity T - a entidade que será persistida em banco de dados
	 * @throws ServicesException - exceção lançada quando ocorre algum erro ao tentar realizar a persistência de dados
	 */
	public void cadastrar(T entity) throws ServicesException;
	
	/**
	 * Método responsável pela alteração de objetos já existente no banco de dados
	 * @param entity T - a entidade que será alterada no banco de dados
	 * @throws ServicesException - exceção lançada quando ocorre algum erro ao tentar realizar a atualização dos dados
	 */
	public void alterar(T entity) throws ServicesException;
	
	/**
	 * Métodoq que exclui um objeto do banco de dados a partir de sua chave primária (id).
	 * @param clazz Class<T> - o tipo de Classe da entidade que será excluída
	 * @param id Long - o id (chave primária) do objeto que será excluído
	 * @throws ServicesException - exceção lançada quando ocorre algum erro ao tentar excluir um objeto do banco de dados
	 */
	public void excluirPeloId(Class<T> clazz, Long id) throws ServicesException;
	
	/**
	 * Método que pesquisa em banco de dados por uma entidade existente
	 * @param clazz Class<T> - o tipo de entidade que será pesquisada
	 * @param id Long - o id (chave primária) da entidade que será pesquisada
	 * @return T - a instância da entidade que foi pesquisada
	 * @throws ServicesException - exceção lançada quando ocorre algum erro an tentar obter pelo id uma entidade do banco de dados
	 */
	public T obterPeloId(Class<T> clazz, Long id) throws ServicesException;
	
	/**
	 * Método que pesquisa por entidades no banco de dados a partir de um exemplo
	 * @param exampleEntity T a intância do objeto que contém os parâmetros de pesquisa das entidades
	 * @return Set<T> a lista de objetos que satisfazem os critérios informados no exemplo
	 * @throws ServicesException - exceção lançada quando ocorre qualquer erro ao tentar objter a lista de entidades pelo exemplo em banco de dados.
	 */
	public Set<T> obterListaPeloExemplo(T exampleEntity) throws ServicesException;
	
	/**
	 * Método que retorna o entityManager do serviço
	 * @return a instância do entity manager que está sendo utilizado
	 */
	public EntityManager getEntityManager();
	
}