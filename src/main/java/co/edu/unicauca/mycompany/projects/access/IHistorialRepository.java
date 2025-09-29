package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.AccionHistorial;
import java.util.List;

public interface IHistorialRepository {
    boolean registrarAccion(int usuarioId, String accion, String detalle);
    List<AccionHistorial> findByUsuarioId(int usuarioId);
}