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
        // Mock del repositorio para pruebas unitarias
        mockUserRepository = mock(IUserRepository.class);
        userService = new UserService(mockUserRepository);
    }

    // === PRUEBAS DE REGISTRO ===

    @Test
    public void testRegisterValidUser() {
        // Arrange
        User user = new User();
        user.setNombres("Ana");
        user.setApellidos("López");
        user.setEmail("ana@unicauca.edu.co");
        user.setContrasena("Contraseña1!");
        user.setRol("estudiante");

        when(mockUserRepository.save(user)).thenReturn(true);

        // Act
        boolean result = userService.register(user);

        // Assert
        assertTrue(result);
        verify(mockUserRepository).save(user);
        // Verificar que la contraseña fue cifrada
        assertNotEquals("Contraseña1!", user.getContrasena());
        assertTrue(Cifrador.verificar("Contraseña1!", user.getContrasena()));
    }

    @Test
    public void testRegisterInvalidEmail_NotUnicauca() {
        User user = new User();
        user.setEmail("ana@gmail.com"); 
        user.setContrasena("Contraseña1!");

        // Debe lanzar excepción
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> userService.register(user)
        );
        assertEquals("El correo debe ser @unicauca.edu.co", exception.getMessage());
    }

    @Test
    public void testRegisterInvalidPassword_TooShort() {
        User user = new User();
        user.setEmail("ana@unicauca.edu.co");
        user.setContrasena("123"); 

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> userService.register(user)
        );
        assertTrue(exception.getMessage().contains("contraseña debe tener"));
    }

    @Test
    public void testRegisterInvalidPassword_NoUppercase() {
        User user = new User();
        user.setEmail("ana@unicauca.edu.co");
        user.setContrasena("contraseña1!"); 

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> userService.register(user)
        );
        assertTrue(exception.getMessage().contains("contraseña debe tener"));
    }

    @Test
    public void testRegisterInvalidPassword_NoDigit() {
        User user = new User();
        user.setEmail("ana@unicauca.edu.co");
        user.setContrasena("Contraseña!"); 

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> userService.register(user)
        );
        assertTrue(exception.getMessage().contains("contraseña debe tener"));
    }

    @Test
    public void testRegisterInvalidPassword_NoSpecialChar() {
        User user = new User();
        user.setEmail("ana@unicauca.edu.co");
        user.setContrasena("Contraseña1"); 

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> userService.register(user)
        );
        assertTrue(exception.getMessage().contains("contraseña debe tener"));
    }

    // === PRUEBAS DE AUTENTICACIÓN ===

    @Test
    public void testAuthenticateSuccess() {
        // Arrange
        String email = "ana@unicauca.edu.co";
        String plainPassword = "Contraseña1!";
        String hashedPassword = Cifrador.cifrar(plainPassword);

        User user = new User();
        user.setEmail(email);
        user.setContrasena(hashedPassword);

        when(mockUserRepository.findByEmail(email)).thenReturn(user);

        // Act
        User result = userService.authenticate(email, plainPassword);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testAuthenticateFailure_WrongPassword() {
        // Arrange
        String email = "ana@unicauca.edu.co";
        String wrongPassword = "Contraseña2!";

        User user = new User();
        user.setEmail(email);
        user.setContrasena(Cifrador.cifrar("Contraseña1!"));

        when(mockUserRepository.findByEmail(email)).thenReturn(user);

        // Act
        User result = userService.authenticate(email, wrongPassword);

        // Assert
        assertNull(result);
    }

    @Test
    public void testAuthenticateFailure_UserNotFound() {
        // Arrange
        String email = "noexiste@unicauca.edu.co";

        when(mockUserRepository.findByEmail(email)).thenReturn(null);

        // Act
        User result = userService.authenticate(email, "Contraseña1!");

        // Assert
        assertNull(result);
    }
}