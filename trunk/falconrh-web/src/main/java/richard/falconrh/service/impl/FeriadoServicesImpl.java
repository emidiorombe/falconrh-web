package richard.falconrh.service.impl;

import javax.ejb.Stateless;

import richard.falconrh.entity.feriado.Feriado;
import richard.falconrh.service.FeriadoServices;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Stateless(name="ejb/FeriadoServices", mappedName="FeriadoServices")
public class FeriadoServicesImpl extends AbstractServicesImpl<Feriado> implements FeriadoServices {
}