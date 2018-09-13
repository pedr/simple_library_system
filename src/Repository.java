import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;

public class Repository {

    private Connection connection;

    public Repository(Connection connection) {
	this.connection = connection;
    }

    public boolean add(Book book) {
	HashMap<String, String> bkData = book.data();
	String query = "INSERT INTO books (NAME, AUTHOR, YEAR, QNT) " +
			"VALUES ('" +
			bkData.get("name") + "', '" +
			bkData.get("author") + "', " +
			bkData.get("year") + ", " +
			bkData.get("qnt") + ");";
	try {
	    Statement stmt = this.connection.createStatement();
	    stmt.executeUpdate(query);

	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    return false;
	}
	return true;
    }

    public ArrayList<Book> getLastBooks(int howMany) {
	ArrayList<Book> books = new ArrayList<Book>();
	String query = "SELECT * FROM books WHERE " +
			"(id > (SELECT max(id) FROM books) - " +
			howMany + ");";
	ResultSet rs = null;

	try {
	    Statement stmt = this.connection.createStatement();
	    rs = stmt.executeQuery(query);

	    while (rs.next()) {
		Book bk = new Book(rs.getString("name"),
				    rs.getString("author"),
				    rs.getInt("year"),
				    "no tags",
				    rs.getInt("qnt"));
		books.add(bk);
	    }

	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    return null;
	}
	return books;

    }
}
