package co.edu.unicauca.mycompany.projects.main;

import co.edu.unicauca.mycompany.projects.access.Factory;
import co.edu.unicauca.mycompany.projects.domain.services.UserService;
import co.edu.unicauca.mycompany.projects.presentation.GUIinicioSesion;

public class Main {

    public static void main(String[] args) {
        // 1. Obtener la fábrica (Singleton)
        Factory factory = Factory.getInstance();
        
        // 2. Crear el servicio de usuario con su repositorio (inyección de dependencias)
        UserService userService = new UserService(factory.getUserRepository());
        
        // 3. Iniciar la interfaz de login
        GUIinicioSesion loginView = new GUIinicioSesion(userService);
        loginView.setVisible(true);
    }
}