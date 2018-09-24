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

    public boolean add(User user) {
		HashMap<String, String> userData = user.data();
		String query = "INSERT INTO users (FNAME, EMAIL) " +
						"VALUES ('" +
						userData.get("fname") + "', '" +
						userData.get("email") + "');";

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

    public ArrayList<User> findUser(String name) {
    	String query = "SELECT * FROM users WHERE (fname = '" + name + "' COLLATE NOCASE);";
    	ArrayList<User> users = new ArrayList<>();
    	ResultSet rs = null;

    	try {
    		Statement stmt = this.connection.createStatement();
    		rs = stmt.executeQuery(query);

			while (rs.next()) {
				User user = new User(rs.getString("fname"), rs.getString("email"));
				user.setId(rs.getInt("id"));
				users.add(user);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return users;
	}

	public ArrayList<Book> findBook(String keyword) {
    	String query = "Select * from books where " +
					"((name = '" + keyword + "' COLLATE NOCASE) ||" +
					" (author = '" + keyword + "' COLLATE NOCASE));";
    	ArrayList<Book> books = new ArrayList<>();
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
				bk.setId(rs.getInt("id"));
				books.add(bk);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

		return books;
	}

	public int exactUser(String fname, String email) {
    	String query = "SELECT id FROM users WHERE " +
						"((fname = '" + fname + "') &&" +
						" (email = '" + email + "'));";
    	ResultSet rs = null;
		int id = -1;

    	try {
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery(query);
			id = rs.getInt("id");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
		return id;

	}




}
