package com.sigmed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sigmed.database.ConexionDB;
import com.sigmed.model.Paciente;

public class PacienteDAO {

	public boolean registrarPaciente(Paciente p) {
		boolean registrado = false;

		String sql = "INSERT INTO PACIENTES (DNI, NOMBRE, APELLIDOS, TELEFONO) VALUES (?, ?, ?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, p.getDni());
			pstmt.setString(2, p.getNombre());
			pstmt.setString(3, p.getApellidos());
			pstmt.setString(4, p.getTelefono());

			int filasAfectadas = pstmt.executeUpdate();

			if (filasAfectadas > 0) {
				registrado = true;
			}

		} catch (SQLException e) {
			System.err.println("Error al registrar paciente: " + e.getMessage());
		}

		return registrado;
	}
}