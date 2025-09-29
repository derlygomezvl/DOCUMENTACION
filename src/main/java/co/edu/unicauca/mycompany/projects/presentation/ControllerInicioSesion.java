package co.edu.unicauca.mycompany.projects.presentation;

import co.edu.unicauca.mycompany.projects.access.Factory;
import co.edu.unicauca.mycompany.projects.domain.entities.User;
import co.edu.unicauca.mycompany.projects.domain.services.UserService;
import co.edu.unicauca.mycompany.projects.infra.Messages;
import javafx.stage.Stage;

/**
 * Controlador para la vista de inicio de sesión.
 * Gestiona la autenticación de usuarios y la redirección a la vista correspondiente.
 */
public class ControllerInicioSesion {

    private final UserService userService;
    private final GUIinicioSesion view;

    public ControllerInicioSesion(UserService userService, GUIinicioSesion view) {
        this.userService = userService;
        this.view = view;
    }

    /**
     * Maneja el evento de inicio de sesión.
     */
    public void actionButtomLogin(String email, char[] passwordChars) {
        if (email == null || email.trim().isEmpty() || passwordChars == null || passwordChars.length == 0) {
            Messages.mensajeVario("Ambos campos son obligatorios");
            return;
        }

        String password = new String(passwordChars);
        User user = userService.authenticate(email, password);

        if (user == null) {
            Messages.mensajeVario("Usuario o contraseña incorrectos");
            return;
        }

        // Redirigir según el rol del usuario
        redirigirPorRol(user);
    }

    /**
     * Redirige al usuario a la vista correspondiente según su rol.
     */
    private void redirigirPorRol(User user) {
        view.dispose(); // Cierra la ventana de login

        switch (user.getRol()) {
            case "estudiante":
                new GUIEstudianteHome(user).setVisible(true);
                break;
            case "comite":
                new GUIComiteHome(user).setVisible(true);
                break;
            case "secretaria":
                new GUISecretariaHome(user).setVisible(true);
                break;
            case "jefe_departamento":
                new GUIJefeDeptoHome(user).setVisible(true);
                break;
            case "director":
            case "jurado":
                new GUIDocenteHome(user).setVisible(true);
                break;
            case "coordinador":
                new GUICoordinadorHome(user).setVisible(true);
                break;
            default:
                Messages.mensajeVario("Rol no reconocido: " + user.getRol());
                // Volver al login
                new GUIinicioSesion(userService).setVisible(true);
        }
    }

    /**
     * Maneja el evento de registro de usuario (solo para estudiantes).
     */
    public void actionButtomRegister() {
        view.dispose();
        // El registro de usuarios (estudiantes/docentes) es un flujo separado
        new GUIRegistroUsuario(userService).setVisible(true);
    }
}