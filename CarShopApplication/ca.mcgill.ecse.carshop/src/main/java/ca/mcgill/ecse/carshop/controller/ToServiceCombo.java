/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.controller;
import java.util.*;

// line 47 "../../../../../CarShopTransferObjects.ump"
public class ToServiceCombo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ToServiceCombo Attributes
  private List<String> mandatory;
  private List<String> services;
  private String mainService;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ToServiceCombo(String aMainService)
  {
    mandatory = new ArrayList<String>();
    services = new ArrayList<String>();
    mainService = aMainService;
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetMany */
  public boolean addMandatory(String aMandatory)
  {
    boolean wasAdded = false;
    wasAdded = mandatory.add(aMandatory);
    return wasAdded;
  }

  public boolean removeMandatory(String aMandatory)
  {
    boolean wasRemoved = false;
    wasRemoved = mandatory.remove(aMandatory);
    return wasRemoved;
  }
  /* Code from template attribute_SetMany */
  public boolean addService(String aService)
  {
    boolean wasAdded = false;
    wasAdded = services.add(aService);
    return wasAdded;
  }

  public boolean removeService(String aService)
  {
    boolean wasRemoved = false;
    wasRemoved = services.remove(aService);
    return wasRemoved;
  }

  public boolean setMainService(String aMainService)
  {
    boolean wasSet = false;
    mainService = aMainService;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_GetMany */
  public String getMandatory(int index)
  {
    String aMandatory = mandatory.get(index);
    return aMandatory;
  }

  public String[] getMandatory()
  {
    String[] newMandatory = mandatory.toArray(new String[mandatory.size()]);
    return newMandatory;
  }

  public int numberOfMandatory()
  {
    int number = mandatory.size();
    return number;
  }

  public boolean hasMandatory()
  {
    boolean has = mandatory.size() > 0;
    return has;
  }

  public int indexOfMandatory(String aMandatory)
  {
    int index = mandatory.indexOf(aMandatory);
    return index;
  }
  /* Code from template attribute_GetMany */
  public String getService(int index)
  {
    String aService = services.get(index);
    return aService;
  }

  public String[] getServices()
  {
    String[] newServices = services.toArray(new String[services.size()]);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(String aService)
  {
    int index = services.indexOf(aService);
    return index;
  }

  public String getMainService()
  {
    return mainService;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "mainService" + ":" + getMainService()+ "]";
  }
}