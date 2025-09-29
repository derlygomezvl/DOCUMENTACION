package co.edu.unicauca.mycompany.projects.domain.entities;

import java.sql.Timestamp;

public class Anteproyecto {
    private int id;
    private int formatoAId;
    private String documentoRuta;
    private Timestamp fechaEnvio;
    private String estado;

    public Anteproyecto() {}

    public Anteproyecto(int formatoAId, String documentoRuta) {
        this.formatoAId = formatoAId;
        this.documentoRuta = documentoRuta;
        this.estado = "pendiente";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getFormatoAId() { return formatoAId; }
    public void setFormatoAId(int formatoAId) { this.formatoAId = formatoAId; }
    public String getDocumentoRuta() { return documentoRuta; }
    public void setDocumentoRuta(String documentoRuta) { this.documentoRuta = documentoRuta; }
    public Timestamp getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Timestamp fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}