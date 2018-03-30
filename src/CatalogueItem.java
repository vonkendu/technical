
public class CatalogueItem {
	String name;
	String brand;
	String price;
	String article;
	String initial_price;

	public CatalogueItem(String name, String brand, String price,  String initial_price, String article) {
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.article = article;
		this.initial_price = initial_price;

	}
	
	

	public String toString() {
		return name + " " + brand + " " + price + " " + initial_price + " "+  article;
	}
	
	

}
