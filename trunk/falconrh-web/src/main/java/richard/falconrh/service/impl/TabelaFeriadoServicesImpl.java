package richard.falconrh.service.impl;

import javax.ejb.Stateless;

import richard.falconrh.entity.feriado.TabelaFeriado;
import richard.falconrh.service.TabelaFeriadoServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/TabelaFeriadoServices", mappedName="TabelaFeriadoServices")
public class TabelaFeriadoServicesImpl extends AbstractServicesImpl<TabelaFeriado> implements TabelaFeriadoServices{
}
