package beans_entity;

import java.io.Serializable;

public class Product implements Serializable{
	private String productId;
	private String categoryId;
	private String productName;
	private String stringPrice;
	private int intPrice;
	
	public Product() {
	}
	
	public Product(String productid, String categoryid,
			String productname, int price) {
		
		this.categoryId=categoryid;
		this.productId=productid;
		this.productName=productname;
		this.intPrice=price;
	}
	public Product(String productid, String categoryid,
			String productname, String price) {
		
		this.categoryId=categoryid;
		this.productId=productid;
		this.productName=productname;
		this.stringPrice=price;
	}
	
	
	
	public void viewProduct() {
		System.out.println(this.productId+" "+this.categoryId+" "
				+this.productName+" "+this.intPrice);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStringPrice() {
		return stringPrice;
	}

	public void setStringPrice(String stringPrice) {
		this.stringPrice = stringPrice;
	}

	public int getIntPrice() {
		return intPrice;
	}

	public void setIntPrice(int intPrice) {
		this.intPrice = intPrice;
	}
}
