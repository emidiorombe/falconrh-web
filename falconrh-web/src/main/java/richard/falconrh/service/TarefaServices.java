package richard.falconrh.service;

import java.util.SortedSet;

import javax.ejb.Remote;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.scheduler.Tarefa;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Remote
public interface TarefaServices extends AbstractServices<Tarefa>{
	public SortedSet<Tarefa> obterListaTodasTarefas() throws ServicesException;
}
