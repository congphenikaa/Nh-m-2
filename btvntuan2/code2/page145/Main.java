// exercise 8
class Connection {
    private static int counter = 0;
    private int id;

    
    private Connection() {
        id = ++counter;
    }

    
    public static Connection createConnection() {
        return new Connection();
    }

    @Override
    public String toString() {
        return "Connection " + id;
    }
}


class ConnectionManager {
    private static final int MAX_CONNECTIONS = 3;
    private static Connection[] connections = new Connection[MAX_CONNECTIONS];
    private static int currentConnection = 0;

    
    static {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            connections[i] = Connection.createConnection();
        }
    }

    /
    public static Connection getConnection() {
        if (currentConnection < MAX_CONNECTIONS) {
            return connections[currentConnection++];
        }
        return null;  
    }
}


public class Main {
    public static void main(String[] args) {
        Connection c1 = ConnectionManager.getConnection();
        Connection c2 = ConnectionManager.getConnection();
        Connection c3 = ConnectionManager.getConnection();
        Connection c4 = ConnectionManager.getConnection();  

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);  
    }
}
