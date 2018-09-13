import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {

    private String dbName;
    private Connection connection;

    public UserInterface(String dbName) {
	this.dbName = dbName;
    }

    public void run() {
	ConnectionDB connDB = new ConnectionDB(this.dbName);	
	if (!connDB.connected()) {
	    return;
	}
	this.connection = connDB.getConn();

	while (true) {
	    System.out.println("\n/ -- My library system --- ");
	    System.out.println("|-- Connected to " + this.dbName + " -- \n");
	    menu();
	    int cmd = option();
	    if (cmd == 0) {
		break;
	    }
	    if (cmd == 1) {
		addBook();
	    }
	}
    }

    private void menu() {
	System.out.println("-- Menu --");
	System.out.println(" 1 - Insert Book");
	System.out.println(" 0 - Exit");
    }

    private int option() {
	Scanner reader = new Scanner(System.in);
	int cmd = reader.nextInt();
	return cmd;
    }

    private boolean addBook() {
	Scanner reader = new Scanner(System.in);
	System.out.println("Adding a new book>");
	System.out.print("\tName: ");
	String name = reader.nextLine();

	System.out.print("\tAuthor: ");
	String author = reader.nextLine();

	System.out.print("\tYear: ");
	int year = reader.nextInt();

	Book bk = new Book(name, author, year, null);
	Repository repo = new Repository (this.connection);
	System.out.println("Book add: " + repo.add(bk));

	return true;
    }
}
