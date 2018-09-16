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

    public boolean available() {
	if (this.quantity > 0) {
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
	return "Title: " + this.name + "\n Author: " + this.author + "\n Year: " + this.year  + "\n Available: " + available();
    }
}
