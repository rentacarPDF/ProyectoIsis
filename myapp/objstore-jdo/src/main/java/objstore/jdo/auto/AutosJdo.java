package objstore.jdo.auto;

import dom.autos.AutoServicio;
import dom.autos.Auto;
import org.apache.isis.applib.query.QueryDefault;
import java.util.List;


public class AutosJdo extends AutoServicio{
	@Override
    public List<Auto> AutosActivos() {
        return allMatches(
                new QueryDefault<Auto>(Auto.class, 
                        "listado_autos"));
    }
}
