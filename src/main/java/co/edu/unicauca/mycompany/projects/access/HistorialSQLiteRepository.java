package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.AccionHistorial;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HistorialSQLiteRepository implements IHistorialRepository {

    private final IConnectionDB connection;

    public HistorialSQLiteRepository(IConnectionDB connection) {
        this.connection = connection;
    }

    @Override
    public boolean registrarAccion(int usuarioId, String accion, String detalle) {
        String sql = "INSERT INTO historial (usuario_id, accion, detalle) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, accion);
            stmt.setString(3, detalle);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<AccionHistorial> findByUsuarioId(int usuarioId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}