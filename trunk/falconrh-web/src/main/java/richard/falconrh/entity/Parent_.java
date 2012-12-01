package richard.falconrh.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Classe necessária para realizar pesquisas utilizando a API Criteria do JPA 2.0.<br/>
 * Esta classe é base para as outras de mesma utilidade.
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@StaticMetamodel(Parent.class)
public class Parent_ {
	public static volatile SingularAttribute<Parent, Long> id;
}