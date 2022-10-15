package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans_entity.Category;



public class CategoryDAO {
	
	private final String JDBC_URL=
			"jdbc:mysql://localhost:3306/product_web_db";
	private final String DB_USER="mysql";
	private final String DB_PASS="mysql";
	
	public List<Category> collectSortedCategory(String sort, String type) {
		Connection con=null;
		String sql="";
		PreparedStatement pStmt=null;
		ResultSet rs=null;
		List<Category> reCa=new ArrayList<>();
		try {
			con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			sql="select category_id, category_name from category order by "+sort+" "+type;
			
			pStmt=con.prepareStatement(sql);
	
			rs=pStmt.executeQuery();
			while(rs.next()) {

				String categoryId=rs.getString("category_id");
				String categoryName=rs.getString("category_name");
				Category cat=new Category(categoryId, categoryName);
				reCa.add(cat);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reCa;
	}
	
	
	
	public String isExistCategory(Category category) {

		
		Connection con=null;
		PreparedStatement pStmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			con =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			sql="select category_id from category where category_id=?";
			pStmt=con.prepareStatement(sql);
			pStmt.setString(1, category.getCategoryId());
			rs=pStmt.executeQuery();
			if(rs.next()) {
				//msg=rs.getString("category_id");
				return "既存のカテゴリ";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return (String) null;
		}
		
		try {
			con =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			sql="select category_name from category where category_name=?";
			pStmt=con.prepareStatement(sql);
			pStmt.setString(1, category.getCategoryName());
			
			rs=pStmt.executeQuery();
			
			if(rs.next()) {
				return "既存のカテゴリ名前";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return  null;
		}
		
		return "";
	}

	
	public boolean createCategory(Category category) {
		
		Connection con=null;
		PreparedStatement pStmt=null;
		String sql="insert into category(category_id, category_name) values(?, ?)";
		try{
			
			con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			pStmt=con.prepareStatement(sql);
			
			pStmt.setString(1, category.getCategoryId());
			pStmt.setString(2, category.getCategoryName());

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
	
	public List<Category> collectCategory(){
		List<Category> categoryList =new ArrayList<>();
		Connection con=null;
		PreparedStatement pStmt=null;
		String sql="select category_id, category_name from category order by category_id asc";
		ResultSet rs=null;
		try{
			
			con=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			pStmt =con.prepareStatement(sql);
			rs=pStmt.executeQuery();
			
			while(rs.next()) {

				String categoryId=rs.getString("category_id");
				String categoryName=rs.getString("category_name");
				Category cat=new Category(categoryId, categoryName);
				categoryList.add(cat);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return categoryList;
	}
	
	public boolean deleteCategory(Category category) {
		String sql="delete from category where category_id=?";
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			stmt =conn.prepareStatement(sql);
			stmt.setString(1, category.getCategoryId());
			
			
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
	
	
	public boolean updateCategory(Category category) {
		String sql="update category set category_name=? where category_id=?";
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			stmt =conn.prepareStatement(sql);
			stmt.setString(1, category.getCategoryName());
			stmt.setString(2, category.getCategoryId());
		
			
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
	/*
	public ArrayList<String> collectCategoryId() {
		ArrayList<String> li=new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql="select category_id from category";
			
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


