package co.edu.unicauca.mycompany.projects.domain.entities;

import java.sql.Timestamp;

public class Sustentacion {
    private int id;
    private int anteproyectoId;
    private Timestamp fechaProgramada;
    private String lugar;
    private String estado;

    public Sustentacion() {}

    public Sustentacion(int anteproyectoId, Timestamp fechaProgramada, String lugar) {
        this.anteproyectoId = anteproyectoId;
        this.fechaProgramada = fechaProgramada;
        this.lugar = lugar;
        this.estado = "programada";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAnteproyectoId() { return anteproyectoId; }
    public void setAnteproyectoId(int anteproyectoId) { this.anteproyectoId = anteproyectoId; }
    public Timestamp getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(Timestamp fechaProgramada) { this.fechaProgramada = fechaProgramada; }
    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}