package EstateAgent;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
  * implemented by Kok heng and Rupak
  */

/**
 * PropertyEstateAgent class to handle the properties and its notion of
	  * essentially acts as a wrapper for the property objects
 * to be used in the implementation class
 *
 * generates each of the properties from a file and then
 * allows for them to be accessed and queried through here
 *
 */

public class PropertyEstateAgent {

	private Map<String, Property> propertys = new HashMap<>();

	/**
	  * no-args constructor
	  * it generates the properties based off a file called auction.properties
	  */

	PropertyEstateAgent() {
		Properties properties = new Properties();
		InputStream inputStream; // reading a file
		String filename = "auction.properties";
		try {
			inputStream = Property.class.getResourceAsStream(filename);
			properties.load(inputStream);

			Enumeration<?> enumeration = properties.propertyNames();

			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				String value = properties.getProperty(key);
				try {
					int price = Integer.parseInt(value);
					propertys.put(key, new Property(key, price, 12));
				} catch(NumberFormatException e) {
			}
		}
		} catch(IOException e) {
		}
	}

	/**
	  * @param property the property being referenced
	  * @return the price of the specified property
	  */

	public int getPropertyPrice(String property) {
		return propertys.get(property).getPrice();
	}

	/**
	  * @return a set of each of the property names
	  * i.e. apartment | house
	  */

	public Set<String> getPropertyNames() {
		return propertys.keySet();
	}

	/**
	  * @param propertyName the property being referenced
	  * @return String representation of the weekly calendar for the property
	  */

	public String getWeeklyCalendar(String propertyName) {
		return propertys.get(propertyName).getWeeklyCalendar();
	}

	/**
	  * function to make a request (booking, viewing etc)
	  * @param propertyName property being referenced
	  * @param day of the week
	  * @param hour in the day
	  * @param id of the request
	  * @return boolean representing whether the request was made
	  * @throws InterruptedException
	  */

	public boolean makeRequest(String propertyName, String day, int hour, String id) throws InterruptedException {
		try {
			return propertys.get(propertyName).makeRequest(day, hour, id);
		} catch(NullPointerException e) {
			return false;
//			System.out.println("Faulty request!");
		}
	}

	/**
	  * function to make a offer
	  * @param propertyName property being referenced
	  * @param day of the week
	  * @param hour in the day
	  * @param id of the offer
	  * @param of the offer amount
	  * @return boolean representing whether the offer was made
	  * @throws InterruptedException
	  */

	public boolean makeOffer(String propertyName, String day, int hour, String id, String offer) throws InterruptedException {
		try {
			return propertys.get(propertyName).makeOffer(day, hour, id, offer);
		} catch(NullPointerException e) {
			return false;
//			System.out.println("Faulty request!");
		}
	}

	/**
	  * function to cancel a request
	  * @param propertyName property being referenced
	  * @param day of the week
	  * @param hour in the day
	  * @param id of the request
	  * @return boolean representing whether the request was cancelled
	  */

	public boolean cancelRequest(String propertyName, String day, int hour, String id) {
		return propertys.get(propertyName).cancelRequest(day, hour, id);
	}

	/**
	  * function to check if a property is available at a given time
	  * @param propertyName property being referenced
	  * @param day of the week
	  * @param hour in the day
	  * @return boolean result for availability
	  */

	public boolean isAvailable(String propertyName, String day, int hour) {
		return propertys.get(propertyName).isAvailable(day, hour);
	}
}