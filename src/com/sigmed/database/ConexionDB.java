package com.sigmed.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	// Configuración de la conexión a ORACLE
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "C##SIGMED";
	private static final String PASS = "admin"; 

	public static Connection conectar() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("¡Conexión establecida con éxito!");

		} catch (ClassNotFoundException e) {
			System.err.println("Error: No se encontró el Driver JDBC. ¿Añadiste el .jar al proyecto?");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Error de SQL: No se pudo conectar a Oracle.");
			e.printStackTrace();
		}
		return conn;
	}
}