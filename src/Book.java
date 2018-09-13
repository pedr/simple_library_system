import java.util.HashMap;
import java.util.ArrayList;

public class Book {

    private String name;
    private String author;
    private int year;
    private String tags;
    private String loaner;
    private int quantity;

    public Book(String name, String author, int year, String tags, int qnt) {
	this.name = name;
	this.author = author;
	this.year = year;
	this.tags = tags;
	this.quantity = qnt;
    }

    public Book(String name, String author, String tags) {
	this(name, author, 0, tags, 1);
    }

    public Book(String name, int year, String tags) {
	this(name, "", year, tags, 1);
    }

    public Book(String name, String author) {
	this(name, author, 0, null, 1);
    }

    public Book(String name) {
	this(name, "", 0, null, 1);
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

    public HashMap<String, String> data() {
	HashMap<String, String> bookData = new HashMap<String, String>();
	bookData.put("name", this.name);
	bookData.put("author", this.author);
	bookData.put("year", ("" + this.year));
	bookData.put("qnt", ("" + this.quantity));

	return bookData;
    }

    public String toString() {
	return "title: " + this.name + "\n author: " + this.author + "\n year: " + this.year  + "\n Available: " + available();
    }
}
