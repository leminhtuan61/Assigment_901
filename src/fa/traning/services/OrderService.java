package fa.traning.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import fa.traning.entities.Order;
import fa.traning.utils.InvalidOrderNumberException;
import fa.traning.utils.StringUtils;
import fa.traning.utils.Validator;

public class OrderService {
	public Set<Order> inputOrder(Scanner scanner) {
		boolean checkStatus, checkLoop;
		Order order;
		Set<Order> orders = new HashSet<>();
		do {
			checkLoop = false;
			do {
				order = new Order();
				// Input Order number
				this.inputOrderNumber(scanner, order);

				// Input Order Date
				this.inputDate(scanner, order);
				break;
			} while (true);

			// Check if the order already exist? Add order if false
			checkStatus = orders.add(order);
			if (checkStatus == false) {
				System.err.println("Order already existed!!!");
				checkLoop = true;
				continue;
			} else {
				checkLoop = Validator.loop(scanner, "Order");
			}
		} while (checkLoop);

		return orders;
	}

	public String inputOrderNumber(Scanner scanner, Order order) {
		String orderNumber = "";
		do {
			try {
				System.out.print("Enter Order number: ");
				orderNumber = scanner.nextLine();
				order.setNumber(orderNumber);
				break;
			} catch (InvalidOrderNumberException e) {
				continue;
			}
		} while (true);

		return orderNumber;
	}

	public Date inputDate(Scanner scanner, Order order) {
		Date checkDate = null;
		do {
			System.out.print("Enter Order's date: ");
			String date = scanner.nextLine();

			try {
				checkDate = StringUtils.convertStringToDate(date);
				order.setDate(checkDate);
				break;
			} catch (Exception e) {
				System.out.println("Invalid date format");
				continue;
			}
		} while (true);

		return checkDate;
	}

}
