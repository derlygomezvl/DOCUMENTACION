
package co.edu.unicauca.mycompany.projects.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación del conector para SQLite.
 * Usa el archivo 'trabajos_grado.db' en la raíz del proyecto.
 */
public class ConnectionSQLite implements IConnectionDB {

    private Connection connection;
    private static final String URL = "jdbc:sqlite:trabajos_grado.db";

    public ConnectionSQLite() {
        this.connection = null;
    }

    @Override
    public void connect() {
        if (this.connection != null) {
            return; // Ya está conectado
        }
        try {
            this.connection = DriverManager.getConnection(URL);
            // Opcional: crear tablas si no existen (mejor hacerlo en un inicializador aparte)
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.SEVERE, "Error al conectar con SQLite", ex);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.WARNING, "Error al desconectar de SQLite", ex);
        } finally {
            this.connection = null;
        }
    }

    @Override
    public PreparedStatement getStatement(String sql) {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                connect();
            }
            return this.connection.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.SEVERE, "Error al crear PreparedStatement", ex);
            return null;
        }
    }

    @Override
    public Statement createStatement() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                connect();
            }
            return this.connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.SEVERE, "Error al crear Statement", ex);
            return null;
        }
    }
}
