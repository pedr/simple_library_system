import java.sql.*;
import java.util.Scanner;

public class Biblioteca {

    public static void main(String args[]) {
	Scanner reader = new Scanner(System.in);
	UserInterface ui = new UserInterface("library.db");
	ui.run();

    }
}
