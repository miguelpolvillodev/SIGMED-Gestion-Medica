package com.sigmed.model;

import java.time.LocalDateTime;

public class Cita {
	private int idCita;
	private int idUsuario;
	private int idPaciente;
	private LocalDateTime fechaHora;
	private String motivo;

	public Cita() {
	}

	public Cita(int idUsuario, int idPaciente, LocalDateTime fechaHora, String motivo) {
		this.idUsuario = idUsuario;
		this.idPaciente = idPaciente;
		this.fechaHora = fechaHora;
		this.motivo = motivo;
	}

	// Getters y Setters
	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}