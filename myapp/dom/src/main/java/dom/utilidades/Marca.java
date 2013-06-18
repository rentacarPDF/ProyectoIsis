package dom.utilidades;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.util.TitleBuffer;
import org.apache.isis.core.objectstore.jdo.applib.annotations.Auditable;

import dom.autos.Auto;

import javax.jdo.annotations.VersionStrategy;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
@javax.jdo.annotations.Query(name="listado_marcas", language="JDQL",
							value="SELECT FROM dom.utilidades.Marcas WHERE ownedBy == :ownedBy")})
@javax.jdo.annotations.Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSION")
@ObjectType("MARCAS")
@Auditable
@AutoComplete(repository=UtilidadesServicio.class, action="autoComplete")

public class Marca {
	
	// {{ Identification on the UI	
	public String title() {
		final TitleBuffer buf = new TitleBuffer();		
		buf.append(getNombre());		       
		return buf.toString(); }
	// }}
	
	// {{ OwnedBy (property)
	private String ownedBy;	
	@Hidden
	// not shown in the UI
	public String getOwnedBy() {
	    return ownedBy;	}

	public void setOwnedBy(final String ownedBy) {
	    this.ownedBy = ownedBy; }	    
	// }}	  
	
	//{{ Marca
	@Persistent(mappedBy="marca")
	private String nombre;
	@DescribedAs("La marca del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="1")
	public String getNombre(){
		return nombre; }
	
	public void setNombre(String nombre){
		this.nombre=nombre; }	
	// }}
	
	//{{ Autos
	private List<Auto> autos = new ArrayList<Auto>();
	public List<Auto> getAutos() { 
		return autos; }
	public void setAutos(List<Auto> autos) { 
		this.autos= autos; }
	
	/*
	public void addToAutos(Autos a) {
	        if(a == null || autos.contains(a)) return;
	        a.setMarca(this);
	        autos.add(a); }
	public void removeFromAutos(Autos e) {
	        if(e == null || !autos.contains(e)) return;
	        e.setMarca(null);
	        autos.remove(e); } */
	//}}	
	
	// {{ injected: DomainObjectContainer
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    public void setDomainObjectContainer(final DomainObjectContainer container) {
        this.container = container;
    }
    // }}
}
