package co.edu.unicauca.mycompany.projects.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        user.setId(1);
        user.setNombres("Juan");
        user.setApellidos("Pérez");
        user.setCelular("3001234567");
        user.setPrograma("Ingeniería de Sistemas");
        user.setRol("estudiante");
        user.setEmail("juan@unicauca.edu.co");
        user.setContrasena("Contraseña1!");

        assertEquals(1, user.getId());
        assertEquals("Juan", user.getNombres());
        assertEquals("Pérez", user.getApellidos());
        assertEquals("3001234567", user.getCelular());
        assertEquals("Ingeniería de Sistemas", user.getPrograma());
        assertEquals("estudiante", user.getRol());
        assertEquals("juan@unicauca.edu.co", user.getEmail());
        assertEquals("Contraseña1!", user.getContrasena());
    }
}