package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans_entity.Product;

public class ShopDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/product_web_db";
	private final String DB_USER = "mysql";
	private final String DB_PASS = "mysql";

	public List<Product> collectProductByProductId(String[] str){
		List<Product> reLi =new ArrayList<>();

		Connection con=null;
		String sql="select product_id, category_id, product_name, price from product where product_id=?";
		PreparedStatement pStmt=null;
		ResultSet rs=null;
		
		for(String st: str) {
			try{
				con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				pStmt =con.prepareStatement(sql);
				pStmt.setString(1, st);
				rs=pStmt.executeQuery();
				
				while(rs.next()) {
					String productId=rs.getString("product_id");
					String categoryId=rs.getString("category_id");
					String productName=rs.getString("product_name");
					int price=rs.getInt("price");
					Product pB=new Product(productId, categoryId, productName, price);
					reLi.add(pB);
				}
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return reLi;
	}
}
