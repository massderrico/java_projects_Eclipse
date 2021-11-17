/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.controller;

// line 3 "../../../../../CarShopTransferObjects.ump"
public class ToService
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ToService Attributes
  private String name;
  private String garage;
  private int duration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ToService(String aName, String aGarage, int aDuration)
  {
    name = aName;
    garage = aGarage;
    duration = aDuration;
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

  public boolean setGarage(String aGarage)
  {
    boolean wasSet = false;
    garage = aGarage;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getGarage()
  {
    return garage;
  }

  public int getDuration()
  {
    return duration;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "garage" + ":" + getGarage()+ "," +
            "duration" + ":" + getDuration()+ "]";
  }
}