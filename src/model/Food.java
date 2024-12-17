package model;

public class Food {
	private String name;
	private int quantity;
	private String expirationDate;

	public Food(String name, int quantity, String expirationDate) {
		this.name = name;
		this.quantity = quantity;
		this.expirationDate = expirationDate;
	}

	public String getName() { return name; }
	public int getQuantity() { return quantity; }
	public String getExpirationDate() { return expirationDate; }

	@Override
	public String toString() {
		return "이름: " + name + ", 수량: " + quantity + ", 유통기한: " + expirationDate;
	}
}
