package dom.utilidades;

 
import java.util.List;

import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.filter.Filter;
import org.apache.isis.applib.AbstractContainedObject;

import com.google.common.base.Objects;
import dom.utilidades.Categoria;

@Named("Utilidades")
public class UtilidadesServicio extends AbstractContainedObject{

	//{{ Marcas
	@MemberOrder(sequence = "1") // Carga
	public Marcas CargarMarca(@Named("Marca") String marcas) { 
		   	final String ownedBy = currentUserName();
		   	return laMarca(marcas, ownedBy); }
	
	@Hidden // for use by fixtures
	public Marcas laMarca(
			String marcas,
		   	String userName) {
	final Marcas aux = newTransientInstance(Marcas.class);
		   	aux.setMarcas(marcas);
		   	aux.setOwnedBy(userName);
		   	persist(aux);
		   	return aux;
	}
			
	@MemberOrder(sequence = "2") // Listado
	public List<Marcas> ListarMarcas()	{
		   	final List<Marcas> aux= allInstances(Marcas.class);
		   	return aux; }
			
	@Hidden    
	public List<Marcas> autoComplete(final String marcas){
			return allMatches(Marcas.class, new Filter<Marcas>(){
			@Override
			public boolean accept(final Marcas t) {		
			return ownedByCurrentUser(t) && t.getMarcas().contains(marcas); }
			});
				
	}
	//}}	
	
	// {{ Categoria
	@MemberOrder(sequence = "3")
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
	// }}
	
	// {{ Helpers
	protected boolean ownedByCurrentUser(final Marcas t){
		    return Objects.equal(t.getOwnedBy(), currentUserName()); }		

	protected String currentUserName(){
			return getContainer().getUser().getName(); }
	// }}
	
}
