package beans_entity;

import java.io.Serializable;

public class Category implements Serializable{
	
	private String categoryId;
	private String categoryName;
	
	public Category() {
	}
	
	public Category(String id, String name) {
		this.categoryId=id;
		this.categoryName=name;
	}
	
	public void viewCategory() {
		System.out.println(this.categoryId+" "+this.categoryName);
	}
	
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
