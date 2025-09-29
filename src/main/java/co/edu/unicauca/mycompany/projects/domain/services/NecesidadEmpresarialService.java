package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.INecesidadEmpresarialRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.NecesidadEmpresarial;

public class NecesidadEmpresarialService {

    private final INecesidadEmpresarialRepository companyRepository;

    public NecesidadEmpresarialService(INecesidadEmpresarialRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public boolean registrar(NecesidadEmpresarial necesidad) {
        return companyRepository.save(necesidad);
    }

    public boolean desactivar(int id) {
        return companyRepository.deactivate(id);
    }
}