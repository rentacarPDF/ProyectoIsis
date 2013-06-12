package dom.categoria;

 
import java.util.List;

import org.apache.isis.applib.AbstractContainedObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

 import dom.categoria.Categoria;

@Named("Categoria")
public class CategoriaServicio extends AbstractContainedObject{

	
	@MemberOrder(sequence = "2")
	 public List<Categoria> ListarCategorias() {

		 final List<Categoria> categoria= allInstances(Categoria.class);

		 return categoria;
		 }
	
	 
//	@MemberOrder(sequence = "1")
//	public Autos CargarCategoria(
//			@Named("Patente") String patente,
//			@Named("Marca") Marcas marca, 
//			@Named("Modelo") String modelo, 
//			@Named("Año") int ano,
//			@Named("Color") String color,
//			@Named("Kilometraje") int kms,
//			@Named("Capacidad Baul (lt)") int baul,
//			@Named("Tipo de Combustible") TipoCombustible combustible,
//			@Named("Estado de Alquiler") Estado estado,
//			@Named("Fecha de Compra") Date fechaCompra,
//			@Named("Compañía de Seguro")Seguro seguro)
//	   { final String ownedBy = currentUserName();
//	     return elAuto(patente,marca,modelo,ano,color,kms,baul,combustible,estado,fechaCompra,seguro,ownedBy);
//	   }
	
}
