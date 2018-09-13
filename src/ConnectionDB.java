import java.io.File;
import java.sql.*;

public class ConnectionDB {

    private String dbName;
    private boolean connected;
    private Connection connection;

    public ConnectionDB(String dbName) {
	this.dbName = dbName;
	this.connected = false;
	alreadyExist();
    }

    private void tryToConnect() {
	try  {
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:" + this.dbName);
	    this.connection = conn; 
	    this.connected = true;

	} catch (SQLException e) {
	    this.connected = false;
	    System.out.println(e.getMessage());
	}
    }

    public boolean changeDB(String db) {
	this.dbName = db;
	alreadyExist();
	return this.connected;
    }

    public boolean connected() {
	return this.connected;
    }

    private boolean alreadyExist() {
	File file = new File(this.dbName);

	if (file.exists()) {
	    tryToConnect();
	}
	return false;
    }

    public Connection getConn() {
	return this.connection;
    }
}
