package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.IHistorialRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.AccionHistorial;
import java.util.List;

public class HistorialService {

    private final IHistorialRepository historialRepository;

    public HistorialService(IHistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public void registrarAccion(int usuarioId, String accion, String detalle) {
        historialRepository.registrarAccion(usuarioId, accion, detalle);
    }
}