package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.IHistorialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HistorialServiceTest {

    private IHistorialRepository mockHistorialRepository;
    private HistorialService historialService;

    @BeforeEach
    public void setUp() {
        mockHistorialRepository = mock(IHistorialRepository.class);
        historialService = new HistorialService(mockHistorialRepository);
    }

    @Test
    public void testRegistrarAccion() {
        int usuarioId = 1;
        String accion = "Registro de avance";
        String detalle = "Avance semanal del trabajo de grado";

        historialService.registrarAccion(usuarioId, accion, detalle);

        verify(mockHistorialRepository).registrarAccion(usuarioId, accion, detalle);
    }
}