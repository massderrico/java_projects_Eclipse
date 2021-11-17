/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.controller;

// line 18 "../../../../../CarShopTransferObjects.ump"
public class ToAppointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ToAppointment Attributes
  private String customer;
  private String startTime;
  private String day;
  private String bookingName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ToAppointment(String aCustomer, String aStartTime, String aDay, String aBookingName)
  {
    customer = aCustomer;
    startTime = aStartTime;
    day = aDay;
    bookingName = aBookingName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCustomer(String aCustomer)
  {
    boolean wasSet = false;
    customer = aCustomer;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(String aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDay(String aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setBookingName(String aBookingName)
  {
    boolean wasSet = false;
    bookingName = aBookingName;
    wasSet = true;
    return wasSet;
  }

  public String getCustomer()
  {
    return customer;
  }

  public String getStartTime()
  {
    return startTime;
  }

  public String getDay()
  {
    return day;
  }

  public String getBookingName()
  {
    return bookingName;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "customer" + ":" + getCustomer()+ "," +
            "startTime" + ":" + getStartTime()+ "," +
            "day" + ":" + getDay()+ "," +
            "bookingName" + ":" + getBookingName()+ "]";
  }
}