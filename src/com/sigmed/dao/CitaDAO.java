package com.sigmed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; // Tipo para Oracle
import java.time.LocalDateTime;

import com.sigmed.database.ConexionDB;
import com.sigmed.model.Cita;

public class CitaDAO {

	public boolean registrarCita(Cita c) {
		boolean guardado = false;

		String sql = "INSERT INTO CITAS (ID_USUARIO, ID_PACIENTE, FECHA_HORA, MOTIVO) VALUES (?, ?, ?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, c.getIdUsuario());
			pstmt.setInt(2, c.getIdPaciente());

			// Convertir de LocalDateTime (Java) a Timestamp (SQL)
			pstmt.setTimestamp(3, Timestamp.valueOf(c.getFechaHora()));

			pstmt.setString(4, c.getMotivo());

			int filas = pstmt.executeUpdate();
			if (filas > 0)
				guardado = true;

		} catch (SQLException e) {
			System.err.println("Error al reservar cita: " + e.getMessage());
		}

		return guardado;
	}

	// Método auxiliar para comprobar duplicados
	public boolean existeCita(int idMedico, LocalDateTime fechaHora) {
		boolean existe = false;

		String sql = "SELECT COUNT(*) FROM CITAS WHERE ID_USUARIO = ? AND FECHA_HORA = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, idMedico);
			pstmt.setTimestamp(2, Timestamp.valueOf(fechaHora));

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				int cantidad = rs.getInt(1);
				if (cantidad > 0) {
					existe = true;
				}
			}

		} catch (SQLException e) {
			System.err.println("Error comprobando citas: " + e.getMessage());
		}

		return existe;
	}
}