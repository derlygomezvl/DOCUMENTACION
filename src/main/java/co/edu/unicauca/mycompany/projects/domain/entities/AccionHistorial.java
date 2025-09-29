package co.edu.unicauca.mycompany.projects.domain.entities;

import java.sql.Timestamp;

public class AccionHistorial {
    private int id;
    private int usuarioId;
    private String accion;
    private String detalle;
    private Timestamp fecha;

    public AccionHistorial() {}

    public AccionHistorial(int usuarioId, String accion, String detalle) {
        this.usuarioId = usuarioId;
        this.accion = accion;
        this.detalle = detalle;
        this.fecha = new Timestamp(System.currentTimeMillis());
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
}