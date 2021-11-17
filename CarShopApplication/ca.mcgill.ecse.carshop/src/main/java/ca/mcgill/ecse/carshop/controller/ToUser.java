/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.controller;

// line 15 "../../../../../CarShopTransferObjects.ump"
public class ToUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ToUser Attributes
  private String username;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ToUser(String aUsername)
  {
    username = aUsername;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "]";
  }
}