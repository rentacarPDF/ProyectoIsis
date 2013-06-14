package dom.autos;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractContainedObject;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;


import com.google.common.base.Objects;


import dom.autos.Autos;
import dom.autos.Autos.Estado;
import dom.autos.Autos.Seguro;
import dom.autos.Autos.TipoCombustible;
import dom.utilidades.Marcas;
 

@Named("Flota")
public class AutosServicio extends AbstractContainedObject{
	 
	@MemberOrder(sequence = "1") // Carga
	public Autos CargarAuto(
			@Named("Patente") String patente,
			@Named("Marca") Marcas marca, 
			@Named("Modelo") String modelo, 
			@Named("Año") int ano,
			@Named("Color") String color,
			@Named("Kilometraje") int kms,
			@Named("Capacidad Baul (lt)") int baul,
			@Named("Tipo de Combustible") TipoCombustible combustible,
			@Named("Estado de Alquiler") Estado estado,
			@Named("Fecha de Compra") Date fechaCompra,
			@Named("Compañía de Seguro")Seguro seguro) { 
		final String ownedBy = currentUserName();
	    return elAuto(patente,marca,modelo,ano,color,kms,baul,combustible,estado,fechaCompra,seguro,ownedBy); 
	    }
		 
	@Hidden // for use by fixtures
	public Autos elAuto(
		   String patente,
		   Marcas marca, 
		   String modelo,
		   int ano,
		   String color,
		   int kms, 
		   int baul,
		   TipoCombustible combustible,
		   Estado estado,
		   Date fechaCompra,
		   Seguro seguro,
		   String userName) {
		 final Autos auto = newTransientInstance(Autos.class);
		   auto.setPatente(patente);
		   auto.setMarca(marca);
		   auto.setModelo(modelo);
		   auto.setAno(ano);
		   auto.setColor(color);
		   auto.setKilometraje(kms);
		   auto.setCapacidadBaul(baul);
		   auto.setTipoCombustible(combustible);
		   auto.setEstado(estado);
		   auto.setFechaCompra(fechaCompra);
		   auto.setSeguro(seguro);
		   auto.setOwnedBy(userName);
 
       persist(auto);
       return auto;
    }
	
	@MemberOrder(sequence = "2") // Listado
	 public List<Autos> ListarAutos() {
		 final List<Autos> autos= allInstances(Autos.class);
		 return autos; }
	// }}

	// {{ Helpers
	protected boolean ownedByCurrentUser(final Autos t) {
	    return Objects.equal(t.getOwnedBy(), currentUserName());
	}
	protected String currentUserName() {
	    return getContainer().getUser().getName();
	}
	// }}

}