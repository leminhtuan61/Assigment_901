package fa.traning.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements Constant{
	
	public static boolean isPhoneNumber(String phone) {
		Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	
	public static boolean isOrderNumber(String orderNumber) {
		Pattern pattern = Pattern.compile(ORDER_NUMBER_REGEX);
		Matcher matcher = pattern.matcher(orderNumber);
		return matcher.matches();
	}
	
	public static boolean loop(Scanner scanner, String string) {
		boolean check;
		do {
			System.out.print("Add more " + string + "(s)? (Y/N): ");
			String decision = scanner.nextLine();

			if (decision.charAt(0) == 'Y' || decision.charAt(0) == 'y') {
				check = true;
				break;
			} else if (decision.charAt(0) == 'N' || decision.charAt(0) == 'n') {
				check = false;
				break;
			} else {
				continue;
			}
		} while (true);
		return check;
	}
	
}
