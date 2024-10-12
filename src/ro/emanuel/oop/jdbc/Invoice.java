package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Invoice {

	public static void main(String[] args) throws SQLException {

		Properties connectionProps = new Properties();
		connectionProps.put("user", "root");
		connectionProps.put("password","");
		
		//open connection
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/oop2024",
				connectionProps);
		
		//open statement
		Statement stmt = conn.createStatement();
		
		int iNumber = 18;
		String iClient_name = "Laura Dobre";
		double iTotal_amount = 2560;
		
		//insert
		String sqlInsert = "INSERT INTO invoice(number, client_name, total_amount) VALUES(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sqlInsert);
		ps.setInt(1, iNumber);
		ps.setString(2, iClient_name);
		ps.setDouble(3, iTotal_amount);
		int rowsAff = ps.executeUpdate();
		System.out.println(rowsAff);
		
		//update
		String update = "UPDATE invoice SET total_amount = ? WHERE number = ? ";
		PreparedStatement psu = conn.prepareStatement(update);
		psu.setDouble(1, iTotal_amount);
		psu.setInt(2, iNumber);
		psu.executeUpdate();
		
		//delete
		String deleteValue = "DELETE FROM invoice WHERE number >=?";
		PreparedStatement psd = conn.prepareStatement(deleteValue);
		psd.setInt(1, 25);
		int result = psd.executeUpdate();
		System.out.println(result);
		
		
		ResultSet rs = stmt.executeQuery("select * from invoice");
		while(rs.next()) {
			int number = rs.getInt("number");
			String client_name = rs.getString("client_name");
			double total_amount = rs.getDouble("total_amount");
			
			System.out.println(number + "|" + client_name + "|" + total_amount);
		}
		
		conn.close();	
		
	}

}