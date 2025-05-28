package designpatterns.structural.bridge.crossdatabasedriver;

// Implementor interface
public interface DataBaseDriver {
    void connect();
    void disconnect();
    void executeQuery();
    void executeUpdate();
    void executeProcedure();
    void executeBatch();
    void executeStoredProcedure();
    void executeBatchStoredProcedure();
}

// Abstraction interface
interface Database {
    void openConnection();
    void closeConnection();
    void runQuery();
}

// Refined Abstraction (abstract class holding the implementor reference)
abstract class ApplicationDatabase implements Database {
    protected DataBaseDriver driver;

    public ApplicationDatabase(DataBaseDriver driver) {
        this.driver = driver;
    }

    @Override
    public void openConnection() {
        driver.connect();
    }

    @Override
    public void closeConnection() {
        driver.disconnect();
    }

    @Override
    public void runQuery() {
        driver.executeQuery();
    }
}

// Concrete Refined Abstraction
class UserManagementDatabase extends ApplicationDatabase {

    public UserManagementDatabase(DataBaseDriver driver) {
        super(driver);
    }

    // Additional user management-specific methods can go here
}

// Concrete Implementors

class PostgreSqlDriver implements DataBaseDriver {
    @Override
    public void connect() {
        System.out.println("Connecting to PostgreSQL database");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from PostgreSQL database");
    }

    @Override
    public void executeQuery() {
        System.out.println("Executing query on PostgreSQL database");
    }

    @Override
    public void executeUpdate() {
        System.out.println("Executing update on PostgreSQL database");
    }

    @Override
    public void executeProcedure() {
        System.out.println("Executing procedure on PostgreSQL database");
    }

    @Override
    public void executeBatch() {
        System.out.println("Executing batch on PostgreSQL database");
    }

    @Override
    public void executeStoredProcedure() {
        System.out.println("Executing stored procedure on PostgreSQL database");
    }

    @Override
    public void executeBatchStoredProcedure() {
        System.out.println("Executing batch stored procedure on PostgreSQL database");
    }
}

class OracleDriver implements DataBaseDriver {
    @Override
    public void connect() {
        System.out.println("Connecting to Oracle database");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from Oracle database");
    }

    @Override
    public void executeQuery() {
        System.out.println("Executing query on Oracle database");
    }

    @Override
    public void executeUpdate() {
        System.out.println("Executing update on Oracle database");
    }

    @Override
    public void executeProcedure() {
        System.out.println("Executing procedure on Oracle database");
    }

    @Override
    public void executeBatch() {
        System.out.println("Executing batch on Oracle database");
    }

    @Override
    public void executeStoredProcedure() {
        System.out.println("Executing stored procedure on Oracle database");
    }

    @Override
    public void executeBatchStoredProcedure() {
        System.out.println("Executing batch stored procedure on Oracle database");
    }
}

class MySQLDriver implements DataBaseDriver {
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL database");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from MySQL database");
    }

    @Override
    public void executeQuery() {
        System.out.println("Executing query on MySQL database");
    }

    @Override
    public void executeUpdate() {
        System.out.println("Executing update on MySQL database");
    }

    @Override
    public void executeProcedure() {
        System.out.println("Executing procedure on MySQL database");
    }

    @Override
    public void executeBatch() {
        System.out.println("Executing batch on MySQL database");
    }

    @Override
    public void executeStoredProcedure() {
        System.out.println("Executing stored procedure on MySQL database");
    }

    @Override
    public void executeBatchStoredProcedure() {
        System.out.println("Executing batch stored procedure on MySQL database");
    }
}
