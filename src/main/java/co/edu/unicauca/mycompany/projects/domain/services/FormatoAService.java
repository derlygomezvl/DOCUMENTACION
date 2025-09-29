package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.IFormatoARepository;
import co.edu.unicauca.mycompany.projects.domain.entities.FormatoA;

public class FormatoAService {

    private final IFormatoARepository formatoARepository;

    public FormatoAService(IFormatoARepository formatoARepository) {
        this.formatoARepository = formatoARepository;
    }

    public boolean submit(FormatoA formatoA) {
        if (formatoA.getTitulo() == null || formatoA.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (!"Práctica Profesional".equals(formatoA.getModalidad()) && !"Investigación".equals(formatoA.getModalidad())) {
            throw new IllegalArgumentException("Modalidad debe ser 'Práctica Profesional' o 'Investigación'");
        }
        return formatoARepository.save(formatoA);
    }

    public boolean approve(int formatoAId, String observaciones) {
        return formatoARepository.updateEstado(formatoAId, "aprobado", observaciones);
    }

    public boolean reject(int formatoAId, String observaciones) {
        return formatoARepository.updateEstado(formatoAId, "rechazado", observaciones);
    }
}