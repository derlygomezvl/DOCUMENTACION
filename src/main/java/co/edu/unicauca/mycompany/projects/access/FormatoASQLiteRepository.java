package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.FormatoA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormatoASQLiteRepository implements IFormatoARepository {

    private final IConnectionDB connection;

    public FormatoASQLiteRepository(IConnectionDB connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(FormatoA formatoA) {
        String sql = "INSERT INTO formatos_a (estudiante_id, titulo, modalidad, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, formatoA.getEstudianteId());
            stmt.setString(2, formatoA.getTitulo());
            stmt.setString(3, formatoA.getModalidad());
            stmt.setString(4, "pendiente");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public FormatoA findById(int id) {
        String sql = "SELECT * FROM formatos_a WHERE id = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FormatoA f = new FormatoA();
                f.setId(rs.getInt("id"));
                f.setEstudianteId(rs.getInt("estudiante_id"));
                f.setTitulo(rs.getString("titulo"));
                f.setModalidad(rs.getString("modalidad"));
                f.setEstado(rs.getString("estado"));
                f.setObservacionesComite(rs.getString("observaciones_comite"));
                f.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                return f;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    @Override
    public List<FormatoA> findByEstudianteId(int estudianteId) {
        List<FormatoA> formatos = new ArrayList<>();
        String sql = "SELECT * FROM formatos_a WHERE estudiante_id = ? ORDER BY fecha_envio DESC";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setInt(1, estudianteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FormatoA f = new FormatoA();
                f.setId(rs.getInt("id"));
                f.setEstudianteId(rs.getInt("estudiante_id"));
                f.setTitulo(rs.getString("titulo"));
                f.setModalidad(rs.getString("modalidad"));
                f.setEstado(rs.getString("estado"));
                f.setObservacionesComite(rs.getString("observaciones_comite"));
                f.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                formatos.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return formatos;
    }

    @Override
    public boolean updateEstado(int id, String estado, String observaciones) {
        String sql = "UPDATE formatos_a SET estado = ?, observaciones_comite = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.getStatement(sql)) {
            stmt.setString(1, estado);
            stmt.setString(2, observaciones);
            stmt.setInt(3, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<FormatoA> findAllPendientesComite() {
        List<FormatoA> formatos = new ArrayList<>();
        String sql = "SELECT * FROM formatos_a WHERE estado = 'pendiente'";
        try (PreparedStatement stmt = connection.getStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FormatoA f = new FormatoA();
                f.setId(rs.getInt("id"));
                f.setEstudianteId(rs.getInt("estudiante_id"));
                f.setTitulo(rs.getString("titulo"));
                f.setModalidad(rs.getString("modalidad"));
                f.setEstado(rs.getString("estado"));
                f.setObservacionesComite(rs.getString("observaciones_comite"));
                f.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                formatos.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return formatos;
    }
}