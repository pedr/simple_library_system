import java.util.ArrayList;
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
		break; // easiest way to get out of infinite loop
	    }
	    switch (cmd) {
		case 1:  {
		    addBook();
		    break;
		}
		case 2: {
		    getBook();
		    break;
		}
	    }
	}
    }

    private void menu() {
	System.out.println("-- Menu --");
	System.out.println(" 1 - Insert a Book");
	System.out.println(" 2 - Find Books");
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
	int year_int, qnt_int;	

	System.out.print("\tName: ");
	String name = reader.nextLine();

	System.out.print("\tAuthor: ");
	String author = gettingInput();

	System.out.print("\tYear: ");
	String year = gettingInput();

	System.out.print("\tTags: ");
	String tags = gettingInput();

	System.out.print("\tQuantity: ");
	String qnt = gettingInput();

	try  {
	    qnt_int = Integer.parseInt(qnt);
	} catch (NumberFormatException e) {
	    qnt_int = 1;
	    System.out.println("Invalid quantity format, 1 used instead");
	}

	try  {
	    year_int = Integer.parseInt(year);
	} catch (NumberFormatException e) {
	    year_int = 1900;
	    System.out.println("Invalid year format, 1900 used instead");
	}
	
	Book bk = new Book(name, author, year_int, tags, qnt_int);
	Repository repo = new Repository (this.connection);
	System.out.println("Book added: " + repo.add(bk));

	return true;
    }

    private void getBook() {
	Scanner reader = new Scanner(System.in);
	System.out.println("-- Search for what?");	
	System.out.println(" 1 - Print the last 10 books added");
	System.out.println(" 2 - Look for a book");
	int a = reader.nextInt();
	switch (a) {
	    case 1: {
		getLastTen();
		break;
	    }
	    case 2: {
		break;
	    }
	}

    }

    private void  getLastTen() {
	ArrayList<Book> bookList = new ArrayList<Book>();
	Repository rp = new Repository(this.connection);
	bookList = rp.getLastBooks(10);

	for (Book bk : bookList) {
	    System.out.println(bk + "\n");
	}
    }

    private String gettingInput() {
	Scanner reader = new Scanner(System.in);
	String tmp = reader.nextLine();
	if (tmp.isEmpty()) {
	    return "";
	}
	return tmp;
    }
}
