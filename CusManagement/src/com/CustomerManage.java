package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class CustomerManage {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/electricitypower?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProject(String cusName, String cusAddress, String cusNIC, String cusEmail, String cusPno) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customermanage(`cusID`,`cusName`,`cusAddress`,`cusNIC`,`cusEmail`,`cusPno`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cusName);
			preparedStmt.setString(3, cusAddress);
			preparedStmt.setString(4, cusNIC);
			preparedStmt.setString(5, cusEmail);
			preparedStmt.setString(6, cusPno);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the project.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\'1\'><tr><th>Customer Name</th><th>Address</th><th>NIC</th><th>Email</th><th>Contact No</th><th>Update</th><th>Delete</th></tr>";
			String query = "select * from customermanage";

			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String cusID = Integer.toString(rs.getInt("cusID"));
				String cusName = rs.getString("cusName");
				String cusAddress = rs.getString("cusAddress");
				String cusNIC = rs.getString("cusNIC");
				String cusEmail = rs.getString("cusEmail");
				String cusPno = rs.getString("cusPno");

				// Add into the html table
				output += "<tr><td><input id=\'hidProjectIDUpdate\' name=\'hidProjectIDUpdate\' type=\'hidden\' value=\'"
						+ cusID + "'>" + cusName + "</td>";
				output += "<td>" + cusAddress + "</td>";
				output += "<td>" + cusNIC + "</td>";
				output += "<td>" + cusEmail + "</td>";
				output += "<td>" + cusPno + "</td>";

				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-cusid='"
						+ cusID + "'>" + "</td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateProject(String cusID, String cusName, String cusAddress, String cusNIC, String cusEmail, String cusPno) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE customermanage SET cusName=?,cusAddress=?,cusNIC=?,cusEmail=?,cusPno=?" + "WHERE cusID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, cusName);
			preparedStmt.setString(2, cusAddress);
			preparedStmt.setString(3, cusNIC);
			preparedStmt.setString(4, cusEmail);
			preparedStmt.setString(5, cusPno);
			preparedStmt.setInt(6, Integer.parseInt(cusID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteProject(String cusID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from customermanage where cusID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cusID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "Error while deleting the project.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
