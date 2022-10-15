package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans_entity.Product;


public class ProductDAO {
	
	private final String JDBC_URL=
			"jdbc:mysql://localhost:3306/product_web_db";
	private final String DB_USER="mysql";
	private final String DB_PASS="mysql";
	
	
	
	
	public boolean createProduct(Product product) {
		
		String sql="insert into product( product_id,"
				+ "category_id,"
				+ "product_name,"
				+ "price) values(?, ?, ?, ?)";
		Connection con=null;
		PreparedStatement pStmt=null;
		
		try {
			con =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			pStmt=con.prepareStatement(sql);
			pStmt.setString(1, product.getProductId());
			pStmt.setString(2, product.getCategoryId());
			pStmt.setString(3, product.getProductName());
			pStmt.setInt(4, product.getIntPrice());
			
			int result=pStmt.executeUpdate();
			if(result!=1) {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
		
	}
	
	
	public String isExistProduct(Product product) {
		String msg="";
		Connection con=null;
		String sql="";
		PreparedStatement pStmt=null;
		ResultSet rs=null;
		try {
			con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				
			sql="select product_id from product where product_id=?";
			
			pStmt=con.prepareStatement(sql);
	
			pStmt.setString(1, product.getProductId());
			
			rs=pStmt.executeQuery();
			if(rs.next()) {
				//msg=rs.getString("category_id");
				msg="プロダクトID既存<br>";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

		try{
			con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			sql="select product_name from product where product_name=?";
			pStmt=con.prepareStatement(sql);
	
			pStmt.setString(1, product.getProductName());
			
			rs=pStmt.executeQuery();
			
			if(rs.next()) {
				msg+="プロダクト名既存<br>";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return  null;
		}
		
		return msg;
		
	}
	
	
	public List<Product> collectSortedProduct(String sort, String type){
		List<Product> productList =new ArrayList<>();

		Connection con=null;
		String sql="select product_id, category_id, product_name, price from product order by "+sort+" "+type;
		PreparedStatement pStmt=null;
		ResultSet rs=null;
		
		
		try{
			con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			pStmt =con.prepareStatement(sql);
			rs=pStmt.executeQuery();
			
			
			while(rs.next()) {
				String productId=rs.getString("product_id");
				String categoryId=rs.getString("category_id");
				String productName=rs.getString("product_name");
				int price=rs.getInt("price");
				Product pB=new Product(productId, categoryId, productName, price);
				productList.add(pB);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}
	
	
	public List<Product> collectProduct(){
		List<Product> productList =new ArrayList<>();

		Connection con=null;
		String sql="select product_id, category_id, product_name, price from product order by category_id asc";
		PreparedStatement pStmt=null;
		ResultSet rs=null;
		
		
		try{
			con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			pStmt =con.prepareStatement(sql);
			rs=pStmt.executeQuery();
			
			
			while(rs.next()) {
				String productId=rs.getString("product_id");
				String categoryId=rs.getString("category_id");
				String productName=rs.getString("product_name");
				int price=rs.getInt("price");
				Product pB=new Product(productId, categoryId, productName, price);
				productList.add(pB);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}
	
	
	public boolean deleteProduct(Product product) {
		
		String sql="delete from product where product_id=?";
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			stmt =conn.prepareStatement(sql);
			stmt.setString(1, product.getProductId());
			
			int result=stmt.executeUpdate();
			if(result!=1) {
				return false;
			}
		}catch(SQLException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}
		
	
	public String updateProduct(Product product) {
		Connection con=null;
		String sql="";
		PreparedStatement pStmt=null;
		ResultSet rs=null;
		
		
		
		try{
			con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			sql="select * from product where product_name=?";
			pStmt=con.prepareStatement(sql);
			pStmt.setString(1, product.getProductName());
			rs=pStmt.executeQuery();
			if(rs.next()) {
				//msg=rs.getString("category_id");
				return "すでに同じ商品名があります。";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return "編集に失敗しました<br>";
		}
		
		
		
		sql="update product set category_id=?, product_name=? ,price=? where product_id=?";

		try {
			con =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			pStmt =con.prepareStatement(sql);
			pStmt.setString(1, product.getCategoryId());
			pStmt.setString(2, product.getProductName());
			pStmt.setInt(3, product.getIntPrice());
			pStmt.setString(4, product.getProductId());
			
			int result=pStmt.executeUpdate();
			if(result!=1) {
				return "編集に失敗しました<br>";
			}
		}catch(SQLException e) {
			e.getStackTrace();
			return "編集に失敗しました<br>";
		}
		return "編集しました<br>";
	}

	/*
	public ArrayList<String> collectProductId() {
		ArrayList<String> li=new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql="select category_id from product";
			
			PreparedStatement pStmt =conn.prepareStatement(sql);

			ResultSet rs=pStmt.executeQuery();
			
			while(rs.next()) {
				String cId=rs.getString("category_id");
				li.add(cId);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return li;
		
	
		
	}*/
	
	
	
	
}
