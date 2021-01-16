package fa.traning.entities;

import java.util.Date;

import fa.traning.utils.InvalidOrderNumberException;
import fa.traning.utils.StringUtils;
import fa.traning.utils.Validator;

public class Order {
	private String number;
	private Date date;

	public Order() {

	}

	public Order(String number, Date date) {
		super();
		this.number = number;
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) throws InvalidOrderNumberException {
		if (Validator.isOrderNumber(number)) {
			this.number = number;
		} else {
			throw new InvalidOrderNumberException();
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return number + "--" + StringUtils.convertDateToString(date) + " | ";
	}
}
