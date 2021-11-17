/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.controller;

// line 32 "../../../../../CarShopTransferObjects.ump"
public class ToTimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ToTimeSlot Attributes
  private String type;
  private String startD;
  private String startT;
  private String endD;
  private String endT;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ToTimeSlot(String aType, String aStartD, String aStartT, String aEndD, String aEndT)
  {
    type = aType;
    startD = aStartD;
    startT = aStartT;
    endD = aEndD;
    endT = aEndT;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartD(String aStartD)
  {
    boolean wasSet = false;
    startD = aStartD;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartT(String aStartT)
  {
    boolean wasSet = false;
    startT = aStartT;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndD(String aEndD)
  {
    boolean wasSet = false;
    endD = aEndD;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndT(String aEndT)
  {
    boolean wasSet = false;
    endT = aEndT;
    wasSet = true;
    return wasSet;
  }

  public String getType()
  {
    return type;
  }

  public String getStartD()
  {
    return startD;
  }

  public String getStartT()
  {
    return startT;
  }

  public String getEndD()
  {
    return endD;
  }

  public String getEndT()
  {
    return endT;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "type" + ":" + getType()+ "," +
            "startD" + ":" + getStartD()+ "," +
            "startT" + ":" + getStartT()+ "," +
            "endD" + ":" + getEndD()+ "," +
            "endT" + ":" + getEndT()+ "]";
  }
}