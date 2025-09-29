package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.NecesidadEmpresarial;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para el repositorio
 * de necesidades empresariales (Requisito 17).
 */
public interface INecesidadEmpresarialRepository {

    /**
     * Registra una nueva necesidad empresarial en el sistema.
     *
     * @param necesidad La necesidad a registrar.
     * @return true si se registró exitosamente, false en caso contrario.
     */
    boolean save(NecesidadEmpresarial necesidad);

    /**
     * Obtiene todas las necesidades empresariales activas (no desactivadas).
     *
     * @return Lista de necesidades activas, ordenadas por fecha de registro descendente.
     */
    List<NecesidadEmpresarial> findAllActive();

    /**
     * Desactiva una necesidad empresarial (marca como inactiva sin eliminarla).
     *
     * @param id Identificador único de la necesidad.
     * @return true si se desactivó exitosamente, false en caso contrario.
     */
    boolean deactivate(int id);
}