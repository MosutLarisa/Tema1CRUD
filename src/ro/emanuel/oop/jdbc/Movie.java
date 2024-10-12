package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Movie {

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
		
		String mTitle = "Heart of Stone";
		int mRelease_year = 2023;
		String mGenre = "Action/Thriller";
		
		//insert
		String sqlInsert = "INSERT INTO movie(title, release_year, genre) VALUES(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sqlInsert);
		ps.setString(1, mTitle);
		ps.setInt(2, mRelease_year);
		ps.setString(3, mGenre);
		int rowsAff = ps.executeUpdate();
		System.out.println(rowsAff);
		
		//update
		String update = "UPDATE movie SET genre = ? WHERE title = ? ";
		PreparedStatement psu = conn.prepareStatement(update);
		psu.setString(1, mGenre);
		psu.setString(2, mTitle);
		psu.executeUpdate();
		
		//delete
		String deleteValue = "DELETE FROM movie WHERE release_year =?";
		PreparedStatement psd = conn.prepareStatement(deleteValue);
		psd.setInt(1, 2024);
		int result = psd.executeUpdate();
		System.out.println(result);
		
		ResultSet rs = stmt.executeQuery("select * from movie");
		while(rs.next()) {
			String title = rs.getString("title");
			int release_year = rs.getInt("release_year");
			String genre = rs.getString("genre");
			
			System.out.println(title + "|" + release_year + "|" + genre);
		}
		
		conn.close();	
	}
}
