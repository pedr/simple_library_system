import java.sql.*;
import java.util.HashMap;

public class Repository {

    private Connection connection;

    public Repository(Connection connection) {
	this.connection = connection;
    }

    public boolean add(Book book) {
	HashMap<String, String> bkData = book.data();
	String query = "INSERT INTO books (NAME, AUTHOR) " +
			"VALUES ('" +
			bkData.get("name") + "', '" +
			bkData.get("author") + "');";
	try {
	    Statement stmt = this.connection.createStatement();
	    stmt.executeUpdate(query);

	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    return false;
	}
	return true;
    }
}
