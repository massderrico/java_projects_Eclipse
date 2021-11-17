/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.controller;

// line 26 "../../../../../CarShopTransferObjects.ump"
public class ToBusinessHour
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ToBusinessHour Attributes
  private String day;
  private String start;
  private String end;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ToBusinessHour(String aDay, String aStart, String aEnd)
  {
    day = aDay;
    start = aStart;
    end = aEnd;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDay(String aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setStart(String aStart)
  {
    boolean wasSet = false;
    start = aStart;
    wasSet = true;
    return wasSet;
  }

  public boolean setEnd(String aEnd)
  {
    boolean wasSet = false;
    end = aEnd;
    wasSet = true;
    return wasSet;
  }

  public String getDay()
  {
    return day;
  }

  public String getStart()
  {
    return start;
  }

  public String getEnd()
  {
    return end;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "day" + ":" + getDay()+ "," +
            "start" + ":" + getStart()+ "," +
            "end" + ":" + getEnd()+ "]";
  }
}