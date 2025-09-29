package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.IFormatoARepository;
import co.edu.unicauca.mycompany.projects.domain.entities.FormatoA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FormatoAServiceTest {

    private IFormatoARepository mockFormatoARepository;
    private FormatoAService formatoAService;

    @BeforeEach
    public void setUp() {
        mockFormatoARepository = mock(IFormatoARepository.class);
        formatoAService = new FormatoAService(mockFormatoARepository);
    }

    @Test
    public void testSubmitValidFormatoA() {
        FormatoA formatoA = new FormatoA();
        formatoA.setEstudianteId(1);
        formatoA.setTitulo("Título del Trabajo");
        formatoA.setModalidad("Investigación");

        when(mockFormatoARepository.save(formatoA)).thenReturn(true);

        boolean result = formatoAService.submit(formatoA);
        assertTrue(result);
        verify(mockFormatoARepository).save(formatoA);
    }

    @Test
    public void testSubmitInvalidTitle() {
        FormatoA formatoA = new FormatoA();
        formatoA.setEstudianteId(1);
        formatoA.setTitulo(""); // Título vacío
        formatoA.setModalidad("Investigación");

        assertThrows(IllegalArgumentException.class, () -> {
            formatoAService.submit(formatoA);
        });
    }

    @Test
    public void testSubmitInvalidModalidad() {
        FormatoA formatoA = new FormatoA();
        formatoA.setEstudianteId(1);
        formatoA.setTitulo("Título del Trabajo");
        formatoA.setModalidad("Otra Modalidad"); // Modalidad inválida

        assertThrows(IllegalArgumentException.class, () -> {
            formatoAService.submit(formatoA);
        });
    }

    @Test
    public void testApproveFormatoA() {
        int formatoAId = 1;
        String observaciones = "Aprobado con observaciones";

        when(mockFormatoARepository.updateEstado(formatoAId, "aprobado", observaciones)).thenReturn(true);

        boolean result = formatoAService.approve(formatoAId, observaciones);
        assertTrue(result);
        verify(mockFormatoARepository).updateEstado(formatoAId, "aprobado", observaciones);
    }

    @Test
    public void testRejectFormatoA() {
        int formatoAId = 1;
        String observaciones = "Rechazado por falta de claridad";

        when(mockFormatoARepository.updateEstado(formatoAId, "rechazado", observaciones)).thenReturn(true);

        boolean result = formatoAService.reject(formatoAId, observaciones);
        assertTrue(result);
        verify(mockFormatoARepository).updateEstado(formatoAId, "rechazado", observaciones);
    }
}