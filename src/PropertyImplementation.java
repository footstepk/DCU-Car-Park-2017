package EstateAgent;

import java.util.Set;
import javax.jws.WebService;

/**
  * implemented by Kok Heng and Rupak
  */

/**
  * this class serve as service implementation
  * this is the implementation for the property request(viewing/query/availability) service
  */

@WebService(endpointInterface = "EstateAgent.PropertyInterface")
public class PropertyImplementation implements PropertyInterface {

	private PropertyEstateAgent estateAgent = new PropertyEstateAgent();

	/**
	  * @return String representation of the properties and unit price
	  */

	@Override
	public String getPropertiesAndPrice() {
		Set<String> keys = estateAgent.getPropertyNames();
		String propertiesAndPrice = "";

		for(String key: keys)
			propertiesAndPrice = propertiesAndPrice + key + ": " + estateAgent.getPropertyPrice(key) + ", ";

			if(propertiesAndPrice.length() > 2)
				propertiesAndPrice = propertiesAndPrice.substring(0, (propertiesAndPrice.length()-2));
				return propertiesAndPrice;
	}

	/**
	  * @param propertyName String name of the property being referenced
	  * @return String representation of a properties weekly calendar
	  */

	@Override
	public String getPropertyTimetable(String propertyName) {
		return estateAgent.getWeeklyCalendar(propertyName);
	}

	/**
	  * @param propertyName String name of the property being referenced
	  * @param day      String day of the week
	  * @param hour     int hour in the day
	  * @return boolean for whether a property is purposely available for request/viewing and sale
	  */

	@Override
	public boolean checkPropertyAvailability(String propertyName, String day, int hour) {
		return estateAgent.isAvailable(propertyName, day, hour);
	}

	/**
	  * @param propertyName String name of the property being referenced
	  * @param day      String day of the week
	  * @param hour     int hour in the day
	  * @param id       String id of the request(i.e. viewing/query/etc)
	  * @return boolean representing the result whether request was success
	  * @throws InterruptedException
	  */

	@Override
	public boolean makePropertyRequest(String propertyName, String day, int hour, String id) throws InterruptedException {
		return estateAgent.makeRequest(propertyName, day, hour, id);
	}

	/**
	  * @param propertyName String name of the property being referenced
	  * @param day      String day of the week
	  * @param hour     int hour in the day
	  * @param id       String id of the request(i.e. offer)
	  * @return boolean representing the result whether request offer was success
	  * @throws InterruptedException
	  */

//	@Override
	public boolean makePropertyOffer(String propertyName, String day, int hour, String id, String offer) throws InterruptedException {
		return estateAgent.makeOffer(propertyName, day, hour, id, offer);
	}

	/**
	  * @param propertyName String name of the property being referenced
	  * @param day      String day of the week
	  * @param hour     int hour in the day
	  * @param id       String id of the request(i.e. viewing/query/etc)
	  * @return boolean representing the result whether request was cancel
	  */

	@Override
	public boolean cancelPropertyRequest(String propertyName, String day, int hour, String id) {
		return estateAgent.cancelRequest(propertyName, day, hour, id);
	}
}