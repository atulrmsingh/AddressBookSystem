package com.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactBookDao {
	public void addContactToDb(ContactBook cb) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook", "root", "root");

			String Query = "insert into contactbook(firstname,lastname,address,city,state,zip,email,mobile) values(?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = con.prepareStatement(Query);
			stmt.setString(1, cb.getFirstName());
			stmt.setString(2, cb.getLastName());
			stmt.setString(3, cb.getAddress());
			stmt.setString(4, cb.getCity());
			stmt.setString(5, cb.getState());
			stmt.setInt(6, cb.getZip());
			stmt.setString(7, cb.getEmail());
			stmt.setLong(8, cb.getPhoneNumber());
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public List<ContactBook> getAllContact() {
		List<ContactBook> list = new ArrayList<>();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook", "root", "root");
			String Query = "select * from contactbook;";
			PreparedStatement stmt1 = con.prepareStatement(Query);
			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				ContactBook cb = new ContactBook();
				cb.setId(rs.getInt(1));
				cb.setFirstName(rs.getString(2));
				cb.setLastName(rs.getString(3));
				cb.setAddress(rs.getString(4));
				cb.setCity(rs.getString(5));
				cb.setState(rs.getString(6));
				cb.setZip(rs.getInt(7));
				cb.setEmail(rs.getString(8));
				cb.setPhoneNumber(rs.getLong(9));
				list.add(cb);
			}
			stmt1.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;
	}

}
