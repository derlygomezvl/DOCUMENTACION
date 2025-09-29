package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Anteproyecto;
import java.util.List;

public interface IAnteproyectoRepository {
    boolean save(Anteproyecto anteproyecto);
    Anteproyecto findById(int id);
    List<Anteproyecto> findByFormatoAId(int formatoAId);
    boolean updateEstado(int id, String estado);
    List<Anteproyecto> findAllPendientesEvaluacion();
}