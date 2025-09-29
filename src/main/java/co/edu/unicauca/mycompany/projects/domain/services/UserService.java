package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.IUserRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.User;
import co.edu.unicauca.mycompany.projects.infra.state.Validador;
import co.edu.unicauca.mycompany.projects.infra.state.Cifrador;

public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(User user) {
        if (!Validador.esEmailUnicauca(user.getEmail())) {
            throw new IllegalArgumentException("El correo debe ser @unicauca.edu.co");
        }
        if (!Validador.esContrasenaSegura(user.getContrasena())) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres, una mayúscula, un número y un carácter especial");
        }
        user.setContrasena(Cifrador.cifrar(user.getContrasena()));
        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && Cifrador.verificar(password, user.getContrasena())) {
            return user;
        }
        return null;
    }
}