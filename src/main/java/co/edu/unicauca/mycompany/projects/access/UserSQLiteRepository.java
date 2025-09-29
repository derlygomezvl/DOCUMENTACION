package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserSQLiteRepository implements IUserRepository {

    private final IConnectionDB connection;

    public UserSQLiteRepository(IConnectionDB connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(User user) {
        String sql = "INSERT INTO usuarios (nombres, apellidos, celular, programa, rol, email, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setString(1, user.getNombres());
            stmt.setString(2, user.getApellidos());
            stmt.setString(3, user.getCelular());
            stmt.setString(4, user.getPrograma());
            stmt.setString(5, user.getRol());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getContrasena());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setCelular(rs.getString("celular"));
                u.setPrograma(rs.getString("programa"));
                u.setRol(rs.getString("rol"));
                u.setEmail(rs.getString("email"));
                u.setContrasena(rs.getString("contrasena"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return findByEmail(email) != null;
    }

    @Override
    public List<User> findByRole(String rol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}