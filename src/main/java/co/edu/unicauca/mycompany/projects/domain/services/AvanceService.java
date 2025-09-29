package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.IHistorialRepository;

public class AvanceService {

    private final IHistorialRepository historialRepository;

    public AvanceService(IHistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public boolean registrarAvance(int estudianteId, String descripcion) {
        return historialRepository.registrarAccion(estudianteId, "Registro de avance", descripcion);
    }
}