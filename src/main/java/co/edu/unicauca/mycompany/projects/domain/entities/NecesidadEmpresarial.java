package co.edu.unicauca.mycompany.projects.domain.entities;

import java.sql.Timestamp;

public class NecesidadEmpresarial {
    private int id;
    private String empresa;
    private String titulo;
    private String descripcion;
    private String contacto;
    private Timestamp fechaRegistro;
    private boolean activa;

    public NecesidadEmpresarial() {}

    public NecesidadEmpresarial(String empresa, String titulo, String descripcion, String contacto) {
        this.empresa = empresa;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contacto = contacto;
        this.activa = true;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}