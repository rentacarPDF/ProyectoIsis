package dom.localidad;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.util.TitleBuffer;
import org.apache.isis.core.objectstore.jdo.applib.annotations.Auditable;


@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
@javax.jdo.annotations.Query(name="listado_localidades", language="JDQL",
							value="SELECT  FROM dom.localidad.Localidad WHERE  ACTIVO==:true")})
@ObjectType("LOCALIDAD")
@Auditable


public class Localidad {

	
	
	public String title()
	{
	final TitleBuffer buf = new TitleBuffer();
	        buf.append(getNombreLocalidad()
	        		);        
	        return buf.toString();
	}	
	
	
	private String nombreLocalidad;
	
	
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="1")
	public String getNombreLocalidad() 
	{
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) 
	{
		this.nombreLocalidad = nombreLocalidad;
	}
	//----------------------------------------------------------------
	private boolean activo=true;
	
	
   	
   	public boolean getActivo() {
   		return activo; 
   	}    
	
	public void setActivo(boolean activo)
	{ 
		this.activo=activo; 
	
	}
	private String ownedBy;
	@Hidden 
	public String getOwnedBy() {
	    return ownedBy;	
	}
	public void setOwnedBy(final String ownedBy){
	    this.ownedBy = ownedBy;	
	}	
	@MemberOrder(sequence = "3") 
	public void remove() 
	{ setActivo(false); 
	//remove(auto); 
	}
	// }}
	
	
}
