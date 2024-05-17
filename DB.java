package ru.ifmo.se.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DB {

	private static String url = "jdbc:postgresql://pg/studs";
	private static String username = "s408564";
	private static String password = "etuOYTJDbwYvoguQ";

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, username,password);
	}
}
