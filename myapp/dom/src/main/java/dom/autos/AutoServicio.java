package dom.autos;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.apache.isis.applib.filter.Filter;


import com.google.common.base.Objects;


import dom.autos.Auto;
import dom.autos.Auto.Estado;
import dom.autos.Auto.Seguro;
import dom.autos.Auto.TipoCombustible;
import dom.utilidades.Marca;
 

@Named("Flota")
public class AutoServicio extends AbstractFactoryAndRepository {
	 
	@MemberOrder(sequence = "1") // Carga
	public Auto CargarAuto(
			@Named("Patente") String patente,
			@Named("Marca") Marca marca, 
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
	public Auto elAuto(
		   String patente,
		   Marca marca, 
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
		 final Auto auto = newTransientInstance(Auto.class);
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
	public List<Auto> ListarAutos() 
	{
		 final List<Auto> autos= allInstances(Auto.class);
		 return autos; 
	}
	
	 
	
	protected boolean ownedByCurrentUser(final Auto t) 
	{
	    return Objects.equal(t.getOwnedBy(), currentUserName());
	}
	protected String currentUserName() {
	    return getContainer().getUser().getName();
	}
	// }}

	
}