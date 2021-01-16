package fa.traning.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fa.traning.services.CustomerService;
import fa.traning.utils.Constant;

public class Test implements Constant {
	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		List<String> customers = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		boolean check = true;
		

		do {
			customerService.menu();
			String input = scanner.nextLine();
			switch (input) {
			case Constant.ADD_CUSTOMERS:
				customerService.createCustomer(scanner);
				try {
					customerService.save(customers);
					System.out.println("saved");
				} catch (Exception e) {
				}
				break;
				
			case Constant.SHOW_CUSTOMERS:
				try {
					customers = customerService.findAll();
					customerService.display(customers);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case Constant.SEARCH_CUSTOMER:
				System.out.print("Enter customer's phone number: ");
				String phone = scanner.nextLine();
				customers = customerService.search(phone);
				customerService.display(customers);
				break;
			case Constant.REMOVE_CUSTOMER:
				System.out.print("Enter customer's phone number: ");
				phone = scanner.nextLine();
				customerService.remove(phone);
				break;
			case Constant.EXIT:
				check = false;
				System.out.println("Thank you!");
				scanner.close();
				break;

			default:
				check = true;
				break;
			}

		} while (check);
	}
}
