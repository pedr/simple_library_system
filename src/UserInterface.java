import java.util.Scanner;

public class UserInterface {

    private String dbName;

    public UserInterface(String dbName) {
	this.dbName = dbName;
    }

    public void run() {

	while (true) {
	    System.out.println("\n/ -- My library system --- ");
	    System.out.println("|-- Connected to " + this.dbName + " -- \n");
	    menu();
	    int cmd = option();
	    if (cmd == 0) {
		break;
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
	System.out.println("Name: ");
	String name = reader.nextLine();

	System.out.println("Author: ");
	String author = reader.nextLine();

	System.out.println("Year: ");
	int year = reader.nextInt();

	Book bk = new Book(name author, year, null);
	System.out.println(bk);

    }
}
