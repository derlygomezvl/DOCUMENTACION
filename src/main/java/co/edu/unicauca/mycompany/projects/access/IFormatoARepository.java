package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.FormatoA;
import java.util.List;

public interface IFormatoARepository {
    boolean save(FormatoA formatoA);
    FormatoA findById(int id);
    List<FormatoA> findByEstudianteId(int estudianteId);
    boolean updateEstado(int id, String estado, String observaciones);
    List<FormatoA> findAllPendientesComite();
}