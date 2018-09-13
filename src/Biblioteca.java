import java.sql.*;
import java.util.Scanner;

public class Biblioteca {

    public static void main(String args[]) {
	Scanner reader = new Scanner(System.in);
	UserInterface ui = new UserInterface("SchoolDB.db");
	ui.run();
	ConnectionDB cDB = new ConnectionDB("school.db");
	System.out.println(cDB.connected());

	cDB.changeDB("SchoolDB.db");
	System.out.println(cDB.connected());



    }
}
