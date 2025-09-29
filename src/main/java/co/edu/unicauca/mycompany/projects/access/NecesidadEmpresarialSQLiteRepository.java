package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.NecesidadEmpresarial;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NecesidadEmpresarialSQLiteRepository implements INecesidadEmpresarialRepository {

    private final IConnectionDB connection;

    public NecesidadEmpresarialSQLiteRepository(IConnectionDB connection) {
        this.connection = connection;
    }

    public boolean save(NecesidadEmpresarial necesidad) {
        String sql = "INSERT INTO necesidades_empresariales (empresa, titulo, descripcion, contacto, activa) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setString(1, necesidad.getEmpresa());
            stmt.setString(2, necesidad.getTitulo());
            stmt.setString(3, necesidad.getDescripcion());
            stmt.setString(4, necesidad.getContacto());
            stmt.setBoolean(5, true);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }


    public List<NecesidadEmpresarial> findAllActive() {
        List<NecesidadEmpresarial> necesidades = new ArrayList<>();
        String sql = "SELECT * FROM necesidades_empresariales WHERE activa = 1 ORDER BY fecha_registro DESC";
        try (PreparedStatement stmt = connection.getStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NecesidadEmpresarial n = new NecesidadEmpresarial();
                n.setId(rs.getInt("id"));
                n.setEmpresa(rs.getString("empresa"));
                n.setTitulo(rs.getString("titulo"));
                n.setDescripcion(rs.getString("descripcion"));
                n.setContacto(rs.getString("contacto"));
                necesidades.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return necesidades;
    }

    
    public boolean deactivate(int id) {
        String sql = "UPDATE necesidades_empresariales SET activa = 0 WHERE id = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }
}