package richard.falconrh.service.impl;

import javax.ejb.Stateless;

import richard.falconrh.entity.inss.TabelaINSS;
import richard.falconrh.service.TabelaINSSServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/TabelaINSSServices", mappedName="TabelaINSSServices")
public class TabelaINSSServicesImpl extends AbstractServicesImpl<TabelaINSS> implements TabelaINSSServices{
}
