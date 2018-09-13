import java.util.ArrayList;

public class Book {

    private String name;
    private String author;
    private int year;
    private ArrayList<String> tags;
    private String loaner;

    public Book(String name, String author, int year, ArrayList<String> tags) {
	this.name = name;
	this.author = author;
	this.year = year;
	this.tags = tags;
    }

    public Book(String name, String author, ArrayList<String> tags) {
	this(name, author, 0, tags);
    }

    public Book(String name, int year, ArrayList<String> tags) {
	this(name, "", year, tags);
    }

    public Book(String name, String author) {
	this(name, author, 0, null);
    }

    public Book(String name) {
	this(name, "", 0, null);
    }

    public void lenting(String p) {
	this.loaner = p;
    }

    public void returning(String p) {
	this.loaner = null;
    }

    public boolean available() {
	if (this.loaner == null) {
	    return true;
	}
	return false;
    }

    public String toString() {
	return "title: " + this.name + "\n author: " + this.author + "\n year: " + this.year  + "\n Available: " + available();
    }
}
