package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.IAnteproyectoRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Anteproyecto;

public class AnteproyectoService {

    private final IAnteproyectoRepository anteproyectoRepository;

    public AnteproyectoService(IAnteproyectoRepository anteproyectoRepository) {
        this.anteproyectoRepository = anteproyectoRepository;
    }

    public boolean submit(Anteproyecto anteproyecto) {
        if (anteproyecto.getDocumentoRuta() == null || anteproyecto.getDocumentoRuta().trim().isEmpty()) {
            throw new IllegalArgumentException("Debe subir un documento PDF");
        }
        return anteproyectoRepository.save(anteproyecto);
    }
}