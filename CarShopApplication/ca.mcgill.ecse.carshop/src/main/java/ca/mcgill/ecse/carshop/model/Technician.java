/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.model;
import java.io.Serializable;
import java.util.*;

// line 27 "../../../../../CarShopPersistence.ump"
// line 32 "../../../../../carshop.ump"
public class Technician extends User implements Serializable
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TechnicianType { Tire, Engine, Transmission, Electronics, Fluids }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Technician Attributes
  private TechnicianType type;

  //Technician Associations
  private Garage garage;
  private CarShop carShop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Technician(String aUsername, String aPassword, TechnicianType aType, CarShop aCarShop)
  {
    super(aUsername, aPassword);
    type = aType;
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create technician due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(TechnicianType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public TechnicianType getType()
  {
    return type;
  }
  /* Code from template association_GetOne */
  public Garage getGarage()
  {
    return garage;
  }

  public boolean hasGarage()
  {
    boolean has = garage != null;
    return has;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setGarage(Garage aNewGarage)
  {
    boolean wasSet = false;
    if (garage != null && !garage.equals(aNewGarage) && equals(garage.getTechnician()))
    {
      //Unable to setGarage, as existing garage would become an orphan
      return wasSet;
    }

    garage = aNewGarage;
    Technician anOldTechnician = aNewGarage != null ? aNewGarage.getTechnician() : null;

    if (!this.equals(anOldTechnician))
    {
      if (anOldTechnician != null)
      {
        anOldTechnician.garage = null;
      }
      if (garage != null)
      {
        garage.setTechnician(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setCarShop(CarShop aCarShop)
  {
    boolean wasSet = false;
    //Must provide carShop to technician
    if (aCarShop == null)
    {
      return wasSet;
    }

    //carShop already at maximum (5)
    if (aCarShop.numberOfTechnicians() >= CarShop.maximumNumberOfTechnicians())
    {
      return wasSet;
    }
    
    CarShop existingCarShop = carShop;
    carShop = aCarShop;
    if (existingCarShop != null && !existingCarShop.equals(aCarShop))
    {
      boolean didRemove = existingCarShop.removeTechnician(this);
      if (!didRemove)
      {
        carShop = existingCarShop;
        return wasSet;
      }
    }
    carShop.addTechnician(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Garage existingGarage = garage;
    garage = null;
    if (existingGarage != null)
    {
      existingGarage.delete();
    }
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeTechnician(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 30 "../../../../../CarShopPersistence.ump"
  private static final long serialVersionUID = -3752343846520653192L ;

  
}