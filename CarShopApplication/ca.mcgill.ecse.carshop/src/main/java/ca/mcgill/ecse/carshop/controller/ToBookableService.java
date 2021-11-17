/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.controller;

// line 52 "../../../../../CarShopTransferObjects.ump"
public class ToBookableService
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ToBookableService Attributes
  private String name;
  private String service;
  private String mandatory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ToBookableService(String aName, String aService, String aMandatory)
  {
    name = aName;
    service = aService;
    mandatory = aMandatory;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setService(String aService)
  {
    boolean wasSet = false;
    service = aService;
    wasSet = true;
    return wasSet;
  }

  public boolean setMandatory(String aMandatory)
  {
    boolean wasSet = false;
    mandatory = aMandatory;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getService()
  {
    return service;
  }

  public String getMandatory()
  {
    return mandatory;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "service" + ":" + getService()+ "," +
            "mandatory" + ":" + getMandatory()+ "]";
  }
}