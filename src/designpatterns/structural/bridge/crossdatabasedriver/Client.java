package designpatterns.structural.bridge.crossdatabasedriver;


// Client example
class Client {
    public static void main(String[] args) {
        DataBaseDriver mySqlDriver = new MySQLDriver();
        Database userDb = new UserManagementDatabase(mySqlDriver);

        userDb.openConnection();
        userDb.runQuery();
        userDb.closeConnection();

        System.out.println();

        DataBaseDriver oracleDriver = new OracleDriver();
        Database orderDb = new UserManagementDatabase(oracleDriver);

        orderDb.openConnection();
        orderDb.runQuery();
        orderDb.closeConnection();
    }
}
