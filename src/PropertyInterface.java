package EstateAgent;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
  * implemented by Kok Heng and Rupak
  */

// Service Endpoint Interface

@WebService
@SOAPBinding(style = Style.RPC)  
public interface PropertyInterface {

	/**
	  * @return String representation of each of the properties and their price
	  */

	@WebMethod String getPropertiesAndPrice();

	/**
	  * @param propertyName String name of the property being referenced
	  * @return String representation of the property's timetable for the week
	 */

	@WebMethod String getPropertyTimetable(String propertyName);

	/**
	  * @param propertyName String name of the property being referenced
	  * @param day String day of the week
	  * @param hour int hour in the day
	  * @return boolean response
	  */

	@WebMethod boolean checkPropertyAvailability(String propertyName, String day, int hour);

	/**
	  * @param propertyName String name of the property being referenced
	  * @param day String day of the week
	  * @param hour int hour in the day
	  * @param id String id of the request
	  * @return boolean representing whether the request was successful
	  * @throws InterruptedException
	  */

	@WebMethod boolean makePropertyRequest(String propertyName, String day, int hour, String id) throws InterruptedException;

	/**
	  * @param propertyName String name of the property being referenced
	  * @param day String day of the week
	  * @param hour int hour in the day
	  * @param id String id of the request
	  * @return boolean representing whether the request offer was successful
	  * @throws InterruptedException
	  */

//	@WebMethod boolean makePropertyOffer(String propertyName, String day, in hour, String id, String offer) throws InterruptedException;

	/**
	  * @param propertyName String name of the property being referenced
	  * @param day String day of the week
	  * @param hour int hour in the day
	  * @param id String id of the request
	  * @return boolean representing whether the cancel was successful
	  */

	@WebMethod boolean cancelPropertyRequest(String propertyName, String day, int hour, String id);
}