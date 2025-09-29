package co.edu.unicauca.mycompany.projects.domain.entities;

import java.sql.Timestamp;

public class ActaSustentacion {
    private int id;
    private int sustentacionId;
    private Integer jurado1Calificacion;
    private Integer jurado2Calificacion;
    private String observaciones;
    private Timestamp fechaActa;
    private boolean aprobado;

    public ActaSustentacion() {}

    public ActaSustentacion(int sustentacionId, int j1, int j2, String observaciones) {
        this.sustentacionId = sustentacionId;
        this.jurado1Calificacion = j1;
        this.jurado2Calificacion = j2;
        this.observaciones = observaciones;
        this.aprobado = (j1 >= 3 && j2 >= 3);
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getSustentacionId() { return sustentacionId; }
    public void setSustentacionId(int sustentacionId) { this.sustentacionId = sustentacionId; }
    public Integer getJurado1Calificacion() { return jurado1Calificacion; }
    public void setJurado1Calificacion(Integer jurado1Calificacion) { this.jurado1Calificacion = jurado1Calificacion; }
    public Integer getJurado2Calificacion() { return jurado2Calificacion; }
    public void setJurado2Calificacion(Integer jurado2Calificacion) { this.jurado2Calificacion = jurado2Calificacion; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public Timestamp getFechaActa() { return fechaActa; }
    public void setFechaActa(Timestamp fechaActa) { this.fechaActa = fechaActa; }
    public boolean isAprobado() { return aprobado; }
    public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }
}