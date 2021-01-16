package fa.traning.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fa.traning.entities.Customer;
import fa.traning.entities.Order;
import fa.traning.utils.Constant;
import fa.traning.utils.InvalidPhoneFormatException;
import fa.traning.utils.StringUtils;
import fa.traning.utils.Validator;

public class CustomerService implements Constant {
	OrderService orderService = new OrderService();
	List<String> customers = new ArrayList<>();

	public void menu() {
		System.out.println("----------------------");
		System.out.println("Choose function:");
		System.out.println("1. Add a new Customer");
		System.out.println("2. Show all Customers");
		System.out.println("3. Search Customer");
		System.out.println("4. Remove Customer");
		System.out.println("5. Exit");
		System.out.print(">");
	}

	/**
	 * Method to input Customer(s)
	 * 
	 * @param scanner
	 * @return list of customer.toString()
	 */

	public List<String> createCustomer(Scanner scanner) {
		boolean check;
		Set<Order> orders = null;
		Customer customer = new Customer();
		do {
			System.out.println("---Enter Customer information---");
			do {
				check = false;
				// Input Customer Name
				System.out.print("Enter customer name: ");
				String name = scanner.nextLine();
				customer.setName(name);

				// Input Customer Phone Number
				this.inputPhoneNumber(scanner, customer);

				// Input Customer Address
				System.out.print("Enter customer address: ");
				String address = scanner.nextLine();
				customer.setAddress(address);

				// Input Customer Order(s)
				orders = orderService.inputOrder(scanner);
				customer.setOrders(orders);
				break;
			} while (true);
			customers.add(customer.toString());

			check = Validator.loop(scanner, "Customer");
		} while (check);
		return customers;
	}

	/**
	 * sub method to input phone number
	 * 
	 * @param scanner
	 * @param customer
	 */
	public void inputPhoneNumber(Scanner scanner, Customer customer) {
		do {
			System.out.print("Enter Customer Phone Number: ");
			String phoneNumber = scanner.nextLine();
			try {
				customer.setPhoneNumber(phoneNumber);
				break;
			} catch (InvalidPhoneFormatException e) {
				continue;
			}
		} while (true);
	}

	/**
	 * Method to save list of customers to FILE_PATH
	 * 
	 * @param customers
	 * @return
	 * @throws Exception
	 */
	public String save(List<String> customers) throws Exception {
		BufferedWriter writer = null;
		customers = this.customers;
		try {
			writer = new BufferedWriter(new FileWriter(Constant.FILE_PATH, false));
			for (String customer : customers) {
				writer.write(customer);
				writer.write("\n");
			}
		} finally {
			writer.close();
		}
		return "";
	}

	/**
	 * Method to read all customer in FILE_PATH
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> findAll() throws Exception {
		List<String> list = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(Constant.FILE_PATH))) {
			String data = null;
			while ((data = reader.readLine()) != null) {
				list.add(data);
			}

		} catch (Exception exception) {

			throw exception;
		}

		return list;
	}

	/**
	 * Method to display all customer in the string list
	 * 
	 * @param customers
	 */
	public void display(List<String> customers) {
		System.out.println("Customer Name\tPhone Number\tAddress\tOrderList");
		for (String string : customers) {
			System.out.println(string);
		}
	}

	/**
	 * Method to search customer by phone 1. Create customers list from FILE_PATH 2.
	 * Search the customers list for exact customer phone number 3. Add the customer
	 * to another String list
	 * 
	 * @param phone
	 * @return
	 */
	public List<String> search(String phone) {
		List<Customer> customers = null;
		List<String> customerByPhone = new ArrayList<>();
		//Check if phone is valid format or not
		if (Validator.isPhoneNumber(phone)) {
			try {
				//Reader file for customers
				customers = this.convertToCustomers(this.findAll());
			} catch (Exception e1) {
			}
			
			//Search customer by phone
			for (Customer customer : customers) {
				try {
					if (phone.equals(customer.getPhoneNumber())) {
						//add customer to another String list
						customerByPhone.add(customer.toString());
					}
				} catch (Exception e) {
				}
			}
		} else {
			customerByPhone = null;
			System.out.println("Invalid phone format!");
		}
		return customerByPhone;
	}

	/**
	 * Method to Convert list of String into list of Customers
	 * 
	 * @param list
	 * @return
	 */
	public List<Customer> convertToCustomers(List<String> list) {
		List<Customer> customers = new ArrayList<>();
		List<String> customerString = null;
		try {
			customerString = this.findAll();
			for (String string : customerString) {
				Customer customer = StringUtils.convertStringToCustomer(string);
				customers.add(customer);
			}

		} catch (Exception e) {
		}
		return customers;
	}

	/**
	 * Method to remove customer with the following phone number
	 * Use iterator to iterate the customers list and then remove as method say 
	 * @param phone
	 * @return
	 */
	public boolean remove(String phone) {
		Customer customerRemove = null;
		List<Customer> customers = null;
		List<String> afterRemoveCustomer = new ArrayList<>();
		if (Validator.isPhoneNumber(phone)) {
			try {
				//Read file for customers
				customers = this.convertToCustomers(this.findAll());
				Iterator<Customer> iterator = customers.iterator();
				while (iterator.hasNext()) {
					customerRemove = iterator.next();
				}
				if (phone.equals(customerRemove)) {
					customers.remove(customerRemove);
				} else
					System.out.println("Phone not found!");

			} catch (Exception e) {
			}
			if (customers.isEmpty())
				afterRemoveCustomer = null;
			//add customer String into another String list
			else {
				for (Customer customer : customers) {
					afterRemoveCustomer.add(customer.toString());
				}
				try {
					//Write to file
					this.save(afterRemoveCustomer);
					System.out.println();
				} catch (Exception e) {
				}
			}
		} else {
			System.out.println("Invalid phone format!");
		}
		return true;
	}
}
