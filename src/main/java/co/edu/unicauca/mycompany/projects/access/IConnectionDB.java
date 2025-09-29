
package co.edu.unicauca.mycompany.projects.access;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Contrato para cualquier conector de base de datos.
 * Permite desacoplar la lógica de negocio del motor de persistencia (SOLID - DIP).
 */
public interface IConnectionDB {

    /**
     * Establece la conexión con la base de datos.
     */
    void connect();

    /**
     * Cierra la conexión con la base de datos.
     */
    void disconnect();

    /**
     * Crea y devuelve un PreparedStatement para consultas parametrizadas.
     *
     * @param sql consulta SQL a preparar
     * @return PreparedStatement listo para usarse
     */
    PreparedStatement getStatement(String sql);

    /**
     * Crea y devuelve un Statement para consultas simples.
     *
     * @return Statement listo para ejecutar consultas
     */
    Statement createStatement();
}
