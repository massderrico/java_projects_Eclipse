/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.model;
import java.io.Serializable;
import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 3 "../../../../../CarShopPersistence.ump"
// line 6 "../../../../../carshop.ump"
public class CarShop implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CarShop Associations
  private Business business;
  private Owner owner;
  private List<Customer> customers;
  private List<Technician> technicians;
  private List<Garage> garages;
  private List<BusinessHour> hours;
  private List<Appointment> appointments;
  private List<TimeSlot> timeSlots;
  private List<BookableService> bookableServices;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CarShop()
  {
    customers = new ArrayList<Customer>();
    technicians = new ArrayList<Technician>();
    garages = new ArrayList<Garage>();
    hours = new ArrayList<BusinessHour>();
    appointments = new ArrayList<Appointment>();
    timeSlots = new ArrayList<TimeSlot>();
    bookableServices = new ArrayList<BookableService>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
  }

  public boolean hasBusiness()
  {
    boolean has = business != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Owner getOwner()
  {
    return owner;
  }

  public boolean hasOwner()
  {
    boolean has = owner != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Customer getCustomer(int index)
  {
    Customer aCustomer = customers.get(index);
    return aCustomer;
  }

  public List<Customer> getCustomers()
  {
    List<Customer> newCustomers = Collections.unmodifiableList(customers);
    return newCustomers;
  }

  public int numberOfCustomers()
  {
    int number = customers.size();
    return number;
  }

  public boolean hasCustomers()
  {
    boolean has = customers.size() > 0;
    return has;
  }

  public int indexOfCustomer(Customer aCustomer)
  {
    int index = customers.indexOf(aCustomer);
    return index;
  }
  /* Code from template association_GetMany */
  public Technician getTechnician(int index)
  {
    Technician aTechnician = technicians.get(index);
    return aTechnician;
  }

  public List<Technician> getTechnicians()
  {
    List<Technician> newTechnicians = Collections.unmodifiableList(technicians);
    return newTechnicians;
  }

  public int numberOfTechnicians()
  {
    int number = technicians.size();
    return number;
  }

  public boolean hasTechnicians()
  {
    boolean has = technicians.size() > 0;
    return has;
  }

  public int indexOfTechnician(Technician aTechnician)
  {
    int index = technicians.indexOf(aTechnician);
    return index;
  }
  /* Code from template association_GetMany */
  public Garage getGarage(int index)
  {
    Garage aGarage = garages.get(index);
    return aGarage;
  }

  public List<Garage> getGarages()
  {
    List<Garage> newGarages = Collections.unmodifiableList(garages);
    return newGarages;
  }

  public int numberOfGarages()
  {
    int number = garages.size();
    return number;
  }

  public boolean hasGarages()
  {
    boolean has = garages.size() > 0;
    return has;
  }

  public int indexOfGarage(Garage aGarage)
  {
    int index = garages.indexOf(aGarage);
    return index;
  }
  /* Code from template association_GetMany */
  public BusinessHour getHour(int index)
  {
    BusinessHour aHour = hours.get(index);
    return aHour;
  }

  public List<BusinessHour> getHours()
  {
    List<BusinessHour> newHours = Collections.unmodifiableList(hours);
    return newHours;
  }

  public int numberOfHours()
  {
    int number = hours.size();
    return number;
  }

  public boolean hasHours()
  {
    boolean has = hours.size() > 0;
    return has;
  }

  public int indexOfHour(BusinessHour aHour)
  {
    int index = hours.indexOf(aHour);
    return index;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_GetMany */
  public TimeSlot getTimeSlot(int index)
  {
    TimeSlot aTimeSlot = timeSlots.get(index);
    return aTimeSlot;
  }

  public List<TimeSlot> getTimeSlots()
  {
    List<TimeSlot> newTimeSlots = Collections.unmodifiableList(timeSlots);
    return newTimeSlots;
  }

  public int numberOfTimeSlots()
  {
    int number = timeSlots.size();
    return number;
  }

  public boolean hasTimeSlots()
  {
    boolean has = timeSlots.size() > 0;
    return has;
  }

  public int indexOfTimeSlot(TimeSlot aTimeSlot)
  {
    int index = timeSlots.indexOf(aTimeSlot);
    return index;
  }
  /* Code from template association_GetMany */
  public BookableService getBookableService(int index)
  {
    BookableService aBookableService = bookableServices.get(index);
    return aBookableService;
  }

  public List<BookableService> getBookableServices()
  {
    List<BookableService> newBookableServices = Collections.unmodifiableList(bookableServices);
    return newBookableServices;
  }

  public int numberOfBookableServices()
  {
    int number = bookableServices.size();
    return number;
  }

  public boolean hasBookableServices()
  {
    boolean has = bookableServices.size() > 0;
    return has;
  }

  public int indexOfBookableService(BookableService aBookableService)
  {
    int index = bookableServices.indexOf(aBookableService);
    return index;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setBusiness(Business aNewBusiness)
  {
    boolean wasSet = false;
    if (business != null && !business.equals(aNewBusiness) && equals(business.getCarShop()))
    {
      //Unable to setBusiness, as existing business would become an orphan
      return wasSet;
    }

    business = aNewBusiness;
    CarShop anOldCarShop = aNewBusiness != null ? aNewBusiness.getCarShop() : null;

    if (!this.equals(anOldCarShop))
    {
      if (anOldCarShop != null)
      {
        anOldCarShop.business = null;
      }
      if (business != null)
      {
        business.setCarShop(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setOwner(Owner aNewOwner)
  {
    boolean wasSet = false;
    if (owner != null && !owner.equals(aNewOwner) && equals(owner.getCarShop()))
    {
      //Unable to setOwner, as existing owner would become an orphan
      return wasSet;
    }

    owner = aNewOwner;
    CarShop anOldCarShop = aNewOwner != null ? aNewOwner.getCarShop() : null;

    if (!this.equals(anOldCarShop))
    {
      if (anOldCarShop != null)
      {
        anOldCarShop.owner = null;
      }
      if (owner != null)
      {
        owner.setCarShop(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCustomers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Customer addCustomer(String aUsername, String aPassword)
  {
    return new Customer(aUsername, aPassword, this);
  }

  public boolean addCustomer(Customer aCustomer)
  {
    boolean wasAdded = false;
    if (customers.contains(aCustomer)) { return false; }
    CarShop existingCarShop = aCustomer.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aCustomer.setCarShop(this);
    }
    else
    {
      customers.add(aCustomer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomer(Customer aCustomer)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomer, as it must always have a carShop
    if (!this.equals(aCustomer.getCarShop()))
    {
      customers.remove(aCustomer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomerAt(Customer aCustomer, int index)
  {  
    boolean wasAdded = false;
    if(addCustomer(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAt(Customer aCustomer, int index)
  {
    boolean wasAdded = false;
    if(customers.contains(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAt(aCustomer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTechnicians()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfTechnicians()
  {
    return 5;
  }
  /* Code from template association_AddOptionalNToOne */
  public Technician addTechnician(String aUsername, String aPassword, Technician.TechnicianType aType)
  {
    if (numberOfTechnicians() >= maximumNumberOfTechnicians())
    {
      return null;
    }
    else
    {
      return new Technician(aUsername, aPassword, aType, this);
    }
  }

  public boolean addTechnician(Technician aTechnician)
  {
    boolean wasAdded = false;
    if (technicians.contains(aTechnician)) { return false; }
    if (numberOfTechnicians() >= maximumNumberOfTechnicians())
    {
      return wasAdded;
    }

    CarShop existingCarShop = aTechnician.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aTechnician.setCarShop(this);
    }
    else
    {
      technicians.add(aTechnician);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTechnician(Technician aTechnician)
  {
    boolean wasRemoved = false;
    //Unable to remove aTechnician, as it must always have a carShop
    if (!this.equals(aTechnician.getCarShop()))
    {
      technicians.remove(aTechnician);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTechnicianAt(Technician aTechnician, int index)
  {  
    boolean wasAdded = false;
    if(addTechnician(aTechnician))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTechnicians()) { index = numberOfTechnicians() - 1; }
      technicians.remove(aTechnician);
      technicians.add(index, aTechnician);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTechnicianAt(Technician aTechnician, int index)
  {
    boolean wasAdded = false;
    if(technicians.contains(aTechnician))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTechnicians()) { index = numberOfTechnicians() - 1; }
      technicians.remove(aTechnician);
      technicians.add(index, aTechnician);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTechnicianAt(aTechnician, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGarages()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfGarages()
  {
    return 5;
  }
  /* Code from template association_AddOptionalNToOne */
  public Garage addGarage(Technician aTechnician)
  {
    if (numberOfGarages() >= maximumNumberOfGarages())
    {
      return null;
    }
    else
    {
      return new Garage(this, aTechnician);
    }
  }

  public boolean addGarage(Garage aGarage)
  {
    boolean wasAdded = false;
    if (garages.contains(aGarage)) { return false; }
    if (numberOfGarages() >= maximumNumberOfGarages())
    {
      return wasAdded;
    }

    CarShop existingCarShop = aGarage.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aGarage.setCarShop(this);
    }
    else
    {
      garages.add(aGarage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGarage(Garage aGarage)
  {
    boolean wasRemoved = false;
    //Unable to remove aGarage, as it must always have a carShop
    if (!this.equals(aGarage.getCarShop()))
    {
      garages.remove(aGarage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGarageAt(Garage aGarage, int index)
  {  
    boolean wasAdded = false;
    if(addGarage(aGarage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGarages()) { index = numberOfGarages() - 1; }
      garages.remove(aGarage);
      garages.add(index, aGarage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGarageAt(Garage aGarage, int index)
  {
    boolean wasAdded = false;
    if(garages.contains(aGarage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGarages()) { index = numberOfGarages() - 1; }
      garages.remove(aGarage);
      garages.add(index, aGarage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGarageAt(aGarage, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BusinessHour addHour(BusinessHour.DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime)
  {
    return new BusinessHour(aDayOfWeek, aStartTime, aEndTime, this);
  }

  public boolean addHour(BusinessHour aHour)
  {
    boolean wasAdded = false;
    if (hours.contains(aHour)) { return false; }
    CarShop existingCarShop = aHour.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aHour.setCarShop(this);
    }
    else
    {
      hours.add(aHour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHour(BusinessHour aHour)
  {
    boolean wasRemoved = false;
    //Unable to remove aHour, as it must always have a carShop
    if (!this.equals(aHour.getCarShop()))
    {
      hours.remove(aHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHourAt(BusinessHour aHour, int index)
  {  
    boolean wasAdded = false;
    if(addHour(aHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHours()) { index = numberOfHours() - 1; }
      hours.remove(aHour);
      hours.add(index, aHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHourAt(BusinessHour aHour, int index)
  {
    boolean wasAdded = false;
    if(hours.contains(aHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHours()) { index = numberOfHours() - 1; }
      hours.remove(aHour);
      hours.add(index, aHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHourAt(aHour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(Customer aCustomer, BookableService aBookableService)
  {
    return new Appointment(aCustomer, aBookableService, this);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    CarShop existingCarShop = aAppointment.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aAppointment.setCarShop(this);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a carShop
    if (!this.equals(aAppointment.getCarShop()))
    {
      appointments.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(Date aStartDate, Time aStartTime, Date aEndDate, Time aEndTime)
  {
    return new TimeSlot(aStartDate, aStartTime, aEndDate, aEndTime, this);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    CarShop existingCarShop = aTimeSlot.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aTimeSlot.setCarShop(this);
    }
    else
    {
      timeSlots.add(aTimeSlot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasRemoved = false;
    //Unable to remove aTimeSlot, as it must always have a carShop
    if (!this.equals(aTimeSlot.getCarShop()))
    {
      timeSlots.remove(aTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeSlotAt(TimeSlot aTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeSlot(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeSlotAt(TimeSlot aTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(timeSlots.contains(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeSlotAt(aTimeSlot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookableServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addBookableService(BookableService aBookableService)
  {
    boolean wasAdded = false;
    if (bookableServices.contains(aBookableService)) { return false; }
    CarShop existingCarShop = aBookableService.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aBookableService.setCarShop(this);
    }
    else
    {
      bookableServices.add(aBookableService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBookableService(BookableService aBookableService)
  {
    boolean wasRemoved = false;
    //Unable to remove aBookableService, as it must always have a carShop
    if (!this.equals(aBookableService.getCarShop()))
    {
      bookableServices.remove(aBookableService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookableServiceAt(BookableService aBookableService, int index)
  {  
    boolean wasAdded = false;
    if(addBookableService(aBookableService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookableServices()) { index = numberOfBookableServices() - 1; }
      bookableServices.remove(aBookableService);
      bookableServices.add(index, aBookableService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookableServiceAt(BookableService aBookableService, int index)
  {
    boolean wasAdded = false;
    if(bookableServices.contains(aBookableService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookableServices()) { index = numberOfBookableServices() - 1; }
      bookableServices.remove(aBookableService);
      bookableServices.add(index, aBookableService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookableServiceAt(aBookableService, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Business existingBusiness = business;
    business = null;
    if (existingBusiness != null)
    {
      existingBusiness.delete();
      existingBusiness.setCarShop(null);
    }
    Owner existingOwner = owner;
    owner = null;
    if (existingOwner != null)
    {
      existingOwner.delete();
      existingOwner.setCarShop(null);
    }
    while (customers.size() > 0)
    {
      Customer aCustomer = customers.get(customers.size() - 1);
      aCustomer.delete();
      customers.remove(aCustomer);
    }
    
    while (technicians.size() > 0)
    {
      Technician aTechnician = technicians.get(technicians.size() - 1);
      aTechnician.delete();
      technicians.remove(aTechnician);
    }
    
    while (garages.size() > 0)
    {
      Garage aGarage = garages.get(garages.size() - 1);
      aGarage.delete();
      garages.remove(aGarage);
    }
    
    while (hours.size() > 0)
    {
      BusinessHour aHour = hours.get(hours.size() - 1);
      aHour.delete();
      hours.remove(aHour);
    }
    
    while (appointments.size() > 0)
    {
      Appointment aAppointment = appointments.get(appointments.size() - 1);
      aAppointment.delete();
      appointments.remove(aAppointment);
    }
    
    while (timeSlots.size() > 0)
    {
      TimeSlot aTimeSlot = timeSlots.get(timeSlots.size() - 1);
      aTimeSlot.delete();
      timeSlots.remove(aTimeSlot);
    }
    
    while (bookableServices.size() > 0)
    {
      BookableService aBookableService = bookableServices.get(bookableServices.size() - 1);
      aBookableService.delete();
      bookableServices.remove(aBookableService);
    }
    
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 6 "../../../../../CarShopPersistence.ump"
  private static final long serialVersionUID = -3062059649216520626L ;

  
}