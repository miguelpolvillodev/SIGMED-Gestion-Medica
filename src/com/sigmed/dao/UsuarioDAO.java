package com.sigmed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sigmed.database.ConexionDB;
import com.sigmed.model.Usuario;

public class UsuarioDAO {

	// Método para hacer Login
	public Usuario login(String user, String pass) {
		Usuario usuarioEncontrado = null;

		String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ? AND PASSWORD = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, user);
			pstmt.setString(2, pass);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				usuarioEncontrado = new Usuario();
				usuarioEncontrado.setIdUsuario(rs.getInt("ID_USUARIO"));
				usuarioEncontrado.setUsername(rs.getString("USERNAME"));
				usuarioEncontrado.setPassword(rs.getString("PASSWORD"));
				usuarioEncontrado.setRol(rs.getString("ROL"));
			}

		} catch (SQLException e) {
			System.err.println("Error en el login: " + e.getMessage());
		}

		return usuarioEncontrado;
	}
}
