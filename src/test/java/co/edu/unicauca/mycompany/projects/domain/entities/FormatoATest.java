package co.edu.unicauca.mycompany.projects.domain.entities;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class FormatoATest {

    @Test
    public void testFormatoASettersAndGetters() {
        FormatoA formatoA = new FormatoA();
        formatoA.setId(1);
        formatoA.setEstudianteId(2);
        formatoA.setTitulo("Título del Trabajo");
        formatoA.setModalidad("Investigación");
        formatoA.setEstado("pendiente");
        formatoA.setObservacionesComite("Observaciones del comité");
        formatoA.setFechaEnvio(new Timestamp(System.currentTimeMillis()));

        assertEquals(1, formatoA.getId());
        assertEquals(2, formatoA.getEstudianteId());
        assertEquals("Título del Trabajo", formatoA.getTitulo());
        assertEquals("Investigación", formatoA.getModalidad());
        assertEquals("pendiente", formatoA.getEstado());
        assertEquals("Observaciones del comité", formatoA.getObservacionesComite());
        assertNotNull(formatoA.getFechaEnvio());
    }
}