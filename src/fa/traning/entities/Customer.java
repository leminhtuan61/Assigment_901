package fa.traning.entities;

import java.util.Set;

import fa.traning.utils.InvalidPhoneFormatException;
import fa.traning.utils.Validator;

public class Customer {
	private String name;
	private String phoneNumber;
	private String address;
	private Set<Order> orders;

	public Customer() {

	}

	public Customer(String name, String phoneNumber, String address, Set<Order> orders) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidPhoneFormatException {
		if (Validator.isPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidPhoneFormatException();
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public String displayOrder() {
		String string = "";
		for (Order order : orders) {
			string += order.toString() + " ";
		}
		return string;
	}

	@Override
	public String toString() {
		return name + "\t" + phoneNumber + "\t" + address + "\t" + this.displayOrder();
	}

}
