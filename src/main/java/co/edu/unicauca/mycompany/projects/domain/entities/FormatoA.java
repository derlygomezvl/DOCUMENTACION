package co.edu.unicauca.mycompany.projects.domain.entities;

import java.sql.Timestamp;

public class FormatoA {
    private int id;
    private int estudianteId;
    private String titulo;
    private String modalidad;
    private Timestamp fechaEnvio;
    private String estado;
    private String observacionesComite;

    public FormatoA() {}

    public FormatoA(int estudianteId, String titulo, String modalidad) {
        this.estudianteId = estudianteId;
        this.titulo = titulo;
        this.modalidad = modalidad;
        this.estado = "pendiente";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getEstudianteId() { return estudianteId; }
    public void setEstudianteId(int estudianteId) { this.estudianteId = estudianteId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }
    public Timestamp getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Timestamp fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservacionesComite() { return observacionesComite; }
    public void setObservacionesComite(String observacionesComite) { this.observacionesComite = observacionesComite; }
}