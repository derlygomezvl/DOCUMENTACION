package co.edu.unicauca.mycompany.projects.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase abstracta que maneja la conexión a una base de datos MariaDB.
 * Proporciona métodos para conectar y desconectar la base de datos.
 */
public abstract class MariaDBConnection {
    
    /**
     * Objeto Connection utilizado para gestionar la conexión con la base de datos.
     */
    protected Connection conn;
    
    /**
     * Se encarga de conectar a la base de datos MariaDB.
     * 
     * @return true si la conexión fue exitosa, false en caso contrario.
     */
    public boolean connect() {
    // PASO 1: Cambiar la URL al formato SQLite.
    // Apunta directamente al archivo 'myDatabase.db' que ya tienes en el proyecto.
    String url = "jdbc:sqlite:myDatabase.db"; 
    
    // PASO 2: Usuario y contraseña son innecesarios para SQLite.
    // Solo se necesita la URL.
    
    try {
        // La conexión a SQLite usa solo la URL.
        conn = DriverManager.getConnection(url); 
        return true;
    } catch (SQLException ex) {
        // El log de error sigue siendo útil
        Logger.getLogger(MariaDBConnection.class.getName()).log(Level.SEVERE, "Fallo al conectar con SQLite", ex);
        return false;
    }
}
    
    /**
     * Se encarga de cerrar la conexión con la base de datos.
     */
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}