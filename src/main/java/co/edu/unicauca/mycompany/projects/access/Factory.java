package co.edu.unicauca.mycompany.projects.access;

public class Factory {

    private static Factory instance;
    private final IConnectionDB connection;

    private Factory() {
        this.connection = new ConnectionSQLite();
    }

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public IUserRepository getUserRepository() {
        return new UserSQLiteRepository(connection);
    }

    public IFormatoARepository getFormatoARepository() {
        return new FormatoASQLiteRepository(connection);
    }

    public IAnteproyectoRepository getAnteproyectoRepository() {
        return new AnteproyectoSQLiteRepository(connection);
    }

    public ISustentacionRepository getSustentacionRepository() {
        return new SustentacionSQLiteRepository(connection);
    }

    public IHistorialRepository getHistorialRepository() {
        return new HistorialSQLiteRepository(connection);
    }

    public INecesidadEmpresarialRepository getNecesidadEmpresarialRepository() {
        return new NecesidadEmpresarialSQLiteRepository(connection);
    }
}