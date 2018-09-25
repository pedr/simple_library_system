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

            switch (cmd) {
                case 0: {
                    return;
                }
                case 1:  {
                    addBook();
                    break;
                }
                case 2: {
                    getBook();
                    break;
                }
                case 3: {
                    addUser();
                    break;
                }
                case 4: {
                    lentBook();
                    break;
                }
            }
        }
    }

    private void menu() {
        System.out.println("-- Menu --");
        System.out.println(" 1 - Insert a Book");
        System.out.println(" 2 - Find Books");
        System.out.println(" 3 - Add user");
        System.out.println(" 4 - Lent a book");
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
                findBook();
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

    private void addUser() {
        System.out.println("\n-- Adding a new user");

        System.out.print(" Name: ");
        String name =  gettingInput();
        if (name.isEmpty()) {
            System.out.println("Name can't be blank");
            return;
        }

        System.out.print(" Email: ");
        String email = gettingInput();
        User user = new User(name, email);
        Repository repo = new Repository(this.connection);
        System.out.println(" User added to database: " + repo.add(user));
    }

    private String gettingInput() {
        Scanner reader = new Scanner(System.in);
        String tmp = reader.nextLine();
        if (tmp.isEmpty()) {
            return "";
        }
        return tmp;
    }

    private void lentBook() {
        int option = 0;

        ArrayList<User> users = findUser();
        option = chooseOption();
        if (option == -1) {
            return;
        }
        User lenter = users.get(option);

        ArrayList<Book> books = findBook();
        option = chooseOption();
        if (option == -1) {
            return;
        }
        Book book = books.get(option);

        if (book.available()) {
            Repository repo = new Repository(this.connection);
            repo.lentBook(lenter, book);
        }

        /* TO FINISH YET */
    }

    private int chooseOption() {
        String option = gettingInput();
        if (option.isEmpty() || option.equals("0")) {
            return -1;
        }

        return Integer.parseInt(option) - 1;
    }

    private ArrayList<Book> findBook() {
        System.out.println("\n-- Which book do you want to find?");
        String keyword = gettingInput();
        keyword = keyword.trim();

        Repository repo = new Repository (this.connection);
        ArrayList<Book> books = repo.findBook(keyword);

        System.out.println("Which book: (type nothing or 0 to exit)");
        int i = 1;
        for (Book bk : books) {
            System.out.println(i + "- " + bk);
            System.out.println("---------");
            i++;
        }
        return books;
    }

    private ArrayList<User> findUser() {
        Scanner reader = new Scanner(System.in);

        System.out.println("\n-- Which user?");
        String name = reader.nextLine();
        name = name.trim();

        Repository repo = new Repository (this.connection);
        ArrayList<User> users = repo.findUser(name);

        System.out.println("Which user: (type nothing or 0 to exit)");
        int i = 1;
        for (User user : users) {
            System.out.println(i + "- " + user);
            i++;
        }
        return users;
    }

}
