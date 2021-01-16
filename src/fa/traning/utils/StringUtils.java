package fa.traning.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fa.traning.entities.Customer;
import fa.traning.entities.Order;

public class StringUtils {
	/**
	 * The method to convert a string data to Customer.
	 * 
	 * @param data
	 * @return
	 */
	public static Customer convertStringToCustomer(String data) {
		// convert to Object

		String fieldValue[] = data.split("\t");
		Customer customer = new Customer(fieldValue[0], fieldValue[1], fieldValue[2],
				convertStringToOders(fieldValue[3]));

		return customer;
	}
	
	/**
	 * Method to convert a string into a Set of orders
	 * @param data
	 * @return
	 */
	public static Set<Order> convertStringToOders(String data) {
		Set<Order> orders = new HashSet<>();
		String[] splitOrder = data.split(" | ");
		String[] actualOrder = null;
		Order order = null;
		for (String string : splitOrder) {
			actualOrder = string.split("--");
			try {
				order = new Order(actualOrder[0], StringUtils.convertStringToDate(actualOrder[1]));
				orders.add(order);
			} catch (Exception e) {
			}
		}
		return orders;
	}
	
	/**
	 * Method to convert a string into a Date
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date convertStringToDate(String date) throws Exception {
		Date checkDate = null;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		checkDate = null;

		try {
			checkDate = format.parse(date);
		} catch (Exception e) {
			throw e;
		}
		return checkDate;
	}
	
	/**
	 * Turn date into String with date format
	 * @param date
	 * @return
	 */
	public static String convertDateToString(Date date) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		return format.format(date);
	}

}
