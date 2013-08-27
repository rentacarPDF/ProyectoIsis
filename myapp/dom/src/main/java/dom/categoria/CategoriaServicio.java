package dom.categoria;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

@Named("Marca")
public class CategoriaServicio extends AbstractFactoryAndRepository{
	
	// {{
	@MemberOrder(sequence = "1") // Listado de Categoria
	public List<Categoria> ListarCategorias(){ 
		final List<Categoria> categoria= allInstances(Categoria.class);
		return categoria;
	}
	// }}
}
