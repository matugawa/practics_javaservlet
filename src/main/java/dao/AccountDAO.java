package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans_entity.Account;

public class AccountDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/product_web_db";
	private final String DB_USER = "mysql";
	private final String DB_PASS = "mysql";

	public boolean createAccount(Account account) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "insert into account(account_id, account_pass) values(?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);

			pStmt.setString(1, account.getAccountId());
			pStmt.setInt(2, account.getIntPass());

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean isExistAccountId(Account account) {

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "select account_id from account where account_id=?";
			PreparedStatement pStmt =con.prepareStatement(sql);
			pStmt.setString(1, account.getAccountId());
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// msg=rs.getString("category_id");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
	public boolean loginByAccount(Account account) {
		
		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			
	
			

			String sql="select account_id from account where account_id=? and account_pass=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			pStmt.setString(1, account.getAccountId());
			pStmt.setInt(2, account.getIntPass());
			
			ResultSet rs=pStmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
		
	}

}
