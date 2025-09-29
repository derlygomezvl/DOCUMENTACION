package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.User;
import java.util.List;

public interface IUserRepository {
    boolean save(User user);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByRole(String rol);
    User findById(int id);
}