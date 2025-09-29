package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.IUserRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.User;
import co.edu.unicauca.mycompany.projects.infra.state.Cifrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private IUserRepository mockUserRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        mockUserRepository = mock(IUserRepository.class);
        userService = new UserService(mockUserRepository);
    }

    @Test
    public void testRegisterValidUser() {
        User user = new User();
        user.setNombres("Ana");
        user.setApellidos("López");
        user.setEmail("ana@unicauca.edu.co");
        user.setContrasena("Contraseña1!");
        user.setRol("estudiante");

        when(mockUserRepository.save(user)).thenReturn(true);

        boolean result = userService.register(user);
        assertTrue(result);
        verify(mockUserRepository).save(user);
    }

    @Test
    public void testRegisterInvalidEmail() {
        User user = new User();
        user.setEmail("ana@gmail.com"); // No es @unicauca.edu.co
        user.setContrasena("Contraseña1!");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.register(user);
        });
    }

    @Test
    public void testRegisterInvalidPassword() {
        User user = new User();
        user.setEmail("ana@unicauca.edu.co");
        user.setContrasena("contraseña"); // No cumple con los requisitos

        assertThrows(IllegalArgumentException.class, () -> {
            userService.register(user);
        });
    }

    @Test
    public void testAuthenticateSuccess() {
        User user = new User();
        user.setEmail("ana@unicauca.edu.co");
        user.setContrasena(Cifrador.cifrar("Contraseña1!"));

        when(mockUserRepository.findByEmail("ana@unicauca.edu.co")).thenReturn(user);

        User result = userService.authenticate("ana@unicauca.edu.co", "Contraseña1!");
        assertNotNull(result);
        assertEquals("ana@unicauca.edu.co", result.getEmail());
    }

    @Test
    public void testAuthenticateFailure() {
        when(mockUserRepository.findByEmail("ana@unicauca.edu.co")).thenReturn(null);

        User result = userService.authenticate("ana@unicauca.edu.co", "Contraseña1!");
        assertNull(result);
    }
}