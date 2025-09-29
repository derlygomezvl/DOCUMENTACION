package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.ISustentacionRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Sustentacion;

public class SustentacionService {

    private final ISustentacionRepository sustentacionRepository;

    public SustentacionService(ISustentacionRepository sustentacionRepository) {
        this.sustentacionRepository = sustentacionRepository;
    }

    public boolean programar(Sustentacion sustentacion) {
        return sustentacionRepository.programar(sustentacion);
    }

    public boolean asignarJurado(int sustentacionId, int juradoId, String rol) {
        return sustentacionRepository.asignarJurado(sustentacionId, juradoId, rol);
    }

    public boolean registrarActa(int sustentacionId, int j1, int j2, String observaciones) {
        boolean aprobado = (j1 >= 3 && j2 >= 3);
        return sustentacionRepository.registrarActa(sustentacionId, j1, j2, observaciones, aprobado);
    }
}