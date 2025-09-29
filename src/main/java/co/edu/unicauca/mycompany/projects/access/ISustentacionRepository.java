package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Sustentacion;
import java.util.List;

public interface ISustentacionRepository {
    boolean programar(Sustentacion sustentacion);
    Sustentacion findById(int id);
    List<Sustentacion> findByAnteproyectoId(int anteproyectoId);
    boolean asignarJurado(int sustentacionId, int juradoId, String rol);
    boolean registrarActa(int sustentacionId, int califJurado1, int califJurado2, String observaciones, boolean aprobado);
}