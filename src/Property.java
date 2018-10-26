package EstateAgent;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
  * implemented by Kok Heng and Rupak
  */

/**
  * Property class to represent a property
  * a property can be requested by a person using SOAP(WS)
  * if the property is not occupied when a person making a requests
  * the property then the will receive it, otherwise they will be
  * added to a waiting list of people for the property at their
  * desired time
 *
  * a property request(viewing) can also be cancelled using the request
  * id as a reference
 *
  * the properties can only be accessed by one client at a time
  * and reentrant locks have been used to ensure mutual exclusion
 *
  * Example Property(name=house, price=350(currency €))
 *
  */

class Property {

	private String propertyName; // the property type i.e. house or apartment
	private int price; // property unit price

	private Queue<String>[][] calendar; // queue for being requested in the calendar list
	private Map<String, Integer> dayMap; // a map list of time
	private final Lock lock = new ReentrantLock(); // a section(critical) areas associated entering into the section.

	/**
	  * full args constructor for the property
	  * @param name to be assigned to the property
	  * @param price of the property
	  * @param hours of request operation (view, sale,cancel)
	  */

	Property(String propertyNames, int prices, int hours) {
		propertyName = propertyNames;
		price = prices;

		// a number of week i.e. 7 and supplies hours
		calendar = new LinkedList[7][hours];
		for (int i=0; i<7; i=i+1) {
			for (int j=0; j<hours; j=j+1) {
				Queue<String> time = new LinkedList<>();
				calendar[i][j] = time;
			}
		}

		/*
		   this map is used to map the string days to their
		   position in the MDArray
		   */

		dayMap = new LinkedHashMap<>();
		dayMap.put("Monday", 0);
		dayMap.put("Tuesday", 1);
		dayMap.put("Wednesday", 2);
		dayMap.put("Thursday", 3);
		dayMap.put("Friday", 4);
		dayMap.put("Saturday", 5);
		dayMap.put("Sunday", 6);
	}

	/**
	  * @return String detail of the property
	  */

//	public String getPropertyDetails() {
//		return propertyType + " " + district + " " + bedroom + " " + price;
//	}

	/**
	  * function to return the price of a property
	  * @return Integer representing the price of the property
	  */

	public int getPrice() {
		lock.lock();
		try {
			return price;
		} finally {
			lock.unlock(); // don't forget to release the lock
		}
	}

	/**
		  * @return String representation of the weekly calendar
	  */

	public String getWeeklyCalendar() {
		String week = ""; // initial the week
		for(Map.Entry<String, Integer> day: dayMap.entrySet()) {
			week += day.getKey() + " available hours: ";

			for(int i=0; i<calendar[day.getValue()].length; i=i+1) {
				if(calendar[day.getValue()][i].isEmpty()) {
					week += i + " ";
				}
			}
			week += "\n";
		}
		return week;
	}

	/**
	  * function to check whether the property is free on this
	  * day at this time
	  * @param day of the week being checked
	  * @param hour of the day being checked
	  * @return boolean representing whether the property is free (Viewing purposes)
	  */

	public boolean isAvailable(String day, int hour) {
		lock.lock();
		try {
			day = day.toLowerCase();
			int dayValue = dayMap.get(day);
			return calendar[dayValue][hour].isEmpty();
		} catch(NullPointerException e) {
			return false;
//			System.out.println("Invalid entry day hour month.");
		} finally {
			// don't forget release the lock
			lock.unlock();
		}
	}

	/**
	  * function to make a request(Viewing) for a property
	  * @param day of the week
	  * @param hour in the day
	  * @param id of the request
	  * @return boolean value representing whether the request was successful
	  * @throws InterruptedException
	  */

	public boolean makeRequest(String day, int hour, String id) throws InterruptedException{
		try {
			lock.lock();
			day = day.toLowerCase();
			int dayValue = dayMap.get(day);
			calendar[dayValue][hour].add(id);
			return true;
		} catch(NullPointerException e) {
			return false;
//			System.out.println("Invalid entry day hour month id.");
		} finally {
			lock.unlock(); // don't forget to release the lock
		}
	}

	/**
	  * function to make a offer for a property
	  * @param day of the week
	  * @param hour in the day
	  * @param id of the request
	  * @param the offer amount
	  * @return boolean value representing whether the request offer was successful
	  * @throws InterruptedException
	  */

	public boolean makeOffer(String day, int hour, String id, String offer) throws InterruptedException{
		try {
			lock.lock();
			day = day.toLowerCase();
			int dayValue = dayMap.get(day);
			calendar[dayValue][hour].add(id);
			calendar[dayValue][hour].add(offer);
			return true;
		} catch(NullPointerException e) {
			return false;
//			System.out.println("Invalid entry day hour month id.");
		} finally {
			lock.unlock(); // don't forget to release the lock
		}
	}

	/**
	  * function to cancel a property request
	  * @param day of the week
	  * @param hour in the day
	  * @param id of the request
	  * @return boolean value representing whether the cancel was successful
	  */

	public boolean cancelRequest(String day, int hour, String id) {

		lock.lock();
		try {
			day = day.toLowerCase();
			int dayValue = dayMap.get(day);
			if(calendar[dayValue][hour].contains(id)) {
				calendar[dayValue][hour].remove(id);
				return true;
			}
			else return false;
		} catch(NullPointerException e) {
			return false;
//			System.out.println("Invalid entry day hour month id.");
		} finally {
			lock.unlock(); // don't forget to release the lock
		}
	}

	@Override
	public String toString() {
		return propertyName + " Price: " + price;
	}
}