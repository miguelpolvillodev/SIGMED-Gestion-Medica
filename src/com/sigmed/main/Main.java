package com.sigmed.main;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.sigmed.dao.CitaDAO;
import com.sigmed.dao.PacienteDAO;
import com.sigmed.dao.UsuarioDAO;
import com.sigmed.model.Cita;
import com.sigmed.model.Paciente;
import com.sigmed.model.Usuario;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		System.out.println("=== SISTEMA SIGMED v1.0 ===");
		System.out.print("Usuario: ");
		String user = sc.nextLine();

		System.out.print("Contraseña: ");
		String pass = sc.nextLine();

		// Llamamos al DAO para verificar
		Usuario usuarioLogueado = usuarioDAO.login(user, pass);

		if (usuarioLogueado != null) {
			System.out.println("¡Login correcto! Bienvenido " + usuarioLogueado.getUsername());

			int opcion = 0;
			do {
				System.out.println("\n--- MENÚ PRINCIPAL ---");
				System.out.println("1. Registrar nuevo paciente");
				System.out.println("2. Nueva cita");
				System.out.println("3. Salir");
				System.out.print("Elige una opción: ");

				
				opcion = sc.nextInt();
				sc.nextLine(); 

				switch (opcion) {
				case 1:
					System.out.println("\n--- REGISTRO DE PACIENTE ---");
					System.out.print("DNI: ");
					String dni = sc.nextLine();
					System.out.print("Nombre: ");
					String nombre = sc.nextLine();
					System.out.print("Apellidos: ");
					String apellidos = sc.nextLine();
					System.out.print("Teléfono: ");
					String tlf = sc.nextLine();

					
					Paciente nuevoPaciente = new Paciente(dni, nombre, apellidos, tlf);

					
					PacienteDAO pacienteDAO = new PacienteDAO();
					if (pacienteDAO.registrarPaciente(nuevoPaciente)) {
						System.out.println("¡Paciente registrado en Oracle!");
					} else {
						System.out.println("Error al registrar.");
					}
					break;

				case 2: 
					System.out.println("\n--- NUEVA CITA ---");

					
					System.out.print("ID del Médico (mira en tu DB, ej: 1): ");
					int idMed = sc.nextInt();

					System.out.print("ID del Paciente (mira en tu DB, ej: 1): ");
					int idPac = sc.nextInt();
					sc.nextLine(); 

					
					System.out.print("Fecha y Hora (Formato: 2023-12-31 10:30): ");
					String fechaTexto = sc.nextLine();

					// Convertimos Texto -> LocalDateTime
					java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
							.ofPattern("yyyy-MM-dd HH:mm");

					try {
						
						LocalDateTime fechaReal = LocalDateTime.parse(fechaTexto, formatter);

						
						CitaDAO citaDAO = new CitaDAO();

						// Comprobamos si ya existe cita ANTES de pedir el motivo
						if (citaDAO.existeCita(idMed, fechaReal)) {
							System.out.println("ERROR: Ese médico ya tiene una cita a esa hora.");
							System.out.println("Por favor, elige otra hora.");

						} else {
							System.out.print("Motivo de la consulta: ");
							String motivo = sc.nextLine();
						
							Cita nuevaCita = new Cita(idMed, idPac, fechaReal, motivo);

							if (citaDAO.registrarCita(nuevaCita)) {
								System.out.println("¡Cita registrada con éxito!");
							} else {
								System.out.println("Error al guardar en base de datos.");
							}
						}

					} catch (Exception e) {
						System.out.println("Error: El formato de fecha no es válido o falló la conexión.");
					}
					break;
				case 3:
					System.out.println("Cerrando sesión...");
					break;
				default:
					System.out.println("Opción no válida.");
				}
			} while (opcion != 3);

		} else {
			System.out.println("Usuario o contraseña incorrectos.");
		}

		sc.close();
	}
}