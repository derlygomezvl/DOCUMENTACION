package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Anteproyecto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnteproyectoSQLiteRepository implements IAnteproyectoRepository {

    private final IConnectionDB connection;

    public AnteproyectoSQLiteRepository(IConnectionDB connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Anteproyecto anteproyecto) {
        String sql = "INSERT INTO anteproyectos (formato_a_id, documento_ruta, estado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, anteproyecto.getFormatoAId());
            stmt.setString(2, anteproyecto.getDocumentoRuta());
            stmt.setString(3, anteproyecto.getEstado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public Anteproyecto findById(int id) {
        String sql = "SELECT * FROM anteproyectos WHERE id = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Anteproyecto a = new Anteproyecto();
                a.setId(rs.getInt("id"));
                a.setFormatoAId(rs.getInt("formato_a_id"));
                a.setDocumentoRuta(rs.getString("documento_ruta"));
                a.setEstado(rs.getString("estado"));
                a.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    @Override
    public List<Anteproyecto> findByFormatoAId(int formatoAId) {
        List<Anteproyecto> anteproyectos = new ArrayList<>();
        String sql = "SELECT * FROM anteproyectos WHERE formato_a_id = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, formatoAId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Anteproyecto a = new Anteproyecto();
                a.setId(rs.getInt("id"));
                a.setFormatoAId(rs.getInt("formato_a_id"));
                a.setDocumentoRuta(rs.getString("documento_ruta"));
                a.setEstado(rs.getString("estado"));
                a.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                anteproyectos.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return anteproyectos;
    }

    @Override
    public boolean updateEstado(int id, String estado) {
        String sql = "UPDATE anteproyectos SET estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setString(1, estado);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<Anteproyecto> findAllPendientesEvaluacion() {
        List<Anteproyecto> anteproyectos = new ArrayList<>();
        String sql = "SELECT * FROM anteproyectos WHERE estado = 'pendiente'";
        try (PreparedStatement stmt = connection.getStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Anteproyecto a = new Anteproyecto();
                a.setId(rs.getInt("id"));
                a.setFormatoAId(rs.getInt("formato_a_id"));
                a.setDocumentoRuta(rs.getString("documento_ruta"));
                a.setEstado(rs.getString("estado"));
                a.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                anteproyectos.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return anteproyectos;
    }
}