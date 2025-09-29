package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Sustentacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SustentacionSQLiteRepository implements ISustentacionRepository {

    private final IConnectionDB connection;

    public SustentacionSQLiteRepository(IConnectionDB connection) {
        this.connection = connection;
    }

    @Override
    public boolean programar(Sustentacion sustentacion) {
        String sql = "INSERT INTO sustentaciones (anteproyecto_id, fecha_programada, lugar, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, sustentacion.getAnteproyectoId());
            stmt.setTimestamp(2, sustentacion.getFechaProgramada());
            stmt.setString(3, sustentacion.getLugar());
            stmt.setString(4, "programada");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public Sustentacion findById(int id) {
        String sql = "SELECT * FROM sustentaciones WHERE id = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sustentacion s = new Sustentacion();
                s.setId(rs.getInt("id"));
                s.setAnteproyectoId(rs.getInt("anteproyecto_id"));
                s.setFechaProgramada(rs.getTimestamp("fecha_programada"));
                s.setLugar(rs.getString("lugar"));
                s.setEstado(rs.getString("estado"));
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    @Override
    public List<Sustentacion> findByAnteproyectoId(int anteproyectoId) {
        List<Sustentacion> sustentaciones = new ArrayList<>();
        String sql = "SELECT * FROM sustentaciones WHERE anteproyecto_id = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, anteproyectoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sustentacion s = new Sustentacion();
                s.setId(rs.getInt("id"));
                s.setAnteproyectoId(rs.getInt("anteproyecto_id"));
                s.setFechaProgramada(rs.getTimestamp("fecha_programada"));
                s.setLugar(rs.getString("lugar"));
                s.setEstado(rs.getString("estado"));
                sustentaciones.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return sustentaciones;
    }

    @Override
    public boolean asignarJurado(int sustentacionId, int juradoId, String rol) {
        String sql = "INSERT INTO jurados_sustentacion (sustentacion_id, jurado_id, rol_jurado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, sustentacionId);
            stmt.setInt(2, juradoId);
            stmt.setString(3, rol);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public boolean registrarActa(int sustentacionId, int califJurado1, int califJurado2, String observaciones, boolean aprobado) {
        String sql = "INSERT INTO actas (sustentacion_id, jurado1_calificacion, jurado2_calificacion, observaciones, aprobado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, sustentacionId);
            stmt.setInt(2, califJurado1);
            stmt.setInt(3, califJurado2);
            stmt.setString(4, observaciones);
            stmt.setBoolean(5, aprobado);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }
}