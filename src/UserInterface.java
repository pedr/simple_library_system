import java.util.Scanner;

public class UserInterface {

    private String dbName;

    public UserInterface(String dbName) {
	this.dbName = dbName;
    }

    public void run() {

	while (true) {
	    System.out.println("--- My library system --- \n");
	    System.out.println(" -- Connected to " + this.dbName + " -- \n");
	    menu();
	    int cmd = option();
	    if (cmd == 0) {
		break;
	    }
	}
    }

    private void menu() {
	System.out.println("-- Menu --\n");
	System.out.println(" 1 - Insert Book\n");
	System.out.println(" 0 - Exit\n");
    }

    private int option() {
	Scanner reader = new Scanner(System.in);
	String cmd = reader.getNextInt();
	return cmd;
    }
}
