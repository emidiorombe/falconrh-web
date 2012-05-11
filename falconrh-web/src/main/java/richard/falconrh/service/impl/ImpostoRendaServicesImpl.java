package richard.falconrh.service.impl;

import javax.ejb.Stateless;

import richard.falconrh.entity.ir.TabelaImpostoRenda;
import richard.falconrh.service.ImpostoRendaServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/ImpostoRendaServices", mappedName="ImpostoRendaServices")
public class ImpostoRendaServicesImpl extends AbstractServicesImpl<TabelaImpostoRenda> implements ImpostoRendaServices{
}
